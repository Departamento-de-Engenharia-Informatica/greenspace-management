package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class ChangeStatusAgendaGUI {

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Button canceledButton;

    @FXML
    private Button postponedButton;

    public void initialize() {
        comboBox.getItems().addAll("Task 1", "Task 2", "Task 3"); // Example items, replace with actual task descriptions
    }

    @FXML
    private void handleCanceledStatus(ActionEvent event) {
        String selectedTask = comboBox.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            // Perform the action to change the status to Canceled
            System.out.println("Changing status of " + selectedTask + " to Canceled.");
            // Add actual logic to change the status in your model/controller here
            showAlert(Alert.AlertType.INFORMATION, "Status Changed", "Status of " + selectedTask + " changed to Canceled.");
        } else {
            showAlert(Alert.AlertType.WARNING, "No Task Selected", "Please select a task to change its status.");
        }
    }

    @FXML
    private void handlePostponedStatus(ActionEvent event) {
        String selectedTask = comboBox.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            // Perform the action to change the status to Postponed
            System.out.println("Changing status of " + selectedTask + " to Postponed.");
            // Add actual logic to change the status in your model/controller here
            showAlert(Alert.AlertType.INFORMATION, "Status Changed", "Status of " + selectedTask + " changed to Postponed.");
        } else {
            showAlert(Alert.AlertType.WARNING, "No Task Selected", "Please select a task to change its status.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
