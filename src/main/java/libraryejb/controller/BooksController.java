package libraryejb.controller;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import libraryejb.domain.Book;
import libraryejb.exception.AuthorNotFoundException;
import libraryejb.exception.BookNotFoundException;
import libraryejb.exception.DuplicateException;
import libraryejb.exception.ValidationException;
import libraryejb.service.BookService;

/**
 * Контроллер книг.
 */
@Named
@RequestScoped
public class BooksController {
    
    @EJB
    private BookService bookService;
    
    @Inject
    private SelectionController selectionController;
    
    @Inject
    private ErrorController errorController;
    
    private long bookAuthorId = 0;
    private String newBookTitle = "";
    
    //-------------------------------------------------------------------------
    
    /**
     * Возвращает перечень книг.
     * Если автор выбран, возвращает книги этого автора, иначе - все имеющиеся книги.
     * @return перечень книг
     */
    public List<Book> getBooksList() {
        long selectedAuthorId = selectionController.getSelectedAuthorId();
        
        // Если автор не выбран - вернуть перечень всех существующих книг
        if (selectedAuthorId == 0) {
            return bookService.getAll();
        }
        // Вернуть перечень книг выбранного автора
        try {
            return bookService.getByAuthor(selectedAuthorId);
        } catch (AuthorNotFoundException ex) {
            errorController.setError(ex.getMessage());
            return new ArrayList();
        }
    }
    
    /**
     * Добавление новой книги.
     */
    public void addBook() {
        try {
            bookService.insert(bookAuthorId, newBookTitle);
            refreshSelectionState();
        } catch (AuthorNotFoundException | DuplicateException | ValidationException ex) {
            errorController.setError(ex.getMessage());
        }
    }

    private void refreshSelectionState() {
        selectionController.clearBookSelected();
        errorController.clearError();
    }
    
    /**
     * Удаление выбранной книги.
     */
    public void removeBook() {
        long selectedBookId = selectionController.getSelectedBookId();
        // Если ни один автор не выбран
        if (selectedBookId == 0) {
            return;
        }
        // Удаление книги
        try {
            bookService.remove(selectedBookId);
            refreshSelectionState();
        } catch (BookNotFoundException ex) {
            errorController.setError(ex.getMessage());
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
