package libraryejb.dao.impl;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;
import libraryejb.dao.AuthorDAO;
import libraryejb.domain.Author;

/**
 * Реализация ДАО автора.
 */
@Stateless
public class AuthorDAOImpl implements AuthorDAO {

    @PersistenceContext(unitName = "library")
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public List<Author> getAll() {
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Criteria criteria = hem.getSession()
                .createCriteria(Author.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .addOrder(Property.forName("name").asc());
        return criteria.list();
    }

    @Override
    public Author getById(long authorId) {
        return em.find(Author.class, authorId);
    }

    @Override
    public Author getByName(String name) {
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Criteria criteria = hem.getSession().createCriteria(Author.class);
        criteria.add(Restrictions.eq("name", name));
        return (Author)criteria.uniqueResult();
    }

    @Override
    public Author insert(Author author) {
        em.persist(author);
        return author;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Author remove(long authorId) {
        Author author = getById(authorId);
        em.remove(author);
        return author;
    }
}
