package libraryejb.controller;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import libraryejb.domain.Country;
import libraryejb.service.CountryService;

/**
 * Контроллер стран.
 */
@Named("countriesController")
@Stateless
public class CountriesController {
    
    @EJB
    private CountryService countryService;
    
    public List<Country> getCountriesList() {
        return countryService.getAll();
    }
}
