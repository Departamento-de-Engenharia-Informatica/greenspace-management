package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.List;

public class DisplayJobsUI  implements Runnable{
    @Override
    public void run() {
        displayJobList();
    }
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
