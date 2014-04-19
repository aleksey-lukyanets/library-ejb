package libraryejb.controller;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import libraryejb.domain.Author;
import libraryejb.domain.Book;
import libraryejb.domain.dto.BookDTO;
import libraryejb.exception.AuthorNotFoundException;
import libraryejb.exception.BookNotFoundException;
import libraryejb.exception.DuplicateException;
import libraryejb.exception.ValidationException;
import libraryejb.service.AuthorService;
import libraryejb.service.BookService;

/**
 * Контроллер книг.
 */
@Named("booksController")
@Stateless
public class BooksController {
    
    @EJB
    private BookService bookService;
    @EJB
    private AuthorService authorService;
    @Inject
    private SelectionController selectionController;
    @Inject
    private ErrorController errorController;
    
    private long bookAuthorId = 0;
    private String newBookTitle = "";
    
    //---------------------------------------------- Методы добавления/удаления
    
    public List<Book> getBooksList() {
        long selectedAuthorId = selectionController.getSelectedAuthorId();
        if (selectedAuthorId != 0) {
            try {
                return bookService.getByAuthor(selectedAuthorId);
            } catch (AuthorNotFoundException ex) {
                errorController.setError(ex.getMessage());
                return new ArrayList();
            }
        }
        return bookService.getAll();
    }
    
    public void addBook() {
        try {
            Author author = authorService.getById(bookAuthorId);
            Book book = new Book(author, newBookTitle);
            bookService.insert(new BookDTO(book));
            selectionController.clearBookSelected();
            errorController.clearError();
        } catch (AuthorNotFoundException | DuplicateException | ValidationException ex) {
            errorController.setError(ex.getMessage());
        }
    }
    
    public void removeBook() {
        long selectedBook = selectionController.getSelectedBookId();
        if (selectedBook != 0) {
            try {
                bookService.remove(selectedBook);
                selectionController.clearBookSelected();
                errorController.clearError();
            } catch (BookNotFoundException ex) {
                errorController.setError(ex.getMessage());
            }
        }
    }
    
    //-------------------------------------------- Аксессоры/мутаторы для полей

    public long getBookAuthorId() {
        return bookAuthorId;
    }

    public void setBookAuthorId(long bookAuthorId) {
        this.bookAuthorId = bookAuthorId;
    }

    public String getNewBookTitle() {
        return newBookTitle;
    }

    public void setNewBookTitle(String newBookTitle) {
        this.newBookTitle = newBookTitle;
    }
}
