package pt.ipp.isep.dei.esoft.project.ui.console;
import pt.ipp.isep.dei.esoft.project.application.controller.*;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;


import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CreateCollaboratorUI implements Runnable {

    private final CreateCollaboratorController controller;
    private final Scanner scanner;

    public CreateCollaboratorUI() {
        this.controller = new CreateCollaboratorController(); // Initialize the controller
        this.scanner = new Scanner(System.in);
    }


    @Override
    public void run() {
        System.out.println("--- Create Collaborator ---");
        requestData();
        Optional<Collaborator> collaboratorOptional = controller.createCollaborator(
                collaboratorName, collaboratorBirthdayDate, collaboratorAdmissionDate,
                collaboratorAddress, collaboratorPhoneNumber, collaboratorEmail,
                collaboratorTaxpayerNumber, collaboratorBINumber, collaboratorJob);

        if (collaboratorOptional.isPresent()) {
            System.out.println("Collaborator created successfully!");
        } else {
            System.out.println("Failed to create collaborator. Please try again.");
        }
    }


    private String collaboratorName;
    private String collaboratorBirthdayDate;
    private String collaboratorAdmissionDate;
    private String collaboratorAddress;
    private String collaboratorPhoneNumber;
    private String collaboratorEmail;
    private String collaboratorTaxpayerNumber;
    private String collaboratorBINumber;
    private String collaboratorJob;

    private void requestData() {
        collaboratorName = requestCollaboratorName();
        collaboratorBirthdayDate = requestCollaboratorBirthdayDate();
        collaboratorAdmissionDate = requestCollaboratorAdmissionDate();
        collaboratorAddress = requestCollaboratorAddress();
        collaboratorPhoneNumber = requestCollaboratorPhoneNumber();
        collaboratorEmail = requestCollaboratorEmail();
        collaboratorTaxpayerNumber = requestCollaboratorTaxpayerNumber();
        collaboratorBINumber = requestCollaboratorBINumber();
        collaboratorJob = requestCollaboratorJob();
    }

    private String requestCollaboratorBINumber() {
        System.out.print("Collaborator BI Number: ");
        return scanner.nextLine();
    }

    private String requestCollaboratorTaxpayerNumber() {
        System.out.print("Collaborator Taxpayer Number: ");
        return scanner.nextLine();
    }

    private String requestCollaboratorEmail() {
        System.out.print("Collaborator Email: ");
        return scanner.nextLine();
    }

    private String requestCollaboratorPhoneNumber() {
        System.out.print("Collaborator Phone Number: ");
        return scanner.nextLine();
    }

    private String requestCollaboratorAddress() {
        System.out.print("Collaborator Address: ");
        return scanner.nextLine();
    }

    private String requestCollaboratorAdmissionDate() {
        System.out.print("Collaborator Admission Date: ");
        return scanner.nextLine();
    }

    private String requestCollaboratorBirthdayDate() {
        System.out.print("Collaborator Birthday Date: ");
        return scanner.nextLine();
    }

    private String requestCollaboratorName() {
        System.out.print("Collaborator Name: ");
        return scanner.nextLine();
    }
    private String requestCollaboratorJob() {
        System.out.println("Available Jobs:");
        List<Job> jobList = controller.getAvailableJobs();

        for (int i = 0; i < jobList.size(); i++) {
            System.out.println((i + 1) + ". " + jobList.get(i).getJobName());
        }

        Scanner input = new Scanner(System.in);
        int selectedJobIndex = input.nextInt();

        // Check if the selected index is within the valid range
        if (selectedJobIndex >= 1 && selectedJobIndex <= jobList.size()) {
            // Return the name of the selected job
            return jobList.get(selectedJobIndex - 1).getJobName();

        } else {
            // Handle invalid input
            System.out.println("Invalid input. Please select a valid job.");
            return requestCollaboratorJob(); // Recursive call to request input again
        }
    }

}
