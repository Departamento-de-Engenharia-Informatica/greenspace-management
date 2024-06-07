package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.TeamProposal;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.gui.ControllerWithEmail;
import pt.ipp.isep.dei.esoft.project.ui.gui.GsmUIApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.time.format.DateTimeParseException;
import javafx.collections.FXCollections;

public class ListTaksControllerGUI implements ControllerWithEmail {

    @FXML
    private TextField startDateField;

    @FXML
    private TextArea taskListArea;

    @FXML
    private TextField endDateField;

    @FXML
    private ComboBox<String> statusComboBox;

    private String userEmail;

    @Override
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void initialize() {
        statusComboBox.setItems(FXCollections.observableArrayList(
                "All", "Planned", "Postponed", "Canceled", "Done"
        ));
        statusComboBox.getSelectionModel().selectFirst();
        setUserEmail(GsmUIApplication.getUserEmail());
        handleListTasks();
    }

    @FXML
    private void handleListTasks() {
        // Retrieve user input
        String startDateStr = startDateField.getText();
        String endDateStr = endDateField.getText();
        String status = statusComboBox.getValue();

        // Check if date fields are empty
        if (startDateStr.isEmpty() || endDateStr.isEmpty()) {
            taskListArea.setText("Please enter valid start and end dates.");
            return;
        }

        // Parse dates
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate startDate;
        LocalDate endDate;
        try {
            startDate = LocalDate.parse(startDateStr, dateFormatter);
            endDate = LocalDate.parse(endDateStr, dateFormatter);

            // Check if end date is after start date
            if (endDate.isBefore(startDate)) {
                taskListArea.setText("End date must be after start date.");
                return;
            }
        } catch (DateTimeParseException e) {
            taskListArea.setText("Invalid date format. Please enter dates in the format DD-MM-YYYY.");
            return;
        }

        // Call DisplayTasks with valid dates
        DisplayTasks(startDate, endDate, status);
    }


    public void DisplayTasks(LocalDate startDate, LocalDate endDate, String status) {
        List<Agenda> agendaEntries = Repositories.getInstance().getAgendaRepository().getAllAgendas();

        if (agendaEntries.isEmpty()) {
            taskListArea.setText("No agenda entries found.");
            return;
        }

        boolean tasksFound = false;
        StringBuilder taskText = new StringBuilder("List of tasks assigned to me:\n");

        // Check if startDate or endDate is null
        if (startDate == null || endDate == null) {
            taskListArea.setText("Invalid date range.");
            return;
        }

        // Filter and sort tasks by date
        List<Agenda> filteredTasks = new ArrayList<>();
        for (Agenda agenda : agendaEntries) {
            LocalDate date = agenda.getExpectedDate();
            if (date != null && isWithinDateRange(date, startDate, endDate) &&
                    (status.equals("All") || agenda.getStatus().equalsIgnoreCase(status))) {
                filteredTasks.add(agenda);
            }
        }
        Collections.sort(filteredTasks, Comparator.comparing(Agenda::getExpectedDate));

        // Display tasks in order of date
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (Agenda agenda : filteredTasks) {
            TeamProposal teamProposal = agenda.getTeamProposal();
            List<Collaborator> collaborators = teamProposal.getSelectedCollaborators();
            for (Collaborator collaborator : collaborators) {
                String collaboratorEmail = collaborator.getEmail();
                if (collaboratorEmail.equals(userEmail)) {
                    tasksFound = true;
                    taskText.append("Task: ").append(agenda.getTaskDescription())
                            .append(", Date: ").append(agenda.getExpectedDate().format(dateFormatter))
                            .append(", Greenspace: ").append(agenda.getGreenspaceName())
                            .append(", Status: ").append(agenda.getStatus())
                            .append("\n");
                    break;
                }
            }
        }

        if (!tasksFound) {
            taskText.append("No tasks found for the specified criteria.");
        }

        taskListArea.setText(taskText.toString());
    }


    private boolean isWithinDateRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return (date.isEqual(startDate) || date.isAfter(startDate)) &&
                (date.isEqual(endDate) || date.isBefore(endDate));
    }
}
