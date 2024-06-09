package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.ui.gui.ControllerWithEmail;

import java.io.IOException;

/**
 * Controller class for the To-Do List GUI.
 * Manages actions related to the To-Do List, including adding and listing entries.
 */
public class ToDoListControllerGUI implements ControllerWithEmail {

    static String userEmail;

    @FXML
    private Label emailLabel;

    /**
     * Sets the user email and updates the email label.
     *
     * @param userEmail the email of the user
     */
    @Override
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        emailLabel.setText(userEmail);
    }

    /**
     * Handles the action for adding a new to-do list entry.
     *
     * @param event the action event
     */
    @FXML
    private void handleAddToDoListEntry(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddToDoListEntry.fxml"));
            Parent root = loader.load();

            AddToDoListEntryControllerGUI controller = loader.getController();
            controller.setUserEmail(userEmail);
            controller.setToDoListController(new ToDoListController());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Add New To-Do List Entry");
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the Add New To-Do List Entry form.");
        }
    }

    /**
     * Handles the action for listing to-do list entries.
     *
     * @param event the action event
     */
    @FXML
    private void handleListToDoEntries(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListToDoEntries.fxml"));
            Parent root = loader.load();

            ListToDoListEntriesControllerGUI controller = loader.getController();
            controller.setUserEmail(userEmail);


            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("List To-Do List Entries");
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the To-Do List Entries form.");
        }
    }

    /**
     * Shows an alert with the given alert type, title, and message.
     *
     * @param alertType the type of alert
     * @param title     the title of the alert
     * @param message   the message of the alert
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
