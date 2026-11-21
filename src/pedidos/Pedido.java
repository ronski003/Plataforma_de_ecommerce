package pedidos;

import domicilios.Domicilio;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;
import pago.MedioPago;
import productos.Carrito;
import usuarios.Cliente;
import java.util.ArrayList;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Arrays;
import java.util.Comparator;
    
public class Pedido {

    
    private Cliente cliente;
    private Carrito carrito;
    private LocalDate fecha;
    private LocalTime hora;
    
    private File carpetaCliente() {
        String usuario = cliente.getNombreUsuario().toLowerCase(Locale.ROOT);

        String nombreCarpeta = URLEncoder.encode(usuario,StandardCharsets.UTF_8);

        return new File("tickets", nombreCarpeta);
    }
    
    private File[] obtenerPedidos() {
        File carpeta = carpetaCliente();

        File[] archivos = carpeta.listFiles((dir, nombre) -> nombre.matches("pedido_[0-9]+\\.txt"));
        if (archivos == null) {
            return new File[0];
        }

        Arrays.sort(archivos,Comparator.comparingInt(archivo -> Integer.parseInt(archivo.getName().replace("pedido_", "").replace(".txt", ""))));

        return archivos;
    }

    public Pedido(Cliente cliente, Carrito carrito) {
        this.cliente = cliente;
        this.carrito = carrito;
    }

    public boolean confirmoPedido() {
        Random aleatorio = new Random();
        this.hora = LocalTime.now();
        this.fecha = LocalDate.now().plusDays(aleatorio.nextInt(1, 16));
        Scanner lector = new Scanner(System.in);
        System.out.println("\n");
        if (this.carrito.getCarrito().isEmpty()) {
            System.out.println("No se puede procesar el pago, dado que el carrito está vacío ");
            return false;
        }
        if (cliente.getDomicilios().isEmpty()) {
            System.out.println("No se puede confirmar el pedido");
            System.out.println("Es necesario que tenga un domicilio registrado");
            return false;
        }

        if (cliente.getPagos().isEmpty()) {
            System.out.println("No se puede confirmar el pedido");
            System.out.println("Es necesario que tenga un medio de pago registrado");
            return false;
        }
        int j = 1;
        System.out.println("Seleccione un domicilio para el envío");
        for (Domicilio domicilio : cliente.getDomicilios()) {

            System.out.println(j + ". ");
            System.out.println(domicilio.getColonia());
            System.out.println(domicilio.getCalle());
            System.out.println(domicilio.getTelefono());
            System.out.println("----------------------------------");
            j++;
        }
        int opcion;
        while (true) {
            System.out.print("Indique su opción:");
            try {
                opcion = Integer.parseInt(lector.nextLine().trim());
                if (opcion >= 1 && opcion <= cliente.getDomicilios().size()) {
                    break;
                }
            } catch (NumberFormatException e) {
                //entrada no es un numero 
            }
            System.out.println("Opción invalida. Inténtelo de nuevo.");
        }

        System.out.println("\n");
        Domicilio domicilio = cliente.getDomicilios().get(opcion - 1);

        System.out.println("Seleccione un medio de pago para envío");
        for (int i = 0; i < cliente.getPagos().size(); i++) {
            System.out.println((i + 1) + ". ");
            cliente.getPagos().get(i).mostrarInformacion();
            System.out.println("----------------------------------");
        }
        int opcion2;
        while (true) {
            System.out.print("Indique su opción: ");

            try {
                opcion2 = Integer.parseInt(lector.nextLine().trim());
                if (opcion2 >= 1 && opcion2 <= cliente.getPagos().size()) {
                    break;
                }
            } catch (NumberFormatException e) {
                //La entrada no es numerica
            }
            
            System.out.println("Opción inválida. Inténtelo de nuevo");
        }
        System.out.println("\n");
        MedioPago medio = cliente.getPagos().get(opcion2 -1);

        float total = 0;

        for (int i = 0; i < carrito.getCarrito().size(); i++) {
            total += carrito.getCarrito().get(i).getPrecio() * carrito.getCarrito().get(i).getStock();
        }
        boolean aceptado = medio.procesarPago(total);

        if (!aceptado) {
            System.out.println("No es posible completar el pedido");
            return false;
        }

        GuardarArchivo(domicilio, medio);

        return true;
    }

    public void GuardarArchivo(Domicilio domicilio, MedioPago medio) {
        DecimalFormat formato = new DecimalFormat("#0.00");
        File carpeta = carpetaCliente();

        if (!carpeta.exists() && !carpeta.mkdirs()) {
            System.out.println("No se pudo crear la carpeta de pedidos");
            return;
        }

        int numero = 1;
        File archivo;

        do {
            archivo = new File( carpeta, "pedido_" + numero +".txt");
            numero++;
        } while (archivo.exists());

        try {
            FileWriter escritor = new FileWriter(archivo);
            float suma = 0;
            escritor.write("\t\t\tPEDIDO\n");
            escritor.write("-------------------------------------------------------------\n");
            for (int i = 0; i < carrito.getCarrito().size(); i++) {

                String documento = "\nProducto: " + carrito.getCarrito().get(i).getNombreProducto() + "\tUnidades: " + carrito.getCarrito().get(i).getStock() + "\tPrecio: $" + (carrito.getCarrito().get(i).getPrecio()*carrito.getCarrito().get(i).getStock());
                suma += carrito.getCarrito().get(i).getPrecio() * carrito.getCarrito().get(i).getStock();
                escritor.write(documento + "\n");
            }
            escritor.write("Monto total: $" + formato.format(suma) + "\n");
            escritor.write("Domicilio :" + domicilio.getColonia() + ", " + domicilio.getCalle() + "\n");
            escritor.write(medio.informacionPedido()+ "\n");
            escritor.write("\n\t\t\tINFORMACIÓN ADICIONAL\n");
            escritor.write("\nFecha del pedido: "+LocalDate.now()+"\tHora del pedido: "+this.hora+"\n");
            escritor.write("Entrega estimada del pedido: "+this.fecha+"\n");
            escritor.write("-------------------------------------------------------------\n");
            escritor.close();
            carrito.getCarrito().clear();

        } catch (IOException e) {
            System.out.println("Error 501, no se pudo registrar el pedido");
        }

        
    }

    public void imprimirTicket (){ 
        ArrayList<String> listaNombres = new ArrayList<>(); 
        Scanner lector =new Scanner(System.in);
        boolean validador; 
        try{
            File comprobacion = new File("Pedido de "+cliente.getNombreCompleto().trim()+ ".txt");
            if(!comprobacion.exists()){
                System.out.println("No se pueden rescatar los pedidos por el momento");
            }else{
                String temp = "Pedido de "+cliente.getNombreCompleto().trim()+" No. 1.txt";
                listaNombres.add(temp); 
                for (int i= 2; i<=15; i++){
                    File comprobacion2 = new File("Pedido de "+cliente.getNombreCompleto().trim()+" No. "+i+".txt");
                    if(!comprobacion2.exists()){
                        break;
                    }
                    else{
                        temp = "Pedido de "+cliente.getNombreCompleto().trim()+" No. "+i+".txt";
                        listaNombres.add(temp); 
                    }
                }
                //Se imprimen los nombres de los pedidos. 
                for (int j=0; j<listaNombres.size(); j++){
                    int m =j+1;
                    System.out.println("Pedido de "+cliente.getNombreCompleto().trim()+" No. "+m+".txt");
                }
                System.out.println("");
                
                do{
                    System.out.println("_______________________________________________________");
                    System.out.println("Ingrese el número del pedido que desea recuperar");
                    validador = lector.hasNextInt();
                    if(validador == false){
                        System.out.println("No se ha detectado ningun numero");
                        System.out.println("Vuélvalo a intentar");
                        lector.nextLine(); 
                        System.out.println("-----");

                    }else{
                        
                        int num = lector.nextInt(); 
                        lector.nextLine(); 
                        if(num<1 || num>listaNombres.size()){ //CAso de error
                            validador = false; 
                            System.out.println("El numero que ha sido ingresado no se encuentra deentro del intervalo de pedidos");
                            System.out.println("Intentelo otra vez....");
                            System.out.println("");
                        }else{
                            //Se imprimen los pedidos 
                            System.out.println("Pedido encontrado");
                            System.out.println("Mostrando en pantalla: ");
                            if(num==1){
                               this.imprimirPedido("Pedido de "+cliente.getNombreCompleto().trim()+ ".txt");
                            }
                            else{
                                  
                                this.imprimirPedido("Pedido de "+cliente.getNombreCompleto().trim()+" No. "+num+".txt");
                                System.out.println("");
                            }
                            break; 
                        }
                        
                    }
                        
                }while(validador == false);
                System.out.println("______________________________________________________________________________");
           }
            
        }catch (Exception e) {
            System.out.println("No se pueeden acceder a los pedidos por el momento");      
        }   
    }

    
     public void imprimirPedido(String nombreArchivo){
         //Se valida que exista un archivo de pedido
         try {   
            File vacio = new File(nombreArchivo);
            if(!vacio.exists()){
                System.out.println("El pedido no se encuentra disponible por el momento");
            }else{
                    String linea =""; 
                    FileReader archivo = new FileReader(nombreArchivo);
                    BufferedReader lectura = new BufferedReader(archivo);
                    while(linea != null){
                        linea = lectura.readLine();
                        if(linea==null){
                            System.out.println("");
                            break; //No imprime NULL cuando ya no hay mas lineas 
                        }
                        System.out.println(linea);                    
                    }
                lectura.close();
            }
        } catch (IOException e) {
            System.out.println("Error al imprimir el ticket");
        }
    }
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}
