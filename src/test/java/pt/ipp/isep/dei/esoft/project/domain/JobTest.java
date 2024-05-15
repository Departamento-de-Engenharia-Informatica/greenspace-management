package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Job.
 */
class JobTest {

    /**
     * Tests the creation of a valid job.
     */
    @Test
    void createValidJob() {
        // Arrange
        String jobName = "Software Engineer";

        // Act
        Job job = new Job(jobName);

        // Assert
        assertEquals(jobName, job.getJobName());
    }

    /**
     * Tests creating a job with a null name, which should throw an IllegalArgumentException.
     */
    @Test
    void createJobWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Job(null));
    }

    /**
     * Tests creating a job with an empty name, which should throw an IllegalArgumentException.
     */
    @Test
    void createJobWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> new Job(""));
    }

    /**
     * Tests creating a job with an invalid name containing digits, which should throw an IllegalArgumentException.
     */
    @Test
    void createJobWithInvalidNameContainingDigits() {
        assertThrows(IllegalArgumentException.class, () -> new Job("Software Engineer 2"));
    }

    /**
     * Tests creating a job with an invalid name containing special characters, which should throw an IllegalArgumentException.
     */
    @Test
    void createJobWithInvalidNameContainingSpecialCharacters() {
        assertThrows(IllegalArgumentException.class, () -> new Job("Software@Engineer"));
    }

    /**
     * Tests if equals method returns true when comparing the same instance.
     */
    @Test
    void equals_sameInstance_returnsTrue() {
        // Arrange
        Job job = new Job("Software Engineer");

        // Act & Assert
        assertEquals(job, job);
    }

    /**
     * Tests if equals method returns true when comparing equal jobs.
     */
    @Test
    void equals_equalJobs_returnsTrue() {
        // Arrange
        Job job1 = new Job("Software Engineer");
        Job job2 = new Job("Software Engineer");

        // Act & Assert
        assertEquals(job1, job2);
    }

    /**
     * Tests if equals method returns false when comparing different jobs.
     */
    @Test
    void equals_differentJobs_returnsFalse() {
        // Arrange
        Job job1 = new Job("Software Engineer");
        Job job2 = new Job("Data Analyst");

        // Act & Assert
        assertNotEquals(job1, job2);
    }

    /**
     * Tests the clone method to ensure it returns a new instance with the same attributes.
     */
    @Test
    void clone_returnsNewInstanceWithSameAttributes() {
        // Arrange
        Job originalJob = new Job("Software Engineer");

        // Act
        Job clonedJob = originalJob.clone();

        // Assert
        assertNotSame(originalJob, clonedJob);
        assertEquals(originalJob, clonedJob);
    }

    /**
     * Tests adding a duplicate job to the repository, which should fail.
     */
    @Test
    void addingDuplicateJobFails() {
        // Arrange
        JobRepository jobRepository = new JobRepository();
        Job job1 = new Job("Software Engineer");
        Job job2 = new Job("Software Engineer");

        // Act
        jobRepository.add(job1);
        Optional<Job> result = jobRepository.add(job2);

        // Assert
        assertTrue(result.isEmpty(), "Adding duplicate job should fail");
    }
}
