package usuarios;

import domicilios.Domicilio;
import java.util.ArrayList;
import pago.MedioPago;

public class Cliente extends Usuario {
    private ArrayList<Domicilio> domicilios; 
    private ArrayList<MedioPago> pagos;

    public Cliente(String nombreUsuario, String nombreCompleto) {
        super(nombreUsuario, nombreCompleto);
        this.domicilios = new ArrayList<>();
        this.pagos = new ArrayList<>();
    }

    @Override
    public Rol getRol() {
        return Rol.CLIENTE;
    }

    public void agregarDomicilio(Domicilio domicilio) {
        if(!domicilios.equals(domicilio))
        {
            this.getDomicilios().add(domicilio);
        }
        else {
            System.out.print("Domicilio ya registrado");
        }
        this.getDomicilios().add(domicilio);
    }

    public ArrayList<Domicilio> getDomicilios() {
        return domicilios;
    }

    public void setDomicilios(ArrayList<Domicilio> domicilios) {
        this.domicilios = domicilios;
    }

    public void agregarMedioPago(MedioPago medio) {
        if(!medio.equals(medio)) {
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
