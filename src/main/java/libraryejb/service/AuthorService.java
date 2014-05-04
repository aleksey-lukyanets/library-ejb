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

    //------------------------------------------------------------------ Чтение

    /**
     * @return перечень всех авторов
     */
    List<Author> getAll();

    /**
     * Получение автора по идентификатору.
     * 
     * @param authorId идентификатор автора
     * @return автор
     * @throws AuthorNotFoundException если автор с указанным id не найден
     */
    Author getById(long authorId) throws AuthorNotFoundException;
    
    /**
     * Получение автора по имени.
     * 
     * @param name имя автора
     * @return автор
     * @throws AuthorNotFoundException если автор с указанным именем не найден
     */
    Author getByName(String name) throws AuthorNotFoundException;

    //--------------------------------------------------------------- Изменение
    
    /**
     * Добавление автора.
     * 
     * @param name имя нового автора
     * @param countryId идентификатор страны
     * @return созданный автор
     * @throws DuplicateException если автор уже существует
     * @throws UnknownCountryException если запрошенной страны не существует
     * @throws ValidationException переданы некорректные данные автора
     */
    Author insert(long countryId, String name) throws DuplicateException, UnknownCountryException, ValidationException;
    
    /**
     * Добавление автора.
     * 
     * @param author данные нового автора
     * @return созданный автор
     * @throws DuplicateException если автор уже существует
     * @throws UnknownCountryException если запрошенной страны не существует
     * @throws ValidationException переданы некорректные данные автора
     */
    Author insert(AuthorDTO author) throws DuplicateException, UnknownCountryException, ValidationException;

    /**
     * Удаление автора.
     * 
     * @param authorId идентификатор автора
     * @return удалённый автор
     * @throws AuthorNotFoundException если автор с указанным именем не найден
     */
    Author remove(long authorId) throws AuthorNotFoundException;
}
