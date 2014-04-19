package libraryejb.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import libraryejb.dao.SelectionDAO;
import libraryejb.domain.Selection;
import libraryejb.service.SelectionService;

/**
 * Реализация сервиса состояния.
 */
@Stateless
public class SelectionServiceImpl implements SelectionService {

    @EJB
    private SelectionDAO selectionDAO;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateAuthor(Long authorId) {
        selectionDAO.updateAuthor(authorId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateBook(Long bookId) {
        selectionDAO.updateBook(bookId);
    }

    @Override
    public Selection get() {
        return selectionDAO.get();
    }
}
