
package BasedeDatos;
import Productos.Producto;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author andre
 */
public class BaseDeDatos{
    //Atrubutos 
    private ArrayList<Producto> listaProductos; 
    private static BaseDeDatos baseDatos; 
    
    
    //-------------------------------------------------------------------
    //Se vuelve privado el metodo constructor de base de datos 
    private BaseDeDatos(){
        this.listaProductos = new ArrayList<>();
        System.out.println("Se ha inicializado la base de datos...");
    }
    
    //Metodo que verifica que no haya otra estancia de base de datos 
    public static BaseDeDatos getBaseDatos(){
        if(baseDatos ==null){
            baseDatos =new BaseDeDatos();
        }
        return baseDatos; 
    }
    
    
  
    
    public void imprimirLista(){
        if(this.listaProductos.isEmpty()){
            System.out.println("No hay productos en el almacen");
        }else{
            System.out.println("______________________________________");
            System.out.println("Lista de productos del almacen");
            for(int i=0; i<this.listaProductos.size(); i++){
                System.out.println("----------");
                System.out.println("Nombre: "+this.listaProductos.get(i).getNombreProducto()+ "\tIdentificador: "+this.listaProductos.get(i).getIdentificadorUnico());
                System.out.println("Precio "+this.listaProductos.get(i).getPrecio());
                System.out.println("----------");
            }
        }
    }
     
     
     
     private static float registrarPrecio(String nombreProduct){
        Scanner lector = new Scanner(System.in);
        float precio = 0f; 
        boolean validador; 
        do{
            System.out.println("Ingresa el precio del producto");
            validador = lector.hasNextFloat();

            if(validador == true){

                precio = lector.nextFloat();

                if(precio <= 0){
                    System.out.println("ERROR");
                    System.out.println("Se ha detectado que al producto " + nombreProduct + " se le ha dado un precio menor o igual a cero");
                    System.out.println("Se solicita que coloque un precio mayor a cero");
                    System.out.println("----");
                    validador = false;
                }

            }else{
                System.out.println("No se ha insertado un precio valido");
                System.out.println("Vuelvalo a intentar");
                System.out.println("------");
                lector.next();
                validador = false;
            }
            lector.nextLine();
        }while(validador == false);
        return precio;  
    }
     
     
    public  void registrarProducto(){
        //Se Declaran las variables y objetos.
        Scanner lector = new Scanner(System.in);
        String salir;
        String uno = "1";
        boolean validadorSalida = true;
        boolean validadorTemp = true;
        String nombreProduct ="";
        String Categoria = ""; 
        long identificador =0L;
        float precio=0f;

       //Desarrollo de la funcion
        while(validadorSalida == true){

            System.out.println("Ingresa el nombre del producto");
            nombreProduct = lector.nextLine(); 
            

            precio = registrarPrecio(nombreProduct); 

            //Se registra la categoria
            System.out.println("Ingrese la categoria");
            Categoria= lector.nextLine();     

            //Se registra el identificador del producto
            do{
                System.out.println("Ingresa el identificador del producto");
                validadorTemp = lector.hasNextInt();
                if(validadorTemp == true){
                    long id = lector.nextLong(); 
                    for(int i =0; i<this.listaProductos.size(); i++){ //Se investiga si el identificador ya fue usado 
                       long idr= this.listaProductos.get(i).getIdentificadorUnico();
                        if(idr ==id){
                            System.out.println("El identificador único ingresado se ha encontrado en otro producto o no es valido");
                            System.out.println("Escriba otro nuevamente");
                            System.out.println("-----");
                            validadorTemp=false;
                            break; 
                        }
                    }
                    if(id > 0 && validadorTemp==true){
                       identificador = id;
                        System.out.println("Identificador único almacenado con éxito"); 
                        System.out.println("-----");
                    }
                    
                }else{
                    System.out.println("No se ha insertado un identificador valido");
                    System.out.println("Vuelvalo a intentar");
                    lector.next();
                }
                lector.nextLine();
            }while(validadorTemp == false);
            
            //Se agrega el producto en la base de datos 
            this.listaProductos.add(new Producto(nombreProduct,precio,Categoria,identificador));

            System.out.println("____________________________________________________");
            System.out.println("¿Desea salir o agregar otro producto?");
            System.out.println("En caso de querer agregar otro producto teclee cualquier digito excepto 1");

            salir = lector.nextLine();

            if(salir.equals(uno)){
                validadorSalida = false;
            }
            System.out.println("____________________________________________________");

        }
    }
    //Modificador de Precios 
    public void modificarPrecio(){
        boolean validador = false;
        long id = 0L; 
        float precio=0f; 
        Scanner lector = new Scanner(System.in); 
        
        System.out.println("\t *** Modificador de precio ***");
        //Se imprime la lista de productos
        this.imprimirLista();
        do{
            System.out.println("Ingrese el identificador del producto del cual deseas modificar");
            validador = lector.hasNextLong(); 
            if(validador){
                id =lector.nextLong(); 
                for(int i =0; i<this.listaProductos.size(); i++){
                    if(this.listaProductos.get(i).getIdentificadorUnico()==id){
                        System.out.println("Se ha encontrado el producto");
                        System.out.println("");
                        precio=registrarPrecio(this.listaProductos.get(i).getNombreProducto());
                       this.listaProductos.get(i).setPrecio(precio);
                        System.out.println("Se a modificado el precio correctamente");
                        System.out.println("Producto: "+this.getListaProductos().get(i).getNombreProducto()+ "\tPrecio: "+this.getListaProductos().get(i).getPrecio());
                        break; 
                    }
                    if(i+1>=this.listaProductos.size()){
                        System.out.println("No se ha encontrado el producto, intentelo con otra clave");
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("");
                        validador=false;
                                              
                    }
                }
            }else{
                System.out.println("Identificardor invalido vuelvalo a intentar");
                System.out.println("");
                lector.nextLine();
            }
        }while(validador == false);    
    } 

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }
    
    
}

    
    
    
    


    
    
    

