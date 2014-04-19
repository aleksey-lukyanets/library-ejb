package libraryejb.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * Контроллер сообщений об ошибках.
 */
@Named("errorController")
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
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("error", error);
        this.error = error;
    }
}
