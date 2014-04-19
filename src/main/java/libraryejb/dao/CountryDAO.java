package libraryejb.dao;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import libraryejb.domain.Country;

/**
 * ДАО страны.
 */
@Remote
public interface CountryDAO {

    void insert(Country country);

    Country getById(long countryId);

    Country getByTitle(String title);

    List<Country> getAll();
}
