package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JobRepository {

    private final List<Job> jobList;

    public JobRepository() {
        jobList = new ArrayList<>();
    }

    /**
     * This method returns an existing job by its description.
     *
     * @param jobName The description of the job to be retrieved.
     * @return The job.
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

    private boolean validateJob(Job job) {
        boolean isValid = !jobList.contains(job);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of jobs.
     *
     * @return The list of jobs.
     */
    public List<Job> getJobList() {
        // This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(jobList);
    }
}
