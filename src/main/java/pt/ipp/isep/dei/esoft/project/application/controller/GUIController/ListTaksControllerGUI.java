package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.ui.gui.ControllerWithEmail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ListTaksControllerGUI implements ControllerWithEmail {

    @FXML
    private TextField startDateField;

    @FXML
    private TextField endDateField;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private TextArea taskListArea;

    private String userEmail;

    @Override
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @FXML
    private void handleListTasks() {
        // Retrieve user input
        String startDateStr = startDateField.getText();
        String endDateStr = endDateField.getText();
        String status = statusComboBox.getValue();

        // Parse dates
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate startDate = LocalDate.parse(startDateStr, dateFormatter);
        LocalDate endDate = LocalDate.parse(endDateStr, dateFormatter);

        // Retrieve tasks based on criteria
        // Implement this according to your business logic
        // List<Agenda> tasks = agendaController.getAllAgendaEntries();

        // Just a dummy example
        taskListArea.setText("Dummy Task List");
    }
}
