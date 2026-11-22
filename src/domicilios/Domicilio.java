package domicilios;

import java.util.Objects;
import java.util.Scanner;

public class Domicilio {
    private String calle;
    private String colonia;
    private String telefono;

    public Domicilio(String colonia, String calle, String telefono) {
        this.colonia = colonia;
        this.calle = calle;
        this.telefono = telefono;
    }

    public void actualizarTelefono() {
        Scanner lector = new Scanner(System.in);
        System.out.println("Ingrese el nuevo número asociado al domicilio");
        String nuevoTelefono = lector.nextLine();
        this.setTelefono(nuevoTelefono);
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.calle);
        hash = 47 * hash + Objects.hashCode(this.colonia);
        hash = 47 * hash + Objects.hashCode(this.telefono);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Domicilio other = (Domicilio) obj;
        if (!Objects.equals(this.calle, other.calle)) {
            return false;
        }
        if (!Objects.equals(this.colonia, other.colonia)) {
            return false;
        }
        return Objects.equals(this.telefono, other.telefono);
    }
    
}
