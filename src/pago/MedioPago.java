package pago;

import java.text.DecimalFormat;

public abstract class MedioPago {
    private String alias; 

    public MedioPago(String alias){
        this.alias = alias;
    }

    public boolean procesarPago(float monto) {
        DecimalFormat formato = new DecimalFormat("#0.00");
        System.out.println("Se debe pagar $"+formato.format(monto));
        return true;
    }

    public abstract void mostrarInformacion(); 
    
    public abstract String informacionPedido();

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
