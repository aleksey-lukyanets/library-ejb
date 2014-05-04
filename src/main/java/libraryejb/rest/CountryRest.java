package libraryejb.rest;

import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import libraryejb.domain.Country;
import libraryejb.exception.UnknownCountryException;
import libraryejb.service.CountryService;

/**
 * REST веб-сервис стран.
 */
@Path("/countries")
@Stateless
public class CountryRest {

    @EJB
    private CountryService countryService;
    
    //------------------------------------------------------------------ Чтение

    /**
     * @return перечень всех стран
     */
    @GET
    @Path("/")
    public Collection<Country> getCountries() {
        return countryService.getAll();
    }
    
    /**
     * Получение одной страны.
     * @param id идентификатор страны
     * @return объект страны
     */
    @GET
    @Path("/{id}")
    public Response getCountry(@PathParam("id") int id) {
        Response.ResponseBuilder response;
        try {
            Country country = countryService.getById(id);
            response = Response.status(Response.Status.OK);
            response.entity(country);
        } catch (UnknownCountryException ex) {
            response = Response.status(Response.Status.NOT_FOUND);
        }
        return response.build();
    }
}
