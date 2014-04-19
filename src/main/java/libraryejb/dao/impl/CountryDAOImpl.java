package libraryejb.dao.impl;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;
import libraryejb.dao.CountryDAO;
import libraryejb.domain.Country;

/**
 * Реализация ДАО страны.
 */
@Stateless
public class CountryDAOImpl implements CountryDAO {

    @PersistenceContext(unitName = "library")
    private EntityManager em;

    @Override
    public void insert(Country country) {
        em.persist(country);
    }

    @Override
    public Country getById(long countryId) {
        return em.find(Country.class, countryId);
    }

    @Override
    public Country getByTitle(String title) {
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Criteria criteria = hem.getSession().createCriteria(Country.class);
        criteria.add(Restrictions.eq("title", title));
        return (Country) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Country> getAll() {
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Criteria criteria = hem.getSession()
                .createCriteria(Country.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .addOrder(Property.forName("title").asc());
        return criteria.list();
    }
}
