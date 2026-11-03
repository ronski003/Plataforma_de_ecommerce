import java.util.Scanner;

public class Domicilio {
    private String calle;
    private String colonia;
    private String telefono;

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
}
