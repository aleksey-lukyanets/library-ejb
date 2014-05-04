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

    //------------------------------------------------------------------ Чтение

    /**
     * @return перечень всех авторов
     */
    List<Book> getAll();
    
    /**
     * Получение всех книг одного автора.
     * 
     * @param authorId идентификатор автора
     * @return книги автора
     * @throws AuthorNotFoundException если автор с указанным id не найден
     */
    List<Book> getByAuthor(long authorId) throws AuthorNotFoundException;

    /**
     * Получение книги по идентификатору.
     * 
     * @param bookId идентификатор книги
     * @return книга
     * @throws BookNotFoundException если книга с указанным id не найдена
     */
    Book getById(long bookId) throws BookNotFoundException;
    
    /**
     * Получение книги по названию.
     * 
     * @param title название книги
     * @return книга
     * @throws BookNotFoundException если книга с указанным id не найдена
     */
    Book getByTitle(String title) throws BookNotFoundException;

    //--------------------------------------------------------------- Изменение
    
    /**
     * Добавление книги.
     * 
     * @param title название новой книги
     * @param authorId идентификатор автора
     * @return созданная книга
     * @throws DuplicateException если автор уже существует
     * @throws AuthorNotFoundException если запрошенного автора не существует
     * @throws ValidationException переданы некорректные данные книги
     */
    Book insert(long authorId, String title) throws DuplicateException, AuthorNotFoundException, ValidationException;
    
    /**
     * Добавление книги.
     * 
     * @param book данные новой книги
     * @return созданная книга
     * @throws DuplicateException если автор уже существует
     * @throws AuthorNotFoundException если запрошенного автора не существует
     * @throws ValidationException переданы некорректные данные книги
     */
    Book insert(BookDTO book) throws DuplicateException, AuthorNotFoundException, ValidationException;

    /**
     * Удаление книги.
     * 
     * @param authorId идентификатор книги
     * @return удалённая книга
     * @throws AuthorNotFoundException если книга с указанным именем не найдена
     */
    Book remove(long bookId) throws BookNotFoundException;
}
