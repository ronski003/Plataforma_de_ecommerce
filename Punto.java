public class Punto extends MedioPago{
    private int saldo;

    public Punto(String alias, float saldoTarjeta) {
        super(alias);
        this.saldo = (int) saldoTarjeta/10;
    }

    public void acumulacionPunto(float monto) {
        int puntosObtenidos = (int) monto/10;
        this.saldo += puntosObtenidos;
        System.out.println("Se han acumulado "+puntosObtenidos+" puntos nuevos.");
        System.out.println("Total de puntos: "+this.getSaldo()+" puntos");
    }

    @Override 
    public void procesarPago(float monto) {
        int valorPuntos = puntosEfectivo(this.saldo);
        

        if(valorPuntos > saldo)
        {
            super.procesarPago(monto);

            int valorMonto = (int) monto/10;
            this.saldo -= valorMonto;

            System.out.println("Saldo de puntos: "+this.getSaldo());
        }
        else {
            System.out.println("No es posible realizar el pago. Intente con otro medio de pago.");
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
