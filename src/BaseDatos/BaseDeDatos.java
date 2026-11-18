package BaseDatos;
import java.util.ArrayList;
import java.util.Scanner;
import productos.Producto;

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
        cargaProductos();
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
            System.out.println("No hay productos en el almacén");
        }else{
            System.out.println("______________________________________");
            System.out.println("Lista de productos del almacén");
            for(int i=0; i<this.listaProductos.size(); i++){
                System.out.println("----------");
                System.out.println("Nombre: "+this.listaProductos.get(i).getNombreProducto()+ "\tIdentificador: "+this.listaProductos.get(i).getIdentificadorUnico());
                System.out.println("Precio $"+this.listaProductos.get(i).getPrecio());
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
                System.out.println("No se ha insertado un precio válido");
                System.out.println("Vuélvalo a intentar");
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
                    System.out.println("Vuélvalo a intentar");
                    lector.next();
                }
                lector.nextLine();
            }while(validadorTemp == false);
            
            //Se agrega el producto en la base de datos 
            this.listaProductos.add(new Producto(nombreProduct,precio,Categoria,identificador));

            System.out.println("____________________________________________________");
            System.out.println("¿Desea salir o agregar otro producto?");
            System.out.println("En caso de querer agregar otro producto teclee cualquier dígito excepto 1");

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
        
        if(this.listaProductos.isEmpty()) {
            System.out.println("No hay productos en el almacén");
            return;
        }

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
                        System.out.println("Se ha modificado el precio correctamente");
                        System.out.println("Producto: "+this.getListaProductos().get(i).getNombreProducto()+ "\tPrecio: $"+this.getListaProductos().get(i).getPrecio());
                        break; 
                    }
                    if(i+1>=this.listaProductos.size()){
                        System.out.println("No se ha encontrado el producto, inténtelo con otra clave");
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("");
                        validador=false;
                                              
                    }
                }
            }else{
                System.out.println("Identificardor inválido vuélvalo a intentar");
                System.out.println("");
                lector.nextLine();
            }
        }while(validador == false);    
        
    } 

    private void cargaProductos() {
        listaProductos.add(new Producto("Laptop Lenovo", 14999.99f, "Computación", 1000000001));
        listaProductos.add(new Producto("Mouse Inalámbrico", 349.99f, "Computación", 1000000002));
        listaProductos.add(new Producto("Teclado Mecánico", 899.99f, "Computación", 1000000003));
        listaProductos.add(new Producto("Monitor 24 Pulgadas", 3299.99f, "Computación", 1000000004));

        listaProductos.add(new Producto("Audífonos Bluetooth", 799.99f, "Audio", 1000000005));
        listaProductos.add(new Producto("Bocina Portátil", 1199.99f, "Audio", 1000000006));
        listaProductos.add(new Producto("Micrófono USB", 699.99f, "Audio", 1000000007));

        listaProductos.add(new Producto("Smartphone Samsung", 8999.99f, "Telefonía", 1000000008));
        listaProductos.add(new Producto("Cargador USB-C", 249.99f, "Telefonía", 1000000009));
        listaProductos.add(new Producto("Power Bank 10000mAh", 599.99f, "Telefonía", 1000000010));

        listaProductos.add(new Producto("Playera Negra", 299.99f, "Ropa", 1000000011));
        listaProductos.add(new Producto("Jeans Azul", 699.99f, "Ropa", 1000000012));
        listaProductos.add(new Producto("Sudadera Gris", 849.99f, "Ropa", 1000000013));

        listaProductos.add(new Producto("Cafetera", 1499.99f, "Hogar", 1000000014));
        listaProductos.add(new Producto("Licuadora", 1199.99f, "Hogar", 1000000015));
        listaProductos.add(new Producto("Sartén Antiadherente", 549.99f, "Hogar", 1000000016));

        listaProductos.add(new Producto("Balón de Fútbol", 399.99f, "Deportes", 1000000017));
        listaProductos.add(new Producto("Mancuernas 5kg", 799.99f, "Deportes", 1000000018));

        listaProductos.add(new Producto("Mochila Escolar", 649.99f, "Accesorios", 1000000019));
        listaProductos.add(new Producto("Reloj Digital", 1299.99f, "Accesorios", 1000000020));
}

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }
    
    
}

    
    
    
    


    
    
    

