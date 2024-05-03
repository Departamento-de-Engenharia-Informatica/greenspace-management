package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * The {@code Job} class represents a job entity.
 * It encapsulates the information related to a job, including its name.
 */
public class Job {

    // Instance variable
    private final String jobName;

    /**
     * Constructs a new {@code Job} object with the specified name.
     *
     * @param jobName The name of the job.
     * @throws IllegalArgumentException If the job name is null, empty, contains digits, or special characters.
     */
    public Job(String jobName) throws IllegalArgumentException {
        validateJobName(jobName);
        this.jobName = jobName;
    }

    /**
     * Validates the job name to ensure it meets the required criteria.
     *
     * @param jobName The name of the job to be validated.
     * @throws IllegalArgumentException If the job name is null, empty, contains digits, or special characters.
     */
    private void validateJobName(String jobName) throws IllegalArgumentException {
        if (jobName == null || jobName.isEmpty()) {
            throw new IllegalArgumentException("Job name cannot be null or empty.");
        }
        // Check if job name contains any digits or special characters
        if (jobName.matches(".*\\d.*") || !jobName.matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("Job name cannot contain numbers or special characters.");
        }
    }

    /**
     * Retrieves the name of the job.
     *
     * @return The name of the job.
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * Checks if this job is equal to another object.
     *
     * @param o The object to compare with this job.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Job)) return false;
        Job job = (Job) o;
        return Objects.equals(jobName, job.jobName);
    }

    /**
     * Creates a clone of this job.
     *
     * @return A new {@code Job} object with the same name as this job.
     */
    public Job clone() {
        return new Job(this.jobName);
    }

    /**
     * Generates a hash code value for this job.
     *
     * @return The hash code value for this job.
     */
    @Override
    public int hashCode() {
        return Objects.hash(jobName);
    }

    /**
     * Returns a string representation of the job.
     *
     * @return A string representation of the job, including its name.
     */
    @Override
    public String toString() {
        return "Job{" +
                "jobName='" + jobName + '\'' +
                '}';
    }
}
