package usuarios;

import domicilios.Domicilio;
import java.util.ArrayList;
import pago.MedioPago;
import productos.Carrito;

public class Cliente extends Usuario {
    private ArrayList<Domicilio> domicilios; 
    private ArrayList<MedioPago> pagos;

    private final Carrito carrito = new Carrito();

    public Carrito getCarrito() {
        return carrito;
    }

    public Cliente(String nombreUsuario, String nombreCompleto) {
        super(nombreUsuario, nombreCompleto);
        this.domicilios = new ArrayList<>();
        this.pagos = new ArrayList<>();
    }

    @Override
    public Rol getRol() {
        return Rol.CLIENTE;
    }

    public void agregarDomicilio(Domicilio domicilioNuevo) {
        if(!this.getDomicilios().equals(domicilioNuevo))
        {
            this.getDomicilios().add(domicilioNuevo);
        }
        else {
            System.out.print("Domicilio ya registrado");
        }
    }

    public ArrayList<Domicilio> getDomicilios() {
        return domicilios;
    }

    public void setDomicilios(ArrayList<Domicilio> domicilios) {
        this.domicilios = domicilios;
    }

    public void agregarMedioPago(MedioPago medio) {
        if(!this.getPagos().contains(medio)) {
        this.getPagos().add(medio);
        }
        else {
            System.out.print("Medio de pago ya registrado");
        }
    }

    public ArrayList<MedioPago> getPagos() {
        return pagos;
    }

}
