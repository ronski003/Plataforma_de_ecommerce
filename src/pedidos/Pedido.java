package pedidos;

import java.io.FileWriter;
import java.io.IOException;
import domicilios.Domicilio;
import productos.Carrito;
import usuarios.Cliente;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.LocalDate;
import pago.MedioPago;

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
        if(this.carrito == null){
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
            i++;
       }
       
       int opcion = lector.nextInt();

       System.out.println("Seleccione un medio de pago para envío");
       for(int i = 0; i<cliente.getPagos().size(); i++) {
            System.out.println(i+". ");
            System.out.println(cliente.getDomicilios().);
            System.out.println(domicilio.getCalle());
            System.out.println(domicilio.getTelefo)
       }

       GuardarArchivo(cliente.getNombreCompleto());
       
       return true; 
    }

    public void GuardarArchivo(String Nombre ){
       String nombreArchivo ="Pedido de "+Nombre+ ".txt";
        
        
        try {
            FileWriter archivo = new FileWriter(nombreArchivo);
            float suma=0; 
            archivo.write("\t\tPedido");
            archivo.write("-------------------------------------------------------------\n");
            for(int i=0; i<carrito.size(); i++){
                String documento = "Producto: "+carrito.get(i).getNombreProducto()+ "\tUnidades: "+carrito.get(i).getStock(); 
                suma += carrito.get(i).getPrecio() * carrito.get(i).getStock(); 
                archivo.write(documento+"\n"); 
            }
            archivo.write("Monto total: "+suma+"\n");
            archivo.write("Domicilio : ' +   Aqui iría el domicio +'   \n");
            archivo.write("Medio de Pago: '+Aqui iriía el medio de pago+'    \n    ");
            archivo.write("-------------------------------------------------------------\n");
            archivo.close();
            carrito.clear(); 
             
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
