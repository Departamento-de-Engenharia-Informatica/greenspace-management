package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.GsmUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ControllerWithEmail;
import pt.ipp.isep.dei.esoft.project.ui.gui.GsmUIApplication;

import java.io.IOException;

public class GsmUIMenuController implements ControllerWithEmail {


    @FXML
    private Label emailLabel;

    private String userEmail;


    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        // Set the email label text
        emailLabel.setText(userEmail);

    }

    @FXML
    private void handleRegisterGreenSpace(ActionEvent event) {
        try {
            // Load the FXML file for the Register Green Space form
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterGreenSpace.fxml"));

            Parent root = loader.load();

            RegisterGreenSpaceControllerGUI controller = loader.getController();
            controller.setUserEmail(userEmail);

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
            e.printStackTrace();
        }

    }



    @FXML
    private void handleListGreenSpaces(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListGreenSpaces.fxml"));
            Parent root = loader.load();

            ListGreenSpacesControllerGUI controller = loader.getController();
            controller.setUserEmail(userEmail);


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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AssignVehicleToAgendaEntry.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Assign Vehicle to Agenda Entry");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
