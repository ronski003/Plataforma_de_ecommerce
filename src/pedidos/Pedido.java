package pedidos;

import domicilios.Domicilio;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import pago.MedioPago;
import productos.Carrito;
import usuarios.Cliente;

public class Pedido {
    private Cliente cliente;
    private Carrito carrito;
    private LocalDate fecha;
    private LocalTime hora;

    public Pedido(Cliente cliente, Carrito carrito) {
        this.cliente = cliente;
        this.carrito = carrito;
    }

    public boolean confirmoPedido() {
        Scanner lector = new Scanner(System.in);
        System.out.println("\n");
        if(this.carrito.getCarrito().isEmpty()){
           System.out.println("No se puede procesar el pago, dado que el carrito está vacío ");
           return false; 
       }
       if(cliente.getDomicilios().isEmpty()) {
            System.out.println("No se puede confirmar el pedido");
            System.out.println("Es necesario que tenga un domicilio registrado");
            return false;
       }

       if(cliente.getPagos().isEmpty()) {
            System.out.println("No se puede confirmar el pedido");
            System.out.println("Es necesario que tenga un medio de pago registrado");
            return false;
       }

       System.out.println("Seleccione un domicilio para el envío");
       for(Domicilio domicilio : cliente.getDomicilios()) {
            int i = 1;
            System.out.println(i+". ");
            System.out.println(domicilio.getColonia());
            System.out.println(domicilio.getCalle());
            System.out.println(domicilio.getTelefono());
            System.out.println("----------------------------------");
            i++;
       }
       System.out.print("Indique su opción: ");
       int opcion = lector.nextInt();
       System.out.println("\n");
       Domicilio domicilio = cliente.getDomicilios().get(opcion-1);

       System.out.println("Seleccione un medio de pago para envío");
       for(int i = 0; i<cliente.getPagos().size(); i++) {
            System.out.println((i+1)+". ");
            cliente.getPagos().get(i).mostrarInformacion();
            System.out.println("----------------------------------");
       }
       System.out.print("Indique su ocpión: ");
       int opcion2 = lector.nextInt();
       System.out.println("\n");

       MedioPago medio = cliente.getPagos().get(opcion2 -1);

       float total = 0;

       for(int i = 0; i < carrito.getCarrito().size(); i++) {
            total += carrito.getCarrito().get(i).getPrecio() * carrito.getCarrito().get(i).getStock();
        }
       boolean aceptado = medio.procesarPago(total);

       if(!aceptado) {
            System.out.println("No es posible completar el pedido");
            return false; 
       }
       
       GuardarArchivo(domicilio, medio);
       
       return true; 
    }

    public void GuardarArchivo (Domicilio domicilio, MedioPago medio){
        DecimalFormat formato = new DecimalFormat("#0.00");
        String nombreArchivo ="Pedido de "+cliente.getNombreCompleto().trim()+ ".txt";
    
        try {
            FileWriter archivo = new FileWriter(nombreArchivo);
            float suma=0; 
            archivo.write("\t\tPEDIDO\n");
            archivo.write("-------------------------------------------------------------\n");
            for(int i=0; i<carrito.getCarrito().size(); i++){

                String documento = "Producto: "+carrito.getCarrito().get(i).getNombreProducto()+ "\tUnidades: "+carrito.getCarrito().get(i).getStock(); 
                suma += carrito.getCarrito().get(i).getPrecio() * carrito.getCarrito().get(i).getStock(); 
                archivo.write(documento+"\n"); 
            }
            archivo.write("Monto total: $"+formato.format(suma)+"\n");
            archivo.write("Domicilio :"+domicilio.getColonia()+", "+domicilio.getCalle()+"\n");
            archivo.write("Medio de Pago: "+medio.getAlias()+"\n");
            archivo.write("-------------------------------------------------------------\n");
            archivo.close();
            carrito.getCarrito().clear(); 
             
        } catch (IOException e) {
            System.out.println("Error 501, no se pudo registrar el pedido");
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
}
