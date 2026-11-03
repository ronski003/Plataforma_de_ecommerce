public abstract class MedioPago {
    private String alias; 

    public MedioPago(String alias){
        this.alias = alias;
    }

    public void procesarPago(float monto) {
        System.out.println("Se debe pagar $"+monto);
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
