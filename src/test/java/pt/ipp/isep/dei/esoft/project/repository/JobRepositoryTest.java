package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for JobRepository.
 */
public class JobRepositoryTest {

    private JobRepository jobRepository;

    /**
     * Sets up the test environment before each test method runs.
     */
    @BeforeEach
    public void setUp() {
        jobRepository = new JobRepository();
    }

    /**
     * Tests adding a job to the repository and retrieving it by name.
     */
    @Test
    public void testAddAndGetJobByName() {
        // Add a job to the repository
        Job job = new Job("Software Developer");
        Optional<Job> addedJob = jobRepository.add(job);

        // Ensure the job was added successfully
        assertTrue(addedJob.isPresent());

        // Retrieve the added job by name
        Job retrievedJob = jobRepository.getJobByName("Software Developer");

        // Check if the retrieved job matches the added job
        assertEquals(job, retrievedJob);
    }

    /**
     * Tests adding a duplicate job to the repository.
     */
    @Test
    public void testAddDuplicateJob() {
        // Add a job to the repository
        Job job = new Job("Software Developer");
        Optional<Job> addedJob1 = jobRepository.add(job);

        // Ensure the job was added successfully
        assertTrue(addedJob1.isPresent());

        // Attempt to add the same job again
        Optional<Job> addedJob2 = jobRepository.add(job);

        // Ensure the second addition fails and returns empty optional
        assertTrue(addedJob2.isEmpty());
    }

    /**
     * Tests attempting to retrieve a job by name that doesn't exist in the repository.
     */
    @Test
    public void testGetJobByNameNonExistent() {
        // Attempt to retrieve a job that doesn't exist in the repository
        assertThrows(IllegalArgumentException.class, () -> jobRepository.getJobByName("Nonexistent Job"));
    }

    /**
     * Tests retrieving the list of jobs from the repository.
     */
    @Test
    public void testGetJobList() {
        // Add some jobs to the repository
        jobRepository.add(new Job("Software Developer"));
        jobRepository.add(new Job("Data Analyst"));
        jobRepository.add(new Job("Project Manager"));

        // Retrieve the list of jobs from the repository
        List<Job> jobList = jobRepository.getJobList();

        // Ensure the size of the retrieved list matches the number of added jobs
        assertEquals(3, jobList.size());

        // Ensure each added job is present in the retrieved list
        assertTrue(jobList.contains(new Job("Software Developer")));
        assertTrue(jobList.contains(new Job("Data Analyst")));
        assertTrue(jobList.contains(new Job("Project Manager")));
    }
}
