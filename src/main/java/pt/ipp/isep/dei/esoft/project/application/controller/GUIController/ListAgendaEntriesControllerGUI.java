package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.ui.gui.ControllerWithEmail;

import java.util.List;

public class ListAgendaEntriesControllerGUI  {

    @FXML
    private ListView<Agenda> agendaListView;

    public void initialize() {
        System.out.println("--- All Agenda Entries ---");
        List<Agenda> agendaEntries = AgendaController.getAllAgendaEntries();
        if (agendaEntries.isEmpty()) {

        } else {
            ObservableList<Agenda> items = FXCollections.observableArrayList(agendaEntries);
            agendaListView.setItems(items);
        }
    }


}
