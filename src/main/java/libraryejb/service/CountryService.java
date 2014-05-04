package libraryejb.service;

import java.util.List;
import javax.ejb.Local;
import libraryejb.domain.Country;
import libraryejb.exception.UnknownCountryException;

/**
 * Сервис управления странами.
 */
@Local
public interface CountryService {

    /**
     * @return перечень всех стран
     */
    List<Country> getAll();

    /**
     * Получение страны по идентификатору.
     * 
     * @param countryId идентификатор страны
     * @return страна
     * @throws UnknownCountryException если страна с указанным id не найдена
     */
    Country getById(long countryId) throws UnknownCountryException;

    /**
     * Получение страны по названию.
     * 
     * @param title название страны
     * @return страна
     * @throws UnknownCountryException если страна с указанным названием не найдена
     */
    Country getByTitle(String title) throws UnknownCountryException;
}
