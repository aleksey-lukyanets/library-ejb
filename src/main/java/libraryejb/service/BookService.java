package libraryejb.service;

import java.util.List;
import javax.ejb.Local;
import libraryejb.domain.Book;
import libraryejb.domain.dto.BookDTO;
import libraryejb.exception.AuthorNotFoundException;
import libraryejb.exception.BookNotFoundException;
import libraryejb.exception.DuplicateException;
import libraryejb.exception.ValidationException;

/**
 * Сервис управления книгами.
 */
@Local
public interface BookService {

    List<Book> getAll();
    
    List<Book> getByAuthor(long authorId) throws AuthorNotFoundException;

    Book getById(long bookId) throws BookNotFoundException;
    
    Book getByTitle(String title) throws BookNotFoundException;
    
    Book insert(BookDTO book) throws DuplicateException, AuthorNotFoundException, ValidationException;

    Book remove(long bookId) throws BookNotFoundException;
}
