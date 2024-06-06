package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
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

    public void initialize() {
        statusChoiceBox.setItems(FXCollections.observableArrayList("Planned", "Postponed", "Canceled", "Done"));
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        populateGreenSpaceChoiceBox();
    }

    public void setAgendaController(AgendaController agendaController) {
        this.agendaController = agendaController;
    }

    public void setToDoListController(ToDoListController toDoListController) {
        this.toDoListController = toDoListController;
    }

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
            showAlert(Alert.AlertType.INFORMATION, "Success", "Agenda entry added with sucess.");
            toDoListController.updateToDoListStatus(selectedToDoDescription, "Processed");
        } else {
            feedbackLabel.setText("Failed to create agenda entry. Ensure the task exists in the To-Do list and the green space is managed by you.");
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
