package libraryejb.exception;

/**
 * 
 */
public class AuthorNotFoundException extends Exception {
    
    public AuthorNotFoundException() {
        super("");
    }

    public AuthorNotFoundException(String message) {
        super(message);
    }
}
