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

/**
 * The {@code CreateJobController} class manages the creation and retrieval of job entities.
 * It provides methods to create new job instances and retrieve the list of available jobs.
 */
public class CreateJobController {

    // Instance variables
    private OrganizationRepository organizationRepository;
    private TaskCategoryRepository taskCategoryRepository;
    private AuthenticationRepository authenticationRepository;
    private static JobRepository jobRepository;

    /**
     * Constructs a new {@code CreateJobController} object.
     * Initializes repositories obtained from the Repositories class.
     */
    public CreateJobController() {
        getOrganizationRepository();
        getTaskCategoryRepository();
        getAuthenticationRepository();
        getJobRepository();
    }

    /**
     * Constructs a new {@code CreateJobController} object with specified repositories.
     *
     * @param organizationRepository    The organization repository.
     * @param taskCategoryRepository    The task category repository.
     * @param authenticationRepository  The authentication repository.
     * @param jobRepository             The job repository.
     */
    public CreateJobController(OrganizationRepository organizationRepository,
                               TaskCategoryRepository taskCategoryRepository,
                               AuthenticationRepository authenticationRepository,
                               JobRepository jobRepository) {
        this.organizationRepository = organizationRepository;
        this.taskCategoryRepository = taskCategoryRepository;
        this.authenticationRepository = authenticationRepository;
        this.jobRepository = jobRepository;
    }

    /**
     * Obtains the task category repository instance.
     *
     * @return The task category repository instance.
     */
    private TaskCategoryRepository getTaskCategoryRepository() {
        if (taskCategoryRepository == null) {
            Repositories repositories = Repositories.getInstance();
            taskCategoryRepository = repositories.getTaskCategoryRepository();
        }
        return taskCategoryRepository;
    }

    /**
     * Obtains the organization repository instance.
     *
     * @return The organization repository instance.
     */
    private OrganizationRepository getOrganizationRepository() {
        if (organizationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            organizationRepository = repositories.getOrganizationRepository();
        }
        return organizationRepository;
    }

    /**
     * Obtains the authentication repository instance.
     *
     * @return The authentication repository instance.
     */
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Obtains the job repository instance.
     *
     * @return The job repository instance.
     */
    private JobRepository getJobRepository() {
        if (jobRepository == null) {
            Repositories repositories = Repositories.getInstance();
            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }

    /**
     * Creates a new job with the given name.
     *
     * @param jobName The name of the job.
     * @return {@code true} if the job creation was successful, {@code false} otherwise.
     */
    public boolean createJob(String jobName) {
        Job job = new Job(jobName);
        Optional<Job> newJob = jobRepository.add(job);
        return newJob.isPresent();
    }

    /**
     * Retrieves the list of available jobs.
     *
     * @return A list of Job objects representing the available jobs.
     */
    public static List<Job> getJobList() {
        return jobRepository.getJobList();
    }


}

