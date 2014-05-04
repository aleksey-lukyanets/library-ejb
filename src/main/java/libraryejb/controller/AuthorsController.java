package libraryejb.controller;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import libraryejb.domain.Author;
import libraryejb.exception.AuthorNotFoundException;
import libraryejb.exception.DuplicateException;
import libraryejb.exception.UnknownCountryException;
import libraryejb.exception.ValidationException;
import libraryejb.service.AuthorService;

/**
 * Контроллер авторов.
 */
@Named
@RequestScoped
public class AuthorsController {
    
    @EJB
    private AuthorService authorService;
    
    @Inject
    private SelectionController selectionController;
    
    @Inject
    private ErrorController errorController;
    
    private long authorCountryId = 0;
    private String newAuthorName = "";
    
    //-------------------------------------------------------------------------

    /**
     * @return перечень всех авторов
     */
    public List<Author> getAuthorsList() {
        return authorService.getAll();
    }

    /**
     * Добавление нового автора.
     */
    public void addAuthor() {
        try {
            authorService.insert(authorCountryId, newAuthorName);
            refreshSelectionState();
        } catch (UnknownCountryException | DuplicateException | ValidationException ex) {
            errorController.setError(ex.getMessage());
        }
    }

    private void refreshSelectionState() {
        selectionController.clearAuthorSelected();
        errorController.clearError();
    }
    
    /**
     * Удаление выбранного автора.
     */
    public void removeAuthor() {
        long selectedAuthorId = selectionController.getSelectedAuthorId();
        
        // Если ни один автор не выбран
        if (selectedAuthorId == 0) {
            return;
        }
        // Удаление автора
        try {
            authorService.remove(selectedAuthorId);
            refreshSelectionState();
        } catch (AuthorNotFoundException ex) {
            errorController.setError(ex.getMessage());
        }
    }
    
    //-------------------------------------------- Аксессоры/мутаторы для полей

    public long getAuthorCountryId() {
        return authorCountryId;
    }

    public void setAuthorCountryId(long authorCountryId) {
        this.authorCountryId = authorCountryId;
    }

    public String getNewAuthorName() {
        return newAuthorName;
    }

    public void setNewAuthorName(String newAuthorName) {
        this.newAuthorName = newAuthorName;
    }
}
