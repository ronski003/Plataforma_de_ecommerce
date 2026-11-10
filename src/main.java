import BaseDatos.BaseDeDatos;
import static productos.Dec_Productos.Producto.modificarPrecio;
import static productos.Dec_Productos.Producto.registrarProducto;


public class main{


    public static void main(String[] args) {
        BaseDeDatos BaseDeDatos =new BaseDeDatos();
        
        registrarProducto(BaseDeDatos); 
        
        
        modificarPrecio(BaseDeDatos);
    }
    
}
