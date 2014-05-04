package libraryejb.service.impl;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import libraryejb.dao.AuthorDAO;
import libraryejb.domain.Author;
import libraryejb.domain.Country;
import libraryejb.domain.dto.AuthorDTO;
import libraryejb.exception.AuthorNotFoundException;
import libraryejb.exception.DuplicateException;
import libraryejb.exception.UnknownCountryException;
import libraryejb.exception.ValidationException;
import libraryejb.service.AuthorService;
import libraryejb.service.CountryService;

@Stateless
public class AuthorServiceImpl implements AuthorService {

    @EJB
    private CountryService countryService;
    @Inject
    private AuthorDAO authorDAO;

    //------------------------------------------------------------------ Чтение
    
    @Override
    public List<Author> getAll() {
        return authorDAO.getAll();
    }

    @Override
    public Author getById(long authorId) throws AuthorNotFoundException {
        Author author = authorDAO.getById(authorId);
        if (author == null) {
            throw new AuthorNotFoundException("Не существует автор с id: " + authorId);
        }
        return author;
    }

    @Override
    public Author getByName(String name) throws AuthorNotFoundException {
        Author author = authorDAO.getByName(name);
        if (author == null) {
            throw new AuthorNotFoundException("Не существует автор с именем: " + name);
        }
        return author;
    }

    //--------------------------------------------------------------- Изменение
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Author insert(AuthorDTO dto) throws DuplicateException, UnknownCountryException, ValidationException {
        String name = dto.getName();
        Country country = countryService.getByTitle(dto.getCountry());
        return createAuthor(name, country);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Author insert(long countryId, String name) throws DuplicateException, UnknownCountryException, ValidationException {
        Country country = countryService.getById(countryId);
        return createAuthor(name, country);
    }

    private Author createAuthor(String authorName, Country country) throws DuplicateException, ValidationException {
        if (authorName.isEmpty()) {
            throw new ValidationException("Имя автора не может быть пустым");
        }
        checkDuplicated(authorName);
        Author author = new Author(country, authorName);
        return authorDAO.insert(author);
    }
    
    private void checkDuplicated(String name) throws DuplicateException {
        if (authorDAO.getByName(name) != null) {
            throw new DuplicateException("Уже существует автор с именем: " + name);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Author remove(long authorId) throws AuthorNotFoundException {
        getById(authorId);
        return authorDAO.remove(authorId);
    }
}
