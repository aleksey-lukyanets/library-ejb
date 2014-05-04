package libraryejb.dao;

import java.util.List;
import javax.ejb.Local;
import libraryejb.domain.Country;

/**
 * ДАО страны.
 */
@Local
public interface CountryDAO {

    Country getById(long countryId);

    Country getByTitle(String title);

    List<Country> getAll();
}
