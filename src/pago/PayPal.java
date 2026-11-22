package pago;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class PayPal extends MedioPago {

    String correo;
    String contrasena;
    private float saldo;

    public PayPal(String alias, String correo) {
        super(alias);
        this.correo = correo;

        Random aleatorio = new Random();
        this.saldo = aleatorio.nextFloat(30000.0f, 80001.0f);
    }

    private boolean centralPaypal() {
        Random aleatorio = new Random();

        boolean conexion = aleatorio.nextBoolean();
        return conexion;
    }

    @Override
    public boolean procesarPago(float monto) {
        DecimalFormat formato = new DecimalFormat("#0.00");
        Scanner lector = new Scanner(System.in);

        System.out.println("Correo registrado: " + this.getCorreo());
        System.out.print("Ingrese la contraseña del correo: ");
        String contrasenaIngreso = lector.nextLine();

        if (contrasenaIngreso.equals(this.getContrasena())) {
            boolean validacion = centralPaypal();
            if (validacion) {
                System.out.println("Saldo: $" + formato.format(this.getSaldo()));
                super.procesarPago(monto);
                if (this.saldo >= monto) {
                    System.out.println("Se ha realizado el pago");
                    this.saldo -= monto;
                    return true;
                } else {
                    System.out.println("Fondos insuficientes. Intente otro medio de pago");
                    return false;
                }
            } else {
                System.out.println("Paypal rechazó el pago. No se pudo establecer conexión con la central. Intente otro medio de pago");
                return false;
            }
        } else {
            System.out.println("El correo ingresado no coincide con la cuenta. El pago fue recha");
            return false;
        }
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("\n");
        DecimalFormat formato = new DecimalFormat("#0.00");
        System.out.println("Alias: " + getAlias());
        System.out.println("Correo: " + correo);
        System.out.println("Saldo: $" + formato.format(this.getSaldo()));
    }

    @Override
    public String informacionPedido() {
        return "Pago con PayPal. Correo: " + this.correo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.correo);
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
        final PayPal other = (PayPal) obj;
        return Objects.equals(this.correo, other.correo);
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
