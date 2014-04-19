package libraryejb.service.impl;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import libraryejb.dao.CountryDAO;
import libraryejb.domain.Country;
import libraryejb.exception.UnknownCountryException;
import libraryejb.service.CountryService;

@Stateless
public class CountryServiceImpl implements CountryService {

    @EJB
    private CountryDAO countryDAO;

    @Override
    public List<Country> getAll() {
        return countryDAO.getAll();
    }

    @Override
    public Country getById(long countryId) throws UnknownCountryException {
        Country country = countryDAO.getById(countryId);
        if (country == null) {
            throw new UnknownCountryException("Не существует страны с id: " + countryId);
        }
        return country;
    }

    @Override
    public Country getByTitle(String title) throws UnknownCountryException {
        Country country = countryDAO.getByTitle(title);
        if (country == null) {
            throw new UnknownCountryException("Не существует страны с названием: " + title);
        }
        return country;
    }
}
