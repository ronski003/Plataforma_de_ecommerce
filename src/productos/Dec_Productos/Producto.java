
package productos.Dec_Productos;
import BaseDatos.BaseDeDatos;
import java.util.Scanner;

public class Producto {
    private String nombreProducto;
    private float precio; 
    private String categoria; 
    private long  identificadorUnico; 

    public Producto(String nombreProducto, float precio, String categoria, long identificadorUnico) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.categoria = categoria;
        this.identificadorUnico = identificadorUnico;
    }
    
    
    private static float registrarPrecio(BaseDeDatos base, String nombreProduct){
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
    
    
    public  static  void registrarProducto(BaseDeDatos base){
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

            precio = registrarPrecio(base, nombreProduct); 

            //Se registra la categoria
            System.out.println("Ingrese la categoría");
            Categoria= lector.nextLine();     

            //Se registra el identificador del producto
            do{
                System.out.println("Ingresa el identificador del producto");
                validadorTemp = lector.hasNextLong();
                if(validadorTemp == true){
                    long id = lector.nextLong(); 
                    for(int i =0; i<base.getListaProductos().size(); i++){
                        if(base.getListaProductos().get(i).equals(id)){
                            System.out.println("El identificador único ingresado se ha encontrado en otro producto");
                            System.out.println("Escriba otro nuevamente");
                            System.out.println("-----");
                            break; 
                        }
                    }
                    identificador = id;
                    System.out.println("Identificador único almacenado con éxito");
                }else{
                    System.out.println("No se ha insertado un identificador válido");
                    System.out.println("Vuélvalo a intentar");
                    lector.next();
                }
                lector.nextLine();
            }while(validadorTemp == false);
            
            //Se agrega el producto en la base de datos 
            base.AgregarProducto(new Producto(nombreProduct,precio,Categoria,identificador));

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
    public static void modificarPrecio(BaseDeDatos base){
        boolean validador = false;
        long id = 0L; 
        float precio=0f; 
        Scanner lector = new Scanner(System.in); 
        System.out.println("\t *** Modificador de precio ***");
        //Se imprime la lista de productos
        base.imprimirLista();
        do{
            System.out.println("Ingrese el identificador del producto del cual deseas modificar");
            validador = lector.hasNextLong(); 
            if(validador){
                id =lector.nextLong(); 
                for(int i =0; i<base.getListaProductos().size(); i++){
                    if(base.getListaProductos().get(i).identificadorUnico==id){
                        System.out.println("Se ha encontrado el producto"); 
                        precio=registrarPrecio(base, base.getListaProductos().get(i).getNombreProducto());
                        base.getListaProductos().get(i).setPrecio(precio);
                        System.out.println("Se ha modificado el precio correctamente");
                        break; 
                    }
                    if(i+1>=base.getListaProductos().size()){
                        System.out.println("No se ha encontrado el producto, inténtelo con otra clave");
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("");
                    }
                }
            }
        }while(validador = false);    
    } 
        
    //Metodos getter y setter 

    public String getNombreProducto() {
        return nombreProducto;
    }

    public float getPrecio() {
        return precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public long getIdentificadorUnico() {
        return identificadorUnico;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setIdentificadorUnico(long identificadorUnico) {
        this.identificadorUnico = identificadorUnico;
    }
    
    


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        if(this.identificadorUnico == other.identificadorUnico){
            return true; 
        }
        return false;
    }
    
    
    
    
   
}