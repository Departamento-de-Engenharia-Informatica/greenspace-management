package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class GsmUIMenuController {

    @FXML
    private void handleRegisterGreenSpace(ActionEvent event) {
        showAlert("Register Green Space");
        // Add your logic to show the Register Green Space UI
    }

    @FXML
    private void handleListGreenSpaces(ActionEvent event) {
        showAlert("List all Green Spaces");
        // Add your logic to show the List Green Spaces UI
    }

    @FXML
    private void handleToDoList(ActionEvent event) {
        showAlert("To-Do List");
        // Add your logic to show the To-Do List UI
    }

    @FXML
    private void handleAgenda(ActionEvent event) {
        showAlert("Agenda");
        // Add your logic to show the Agenda UI
    }

    @FXML
    private void handleListAgendaEntries(ActionEvent event) {
        showAlert("List Agenda Entries");
        // Add your logic to show the List Agenda Entries UI
    }

    @FXML
    private void handleChangeAgendaStatus(ActionEvent event) {
        showAlert("Change Agenda Status");
        // Add your logic to show the Change Agenda Status UI
    }

    @FXML
    private void handleAssignVehicle(ActionEvent event) {
        showAlert("Assign Vehicle to Agenda Entry");
        // Add your logic to show the Assign Vehicle to Agenda Entry UI
    }

    @FXML
    private void handleAssignTeam(ActionEvent event) {
        showAlert("Assign Team to Agenda Entry");
        // Add your logic to show the Assign Team to Agenda Entry UI
    }

    private void showAlert(String title) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(title + " button clicked!");
        alert.showAndWait();
    }
}
