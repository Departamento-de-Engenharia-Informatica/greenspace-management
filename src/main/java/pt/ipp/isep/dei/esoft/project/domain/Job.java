package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Job {
    private final String jobName;

    public Job(String jobName) {
        validateJobName(jobName);
        this.jobName = jobName;
    }

    private void validateJobName(String jobName) {
        if (jobName == null || jobName.isEmpty()) {
            throw new IllegalArgumentException("Job name cannot be null or empty.");
        }
        // Check if job name contains any digits or special characters
        if (jobName.matches(".*\\d.*") || !jobName.matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("Job name cannot contain numbers or special characters.");
        }
    }

    public String getJobName() {
        return jobName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Job)) return false;
        Job job = (Job) o;
        return Objects.equals(jobName, job.jobName);
    }

    public Job clone() {
        return new Job(this.jobName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobName);
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobName='" + jobName + '\'' +
                '}';
    }
}
