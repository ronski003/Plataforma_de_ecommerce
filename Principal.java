//import java.text.DecimalFormat;
import java.util.Scanner;

public class Principal {
    static Scanner lector = new Scanner(System.in);

    public static void main(String[] args) { 
        /*DecimalFormat formato = new DecimalFormat("#0.00");
        TarjetaCredito tarjeta = new TarjetaCredito("Erika", "10012010", 2704);


        System.out.println(formato.format(tarjeta.getSaldo()));*/ //Idea para control de decimales
        //Inicialización de todas las instancias necesarias
        int opcion = 0;

        do {
        System.out.println("-----INGRESO A SISTEMA-----");
        System.out.println("1. Ingresar al sistema");
        System.out.println("2. Registro como cliente");
        System.out.println("3. Salir");
        System.out.print("Ingrese su opción: ");
        opcion = lector.nextInt();
        lector.nextLine();

        switch(opcion) {
            case 1:
                break;
            case 2:
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
        System.out.println("-----CLIENTES-----");
        System.out.println("1. Agregar producto al carrito");
        System.out.println("2. Registrar domicilio");
        System.out.println("3. Registrar medio de pago");
        System.out.println("4. Confirmar pedido");
        System.out.println("5. Vaciar carrito");
        System.out.println("6. Consultar carrito");
        System.out.println("7. Imprimir ticket de un pedido");
        System.out.println("Ingrese su opción: ");
        opcion = lector.nextInt();
        lector.nextLine();

        switch(opcion) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            default:
                System.out.println("Ingrese una opción válida");
                break;
        }
        } while(opcion != 7);
    }

     public static void menuAdministrador() {
        int opcion = 0;

        do{
        System.out.println("-----ADMINISTRADORES-----");
        System.out.println("1. Alta de productos");
        System.out.println("2. Modificación de productos");
        System.out.println("Ingrese su opción: ");
        opcion = lector.nextInt();
        lector.nextLine();

        switch(opcion) {
            case 1:
                break;
            case 2:
                break;
            default:
                System.out.println("Ingrese una opción válida");
                break;
        }
        } while(opcion != 2);
    }
}
