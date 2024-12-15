
package recursos;


public class NoDataExeption extends Exception {
    public NoDataExeption(String message) {
        super(message);
    }

    public NoDataExeption(String message, Throwable cause) {
        super(message, cause);  // Pasa la causa a la superclase
    }
}

