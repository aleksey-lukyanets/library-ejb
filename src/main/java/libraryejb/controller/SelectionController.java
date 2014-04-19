package libraryejb.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import libraryejb.service.SelectionService;

/**
 * 
 */
@Named("selectionController")
@Stateless
public class SelectionController {
    
    @EJB
    private SelectionService selectionService;
    
    public void clearAuthorSelected() {
        setSelectedAuthorId(0);
        setSelectedBookId(0);
    }
    
    public void clearBookSelected() {
        setSelectedBookId(0);
    }
    
    //-------------------------------------------- Аксессоры/мутаторы для полей
    
    public long getSelectedAuthorId() {
        return selectionService.get().getAuthorId();
    }

    public void setSelectedAuthorId(long selectedAuthorId) {
        selectionService.updateAuthor(selectedAuthorId);
        clearBookSelected();
    }

    public long getSelectedBookId() {
        return selectionService.get().getBookId();
    }

    public void setSelectedBookId(long selectedBookId) {
        selectionService.updateBook(selectedBookId);
    }
}
