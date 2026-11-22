package pago;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class TarjetaCredito extends MedioPago {

    private String numTarjeta;
    private String nip;
    private float saldo;
    private Punto punto;

    public TarjetaCredito(String alias, String numTarjeta, String nip) {
        super(alias);
        setNumTarjeta(numTarjeta);
        setNip(nip);

        Random aleatorio = new Random();
        this.saldo = aleatorio.nextFloat(30000.0f, 80001.0f);

        this.punto = new Punto(this.saldo);
    }

    @Override
    public boolean procesarPago(float monto) {
        DecimalFormat formato = new DecimalFormat("#0.00");
        Scanner lector = new Scanner(System.in);
        System.out.print("Ingrese el NIP: ");
        String nipIngresado = lector.nextLine().trim();

        if (!nipIngresado.matches("\\d{4}")) {
            System.out.println("El NIP debe contener exactamente 4 dígitos");
            return false;
        }

        if (!nipIngresado.equals(this.getNip())) {
            System.out.println("NIP incorrecto");
            return false;
        }

        System.out.println("\n");
        System.out.println("1. Saldo de la tarjeta");
        System.out.println("2. Puntos acumulados");
        int opcion;

        while (true) {
            System.out.print("Seleccione el método de pago: ");

            try {
                opcion = Integer.parseInt(lector.nxtLine().trim());
                
                if (opcion == 1 || opcion == 2) {
                    break;
                }
            } catch (NumberFormatException e) {
                //La entrada no es numérica
            }

            System.out.println("Opción inválida. Inténtelo de nuevo.");
        }

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

        return "Pago con Tarjeta. Terminación: "+ultimos;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        if (numTarjeta == null || !numTarjeta.matches("\\d{16}")) {
            throw new IllegalArgumentException( "El número de tarjeta debe contener exactamente 16 ´digitos");
        }
        this.numTarjeta = numTarjeta;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        if (nip == null || !nip.matches("\\d{4}")) {
            throw new IllegalArgumentException("NIP inválido");
        }
        
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
