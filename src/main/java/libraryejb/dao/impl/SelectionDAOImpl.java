package libraryejb.dao.impl;

import libraryejb.dao.SelectionDAO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import libraryejb.domain.Selection;

/**
 * Реализация ДАО состояния.
 */
@Stateless
public class SelectionDAOImpl implements SelectionDAO {

    @PersistenceContext(unitName = "library")
    private EntityManager em;

    @Override
    public void updateAuthor(Long authorId) {
        Selection s = get();
        s.setAuthorId(authorId);
        em.merge(s);
        em.flush();
    }

    @Override
    public void updateBook(Long bookId) {
        Selection s = get();
        s.setBookId(bookId);
        em.merge(s);
        em.flush();
    }

    @Override
    public Selection get() {
        return em.find(Selection.class, Long.valueOf(1));
    }
}
