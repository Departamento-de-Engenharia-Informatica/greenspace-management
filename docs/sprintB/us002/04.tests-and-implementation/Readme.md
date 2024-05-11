# US002 - Create a job

## 4.1 Tests 

**Test 1:** Tests the creation of a valid job.

	 @Test
    void createValidJob() {
        // Arrange
        String jobName = "Software Engineer";

        // Act
        Job job = new Job(jobName);

        // Assert
        assertEquals(jobName, job.getJobName());
    }



**Test 2:** Tests creating a job with a null name, which should throw an IllegalArgumentException.

    @Test
    void createJobWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Job(null));
    }


**Test 3:** Tests creating a job with an invalid name containing digits, which should throw an IllegalArgumentException.
  
    @Test
    void createJobWithInvalidNameContainingDigits() {
        assertThrows(IllegalArgumentException.class, () -> new Job("Software Engineer 2"));
    }

**Test 4:** Tests creating a job with an invalid name containing special characters, which should throw an IllegalArgumentException.

    @Test
    void createJobWithInvalidNameContainingSpecialCharacters() {
        assertThrows(IllegalArgumentException.class, () -> new Job("Software@Engineer"));
    }

    
**Test 5:** Tests if equals method returns true when comparing the same instance.
    
    @Test
    void equals_sameInstance_returnsTrue() {
        // Arrange
        Job job = new Job("Software Engineer");

        // Act & Assert
        assertEquals(job, job);
    }

**Test 6:** Tests if equals method returns true when comparing equal jobs.

    @Test
    void equals_equalJobs_returnsTrue() {
    // Arrange
    Job job1 = new Job("Software Engineer");
    Job job2 = new Job("Software Engineer");

        // Act & Assert
        assertEquals(job1, job2);
    }

    
**Test 7:**  Tests if equals method returns false when comparing different jobs.
     
    @Test
    void equals_differentJobs_returnsFalse() {
        // Arrange
        Job job1 = new Job("Software Engineer");
        Job job2 = new Job("Data Analyst");

        // Act & Assert
        assertNotEquals(job1, job2);
    }

    
**Test 8:**   Tests the clone method to ensure it returns a new instance with the same attributes.
     
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

    
**Test 9:** Tests adding a duplicate job to the repository, which should fail.
     
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

## 4.2 Repository tests


**Test 1:** Tests adding a job to the repository and retrieving it by name.

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


**Test 2:** Tests adding a duplicate job to the repository.
     
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

    
**Test 3:** Tests attempting to retrieve a job by name that doesn't exist in the repository.
     
    @Test
    public void testGetJobByNameNonExistent() {
        // Attempt to retrieve a job that doesn't exist in the repository
        assertThrows(IllegalArgumentException.class, () -> jobRepository.getJobByName("Nonexistent Job"));
    }

    
**Test 4:** Tests retrieving the list of jobs from the repository.
     
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





## 5. Construction (Implementation)

### Class CreateCollaboratorController 

```java
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
```

### Class Job

```java
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
```

### Class JobRepository

```java
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

```


## 6. Integration and Demo 

* A new option on the HRM menu options was added.

* For demo purposes some collaborators are bootstrapped while system starts.


## 7. Observations

n/a