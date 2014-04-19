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
 *
 */
@Path("/countries")
@Stateless
@LocalBean
public class CountryWS {

    @EJB
    private CountryService countryService;

    @GET
    @Path("/")
    public Collection<Country> getCountries() {
        return countryService.getAll();
    }
    
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
