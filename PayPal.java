import java.util.Random;
import java.util.Scanner;

public class PayPal extends MedioPago{
    String correo;
    String contrasena;
    private float saldo;

    public PayPal(String alias, String correo) {
        super(alias);
        this.correo = correo;

        Random aleatorio = new Random();
        this.saldo = aleatorio.nextFloat(100.0f, 2001.0f);
    }

    private boolean centralPaypal() {
        Random aleatorio = new Random();

        boolean conexion = aleatorio.nextBoolean();
        return conexion;
    }
    
    @Override 
    public void procesarPago(float monto) {
        Scanner lector = new Scanner(System.in);

        System.out.println("Ingrese el correo: ");
        String correoIngreso = lector.nextLine();

        if(correoIngreso.equals(correo)) {
            boolean validacion = centralPaypal();
            if(validacion)
            {
                super.procesarPago(monto);
                if(this.saldo >= monto)
                {
                    System.out.println("Se ha realizado el pago");
                    this.saldo -= monto;
                }
                else {
                System.out.println("Fondos insuficientes. Intente otro medio de pago");
                }
            }
            else{
                System.out.println("Paypal rechazó el pago. Intente otro medio de pago");
            }
        }
        else {
            System.out.println("El correo ingresado no coincide con la cuenta. El pago fue recha");
        }
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
}