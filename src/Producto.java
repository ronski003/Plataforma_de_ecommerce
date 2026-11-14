
package Productos;
import java.util.ArrayList;
import java.util.Scanner;




public class Producto {
    private String nombreProducto;
    private float precio; 
    private String categoria; 
    private long  identificadorUnico; 
    private int stock; 

    public Producto(String nombreProducto, float precio, String categoria, long identificadorUnico) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.categoria = categoria;
        this.identificadorUnico = identificadorUnico;
        this.stock = 10; //Stock ilimitado, solo le coloco una cantidad de default para que se imprima.
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

  
