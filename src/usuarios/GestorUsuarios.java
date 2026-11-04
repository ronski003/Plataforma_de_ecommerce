package usuarios;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class GestorUsuarios {
    private final Map<String, Usuario> usuarios = new LinkedHashMap<>();

    public boolean registrarCliente(String nombreUsuario, String nombreCompleto) {
        return agregarUsuario(new Cliente(nombreUsuario, nombreCompleto));
    }


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

    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios.values());
    }

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
