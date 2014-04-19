package libraryejb.controller;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import libraryejb.domain.Author;
import libraryejb.domain.Country;
import libraryejb.domain.dto.AuthorDTO;
import libraryejb.exception.AuthorNotFoundException;
import libraryejb.exception.DuplicateException;
import libraryejb.exception.UnknownCountryException;
import libraryejb.exception.ValidationException;
import libraryejb.service.AuthorService;
import libraryejb.service.CountryService;

/**
 * Контроллер авторов.
 */
@Named("authorsController")
@Stateless
public class AuthorsController {
    
    @EJB
    private AuthorService authorService;
    @EJB
    private CountryService countryService;
    @Inject
    private SelectionController selectionController;
    @Inject
    private ErrorController errorController;
    
    private long authorCountryId = 0;
    private String newAuthorName = "";
    
    public AuthorsController() {
    }
    
    //---------------------------------------------- Методы добавления/удаления

    public List<Author> getAuthorsList() {
        return authorService.getAll();
    }

    public void addAuthor() {
        try {
            Country country = countryService.getById(authorCountryId);
            Author author = new Author(country, newAuthorName);
            authorService.insert(new AuthorDTO(author));
            selectionController.clearAuthorSelected();
            errorController.clearError();
        } catch (UnknownCountryException | DuplicateException | ValidationException ex) {
            errorController.setError(ex.getMessage());
        }
    }
    
    public void removeAuthor() {
        long selectedAuthorId = selectionController.getSelectedAuthorId();
        if (selectedAuthorId != 0) {
            try {
                authorService.remove(selectedAuthorId);
                selectionController.clearAuthorSelected();
                errorController.clearError();
            } catch (AuthorNotFoundException ex) {
                errorController.setError(ex.getMessage());
            }
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
