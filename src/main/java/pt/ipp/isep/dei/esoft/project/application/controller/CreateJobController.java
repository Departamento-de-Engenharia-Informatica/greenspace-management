package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.List;
import java.util.Optional;

public class CreateJobController {
    private OrganizationRepository organizationRepository;
    private TaskCategoryRepository taskCategoryRepository;
    private AuthenticationRepository authenticationRepository;
    private static JobRepository jobRepository;


    //Repository instances are obtained from the Repositories class
    public CreateJobController() {
        getOrganizationRepository();
        getTaskCategoryRepository();
        getAuthenticationRepository();
        getJobRepository();
    }

    private TaskCategoryRepository getTaskCategoryRepository() {
        if (taskCategoryRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            taskCategoryRepository = repositories.getTaskCategoryRepository();
        }
        return taskCategoryRepository;
    }

    private OrganizationRepository getOrganizationRepository() {
        if (organizationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            organizationRepository = repositories.getOrganizationRepository();
        }
        return organizationRepository;

    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }
    private JobRepository getJobListRepository() {
        if (jobRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the jobRepository
            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
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

    public static List<Job> getJobList() {
        // Assuming jobRepository is an instance variable of the class
        // You need to access the jobRepository instance to retrieve the job list
        return jobRepository.getJobList();
    }
}
