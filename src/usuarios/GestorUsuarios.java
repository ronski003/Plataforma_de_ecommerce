package usuarios;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Mantiene a todos los usuarios registrados durante la ejecucion.
 * La busqueda y la unicidad ignoran espacios externos y mayusculas.
 */
public class GestorUsuarios {
    private final Map<String, Usuario> usuarios = new LinkedHashMap<>();

    /**
     * Registra a un cliente. Devuelve false si su nombre de usuario ya existe.
     * Lanza IllegalArgumentException si los datos estan vacios.
     */
    public boolean registrarCliente(String nombreUsuario, String nombreCompleto) {
        return agregarUsuario(new Cliente(nombreUsuario, nombreCompleto));
    }

    /**
     * Busca a un usuario para iniciar sesion; devuelve null si no existe.
     * El enunciado no pide contrasena de acceso al sistema.
     */
    public Usuario ingresar(String nombreUsuario) {
        return buscarUsuario(nombreUsuario);
    }

    public Usuario buscarUsuario(String nombreUsuario) {
        if (nombreUsuario == null || nombreUsuario.isBlank()) {
            return null;
        }
        return usuarios.get(clave(nombreUsuario));
    }

    public boolean existeUsuario(String nombreUsuario) {
        return buscarUsuario(nombreUsuario) != null;
    }

    public int cantidadUsuarios() {
        return usuarios.size();
    }

    /** Devuelve una copia para que nadie modifique la coleccion interna. */
    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios.values());
    }

    /**
     * Datos minimos para las pruebas. Se puede llamar varias veces sin
     * duplicarlos. Los productos iniciales pertenecen a otro modulo.
     */
    public void cargarDatosIniciales() {
        registrarAdministradorInterno("admin", "Administrador de prueba");
        registrarCliente("cliente1", "Cliente de prueba");
    }

    private boolean registrarAdministradorInterno(String nombreUsuario, String nombreCompleto) {
        return agregarUsuario(new Administrador(nombreUsuario, nombreCompleto));
    }

    private boolean agregarUsuario(Usuario usuario) {
        String clave = clave(usuario.getNombreUsuario());
        if (usuarios.containsKey(clave)) {
            return false;
        }
        usuarios.put(clave, usuario);
        return true;
    }

    private static String clave(String nombreUsuario) {
        return nombreUsuario.trim().toLowerCase(Locale.ROOT);
    }
}
