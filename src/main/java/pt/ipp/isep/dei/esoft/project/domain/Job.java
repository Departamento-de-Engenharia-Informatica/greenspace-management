package pt.ipp.isep.dei.esoft.project.domain;


import java.util.Objects;

public class Job {

    private final String description;

    public Job(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Job)) {
            return false;
        }
        Job that = (Job) o;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    /**
     * This method returns the description of the job.
     *
     * @return The description of the job.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Clone method.
     *
     * @return A clone of the current job.
     */
    public Job clone() {
        return new Job(this.description);
    }
}
