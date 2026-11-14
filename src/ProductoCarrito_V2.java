/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package productocarrito_v2;

import BasedeDatos.BaseDeDatos;
import static BasedeDatos.BaseDeDatos.getBaseDatos;
import Productos.Carrito;

/**
 *
 * @author andre
 */
public class ProductoCarrito_V2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Carrito carrito = new Carrito(); 
        BaseDeDatos bd= getBaseDatos(); 
        
        bd.registrarProducto();
        
        bd.modificarPrecio();
        
        
        bd.imprimirLista();
        
        carrito.AgregarCarrito();
        
        carrito.ProcesandoPago(); 
        
    }
    
}
