package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.ui.gui.ControllerWithEmail;
import pt.ipp.isep.dei.esoft.project.ui.gui.GsmUIApplication;
import pt.ipp.isep.dei.esoft.project.ui.gui.SceneSwitcher;

import java.io.IOException;
import java.util.List;

/**
 * The AddToDoListEntryControllerGUI class represents the controller for adding a to-do list entry in the GUI.
 */
public class AddToDoListEntryControllerGUI implements ControllerWithEmail {

    @FXML
    private TextField taskDescriptionField;

    @FXML
    private ChoiceBox<String> urgencyChoiceBox;

    @FXML
    private TextField durationField;

    @FXML
    private ChoiceBox<String> greenSpaceChoiceBox;

    @FXML
    private Label feedbackLabel;

    private ToDoListController toDoListController;
    private String userEmail;

    /**
     * Initializes the controller.
     */
    public void initialize() {
        setUserEmail(GsmUIApplication.getUserEmail());
        populateGreenSpaceChoiceBox();
        urgencyChoiceBox.setItems(FXCollections.observableArrayList("High", "Medium", "Low"));
    }

    /**
     * Sets the email of the current user.
     *
     * @param email the email of the current user
     */
    public void setUserEmail(String email) {
        this.userEmail = email;
    }


    private void populateGreenSpaceChoiceBox() {
        RegisterGreenSpaceController controller = new RegisterGreenSpaceController();
        List<GreenSpace> greenSpaces = controller.getAllGreenSpaces();
        boolean hasGreenSpaces = false;
        for (GreenSpace greenSpace : greenSpaces) {
            if (greenSpace.getEmail().equals(userEmail)) {
                greenSpaceChoiceBox.getItems().add(greenSpace.getName());
                hasGreenSpaces = true;
            }
        }

        if (!hasGreenSpaces) {
            feedbackLabel.setText("No green spaces registered for this user.");
            greenSpaceChoiceBox.setDisable(true);
        } else {
            feedbackLabel.setText(""); // Clear any previous messages
            greenSpaceChoiceBox.setDisable(false);
        }
    }

    /**
     * Handles the event of adding a to-do list entry.
     *
     * @param event the ActionEvent representing the event
     */
    @FXML
    private void handleAddToDoListEntry(ActionEvent event) {
        String taskDescription = taskDescriptionField.getText().trim();
        String urgency = urgencyChoiceBox.getValue();
        String durationText = durationField.getText().trim();
        String selectedGreenSpaceName = greenSpaceChoiceBox.getValue();
        String regex = "^[a-zA-Z\\s]+$";

        if (taskDescription.isEmpty() || urgency == null || durationText.isEmpty() || selectedGreenSpaceName == null) {
            feedbackLabel.setText("Please fill in all fields with valid data.");
            return;
        }
        if (!taskDescription.matches(regex)) {
            feedbackLabel.setText("Task description can only contain letters and spaces.");
            return;
        }

        int expectedDuration;
        try {
            expectedDuration = Integer.parseInt(durationText);
            if (expectedDuration <= 0) {
                feedbackLabel.setText("Please enter a valid duration.");
                return;
            }
        } catch (NumberFormatException e) {
            feedbackLabel.setText("Please enter a valid duration.");
            return;
        }

        String status = "Pending";
        toDoListController.createToDoListEntry(taskDescription, urgency, expectedDuration, selectedGreenSpaceName, status, userEmail);
        showAlert(Alert.AlertType.INFORMATION, "Success", "To-Do List entry added successfully.");
    }

    /**
     * Sets the to-do list controller.
     *
     * @param toDoListController the to-do list controller
     */
    public void setToDoListController(ToDoListController toDoListController) {
        this.toDoListController = toDoListController;
    }

    /**
     * Shows an alert dialog with the specified type, title, and message.
     *
     * @param alertType the type of the alert dialog
     * @param title     the title of the alert dialog
     * @param message   the message of the alert dialog
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Handles the event of navigating back to the previous scene.
     *
     * @param actionEvent the ActionEvent representing the event
     */
    @FXML
    public void handleBack(javafx.event.ActionEvent actionEvent) {

        try {
            SceneSwitcher.switchToScene("/fxml/GsmUIMenu.fxml", "Register Green Space", (Node) actionEvent.getSource(), userEmail);

        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the Main Menu.");
        }
    }
}
