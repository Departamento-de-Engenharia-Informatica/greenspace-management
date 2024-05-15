package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.List;

/**
 * The {@code DisplayJobsUI} class represents a user interface for displaying a list of jobs.
 * It retrieves the list of jobs from the controller and displays them to the user.
 * The class implements the {@code Runnable} interface, allowing it to be executed as a thread.
 */
public class DisplayJobsUI implements Runnable {

    /**
     * Runs the UI for displaying the list of jobs.
     * Retrieves the list of jobs from the controller and displays them to the user.
     */
    @Override
    public void run() {
        displayJobList();
    }

    /**
     * Displays the list of jobs to the user.
     * Retrieves the list of jobs from the controller and prints their names.
     */
    public void displayJobList() {
        List<Job> jobList = CreateJobController.getJobList();

        if (jobList.isEmpty()) {
            System.out.println("No jobs created yet.");
        } else {
            System.out.println("List of Jobs:");
            for (Job job : jobList) {
                System.out.println(job.getJobName());
            }
        }
    }
}

