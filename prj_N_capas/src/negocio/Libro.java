package negocio;

public class Libro {
    private String ISBN;
    private String titulo;
    private String autor;
    private double valorPrestamo;

    // Constructor con parámetros
    public Libro(String ISBN, String titulo, String autor, double valorPrestamo) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.valorPrestamo = valorPrestamo;
    }

    // Constructor sin parámetros (para la búsqueda y otros casos)
    public Libro() {
        // Este constructor puede dejarse vacío o inicializar los valores con valores predeterminados si es necesario.
        this.ISBN = "";
        this.titulo = "";
        this.autor = "";
        this.valorPrestamo = 0.0;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getValorPrestamo() {
        return valorPrestamo;
    }

    public void setValorPrestamo(double valorPrestamo) {
        this.valorPrestamo = valorPrestamo;
    }

    @Override
    public String toString() {
        return "Libro{ISBN='" + ISBN + "', titulo='" + titulo + "', autor='" + autor + "', valorPrestamo=" + valorPrestamo + "}";
    }
}

