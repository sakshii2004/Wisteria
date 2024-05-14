package journal;


import base.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ViewJournalEntryController extends SceneController {

    @FXML
    private Label entryDate;

    @FXML
    private Label entrySubject;

    @FXML
    private Label entryText;

    public JournalEntry selectedJournalEntry;    
    
    
    /*public ViewJournalEntryController() {        
    }*/

    public void initialize(JournalEntry selectedJournalEntry) {
    	entryText.setWrapText(true);
    }
    
    public void setJournalEntry(JournalEntry selectedJournalEntry) {
        // update the UI get the selected journal entry
        if (selectedJournalEntry != null) {
            entrySubject.setText(selectedJournalEntry.getSubject());
            entryDate.setText(selectedJournalEntry.getDate().toString());
            entryText.setText(selectedJournalEntry.getEntry());          
        }
    }
    
}

