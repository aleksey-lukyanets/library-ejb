package libraryejb.dao.impl;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;
import libraryejb.dao.BookDAO;
import libraryejb.domain.Author;
import libraryejb.domain.Book;

/**
 * Реализация ДАО книги.
 */
@Stateless
public class BookDAOImpl implements BookDAO {

    @PersistenceContext(unitName = "library")
    private EntityManager em;

    @Override
    public List<Book> getAll() {
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Criteria criteria = hem.getSession()
                .createCriteria(Book.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .addOrder(Property.forName("title").asc());
        return criteria.list();
    }

    @Override
    public List<Book> getByAuthor(Author author) {
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Criteria criteria = hem.getSession().createCriteria(Book.class);
        criteria.add(Restrictions.eq("author", author));
        return criteria.list();
    }

    @Override
    public Book getById(long bookId) {
        return em.find(Book.class, bookId);
    }

    @Override
    public Book getByTitle(String title) {
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Query query = hem.getSession().createQuery("from Book where title = :title");
        query.setParameter("title", title);
        return (Book) query.uniqueResult();
    }

    @Override
    public Book insert(Book book) {
        em.persist(book);
        return book;
    }

    @Override
    public Book remove(long bookId) {
        Book book = getById(bookId);
        em.remove(book);
        return book;
    }
}
