package libraryejb.dao.impl;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import libraryejb.dao.SelectionDAO;
import libraryejb.domain.Selection;

/**
 * Реализация ДАО состояния.
 */
public class SelectionDAOImpl implements SelectionDAO {

    @PersistenceContext(unitName = "library")
    private EntityManager em;

    //------------------------------------------------------------------ Чтение

    @Override
    public Selection get() {
        return em.find(Selection.class, Long.valueOf(1));
    }

    //--------------------------------------------------------------- Изменение

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateAuthor(Long authorId) {
        Selection s = get();
        s.setAuthorId(authorId);
        em.merge(s);
        em.flush();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateBook(Long bookId) {
        Selection s = get();
        s.setBookId(bookId);
        em.merge(s);
        em.flush();
    }
}
