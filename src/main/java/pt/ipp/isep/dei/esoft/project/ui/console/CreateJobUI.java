package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateJobController;

import java.util.Scanner;

public class CreateJobUI implements Runnable {

    private final CreateJobController controller;
    private final Scanner scanner;

    public CreateJobUI() {
        this.controller = new CreateJobController();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        System.out.println("\n\n--- Create Job ------------------------");

        // Request job details from the user
        String jobDescription = requestJobName();

        // Submit job data to the controller
        submitJobData(jobDescription);
    }

    private String requestJobName() {
        System.out.print("Enter job name: ");
        return scanner.nextLine();
    }

    private void submitJobData(String jobName) {
        boolean success = controller.createJob(jobName);

        if (success) {
            System.out.println("Job created successfully!");
        } else {
            System.out.println("Failed to create job. Please try again.");
        }
    }

    public static void main(String[] args) {
        CreateJobUI createJobUI = new CreateJobUI();
        createJobUI.run();
    }
}
