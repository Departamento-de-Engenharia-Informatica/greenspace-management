package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.TeamProposalController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.TeamProposal;
import pt.ipp.isep.dei.esoft.project.emailSender.EmailSender;
import pt.ipp.isep.dei.esoft.project.emailSender.EmailConfig;
import pt.ipp.isep.dei.esoft.project.ui.gui.ControllerWithEmail;
import pt.ipp.isep.dei.esoft.project.ui.gui.SceneSwitcher;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The AssignTeamToAgendaControllerGUI class represents the controller for assigning teams to agenda entries in the GUI.
 */
public class AssignTeamToAgendaControllerGUI implements ControllerWithEmail {

    @FXML
    private ChoiceBox<Agenda> agendaChoiceBox;

    @FXML
    private ListView<String> teamListView;

    private String userEmail;

    private final AgendaController agendaController = new AgendaController();
    private final TeamProposalController teamProposalController = new TeamProposalController();
    private final EmailSender emailSender;

    /**
     * Constructor for AssignTeamToAgendaControllerGUI.
     */
    public AssignTeamToAgendaControllerGUI() {
        EmailConfig emailConfig = new EmailConfig("email.properties");
        this.emailSender = new EmailSender(emailConfig);
    }

    @Override
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getUserEmail() {
        return userEmail;
    }
    /**
     * Initializes the controller.
     */
    public void initialize() {
        List<Agenda> agendaEntries = agendaController.getAllAgendaEntries()
                .stream()
                .filter(agenda -> agenda.getTeamProposal() == null)
                .collect(Collectors.toList());

        agendaChoiceBox.getItems().addAll(agendaEntries);

        agendaChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            populateTeamListView();
        });
    }

    /**
     * Populates the team list view.
     */
    private void populateTeamListView() {
        List<TeamProposal> teamProposals = teamProposalController.getAllTeamProposals();
        teamListView.getItems().clear();
        for (TeamProposal teamProposal : teamProposals) {
            String teamDetails = "Team " + ": \n" + teamProposal.getSelectedCollaborators()
                    .stream()
                    .map(Collaborator::getName)
                    .collect(Collectors.joining("\n   - ", "   - ", ""));
            teamListView.getItems().add(teamDetails);
        }
    }

    /**
     * Handles the event of assigning a team to an agenda entry.
     *
     * @param actionEvent the ActionEvent representing the event
     */
    @FXML
    private void handleAssignTeam(ActionEvent actionEvent) {
        Agenda selectedAgenda = agendaChoiceBox.getValue();
        String selectedTeamDetails = teamListView.getSelectionModel().getSelectedItem();

        if (selectedAgenda != null && selectedTeamDetails != null) {
            TeamProposal selectedTeam = getTeamProposalByDetails(selectedTeamDetails);
            if (selectedTeam != null) {
                selectedAgenda.setTeamProposal(selectedTeam);
                agendaController.updateAgendaEntry(selectedAgenda);
                sendEmailNotifications(selectedTeam);
                showAlert(Alert.AlertType.INFORMATION, "Success", "Team assigned to the agenda entry successfully.");
                handleBack(actionEvent);
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Team not found.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Invalid Selection", "Please select an agenda entry and a team to assign.");
        }
    }

    /**
     * Retrieves a team proposal based on its details.
     *
     * @param teamDetails the details of the team proposal
     * @return the TeamProposal object if found, null otherwise
     */
    private TeamProposal getTeamProposalByDetails(String teamDetails) {
        List<TeamProposal> teamProposals = teamProposalController.getAllTeamProposals();
        for (TeamProposal teamProposal : teamProposals) {
            String details = "Team " + ": \n" + teamProposal.getSelectedCollaborators()
                    .stream()
                    .map(Collaborator::getName)
                    .collect(Collectors.joining("\n   - ", "   - ", ""));
            if (details.equals(teamDetails)) {
                return teamProposal;
            }
        }
        return null;
    }

    /**
     * Sends email notifications to the team members.
     *
     * @param team the team proposal to send notifications to
     */
    private void sendEmailNotifications(TeamProposal team) {
        String subject = "You have been assigned to a new task";
        String body = "Dear Collaborator,\n\nYou have been assigned to a new task. Please check your agenda for more details.\n\nBest regards,\nThe Team";

        for (Collaborator collaborator : team.getSelectedCollaborators()) {
            emailSender.sendEmail(collaborator.getEmail(), subject, body);
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
