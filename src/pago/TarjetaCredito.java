package pago;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class TarjetaCredito extends MedioPago {

    private String numTarjeta;
    private int nip;
    private float saldo;
    private Punto punto;

    public TarjetaCredito(String alias, String numTarjeta, int nip) {
        super(alias);
        this.numTarjeta = numTarjeta;
        this.nip = nip;

        Random aleatorio = new Random();
        this.saldo = aleatorio.nextFloat(30000.0f, 80001.0f);

        this.punto = new Punto(this.saldo);
    }

    @Override
    public boolean procesarPago(float monto) {
        DecimalFormat formato = new DecimalFormat("#0.00");
        Scanner lector = new Scanner(System.in);
        int nip = 0;
        try {
            System.out.print("Ingrese el NIP: ");
            nip = lector.nextInt();
            lector.nextLine();
        } catch (Exception e) {
            System.out.println("El NIP debe contener únicamente números");
            lector.nextLine();
            return false;
        }

        if (nip != this.getNip()) {
            System.out.println("NIP incorrecto");
            return false;
        }

        System.out.println("\n");
        System.out.println("1. Saldo de la tarjeta");
        System.out.println("2. Puntos acumulados");
        System.out.print("Seleccione el método de pago: ");
        int opcion = lector.nextInt();

        if (opcion == 2) {
            super.procesarPago(monto);

            return punto.procesarPago(monto);
        }
        System.out.println("\n");
        System.out.println("Saldo: $" + formato.format(this.getSaldo()));
        super.procesarPago(monto);
        if (this.saldo >= monto) {
            System.out.println("Se ha realizado el pago");
            this.saldo -= monto;
            punto.acumulacionPunto(monto);
            return true;
        } else {
            System.out.println("Fondos insuficientes");
            return false;

        }

    }

    @Override
    public void mostrarInformacion() {
        DecimalFormat formato = new DecimalFormat("#0.00");
        String ultimos;
        if (this.getNumTarjeta().length() >= 4) {
            ultimos = this.getNumTarjeta().substring(this.numTarjeta.length() - 4);
        } else {
            ultimos = this.getNumTarjeta();
        }
        System.out.println("Alias: " + getAlias());
        System.out.println("Tarjeta: **** **** **** " + ultimos);
        System.out.println("Saldo: $" + formato.format(this.getSaldo()));
        System.out.println("Puntos acumulados: " + punto.getSaldo());
    }

    @Override
    public String informacionPedido() {
        String ultimos = this.getNumTarjeta().substring(this.numTarjeta.length() - 4);

        return "Pago con Tarjeta. Terminación: " + ultimos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.numTarjeta);
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
        final TarjetaCredito other = (TarjetaCredito) obj;
        return Objects.equals(this.numTarjeta, other.numTarjeta);
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

    public Punto getPunto() {
        return punto;
    }

    public void setPunto(Punto punto) {
        this.punto = punto;
    }
}
