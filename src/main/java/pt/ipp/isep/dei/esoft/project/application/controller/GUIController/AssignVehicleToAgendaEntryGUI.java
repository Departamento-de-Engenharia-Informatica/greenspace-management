package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignVehicleToAgendaEntryController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.List;

/**
 * GUI controller for assigning vehicles to an agenda entry.
 */
public class AssignVehicleToAgendaEntryGUI {

    @FXML
    private ComboBox<Agenda> agendaEntryComboBox;

    @FXML
    private ListView<Vehicle> vehicleListView;

    @FXML
    private Label promptLabel;

    private AssignVehicleToAgendaEntryController controller;

    /**
     * Initializes the controller. Loads agenda entries and sets up the vehicle list view.
     */
    @FXML
    public void initialize() {
        controller = new AssignVehicleToAgendaEntryController();

        List<Agenda> entries = AssignVehicleToAgendaEntryController.getAllAgendaEntries();
        if (!entries.isEmpty()) {
            agendaEntryComboBox.setItems(FXCollections.observableArrayList(entries));
            System.out.println("Agenda entries loaded: " + entries.size());
        } else {
            System.out.println("No agenda entries found.");
        }

        vehicleListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        updatePromptLabelVisibility();
    }

    /**
     * Handles the selection of an agenda entry from the combo box.
     * Loads available vehicles for the selected agenda entry.
     */
    @FXML
    public void handleAgendaEntrySelection() {
        Agenda selectedEntry = agendaEntryComboBox.getValue();
        if (selectedEntry != null) {
            System.out.println("Selected agenda entry: " + selectedEntry.getTaskDescription());
            List<Vehicle> availableVehicles = controller.getAvailableVehicles(selectedEntry.getStartTime(), selectedEntry.getEndTime());
            if (availableVehicles != null && !availableVehicles.isEmpty()) {
                vehicleListView.setItems(FXCollections.observableArrayList(availableVehicles));
                System.out.println("Available vehicles loaded: " + availableVehicles.size());
            } else {
                System.out.println("No available vehicles found for the selected agenda entry.");
            }
        }
        updatePromptLabelVisibility();
    }

    /**
     * Handles the assignment of selected vehicles to the selected agenda entry.
     * Displays an alert indicating success or failure.
     */
    @FXML
    public void handleAssignVehicles() {
        Agenda selectedEntry = agendaEntryComboBox.getValue();
        List<Vehicle> selectedVehicles = vehicleListView.getSelectionModel().getSelectedItems();

        if (selectedEntry != null && !selectedVehicles.isEmpty()) {
            controller.assignVehiclesToEntry(selectedEntry, selectedVehicles);
            showAlert("Success", "Vehicles assigned successfully.");
        } else {
            showAlert("Error", "Select an agenda entry and at least one vehicle.");
        }
    }

    /**
     * Handles the exit action by closing the current stage.
     */
    @FXML
    public void handleExit() {
        Stage stage = (Stage) agendaEntryComboBox.getScene().getWindow();
        stage.close();
    }

    /**
     * Updates the visibility of the prompt label based on whether an agenda entry is selected.
     */
    private void updatePromptLabelVisibility() {
        boolean hasSelectedEntry = agendaEntryComboBox.getValue() != null;
        promptLabel.setVisible(!hasSelectedEntry);
    }

    /**
     * Displays an alert with the given title and message.
     *
     * @param title   the title of the alert
     * @param message the message of the alert
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
