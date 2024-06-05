package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

public class GsmUIMenuController {

    @FXML
    private void handleRegisterGreenSpace(ActionEvent event) {
        try {
            // Load the FXML file for the Register Green Space form
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterGreenSpace.fxml"));
            Parent root = loader.load();

            // Get the current stage (window)
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene to the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Register Green Space");

            // Show the new stage
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the Register Green Space form.");
        }

    }



    @FXML
    private void handleListGreenSpaces(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListGreenSpaces.fxml"));
            Parent root = loader.load();

            ListGreenSpacesControllerGUI controller = loader.getController();
            controller.setUserEmail("tugahdchester@gmail.com");

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("List Green Spaces");
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the List Green Spaces form.");
        }
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
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
