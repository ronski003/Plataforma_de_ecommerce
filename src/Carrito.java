
package Productos;


import BasedeDatos.BaseDeDatos;
import static BasedeDatos.BaseDeDatos.getBaseDatos;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author andre
 */
public class Carrito {
       private ArrayList<Producto> carrito =new ArrayList<>(); 
        private BaseDeDatos BD =getBaseDatos(); 
    
    public Carrito() {
    }
    
    public void AgregarCarrito(){
        
        Scanner lector = new Scanner(System.in);
        String temporal; 
        boolean opcion=true; 
               
        BD.imprimirLista();
        int i; 
         boolean validador=true;
       
        do{
            System.out.println("Seleccione el elemento que desea agregar al carrito, ya sea escribiendo su identificador único o su nombre");
            do{
                //Variables utilizadas  
                temporal = lector.nextLine();
                temporal =temporal.trim().toUpperCase(); 
                    
                for(i=0; i<BD.getListaProductos().size(); i++){
                    String nombre = BD.getListaProductos().get(i).getNombreProducto().toUpperCase().trim(); 
                    String id =String.valueOf(BD.getListaProductos().get(i).getIdentificadorUnico());
                    id = id.trim().toUpperCase(); 
                    if( temporal.equals(nombre) || temporal.equals(id)){ 
                        do{
                            System.out.println("Ingrese la cantidad que desea agregar al carrito");
                            validador = lector.hasNextInt();
                            if(validador == false){
                                System.out.println("No se ha digitado un valor valido");
                                System.out.println("Vuelvalo a intentar");
                                System.out.println("Recuerda que solo se pueden valores enteros");
                                lector.nextLine(); 
                                System.out.println("-----");

                            }else{
                                int cantidad = lector.nextInt(); 
                                Producto temp = BD.getListaProductos().get(i); 
                                if(cantidad>0){
                                    temp.setStock(cantidad);
                                    carrito.add(temp);
                                    System.out.println("El producto "+temp.getNombreProducto()+ " ha sido agregado correctamente a tu carrito");
                                    this.imprimirCarrito();
                                    System.out.println("");

                                }else{
                                    System.out.println("No puedes digitar cantidades menores o iguales a cero");
                                    System.out.println("Intentelo de nuevo");
                                    System.out.println("-----");
                                    validador =false; 
                                }
                            }
                        }while(validador==false);
                    }else{
                        if(BD.getListaProductos().size() <1+i)
                        System.out.println("Error 404, no se encontro el producto solicitado");
                        System.out.println("------");
                        System.out.println("Escriba el nombre o identificador del producto por favor");
                        System.out.println("");  
                        validador=false; 
                        
                    }
                }
            }while(validador ==false);

        lector.nextLine(); 
        System.out.println("____________________________________________________");
        System.out.println("¿Desea salir o agregar otro producto al carrito?");
        System.out.println("En caso de querer agregar otro producto teclee cualquier digito excepto 1");

        String salir = lector.nextLine();
        String uno = "1"; 
        if(salir.equals(uno)){
            opcion = false;
        }
        System.out.println("____________________________________________________");
    }while(opcion != false);
        
    }
    
    public void VaciarCarrito(){
        carrito.clear();    
    }
    
    public void imprimirCarrito(){
        if(carrito.isEmpty()){
            System.out.println("No hay productos en el carrito");
        }else{
            System.out.println("______________________________________");
            System.out.println("Lista de productos del carrito");
            for(int i=0; i<carrito.size(); i++){
                System.out.println("----------");
                System.out.println("Nombre: "+carrito.get(i).getNombreProducto()+ "\tUnidades: "+this.carrito.get(i).getStock());
                System.out.println("----------");
            }
        }
    }
   
//Si desean pueden ocupaar esta funcion para crear el archivo, o copiarla en usuario, o igual no usarla  
   public boolean ProcesandoPago(){
       if(carrito.isEmpty()){
           System.out.println("No se puede procesar el pago, dado que el carrito esta vacio ");
           return false; 
       }
       String nombreCliente = "Yuno"; //Nombre imaginario, si se usa aqui iría el nombre del cliente 
       this.GuardarArchivo(nombreCliente);
       
       return true; 
   }

   public void GuardarArchivo(String Nombre ){
       String nombreArchivo ="Pedido de"+Nombre+ ".txt";
        
        
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
}

