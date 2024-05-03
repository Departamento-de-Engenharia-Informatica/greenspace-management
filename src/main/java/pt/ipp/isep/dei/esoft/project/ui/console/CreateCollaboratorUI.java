package pt.ipp.isep.dei.esoft.project.ui.console;
import pt.ipp.isep.dei.esoft.project.application.controller.*;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * The {@code CreateCollaboratorUI} class represents a user interface for creating collaborators.
 * It allows users to input collaborator information and validates the input before creating a new collaborator.
 * The class implements the {@code Runnable} interface, allowing it to be executed as a thread.
 */
public class CreateCollaboratorUI implements Runnable {

    private final CreateCollaboratorController controller;
    private final Scanner scanner;

    /**
     * Constructs a new {@code CreateCollaboratorUI} object.
     * Initializes the controller and scanner.
     */
    public CreateCollaboratorUI() {
        this.controller = new CreateCollaboratorController();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Runs the UI for creating a collaborator.
     * Prompts the user for collaborator information and validates the input.
     * Creates a new collaborator if the input is valid.
     */
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
    private LocalDate collaboratorBirthdayDate;
    private LocalDate collaboratorAdmissionDate;
    private String collaboratorAddress;
    private String collaboratorPhoneNumber;
    private String collaboratorEmail;
    private int collaboratorTaxpayerNumber;
    private long collaboratorBINumber;
    private String collaboratorJob;

    /**
     * Requests and validates the collaborator data from the user.
     */
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

    /**
     * Requests the collaborator's BI number from the user.
     * Validates the input to ensure it has 8 digits and is not a duplicate.
     *
     * @return The BI number entered by the user.
     */
    private long requestCollaboratorBINumber() {
        long biNumber;
        while (true) {
            System.out.print("Collaborator BI Number: ");
            String input = scanner.nextLine();
            try {
                biNumber = Long.parseLong(input);
                if (String.valueOf(biNumber).length() != 8) {
                    System.out.println("BI number must have 8 digits.");
                } else if (controller.isBINumberDuplicate(biNumber)) {
                    System.out.println("BI number already exists. Please enter a different number.");
                } else {
                    // Valid input, exit loop
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return biNumber;
    }

    /**
     * Requests the collaborator's taxpayer number from the user.
     * Validates the input to ensure it has 9 digits and is not a duplicate.
     *
     * @return The taxpayer number entered by the user.
     */

    private int requestCollaboratorTaxpayerNumber() {
        int taxpayerNumber;
        while (true) {
            System.out.print("Collaborator Taxpayer Number: ");
            String input = scanner.nextLine();
            try {
                taxpayerNumber = Integer.parseInt(input);
                if (String.valueOf(taxpayerNumber).length() != 9) {
                    System.out.println("Taxpayer number must have 9 digits.");
                } else if (controller.isTaxpayerNumberDuplicate(taxpayerNumber)) {
                    System.out.println("Taxpayer number already exists. Please enter a different number.");
                } else {
                    // Valid input, exit loop
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return taxpayerNumber;
    }

    /**
     * Requests the collaborator's email address from the user.
     * Validates the input to ensure it contains '@'.
     *
     * @return The email address entered by the user.
     */

    private String requestCollaboratorEmail() {
        String email;
        while (true) {
            System.out.print("Collaborator Email: ");
            email = scanner.nextLine();
            if (email == null || !email.contains("@")) {
                System.out.println("Email must contain '@'.");
            } else {
                // Valid input, exit loop
                break;
            }
        }
        return email;
    }

    /**
     * Requests the collaborator's phone number from the user.
     * Validates the input to ensure it has 9 digits.
     *
     * @return The phone number entered by the user.
     */

    private String requestCollaboratorPhoneNumber() {
        String phoneNumber;
        while (true) {
            System.out.print("Collaborator Phone Number: ");
            phoneNumber = scanner.nextLine();
            if (phoneNumber == null || !phoneNumber.matches("\\d{9}")) {
                System.out.println("Phone number must have 9 digits.");
            } else {
                // Valid input, exit loop
                break;
            }
        }
        return phoneNumber;
    }

    /**
     * Requests the collaborator's address from the user.
     * Validates the input to ensure it is not empty.
     *
     * @return The address entered by the user.
     */

    private String requestCollaboratorAddress() {
        String address;
        while (true) {
            System.out.print("Collaborator Address: ");
            address = scanner.nextLine();
            if (address == null || address.trim().isEmpty()) {
                System.out.println("Address must be provided.");
            } else {
                // Valid input, exit loop
                break;
            }
        }
        return address;
    }

    /**
     * Requests the collaborator's admission date from the user.
     * Validates the input to ensure it is in the correct format.
     *
     * @return The admission date entered by the user.
     */

    private LocalDate requestCollaboratorAdmissionDate() {
        LocalDate date;
        while (true) {
            System.out.print("Collaborator Admission Date (dd-mm-yyyy): ");
            String input = scanner.nextLine();
            try {
                date = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                break; // If parsing succeeds, exit loop
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter date in dd-mm-yyyy format.");
            }
        }
        return date;
    }

    /**
     * Requests the collaborator's birthday date from the user.
     * Validates the input to ensure it is in the correct format.
     *
     * @return The birthday date entered by the user.
     */
    private LocalDate requestCollaboratorBirthdayDate() {
        LocalDate date;
        while (true) {
            System.out.print("Collaborator Birthday Date (dd-mm-yyyy): ");
            String input = scanner.nextLine();
            try {
                date = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                break; // If parsing succeeds, exit loop
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter date in dd-mm-yyyy format.");
            }
        }
        return date;
    }

    /**
     * Requests the collaborator's name from the user.
     * Validates the input to ensure it is not empty.
     *
     * @return The name entered by the user.
     */
    private String requestCollaboratorName() {
        String name;
        while (true) {
            System.out.print("Collaborator Name: ");
            name = scanner.nextLine();
            if (name == null || name.trim().isEmpty()) {
                System.out.println("Name must be provided.");
            } else {
                // Valid input, exit loop
                break;
            }
        }
        return name;
    }

    /**
     * Requests the collaborator's job from the user.
     * Displays a list of available jobs retrieved from the controller.
     * Prompts the user to select a job by entering the corresponding number.
     * Validates the input to ensure it is a valid integer within the range of available jobs.
     *
     * @return The name of the selected job.
     */
    private String requestCollaboratorJob() {
        System.out.println("Available Jobs:");
        List<Job> jobList = controller.getAvailableJobs();

        for (int i = 0; i < jobList.size(); i++) {
            System.out.println((i + 1) + ". " + jobList.get(i).getJobName());
        }

        int selectedJobIndex;
        while (true) {
            System.out.print("Select job: ");
            try {
                selectedJobIndex = Integer.parseInt(scanner.nextLine());
                if (selectedJobIndex >= 1 && selectedJobIndex <= jobList.size()) {
                    break; // Valid input, exit loop
                } else {
                    System.out.println("Invalid input. Please select a valid job.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        // Return the name of the selected job
        return jobList.get(selectedJobIndex - 1).getJobName();
    }
}
