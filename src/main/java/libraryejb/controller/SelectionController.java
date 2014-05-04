package libraryejb.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Контроллер состояния списков.
 * Хранит в сессии индентификаторы выбранных в текущий момент автора и книги.
 */
@Named
@SessionScoped
public class SelectionController implements Serializable {
    
    private long selectedAuthorId = 0;
    private long selectedBookId = 0;
    
    public void clearAuthorSelected() {
        setSelectedAuthorId(0);
        setSelectedBookId(0);
    }
    
    public void clearBookSelected() {
        setSelectedBookId(0);
    }
    
    //-------------------------------------------- Аксессоры/мутаторы для полей
    
    public long getSelectedAuthorId() {
        return selectedAuthorId;
    }

    public void setSelectedAuthorId(long selectedAuthorId) {
        this.selectedAuthorId = selectedAuthorId;
        clearBookSelected();
    }

    public long getSelectedBookId() {
        return selectedBookId;
    }

    public void setSelectedBookId(long selectedBookId) {
        this.selectedBookId = selectedBookId;
    }
}
