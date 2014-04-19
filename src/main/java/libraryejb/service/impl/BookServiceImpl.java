package libraryejb.service.impl;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import libraryejb.dao.BookDAO;
import libraryejb.domain.Author;
import libraryejb.domain.Book;
import libraryejb.domain.dto.BookDTO;
import libraryejb.exception.AuthorNotFoundException;
import libraryejb.exception.BookNotFoundException;
import libraryejb.exception.DuplicateException;
import libraryejb.exception.ValidationException;
import libraryejb.service.AuthorService;
import libraryejb.service.BookService;

@Stateless
public class BookServiceImpl implements BookService {

    @EJB
    private BookDAO bookDAO;
    @EJB
    private AuthorService authorService;

    //------------------------------------------------------------------ Чтение

    @Override
    public List<Book> getAll() {
        return bookDAO.getAll();
    }

    @Override
    public List<Book> getByAuthor(long authorId) throws AuthorNotFoundException {
        Author author = authorService.getById(authorId);
        return bookDAO.getByAuthor(author);
    }

    @Override
    public Book getById(long bookId) throws BookNotFoundException {
        Book book = bookDAO.getById(bookId);
        if (book == null) {
            throw new BookNotFoundException("Не существует книги с id: " + bookId);
        }
        return book;
    }
  
    @Override
    public Book getByTitle(String title) throws BookNotFoundException {
        Book book = bookDAO.getByTitle(title);
        if (book == null) {
            throw new BookNotFoundException("Не существует книги с названием: " + title);
        }
        return book;
    }
    
    //--------------------------------------------------------------- Изменение
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Book insert(BookDTO dto) throws DuplicateException, AuthorNotFoundException, ValidationException {
        String title = dto.getTitle();
        if (title.isEmpty()) {
            throw new ValidationException("Название книги не может быть пустым");
        }
        Author author = authorService.getByName(dto.getAuthor());
        checkIfBookDuplicates(title, author);
        Book book = new Book(author, title);
        return bookDAO.insert(book);
    }
    
    private void checkIfBookDuplicates(String checkedTitle, Author author) throws DuplicateException {
        List<Book> authoredBooks = bookDAO.getByAuthor(author);
        for (Book book : authoredBooks) {
            if (book.getTitle().equals(checkedTitle)) {
                throw new DuplicateException("У автора " + author.getName() + " уже существует книга: " + checkedTitle);
            }
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Book remove(long bookId) throws BookNotFoundException {
        getById(bookId);
        return bookDAO.remove(bookId);
    }
}
