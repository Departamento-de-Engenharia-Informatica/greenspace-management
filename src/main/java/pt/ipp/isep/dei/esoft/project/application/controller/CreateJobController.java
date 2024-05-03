package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.List;
import java.util.Optional;

public class CreateJobController {
    private OrganizationRepository organizationRepository;
    private TaskCategoryRepository taskCategoryRepository;
    private AuthenticationRepository authenticationRepository;
    private JobRepository jobRepository;


    //Repository instances are obtained from the Repositories class
    public CreateJobController() {
        getOrganizationRepository();
        getTaskCategoryRepository();
        getAuthenticationRepository();
        getJobRepository();
    }

    private void getAuthenticationRepository() {
    }

    private void getTaskCategoryRepository() {
    }

    private void getOrganizationRepository() {
    }


    //Allows receiving the repositories as parameters for testing purposes
    public CreateJobController(OrganizationRepository organizationRepository,
                                TaskCategoryRepository taskCategoryRepository,
                                AuthenticationRepository authenticationRepository,
                                JobRepository jobRepository) {
        this.organizationRepository = organizationRepository;
        this.taskCategoryRepository = taskCategoryRepository;
        this.authenticationRepository = authenticationRepository;
        this.jobRepository = jobRepository;
    }

    // Allows receiving the repository as a parameter for testing purposes
    public CreateJobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    private JobRepository getJobRepository() {
        if (jobRepository == null) {
            Repositories repositories = Repositories.getInstance();

            // Get the JobRepository
            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }

    public boolean createJob(String jobName) {
        Job job = new Job(jobName);
        Optional<Job> newJob = jobRepository.add(job);

        return newJob.isPresent(); // Returns true if job creation was successful, false otherwise
    }
}
