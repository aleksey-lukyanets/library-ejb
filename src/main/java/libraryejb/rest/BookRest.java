package libraryejb.rest;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import libraryejb.domain.Book;
import libraryejb.domain.dto.BookDTO;
import libraryejb.exception.AuthorNotFoundException;
import libraryejb.exception.BookNotFoundException;
import libraryejb.exception.DuplicateException;
import libraryejb.exception.ValidationException;
import libraryejb.service.BookService;

/**
 * REST веб-сервис книг.
 */
@Path("/books")
@Stateless
@LocalBean
public class BookRest {

    @EJB
    private BookService bookService;
    
    //------------------------------------------------------------------ Чтение

    /**
     * @return перечень всех книг
     */
    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<BookDTO> getBooks() {
        return createBookDtoList(bookService.getAll());
    }

    private List<BookDTO> createBookDtoList(List<Book> books) {
        List<BookDTO> dtos = new ArrayList<>();
        for (Book book : books) {
            dtos.add(new BookDTO(book));
        }
        return dtos;
    }
    
    /**
     * Получение одной книги.
     * @param id идентификатор книги
     * @return объект книги
     */
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getBook(@PathParam("id") long id) {
        Response.ResponseBuilder response;
        try {
            BookDTO dto = new BookDTO(bookService.getById(id));
            response = Response.status(Response.Status.OK);
            response.entity(dto);
        } catch (BookNotFoundException ex) {
            response = Response.status(Response.Status.NOT_FOUND);
        }
        return response.build();
    }
    
    //--------------------------------------------------------------- Изменение
    
    /**
     * Добавление новой книги.
     * @param book данные о новой книге
     * @return добавленная книга
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createBook(BookDTO book) {
        Response.ResponseBuilder response;
        try {
            BookDTO dto = new BookDTO(bookService.insert(book));
            response = Response.status(Response.Status.CREATED);
            response.entity(dto);
        } catch (AuthorNotFoundException | ValidationException ex) {
            response = Response.status(Response.Status.BAD_REQUEST);
        } catch (DuplicateException ex) {
            response = Response.status(Response.Status.NOT_ACCEPTABLE);
        }
        return response.build();
    }
    
    /**
     * Удаление книги.
     * @param id идентификатор книги
     * @return удалённая книга
     */
    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") long id) {
        Response.ResponseBuilder response;
        try {
            BookDTO dto = new BookDTO(bookService.remove(id));
            response = Response.status(Response.Status.OK);
            response.entity(dto);
        } catch (BookNotFoundException ex) {
            response = Response.status(Response.Status.NOT_FOUND);
        }
        return response.build();
    }
}
