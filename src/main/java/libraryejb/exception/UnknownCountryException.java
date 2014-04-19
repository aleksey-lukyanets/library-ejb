package libraryejb.exception;

/**
 * 
 */
public class UnknownCountryException extends Exception {
    
    public UnknownCountryException() {
        super("");
    }

    public UnknownCountryException(String message) {
        super(message);
    }
}
