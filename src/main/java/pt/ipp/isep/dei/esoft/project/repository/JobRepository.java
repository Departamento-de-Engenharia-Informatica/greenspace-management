package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The {@code JobRepository} class represents a repository for storing and retrieving job entities.
 * It provides methods for adding jobs to the repository, retrieving jobs by name,
 * and obtaining a defensive copy of the list of jobs.
 */
public class JobRepository {

    private final List<Job> jobList;

    /**
     * Constructs a new {@code JobRepository} object.
     * Initializes the list of jobs.
     */
    public JobRepository() {
        jobList = new ArrayList<>();
    }

    /**
     * Retrieves an existing job by its description.
     *
     * @param jobName The description of the job to be retrieved.
     * @return The job with the specified description.
     * @throws IllegalArgumentException if the job does not exist.
     */
    public Job getJobByName(String jobName) {
        Job foundJob = null;
        for (Job job : jobList) {
            if (job.getJobName().equals(jobName)) {
                foundJob = job;
                break;
            }
        }
        if (foundJob == null) {
            throw new IllegalArgumentException(
                    "Job requested for [" + jobName + "] does not exist.");
        }
        return foundJob;
    }

    /**
     * Adds a new job to the repository.
     *
     * @param job The job to be added.
     * @return An optional containing the newly added job if successful, empty otherwise.
     */
    public Optional<Job> add(Job job) {
        Optional<Job> newJob = Optional.empty();
        boolean operationSuccess = false;

        if (validateJob(job)) {
            newJob = Optional.of(job.clone());
            operationSuccess = jobList.add(newJob.get());
        }

        if (!operationSuccess) {
            newJob = Optional.empty();
        }

        return newJob;
    }

    /**
     * Validates whether a job can be added to the repository.
     * Checks if the job already exists in the repository.
     *
     * @param job The job to be validated.
     * @return True if the job is valid (not already in the repository), false otherwise.
     */
    private boolean validateJob(Job job) {
        return !jobList.contains(job);
    }

    /**
     * Returns a defensive (immutable) copy of the list of jobs.
     *
     * @return The list of jobs.
     */
    public List<Job> getJobList() {
        // This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(jobList);
    }
}
