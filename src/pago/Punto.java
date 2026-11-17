package pago;
public class Punto{
    private int saldo;

    public Punto(float saldoTarjeta) {
        this.saldo = (int) saldoTarjeta/10;
    }

    public void acumulacionPunto(float monto) {
        int puntosObtenidos = (int) monto/10;
        this.saldo += puntosObtenidos;
        System.out.println("Se han acumulado "+puntosObtenidos+" puntos nuevos.");
        System.out.println("Total de puntos: "+this.getSaldo()+" puntos");
    }

    public boolean procesarPago(float monto) {
        int valorPuntos = puntosEfectivo(this.saldo);
        System.out.println("Puntos acumulados: "+this.getSaldo()+" puntos");

        if(valorPuntos >= monto)
        {
            int valorMonto = (int) monto*10;
            this.saldo -= valorMonto;

            System.out.println("Saldo de puntos: "+this.getSaldo());
            return true;
        }
        else {
            System.out.println("No es posible realizar el pago. Puntos insuficientes.");
            return false;
        }
    }

    public int puntosEfectivo(int puntosUsados) {
        return puntosUsados / 10;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

}
