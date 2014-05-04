package libraryejb.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Контроллер сообщений об ошибках.
 */
@Named
@SessionScoped
public class ErrorController implements Serializable {
    
    private String error = "";

    public void clearError() {
        error = "";
    }
    
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
