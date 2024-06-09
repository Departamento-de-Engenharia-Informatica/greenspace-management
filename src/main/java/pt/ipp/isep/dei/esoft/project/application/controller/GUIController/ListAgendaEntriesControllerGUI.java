package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.ui.gui.ControllerWithEmail;
import pt.ipp.isep.dei.esoft.project.ui.gui.SceneSwitcher;

import java.io.IOException;
import java.util.List;

public class ListAgendaEntriesControllerGUI  implements ControllerWithEmail {

    private String userEmail;
    @Override
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

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
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    public void handleBack(javafx.event.ActionEvent actionEvent) {

        try {
            SceneSwitcher.switchToScene("/fxml/GsmUIMenu.fxml", "Register Green Space", (Node) actionEvent.getSource(), userEmail);

        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the Main Menu.");
        }
    }


}
