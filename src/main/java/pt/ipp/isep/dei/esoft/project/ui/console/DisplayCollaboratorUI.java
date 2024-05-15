package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * The {@code DisplayCollaboratorUI} class represents a user interface for displaying a list of collaborators.
 * It retrieves the list of collaborators from the controller and formats the information for display.
 * The class implements the {@code Runnable} interface, allowing it to be executed as a thread.
 */
public class DisplayCollaboratorUI implements Runnable {

    /**
     * Runs the UI for displaying the list of collaborators.
     * Retrieves the list of collaborators from the controller and formats the information for display.
     */
    @Override
    public void run() {
        displayCollaboratorsList();
    }

    /**
     * Displays the list of collaborators retrieved from the controller.
     */
    public void displayCollaboratorsList() {
        List<Collaborator> collaboratorsList = CreateCollaboratorController.getCollaboratorsList();

        if (collaboratorsList.isEmpty()) {
            System.out.println("No collaborators created yet.");
        } else {
            System.out.println("List of Collaborators:");
            for (Collaborator collaborator : collaboratorsList) {
                System.out.println("Name: " + collaborator.getName());
                System.out.println("Birthday Date: " + formatDate(collaborator.getBirthdayDate()));
                System.out.println("Admission Date: " + formatDate(collaborator.getAdmissionDate()));
                System.out.println("Address: " + collaborator.getAddress());
                System.out.println("Phone Number: " + collaborator.getPhoneNumber());
                System.out.println("Email: " + collaborator.getEmail());
                System.out.println("Taxpayer Number: " + collaborator.getTaxpayerNumber());
                System.out.println("BI Number: " + collaborator.getBINumber());
                System.out.println("Job: " + collaborator.getJob());
                System.out.println("---------------------------------------------------------");
            }
        }
    }

    /**
     * Formats the given {@code LocalDate} object into a string with the "dd-MM-yyyy" format.
     *
     * @param date The {@code LocalDate} object to format.
     * @return A string representation of the date in the "dd-MM-yyyy" format.
     */
    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
