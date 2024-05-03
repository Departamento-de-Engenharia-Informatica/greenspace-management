package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;

import java.util.List;

public class DisplayCollaboratorUI  implements Runnable{
    @Override
    public void run() {
        displayCollaboratorsList();
    }
    public void displayCollaboratorsList() {
        List<Collaborator> collaboratorsList = CreateCollaboratorController.getCollaboratorsList();

        if (collaboratorsList.isEmpty()) {
            System.out.println("No collaborators created yet.");
        } else {
            System.out.println("List of Collaborators:");
            for (Collaborator collaborator : collaboratorsList) {
                System.out.println("Name: " + collaborator.getName());
                System.out.println("Birthday Date: " + collaborator.getBirthdayDate());
                System.out.println("Admission Date: " + collaborator.getAdmissionDate());
                System.out.println("Address: " + collaborator.getAddress());
                System.out.println("Phone Number: " + collaborator.getPhoneNumber());
                System.out.println("Email: " + collaborator.getEmail());
                System.out.println("Taxpayer Number: " + collaborator.getTaxpayerNumber());
                System.out.println("BI Number: " + collaborator.getBINumber());
                System.out.println("Job: " + collaborator.getJob());

            }
        }
    }
}