package libraryejb.controller;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import libraryejb.domain.Country;
import libraryejb.service.CountryService;

/**
 * Контроллер стран.
 */
@Named
@RequestScoped
public class CountriesController {
    
    @EJB
    private CountryService countryService;
    
    /**
     * @return перечень всех стран
     */
    public List<Country> getCountriesList() {
        return countryService.getAll();
    }
}
