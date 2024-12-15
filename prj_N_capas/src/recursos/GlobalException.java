package recursos;

public class GlobalException extends Exception {
    public GlobalException(String message) {
        super(message);
    }

    public GlobalException(String message, Throwable cause) {
        super(message, cause);  // Pasa la causa a la superclase
    }
}

