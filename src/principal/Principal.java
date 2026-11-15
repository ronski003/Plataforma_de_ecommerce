package principal;
//import java.text.DecimalFormat;
import BaseDatos.BaseDeDatos;
import java.util.Scanner;
import static productos.Dec_Productos.Producto.modificarPrecio;
import static productos.Dec_Productos.Producto.registrarProducto;
import usuarios.*;

public class Principal {
    static Scanner lector = new Scanner(System.in);
    static GestorUsuarios gestor = new GestorUsuarios();
    static BaseDeDatos baseDatos = new BaseDeDatos();

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

                Usuario usuario = gestor.ingresar(nombre);

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
                }
                System.out.println("No es posible registrar el usuario. Se ha detectado una coincidencia en el sistema");
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

                break;
            case 2:
                break;
            case 3:
                System.out.println("Indique el medio de pago que desee registrar");
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
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
                registrarProducto(baseDatos);
                break;
            case 2:
                modificarPrecio(baseDatos);
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
        
    }
}
