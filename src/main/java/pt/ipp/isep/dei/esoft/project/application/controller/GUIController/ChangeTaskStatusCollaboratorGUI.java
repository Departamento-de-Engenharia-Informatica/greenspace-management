package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.ui.gui.ControllerWithEmail;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * GUI controller for changing the status of tasks assigned to a collaborator.
 */
public class ChangeTaskStatusCollaboratorGUI implements ControllerWithEmail {

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Button doneButton;

    private AgendaController agendaController;
    private List<Agenda> agendaEntries;
    private String userEmail;

    /**
     * Initializes the controller. Loads tasks assigned to the user.
     */
    @FXML
    public void initialize() {
        agendaController = new AgendaController();
        loadUserTasks();  // Load tasks assigned to the user
    }

    /**
     * Sets the email of the user and reloads the tasks assigned to the user.
     *
     * @param email the email of the user
     */
    @Override
    public void setUserEmail(String email) {
        this.userEmail = email;
        loadUserTasks();  // Reload tasks when user email is set
    }

    /**
     * Loads tasks assigned to the logged-in user with status "Planned" and populates the ChoiceBox.
     */
    private void loadUserTasks() {
        if (userEmail == null || userEmail.isEmpty()) {
            return;  // Do not load tasks if user email is not set
        }

        agendaEntries = agendaController.getAllAgendaEntries();

        // Clear previous items
        choiceBox.getItems().clear();

        // Populate ChoiceBox with tasks assigned to the logged-in user and with status "Planned"
        for (Agenda agenda : agendaEntries) {
            if (isTaskAssignedToUser(agenda) && "Planned".equals(agenda.getStatus())) {
                choiceBox.getItems().add(formatTaskDetails(agenda));
            }
        }

        if (choiceBox.getItems().isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "No Tasks Found", "No planned tasks found for the logged-in user.");
        }
    }

    /**
     * Checks if the given task is assigned to the logged-in user.
     *
     * @param agenda the agenda entry to check
     * @return true if the task is assigned to the user, false otherwise
     */
    private boolean isTaskAssignedToUser(Agenda agenda) {
        return agenda.getTeamProposal().getSelectedCollaborators().stream()
                .anyMatch(collaborator -> collaborator.getEmail().equals(userEmail));
    }

    /**
     * Formats the task details into a string for display in the ChoiceBox.
     *
     * @param agenda the agenda entry to format
     * @return a formatted string containing task details
     */
    private String formatTaskDetails(Agenda agenda) {
        return "Task: " + agenda.getTaskDescription() + ", Greenspace: " + agenda.getGreenspaceName() +
                ", Date: " + agenda.getExpectedDate() + ", Status: " + agenda.getStatus();
    }

    /**
     * Handles the action of marking a selected task as "Done".
     */
    @FXML
    private void handleDoneStatus() {
        String selectedTask = choiceBox.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            Optional<Agenda> optionalAgenda = agendaEntries.stream()
                    .filter(agenda -> formatTaskDetails(agenda).equals(selectedTask))
                    .findFirst();

            if (optionalAgenda.isPresent()) {
                Agenda agenda = optionalAgenda.get();
                if ("Planned".equals(agenda.getStatus())) {
                    agenda.setStatus("Done");
                    agendaController.updateAgendaEntry(agenda);
                    showAlert(Alert.AlertType.INFORMATION, "Status Changed", "Status of " + agenda.getTaskDescription() + " changed to Done.");
                } else {
                    showAlert(Alert.AlertType.WARNING, "Invalid Status Change", "Only tasks with status 'Planned' can be changed to 'Done'.");
                }
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No Task Selected", "Please select a task to change its status.");
        }
    }

    /**
     * Handles the action of navigating back to the Collaborator menu.
     */
    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CollaboratorUIMenu.fxml"));
            Parent root = loader.load();

            CollaboratorUIMenuControllerGUI controller = loader.getController();
            controller.setUserEmail(userEmail);

            Stage stage = (Stage) choiceBox.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Collaborator Menu");
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the Collaborator Menu. " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Displays an alert with the given title and message.
     *
     * @param alertType the type of the alert
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
