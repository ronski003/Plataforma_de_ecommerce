package usuarios;

/**
 * Perfil de quien administra el catalogo de productos.
 * Las operaciones sobre productos pertenecen al modulo de productos.
 */
public class Administrador extends Usuario {
    public Administrador(String nombreUsuario, String nombreCompleto) {
        super(nombreUsuario, nombreCompleto);
    }

    @Override
    public Rol getRol() {
        return Rol.ADMINISTRADOR;
    }
}
