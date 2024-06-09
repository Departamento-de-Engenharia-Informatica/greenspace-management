package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.TeamProposal;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;
import pt.ipp.isep.dei.esoft.project.ui.gui.ControllerWithEmail;
import pt.ipp.isep.dei.esoft.project.ui.gui.SceneSwitcher;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

/**
 * The AgendaControllerGUI class represents the controller for managing agenda entries in the GUI.
 */
public class AgendaControllerGUI implements ControllerWithEmail {

    @FXML
    private ChoiceBox<String> greenSpaceChoiceBox;

    @FXML
    private ChoiceBox<String> toDoListChoiceBox;

    @FXML
    private TextField dateField;

    @FXML
    private ChoiceBox<String> statusChoiceBox;

    @FXML
    private Label feedbackLabel;

    private AgendaController agendaController;
    private ToDoListController toDoListController;
    private String userEmail;
    private TeamProposal teamProposal;

    /**
     * Initializes the controller.
     */
    public void initialize() {
        statusChoiceBox.setItems(FXCollections.observableArrayList("Planned", "Postponed", "Canceled", "Done"));
    }

    /**
     * Sets the email of the current user.
     *
     * @param userEmail the email of the current user
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        populateGreenSpaceChoiceBox();
    }

    /**
     * Sets the agenda controller.
     *
     * @param agendaController the agenda controller
     */
    public void setAgendaController(AgendaController agendaController) {
        this.agendaController = agendaController;
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
     * Populates the green space choice box.
     */
    private void populateGreenSpaceChoiceBox() {
        List<GreenSpace> greenSpaces = agendaController.getGreenSpaces(userEmail);
        if (greenSpaces.isEmpty()) {
            feedbackLabel.setText("No available Green Spaces for this user.");
            greenSpaceChoiceBox.setDisable(true);
        } else {
            for (GreenSpace greenSpace : greenSpaces) {
                greenSpaceChoiceBox.getItems().add(greenSpace.getName());
            }
            greenSpaceChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> populateToDoListChoiceBox(newValue));
        }
    }

    /**
     * Populates the to-do list choice box based on the selected green space.
     *
     * @param greenSpaceName the name of the selected green space
     */
    private void populateToDoListChoiceBox(String greenSpaceName) {
        if (greenSpaceName != null) {
            List<ToDoList> toDoListEntries = agendaController.getToDoListEntries(userEmail, greenSpaceName);
            toDoListChoiceBox.getItems().clear();
            if (toDoListEntries.isEmpty()) {
                feedbackLabel.setText("No available To-Do list entries for this green space.");
                toDoListChoiceBox.setDisable(true);
            } else {
                toDoListChoiceBox.setDisable(false);
                for (ToDoList toDoList : toDoListEntries) {
                    toDoListChoiceBox.getItems().add(toDoList.getTaskDescription());
                }
            }
        }
    }

    /**
     * Handles the event of creating an agenda entry.
     *
     * @param event the ActionEvent representing the event
     */
    @FXML
    private void handleCreateAgendaEntry(ActionEvent event) {
        String selectedGreenSpaceName = greenSpaceChoiceBox.getValue();
        String selectedToDoDescription = toDoListChoiceBox.getValue();
        String expectedDateString = dateField.getText().trim();
        String status = statusChoiceBox.getValue();

        if (selectedGreenSpaceName == null || selectedToDoDescription == null || expectedDateString.isEmpty() || status == null) {
            feedbackLabel.setText("Please fill in all fields with valid data.");
            return;
        }

        LocalDate expectedDate;
        try {
            expectedDate = LocalDate.parse(expectedDateString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            if (expectedDate.isBefore(LocalDate.now())) {
                feedbackLabel.setText("The date cannot be before today. Please enter a valid date.");
                return;
            }
        } catch (DateTimeParseException e) {
            feedbackLabel.setText("Invalid date format. Please enter date in dd-MM-yyyy format.");
            return;
        }

        Optional<Agenda> agendaEntry = agendaController.createAgendaEntry(selectedToDoDescription, selectedGreenSpaceName, expectedDate, status, teamProposal);
        if (agendaEntry.isPresent()) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Agenda entry added with success.");
            toDoListController.updateToDoListStatus(selectedToDoDescription, "Processed");
        } else {
            feedbackLabel.setText("Failed to create agenda entry. Ensure the task exists in the To-Do list and the green space is managed by you.");
        }
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
