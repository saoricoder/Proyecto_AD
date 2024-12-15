package negocio;

public class Usuario {
    private int idUsuario;
    private String usuario;
    private String password;

    // Constructor con parámetros
    public Usuario(int idUsuario, String usuario, String password) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.password = password;
    }

    // Constructor sin parámetros (para la búsqueda y otros casos)
    public Usuario() {
        // Este constructor puede dejarse vacío o inicializar los valores con valores predeterminados si es necesario.
        this.idUsuario = 0;
        this.usuario = "";
        this.password = "";
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{idUsuario=" + idUsuario + ", usuario='" + usuario + "', password='" + password + "'}";
    }
}


