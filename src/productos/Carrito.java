package productos;

import BaseDatos.BaseDeDatos;
import static BaseDatos.BaseDeDatos.getBaseDatos;
import java.util.ArrayList;
import java.util.Scanner;

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
        boolean encontrado = false;
        Scanner lector = new Scanner(System.in);
        String temporal; 
        boolean opcion=true; 
               
        BD.imprimirLista();
        int i; 
        boolean validador=true;
        
        if(BD.getListaProductos().isEmpty()) {
            return;
        }

        do{
            System.out.println("Seleccione el elemento que desea agregar al carrito, ya sea escribiendo su identificador único o su nombre");
            do{
                //Variables utilizadas  
                temporal = lector.nextLine();
                temporal =temporal.trim().toUpperCase(); 
                encontrado = true; 

                for(i=0; i<BD.getListaProductos().size(); i++){
                    //Object p = BD.getListaProductos().get(i);
                    String nombre = BD.getListaProductos().get(i).getNombreProducto().toUpperCase().trim(); 
                    String id =String.valueOf(BD.getListaProductos().get(i).getIdentificadorUnico());
                    id = id.trim().toUpperCase(); 
                    if( temporal.equals(nombre) || temporal.equals(id)){ 
                        validador = true;
                        do{
                            System.out.println("Ingrese la cantidad que desea agregar al carrito");
                            validador = lector.hasNextInt();
                            if(validador == false){
                                System.out.println("No se ha digitado un valor valido");
                                System.out.println("Vuélvalo a intentar");
                                System.out.println("Recuerda que solo se pueden valores enteros");
                                lector.nextLine(); 
                                System.out.println("-----");

                            }else{
                                int cantidad = lector.nextInt(); 
                                Producto temp = BD.getListaProductos().get(i); 
                                if(cantidad>0){
                                    if(carrito.contains(temp)) {
                                        int posicion = carrito.indexOf(temp);
                                        Producto productoCarrito = carrito.get(posicion);
                                        productoCarrito.setStock(productoCarrito.getStock() + cantidad);
                                        this.imprimirCarrito();
                                    } else {
                                        temp.setStock(cantidad);
                                        carrito.add(temp);
                                        System.out.println("El producto "+temp.getNombreProducto()+ " ha sido agregado correctamente a tu carrito");
                                        this.imprimirCarrito();
                                        System.out.println("");
                                        break;
                                    }
                                }else{
                                    System.out.println("No puedes digitar cantidades menores o iguales a cero");
                                    System.out.println("Inténtelo de nuevo");
                                    System.out.println("-----");
                                    validador =false; 
                                }
                            }
                        }while(validador==false);
                    }
                }
                if(!encontrado) {
                    System.out.println("Error 404, no se encontró el producto solicitado");
                    System.out.println("------");
                    validador = false;
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
        System.out.println("Se ha vaciado el carrito");
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

    public ArrayList<Producto> getCarrito() {
        return carrito;
    }
   
//Si desean pueden ocupaar esta funcion para crear el archivo, o copiarla en usuario, o igual no usarla  

}

