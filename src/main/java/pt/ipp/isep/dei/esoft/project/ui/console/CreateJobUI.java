package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.application.controller.*;


import java.util.List;
import java.util.Scanner;

/**
 * The {@code CreateJobUI} class represents a user interface for creating jobs.
 * It allows users to input job details and submits the data to the controller for job creation.
 * The class implements the {@code Runnable} interface, allowing it to be executed as a thread.
 */
public class CreateJobUI implements Runnable {

    private final CreateJobController controller;
    private final Scanner scanner;

    /**
     * Constructs a new {@code CreateJobUI} object.
     * Initializes the controller and scanner.
     */
    public CreateJobUI() {
        this.controller = new CreateJobController();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Runs the UI for creating a job.
     * Prompts the user to enter job details and submits the data to the controller for job creation.
     */
    @Override
    public void run() {
        System.out.println("\n\n--- Create Job ------------------------");

        // Request job details from the user
        String jobDescription = requestJobName();

        // Submit job data to the controller
        submitJobData(jobDescription);
    }

    /**
     * Requests the job name from the user.
     *
     * @return The job name entered by the user.
     */
    private String requestJobName() {
        System.out.print("Enter job name: ");
        return scanner.nextLine();
    }

    /**
     * Submits the job data to the controller for job creation.
     *
     * @param jobName The name of the job to be created.
     */
    private void submitJobData(String jobName) {
        boolean success = controller.createJob(jobName);

        if (success) {
            System.out.println("Job created successfully!");
        } else {
            System.out.println("Failed to create job. Please try again.");
        }
    }

    /**
     * The main method to run the CreateJobUI.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        CreateJobUI createJobUI = new CreateJobUI();
        createJobUI.run();
    }
}

