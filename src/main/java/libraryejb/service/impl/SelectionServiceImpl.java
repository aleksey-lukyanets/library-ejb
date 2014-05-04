package libraryejb.service.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import libraryejb.dao.SelectionDAO;
import libraryejb.domain.Selection;
import libraryejb.service.SelectionService;

/**
 * Реализация сервиса состояния.
 */
@Stateless
public class SelectionServiceImpl implements SelectionService {

    @Inject
    private SelectionDAO selectionDAO;

    @Override
    public Selection get() {
        return selectionDAO.get();
    }

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
}
