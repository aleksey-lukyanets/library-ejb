package libraryejb.dao;

import java.util.List;
import javax.ejb.Remote;
import libraryejb.domain.Author;
import libraryejb.domain.Book;

/**
 * ДАО книги.
 */
@Remote
public interface BookDAO {

    List<Book> getAll();
    
    List<Book> getByAuthor(Author author);

    Book getById(long bookId);

    Book getByTitle(String title);

    Book insert(Book book);

    Book remove(long bookId);
}
