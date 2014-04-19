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

    List<Country> getAll();

    Country getById(long countryId) throws UnknownCountryException;

    Country getByTitle(String title) throws UnknownCountryException;
}
