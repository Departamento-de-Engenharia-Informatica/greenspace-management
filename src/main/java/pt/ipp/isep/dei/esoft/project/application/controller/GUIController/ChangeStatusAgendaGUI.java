package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.ui.gui.ControllerWithEmail;
import pt.ipp.isep.dei.esoft.project.ui.gui.GsmUIApplication;
import pt.ipp.isep.dei.esoft.project.ui.gui.SceneSwitcher;

import javafx.scene.Node;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ChangeStatusAgendaGUI implements ControllerWithEmail {

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private DatePicker datePicker;
    @FXML
    private Button canceledbtn;
    @FXML
    private Button postponedbtn;
    @FXML
    private Button backbtn;
    @FXML
    private Label datelbl;

    private AgendaController agendaController;
    private List<Agenda> agendaEntries;
    private String userEmail;

    public void initialize() {
        setUserEmail(GsmUIApplication.getUserEmail());
        agendaController = new AgendaController();
        agendaEntries = agendaController.getAllAgendaEntries();

        // Populate ChoiceBox
        for (Agenda agenda : agendaEntries) {
            choiceBox.getItems().add(agenda.getTaskDescription());
        }
    }

    public void setUserEmail(String email) {
        this.userEmail = email;
        System.out.println("link 2" + userEmail);
    }

    @FXML
    private void handleCanceledStatus(ActionEvent event) {
        String selectedTask = choiceBox.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            Optional<Agenda> optionalAgenda = agendaEntries.stream()
                    .filter(agenda -> agenda.getTaskDescription().equals(selectedTask))
                    .findFirst();

            if (optionalAgenda.isPresent()) {
                Agenda agenda = optionalAgenda.get();
                if (agenda.getStatus().equals("Canceled")) {
                    showAlert(Alert.AlertType.WARNING, "Status Already Canceled", "The status of " + selectedTask + " is already Canceled.");
                } else {
                    agenda.setStatus("Canceled");
                    agendaController.updateAgendaEntry(agenda);
                    showAlert(Alert.AlertType.INFORMATION, "Status Changed", "Status of " + selectedTask + " changed to Canceled.");
                }
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No Task Selected", "Please select a task to change its status.");
        }
    }

    @FXML
    private void handlePostponedStatus(ActionEvent event) {
        String selectedTask = choiceBox.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            Optional<Agenda> optionalAgenda = agendaEntries.stream()
                    .filter(agenda -> agenda.getTaskDescription().equals(selectedTask))
                    .findFirst();

            if (optionalAgenda.isPresent()) {
                Agenda agenda = optionalAgenda.get();
                datePicker.setVisible(true);
                canceledbtn.setVisible(false);
                postponedbtn.setVisible(false);
                backbtn.setVisible(false);
                datelbl.setVisible(true);
                choiceBox.setDisable(true);
                datePicker.setOnAction(e -> {
                    LocalDate newDate = datePicker.getValue();
                    if (newDate != null && newDate.isAfter(agenda.getExpectedDate())) {
                        agenda.setExpectedDate(newDate);
                        agenda.setStatus("Postponed");
                        agendaController.updateAgendaEntry(agenda);
                        showAlert(Alert.AlertType.INFORMATION, "Status Changed", "Status of " + selectedTask + " changed to Postponed.");
                        datePicker.setVisible(false);
                        postponedbtn.setVisible(true);
                        canceledbtn.setVisible(true);
                        backbtn.setVisible(true);
                        datelbl.setVisible(false);
                        choiceBox.setDisable(false);
                    } else {
                        showAlert(Alert.AlertType.WARNING, "Invalid Date", "Please select a date after the current task date.");
                    }
                });
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No Task Selected", "Please select a task to change its status.");
        }
    }

    @FXML
    public void handleBack(javafx.event.ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchToScene("/fxml/GsmUIMenu.fxml", "Register Green Space", (Node) actionEvent.getSource(), userEmail);
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the Main Menu.");
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
