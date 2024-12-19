package persistencia;

import recursos.conexion.conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import negocio.CabeceraPrestamo;
import negocio.DetallePrestamo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BibliotecaDAO {
    //private static final Logger LOGGER = Logger.getLogger(BibliotecaDAO.class.getName());
    
    private final Connection conn;

    public BibliotecaDAO() {
        this.conn = new conexion().conectar(); // Conectar a la base de datos
    }

    public boolean agregarAutor(String nombre, String apellido) {
        String sql = "INSERT INTO AUTOR (nombre, apellido) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            int filasInsertadas = stmt.executeUpdate();
            return filasInsertadas > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public ResultSet buscarAutores(String nombre, String apellido) {
        String sql = "SELECT * FROM AUTOR WHERE NOMBRE LIKE ? AND APELLIDO LIKE ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nombre + "%");
            stmt.setString(2, "%" + apellido + "%");
            return stmt.executeQuery();
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean modificarAutor(int codigo, String nombre, String apellido) {
        String sql = "UPDATE AUTOR SET NOMBRE = ?, APELLIDO = ? WHERE CODIGO = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setInt(3, codigo);
            int filasActualizadas = stmt.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean eliminarAutor(int codigo) {
        String sql = "DELETE FROM AUTOR WHERE CODIGO = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            int filasEliminadas = stmt.executeUpdate();
            return filasEliminadas > 0;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public List<String[]> obtenerTodosAutores() throws SQLException {
            String sql = "SELECT * FROM AUTOR";
            List<String[]> autores = new ArrayList<>();
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    String[] autor = {
                        String.valueOf(rs.getInt("codigo")),
                        rs.getString("nombre"),
                        rs.getString("apellido")
                    };
                    autores.add(autor);
                }
            }
            return autores;
        }
    
    public List<String[]> listarAutores() {
        String sql = "SELECT * FROM AUTOR";
        List<String[]> autores = new ArrayList<>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String[] autor = {
                    String.valueOf(rs.getInt("codigo")),
                    rs.getString("nombre"),
                    rs.getString("apellido")
                };
                autores.add(autor);
            }
        } catch (SQLException e) {
        }
        return autores;
    }
    
        public void insertarLibro(String isbn, String titulo, String autor, double valorPrestamo) throws SQLException {
        String sql = "INSERT INTO LIBRO (ISBN, TITULO, AUTOR, VALOR_PRESTAMO) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, isbn);
            ps.setString(2, titulo);
            ps.setString(3, autor);
            ps.setDouble(4, valorPrestamo);
            ps.executeUpdate();
        }
    }

    public void actualizarLibro(String isbn, String titulo, String autor, double valorPrestamo) throws SQLException {
        String sql = "UPDATE LIBRO SET TITULO = ?, AUTOR = ?, VALOR_PRESTAMO = ? WHERE ISBN = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, titulo);
            ps.setString(2, autor);
            ps.setDouble(3, valorPrestamo);
            ps.setString(4, isbn);
            ps.executeUpdate();
        }
    }

    public void eliminarLibro(String isbn) throws SQLException {
        String sql = "DELETE FROM LIBRO WHERE ISBN = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, isbn);
            ps.executeUpdate();
        }
    }

    public ResultSet buscarLibro(String isbn, String titulo) throws SQLException {
        String sql = "SELECT * FROM LIBRO WHERE ISBN LIKE ? OR TITULO LIKE ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%" + isbn + "%");
        ps.setString(2, "%" + titulo + "%");
        return ps.executeQuery();
    }
    
        public List<String[]> obtenerTodosLibros() throws SQLException {
            String sql = "SELECT * FROM LIBRO";
            List<String[]> libros = new ArrayList<>();
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    String[] libro = {
                        String.valueOf(rs.getInt("ISBN")),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("valorPrestamo")
                    };
                    libros.add(libro);
                }
            }
            return libros;
        }

    
    public List<CabeceraPrestamo> obtenerCabecerasPrestamo() {
        List<CabeceraPrestamo> cabeceras = new ArrayList<>();
        try {
            String query = "SELECT * FROM DETALLEPRESTAMO";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CabeceraPrestamo cabecera = new CabeceraPrestamo(
                        rs.getString("numero"),
                        rs.getString("fechaPrestamo"),
                        rs.getString("descripcion")
                );
                cabeceras.add(cabecera);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cabeceras;
    }

    public List<DetallePrestamo> obtenerDetallesPrestamo() {
        List<DetallePrestamo> detalles = new ArrayList<>();
        try {
            String query = "SELECT * FROM DETALLEPRESTAMO";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DetallePrestamo detalle = new DetallePrestamo(
                        rs.getString("codigoLibro"),
                        rs.getInt("cantidad"),
                        rs.getString("fechaEntrega")
                );
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }

    public void agregarCabecera(CabeceraPrestamo cabecera) {
        try {
            String query = "INSERT INTO CABECERAPRESTAMO (numero, fechaPrestamo, descripcion) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, cabecera.getNumero());
            stmt.setString(2, cabecera.getFechaPrestamo());
            stmt.setString(3, cabecera.getDescripcion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarCabecera(String numero) {
        try {
            String query = "DELETE FROM CABECERAPRESTAMO WHERE numero = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, numero);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}


