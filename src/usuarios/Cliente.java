package usuarios;

/**
 * Perfil de quien realiza compras.
 * Las relaciones con Carrito, Domicilio, MedioPago y Pedido se agregaran
 * cuando sus paquetes e interfaces esten acordados con el equipo.
 */
public class Cliente extends Usuario {
    public Cliente(String nombreUsuario, String nombreCompleto) {
        super(nombreUsuario, nombreCompleto);
    }

    @Override
    public Rol getRol() {
        return Rol.CLIENTE;
    }
}
