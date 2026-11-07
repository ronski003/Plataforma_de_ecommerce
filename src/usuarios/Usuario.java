package usuarios;


public abstract class Usuario {
    public enum Rol {
        CLIENTE,
        ADMINISTRADOR
    }

    private final String nombreUsuario;
    private final String nombreCompleto;

    protected Usuario(String nombreUsuario, String nombreCompleto) {
        this.nombreUsuario = validarTexto(nombreUsuario, "Nombre de usuario");
        this.nombreCompleto = validarTexto(nombreCompleto, "Nombre completo");
    }

    private static String validarTexto(String texto, String campo) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException(campo + " no puede estar vacio.");
        }
        return texto.trim();
    }

    public final String getNombreUsuario() {
        return nombreUsuario;
    }

    public final String getNombreCompleto() {
        return nombreCompleto;
    }

    public abstract Rol getRol();

    @Override
    public String toString() {
        return getRol() + " - " + nombreUsuario + " (" + nombreCompleto + ")";
    }
}
