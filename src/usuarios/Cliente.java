package usuarios;

public class Cliente extends Usuario {
    public Cliente(String nombreUsuario, String nombreCompleto) {
        super(nombreUsuario, nombreCompleto);
    }

    @Override
    public Rol getRol() {
        return Rol.CLIENTE;
    }
}
