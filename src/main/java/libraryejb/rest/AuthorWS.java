package libraryejb.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;
import libraryejb.domain.Author;
import libraryejb.domain.Book;
import libraryejb.domain.dto.AuthorDTO;
import libraryejb.domain.dto.BookDTO;
import libraryejb.exception.AuthorNotFoundException;
import libraryejb.exception.DuplicateException;
import libraryejb.exception.UnknownCountryException;
import libraryejb.exception.ValidationException;
import libraryejb.service.AuthorService;
import libraryejb.service.BookService;

/**
 *
 */
@Path("/authors")
@Stateless
@LocalBean
public class AuthorWS {

    @EJB
    private AuthorService authorService;

    @EJB
    private BookService bookService;
    
    @Context
    UriInfo rootUri;

    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<AuthorDTO> getAuthors() {
        return createAuthorDtoList(authorService.getAll());
    }

    private List<AuthorDTO> createAuthorDtoList(List<Author> authors) {
        List<AuthorDTO> dtos = new ArrayList<>();
        for (Author author : authors) {
            dtos.add(new AuthorDTO(author));
        }
        return dtos;
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAuthor(@PathParam("id") long id) {
        ResponseBuilder response;
        try {
            AuthorDTO dto = new AuthorDTO(authorService.getById(id));
            response = Response.status(Response.Status.OK);
            response.entity(dto);
        } catch (AuthorNotFoundException ex) {
            response = Response.status(Response.Status.NOT_FOUND);
        }
        return response.build();
    }
    
    @GET
    @Path("/{authorId}/books")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAuthoredBooks(@PathParam("authorId") long authorId) {
        Response.ResponseBuilder response;
        try {
            List<BookDTO> list = createBookDtoList(bookService.getByAuthor(authorId));
            GenericEntity<List<BookDTO>> dtos = new GenericEntity<List<BookDTO>>(list){};
            response = Response.status(Response.Status.OK);
            response.entity(dtos);
        } catch (AuthorNotFoundException ex) {
            response = Response.status(Response.Status.NOT_FOUND);
        }
        return response.build();
    }

    private List<BookDTO> createBookDtoList(List<Book> books) {
        List<BookDTO> dtos = new ArrayList<>();
        for (Book book : books) {
            dtos.add(new BookDTO(book));
        }
        return dtos;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createAuthor(AuthorDTO author) {
        ResponseBuilder response;
        try {
            AuthorDTO dto = new AuthorDTO(authorService.insert(author));
            URI location = URI.create(rootUri.getPath() + "/" + dto.getId());
            response = Response.status(Response.Status.CREATED);
            response.location(location);
            response.entity(dto);
        } catch (UnknownCountryException | ValidationException ex) {
            response = Response.status(Response.Status.BAD_REQUEST);
        } catch (DuplicateException ex) {
            response = Response.status(Response.Status.NOT_ACCEPTABLE);
        }
        return response.build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") long id) {
        ResponseBuilder response;
        try {
            AuthorDTO dto = new AuthorDTO(authorService.remove(id));
            response = Response.status(Response.Status.OK);
            response.entity(dto);
        } catch (AuthorNotFoundException ex) {
            response = Response.status(Response.Status.NOT_FOUND);
        }
        return response.build();
    }
}
