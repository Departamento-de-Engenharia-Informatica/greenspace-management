package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class JobTest {

    @Test
    void createValidJob() {

        String jobName = "Software Engineer";


        Job job = new Job(jobName);


        assertEquals(jobName, job.getJobName());
    }

    @Test
    void createJobWithNullName() {

        assertThrows(IllegalArgumentException.class, () -> new Job(null));
    }

    @Test
    void createJobWithEmptyName() {

        assertThrows(IllegalArgumentException.class, () -> new Job(""));
    }

    @Test
    void createJobWithInvalidNameContainingDigits() {

        assertThrows(IllegalArgumentException.class, () -> new Job("Software Engineer 2"));
    }

    @Test
    void createJobWithInvalidNameContainingSpecialCharacters() {

        assertThrows(IllegalArgumentException.class, () -> new Job("Software@Engineer"));
    }

    @Test
    void equals_sameInstance_returnsTrue() {
        // Arrange
        Job job = new Job("Software Engineer");

        // Act & Assert
        assertEquals(job, job);
    }

    @Test
    void equals_equalJobs_returnsTrue() {
        // Arrange
        Job job1 = new Job("Software Engineer");
        Job job2 = new Job("Software Engineer");

        // Act & Assert
        assertEquals(job1, job2);
    }

    @Test
    void equals_differentJobs_returnsFalse() {
        // Arrange
        Job job1 = new Job("Software Engineer");
        Job job2 = new Job("Data Analyst");

        // Act & Assert
        assertNotEquals(job1, job2);
    }

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

