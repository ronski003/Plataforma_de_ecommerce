package BaseDatos;
import java.util.ArrayList;
import productos.Dec_Productos.Producto;  
/**
 *
 * @author andre
 */
public class BaseDeDatos {
    private ArrayList<Producto> listaProductos = new ArrayList<>(); 
    
    public BaseDeDatos(){
        System.out.println("Se ha inicializado la base de datos...");
    }
    
    public void  AgregarProducto(Producto producto){
        listaProductos.add(producto);
         
    }
    
    public void imprimirLista(){
        if(listaProductos.isEmpty()){
            System.out.println("No hay productos en el almacén");
        }else{
            System.out.println("______________________________________");
            System.out.println("Lista de productos del almacén");
            for(int i=0; i<listaProductos.size(); i++){
                System.out.println("----------");
                System.out.println("Nombre: "+listaProductos.get(i).getNombreProducto()+ "\tIdentificador: "+listaProductos.get(i).getIdentificadorUnico());
                System.out.println("Precio "+listaProductos.get(i).getPrecio());
                System.out.println("----------");
            }
        }
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }
    
}