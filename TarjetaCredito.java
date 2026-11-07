import java.util.Random;
import java.util.Scanner;

public class TarjetaCredito extends MedioPago{
    private String numTarjeta;
    private int nip;
    private float saldo;
    private Punto punto;

    public TarjetaCredito(String alias, String numTarjeta, int nip){
        super(alias);
        this.numTarjeta = numTarjeta;
        this.nip = nip;

        Random aleatorio = new Random();
        this.saldo = aleatorio.nextFloat(100.0f, 1001.0f);

        this.punto = new Punto(alias, this.saldo);
    }

    @Override 
    public void procesarPago(float monto) {
        Scanner lector = new Scanner(System.in);

        System.out.print("Ingrese el NIP: ");
        int nip = lector.nextInt();

        if(nip == this.getNip())
        {
            super.procesarPago(monto);
            if(this.saldo >= monto)
            {
                System.out.println("Se ha realizado el pago");
                this.saldo -= monto;
            }
            else{
                System.out.println("Fondos insuficientes");
                
            }
        }
        else {
            System.out.println("El pago fue rechazado");
        }
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public int getNip() {
        return nip;
    }

    public void setNio(int nip) {
        this.nip = nip;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
