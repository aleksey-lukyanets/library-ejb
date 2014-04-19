package libraryejb.service;

import java.util.List;
import javax.ejb.Local;
import libraryejb.domain.Author;
import libraryejb.domain.dto.AuthorDTO;
import libraryejb.exception.AuthorNotFoundException;
import libraryejb.exception.DuplicateException;
import libraryejb.exception.UnknownCountryException;
import libraryejb.exception.ValidationException;

/**
 * Сервис управления авторами.
 */
@Local
public interface AuthorService {

    List<Author> getAll();

    Author getById(long authorId) throws AuthorNotFoundException;
    
    Author getByName(String name) throws AuthorNotFoundException;
    
    Author insert(AuthorDTO author) throws DuplicateException, UnknownCountryException, ValidationException;

    Author remove(long authorId) throws AuthorNotFoundException;
}
