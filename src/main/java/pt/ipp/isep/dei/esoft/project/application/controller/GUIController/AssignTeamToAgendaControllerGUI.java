package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.TeamProposalController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.TeamProposal;

import java.util.List;

public class AssignTeamToAgendaControllerGUI {

    @FXML
    private ChoiceBox<Agenda> agendaChoiceBox;

    @FXML
    private ListView<TeamProposal> teamListView;

    private final AgendaController agendaController = new AgendaController();
    private final TeamProposalController teamProposalController = new TeamProposalController();

    public void initialize() {
        List<Agenda> agendaEntries = agendaController.getAllAgendaEntries();
        agendaChoiceBox.getItems().addAll(agendaEntries);

        agendaChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            List<TeamProposal> teamProposals = teamProposalController.getAllTeamProposals();
            teamListView.getItems().addAll(teamProposals);
        });
    }

    @FXML
    private void handleAssignTeam() {
        Agenda selectedAgenda = agendaChoiceBox.getValue();
        TeamProposal selectedTeam = teamListView.getSelectionModel().getSelectedItem();

        if (selectedAgenda != null && selectedTeam != null) {
            selectedAgenda.setTeamProposal(selectedTeam);
            agendaController.updateAgendaEntry(selectedAgenda);
        } else {
            System.out.println("Please select an agenda entry and a team to assign.");
        }
    }
}
