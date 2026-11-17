package principal;
//import java.text.DecimalFormat;
import BaseDatos.BaseDeDatos;
import domicilios.Domicilio;
import java.util.Scanner;
import pago.PayPal;
import pago.TarjetaCredito;
import pedidos.Pedido;
import productos.Carrito;
import usuarios.*;

public class Principal {
    static BaseDeDatos bd = BaseDeDatos.getBaseDatos();
    static Scanner lector = new Scanner(System.in);
    static GestorUsuarios gestor = new GestorUsuarios();
    static Usuario usuario;
    static Carrito carrito = new Carrito();

    public static void main(String[] args) { 
        /*DecimalFormat formato = new DecimalFormat("#0.00");
        TarjetaCredito tarjeta = new TarjetaCredito("Erika", "10012010", 2704);
        

        System.out.println(formato.format(tarjeta.getSaldo()));*/ //Idea para control de decimales
        //Inicialización de todas las instancias necesarias
        int opcion = 0;

        do {
        System.out.println("\n-----INGRESO A SISTEMA-----");
        System.out.println("1. Ingresar al sistema");
        System.out.println("2. Registro como cliente");
        System.out.println("3. Salir");
        System.out.print("Ingrese su opción: ");
        opcion = lector.nextInt();
        lector.nextLine();

        switch(opcion) {
            case 1:
                System.out.print("\nProporcione el nombre de usuario: ");
                String nombre = lector.nextLine();

                usuario = gestor.ingresar(nombre);

                if(usuario != null) {
                    System.out.println("Bienvienido "+usuario.getNombreCompleto());

                    if(usuario.getRol() == Usuario.Rol.ADMINISTRADOR) {
                        menuAdministrador();
                    }
                    else {
                        menuCliente();
                    }
                }
                else {
                    System.out.println("Usuario no encontrado");
                    break;
                }
                break;
            case 2:
                System.out.println("\n-----DATOS A PROPORCIONAR-----");
                System.out.print("Nombre de usuario: ");
                nombre = lector.nextLine();
                System.out.print("Nombre completo: ");
                String nombreCompleto = lector.nextLine();

                if(gestor.registrarCliente(nombre, nombreCompleto)) {
                    System.out.println("Usuario registrado");
                } else {
                System.out.println("No es posible registrar el usuario. Se ha detectado una coincidencia en el sistema");
                }
                break;
            case 3:
                System.out.println("¿Está seguro de querer abandonar el sistema?");
                String respuesta = lector.nextLine();

                if(respuesta.equalsIgnoreCase("sí") || respuesta.equalsIgnoreCase("si")) {
                    System.out.println("Abandonando el sitio...");
                    return;
                }
                opcion = 0;
                break;
            default:
                System.out.println("Ingrese una opción válida");
                break;
        }
        } while(opcion != 3);

    }

    public static void menuCliente() {
        int opcion = 0;

        do{
        System.out.println("\n-----CLIENTES-----");
        System.out.println("1. Agregar producto al carrito");
        System.out.println("2. Registrar domicilio");
        System.out.println("3. Registrar medio de pago");
        System.out.println("4. Confirmar pedido");
        System.out.println("5. Vaciar carrito");
        System.out.println("6. Consultar carrito");
        System.out.println("7. Imprimir ticket de un pedido");
        System.out.println("8. Salir");
        System.out.print("Ingrese su opción: ");
        opcion = lector.nextInt();
        lector.nextLine();

        switch(opcion) {
            case 1:
                carrito.AgregarCarrito();
                break;
            case 2:
                registrarDomicilio();
                break;
            case 3:
                registroPago();
                break;
            case 4:
                Cliente cliente = (Cliente) usuario;
                Pedido pedido = new Pedido(cliente, carrito);

                String continuar = pedido.confirmoPedido() ? "Gracias por su compra":"Realice lo indicado e intenté nuevamente";
                System.out.println(continuar);
                break;
            case 5:
                carrito.VaciarCarrito();
                break;
            case 6:
                carrito.imprimirCarrito();
                break;
            case 7:

                break;
            case 8:
                System.out.println("Regresando...");
                return;
            default:
                System.out.println("Ingrese una opción válida");
                break;
        }
        } while(opcion != 8);
    }

     public static void menuAdministrador() {
        int opcion = 0;

        do{
        System.out.println("\n-----ADMINISTRADORES-----");
        System.out.println("1. Alta de productos");
        System.out.println("2. Modificación de productos");
        System.out.println("3. Salir");
        System.out.print("Ingrese su opción: ");
        opcion = lector.nextInt();
        lector.nextLine();

        switch(opcion) {
            case 1:
                bd.registrarProducto();
                break;
            case 2:
                bd.modificarPrecio();
                break;
            case 3:
                System.out.println("Regresando...");
                return;
            default:
                System.out.println("Ingrese una opción válida");
                break;
        }
        } while(opcion != 3);
    }

    public static void registroPago() {
        Cliente cliente = (Cliente) usuario;

        System.out.println("\nMEDIO DE PAGO");
        System.out.println("1. Tarjeta de crédito");
        System.out.println("2. PayPal");
        System.out.print("Indique el medio de pago: ");
        int opcion = lector.nextInt();
        lector.nextLine();

        switch(opcion) {
            case 1: 
                System.out.print("Alias: ");
                String alias = lector.nextLine(); 

                System.out.print("Número de tarjeta: ");
                String numero = lector.nextLine();

                System.out.print("NIP: ");
                int nip = lector.nextInt();
                lector.nextLine();

                TarjetaCredito tarjeta = new TarjetaCredito(alias, numero, nip);   
                cliente.agregarMedioPago(tarjeta);
                break;
            case 2:
                System.out.print("Alias: ");
                alias = lector.nextLine(); 

                System.out.print("Correo: ");
                String correo = lector.nextLine();

                System.out.print("Contraseña: ");
                String contrasena = lector.nextLine();

                PayPal paypal = new PayPal(alias, correo);
                paypal.setContrasena(contrasena);
                cliente.agregarMedioPago(paypal);
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    
    }

    public static void registrarDomicilio() {
        Cliente cliente = (Cliente) usuario;
        boolean salir = true;
        System.out.println("\n-----DOMICILIOS-----");
        while(salir) {
            System.out.print("Colonia: ");
            String colonia = lector.nextLine();
            System.out.print("Calle: ");
            String calle = lector.nextLine();
            System.out.print("Teléfono: ");
            String telefono = lector.nextLine();
            
            Domicilio domicilio = new Domicilio(colonia, calle, telefono);
            cliente.agregarDomicilio(domicilio);
                
            System.out.println("¿Desea agregar un nuevo domicilio?");
            String respuesta = lector.nextLine();
            System.out.println("\n");
            if(!respuesta.equalsIgnoreCase("sí") && !respuesta.equalsIgnoreCase("si")) {
                salir = false;
                
            }
        }
    }

}
