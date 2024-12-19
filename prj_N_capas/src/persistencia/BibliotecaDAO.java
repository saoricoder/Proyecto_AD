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
import java.util.Date; 
import javax.swing.JOptionPane;
import java.util.*;
import java.sql.*;
import negocio.Usuario;

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
    
        // Método para obtener los autores
    public ResultSet obtenerAutores() throws SQLException {
        String query = "SELECT NOMBRE, APELLIDO FROM AUTOR";
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
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

    /**
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
    }**/

    public void agregarCabecera(CabeceraPrestamo cabecera) { 
        try {
            String query = "INSERT INTO CABECERAPRESTAMO (NUMERO, FECHAPRESTAMO, DESCRIPCION) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, cabecera.getNumero()); // Asignar el número como string
            stmt.setDate(2, cabecera.getFechaPrestamo()); // Asignar la fecha como java.sql.Date
            stmt.setString(3, cabecera.getDescripcion()); // Asignar la descripción como string
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void eliminarCabecera(String numero) {
        try {
            String query = "DELETE FROM CABECERAPRESTAMO WHERE NUMERO = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, numero);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Método para obtener datos de la tabla CABECERAPRESTAMO
    public List<CabeceraPrestamo> obtenerCabeceraPrestamo(Date fechaInicio, Date fechaFin) {
        List<CabeceraPrestamo> datos = new ArrayList<>();
        String query = "SELECT NUMERO, FECHAPRESTAMO, DESCRIPCION FROM CABECERAPRESTAMO WHERE FECHAPRESTAMO BETWEEN ? AND ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            stmt.setDate(2, new java.sql.Date(fechaFin.getTime()));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Crear una nueva instancia de CabeceraPrestamo y añadirla a la lista
                CabeceraPrestamo prestamo = new CabeceraPrestamo(
                    rs.getString("NUMERO"),
                    rs.getDate("FECHAPRESTAMO"),
                    rs.getString("DESCRIPCION")
                );
                datos.add(prestamo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datos;
    }


    // Método para obtener datos de la tabla DETALLEPRESTAMO
    public List<Object[]> obtenerDetallePrestamo(Date fechaInicio, Date fechaFin) {
        List<Object[]> datos = new ArrayList<>();
        String query = "SELECT COD_LIBRO, CANTIDAD, FECHA FROM DETALLEPRESTAMO WHERE FECHA BETWEEN ? AND ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            stmt.setDate(2, new java.sql.Date(fechaFin.getTime()));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                datos.add(new Object[] {
                    rs.getString("COD_LIBRO"),
                    rs.getInt("CANTIDAD"),
                    rs.getDate("FECHA")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return datos;
    }



    public List<DetallePrestamo> obtenerDetallePrestamo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    // Método para generar el reporte de los libros prestados en una fecha específica
    public List<String[]> generarReportePorFecha(java.util.Date fecha) {
           List<String[]> resultados = new ArrayList<>();
           String query = "SELECT c.FECHAPRESTAMO, d.COD_LIBRO, d.CANTIDAD " +
                          "FROM CABECERAPRESTAMO c " +
                          "JOIN DETALLEPRESTAMO d ON c.NUMERO = d.NUMERO " +
                          "WHERE c.FECHAPRESTAMO = ?";

           try (PreparedStatement stmt = conn.prepareStatement(query)) {
               // Convertir java.util.Date a java.sql.Date
               java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
               stmt.setDate(1, sqlDate);  // Establecer el parámetro de fecha

               try (ResultSet rs = stmt.executeQuery()) {
                   while (rs.next()) {
                       String fechaPrestamo = rs.getDate("FECHAPRESTAMO").toString();
                       String codigoLibro = rs.getString("COD_LIBRO");
                       int cantidad = rs.getInt("CANTIDAD");
                       resultados.add(new String[]{fechaPrestamo, codigoLibro, String.valueOf(cantidad)});
                   }
               }

           } catch (SQLException e) {
               JOptionPane.showMessageDialog(null, "Error al obtener los datos del reporte: " + e.getMessage());
           }
           return resultados;
       }

    // Método para cerrar la conexión
    public void cerrarConexion() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public int obtenerCantidadTotalLibros(java.util.Date fechaSeleccionada) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    // Método para obtener las fechas únicas de la tabla CABECERAPRESTAMO
    public List<java.util.Date> obtenerFechasDisponibles() {
        List<java.util.Date> fechas = new ArrayList<>();
        String query = "SELECT DISTINCT FECHAPRESTAMO FROM CABECERAPRESTAMO";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                java.sql.Date sqlDate = rs.getDate("FECHAPRESTAMO");
                if (sqlDate != null) {
                    // Conversión de java.sql.Date a java.util.Date
                    fechas.add(new java.util.Date(sqlDate.getTime()));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener las fechas: " + e.getMessage());
        }
        return fechas;
    }
    
    public Collection<Usuario> obtenerUsuarios() throws SQLException {
        Collection<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT ID_USUARIO, USUARIO, PASSWORD FROM USUARIOS";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Usuario usuario = new Usuario(rs.getInt("ID_USUARIO"), rs.getString("USUARIO"), rs.getString("PASSWORD"));
            usuarios.add(usuario);
        }

        return usuarios;
    }
}


