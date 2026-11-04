package usuarios;


public class Administrador extends Usuario {
    public Administrador(String nombreUsuario, String nombreCompleto) {
        super(nombreUsuario, nombreCompleto);
    }

    @Override
    public Rol getRol() {
        return Rol.ADMINISTRADOR;
    }
}
