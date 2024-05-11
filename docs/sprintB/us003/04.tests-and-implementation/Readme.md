# US003 - Create a collaborator with a job

## 4. Tests

**Test 1:** Check that it is not possible to create a name with null values

	 @Test
    public void testConstructor_NullName_ThrowsIllegalArgumentException() {
        // Arrange
        LocalDate birthdayDate = LocalDate.of(1990, 5, 15);
        LocalDate admissionDate = LocalDate.of(2020, 1, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Collaborator(null,
                birthdayDate, admissionDate,
                "123 Main St", "123456789",
                "john.doe@example.com",
                123456789, 12345678L, "Software Developer"));
    }



**Test 2:** Tests if the constructor throws IllegalArgumentException when birthday date is null.

@Test
public void testConstructor_NullBirthdayDate_ThrowsIllegalArgumentException() {
// Arrange
LocalDate admissionDate = LocalDate.of(2020, 1, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Collaborator("John Doe",
                null, admissionDate,
                "123 Main St", "123456789",
                "john.doe@example.com",
                123456789, 12345678L, "Software Developer"));
    }


**Test 3:** Tests if the constructor throws IllegalArgumentException when admission date is null.

    @Test
    public void testConstructor_NullAdmissionDate_ThrowsIllegalArgumentException() {
        // Arrange
        LocalDate birthdayDate = LocalDate.of(1990, 5, 15);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Collaborator("John Doe",
                birthdayDate, null,
                "123 Main St", "123456789",
                "john.doe@example.com",
                123456789, 12345678L, "Software Developer"));
    }

**Test 4:** Tests if the constructor throws IllegalArgumentException when address is null.

    @Test
    public void testConstructor_NullAddress_ThrowsIllegalArgumentException() {
        // Arrange
        LocalDate birthdayDate = LocalDate.of(1990, 5, 15);
        LocalDate admissionDate = LocalDate.of(2020, 1, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Collaborator("John Doe",
                birthdayDate, admissionDate,
                null, "123456789",
                "john.doe@example.com",
                123456789, 12345678L, "Software Developer"));
    }


**Test 5:** Tests if the constructor throws IllegalArgumentException when phone number is invalid.

    @Test
    public void testConstructor_InvalidPhoneNumber_ThrowsIllegalArgumentException() {
        // Arrange
        LocalDate birthdayDate = LocalDate.of(1990, 5, 15);
        LocalDate admissionDate = LocalDate.of(2020, 1, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Collaborator("John Doe",
                birthdayDate, admissionDate,
                "123 Main St", "1234567", // Invalid phone number (less than 9 digits)
                "john.doe@example.com",
                123456789, 12345678L, "Software Developer"));
    }


**Test 6:**  Tests if the constructor throws IllegalArgumentException when email is invalid.

    @Test
    public void testConstructor_InvalidEmail_ThrowsIllegalArgumentException() {
        // Arrange
        LocalDate birthdayDate = LocalDate.of(1990, 5, 15);
        LocalDate admissionDate = LocalDate.of(2020, 1, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Collaborator("John Doe",
                birthdayDate, admissionDate,
                "123 Main St", "123456789",
                "johndoeexample.com", // Invalid email (missing "@")
                123456789, 12345678L, "Software Developer"));
    }


**Test 6:**  Tests if the constructor throws IllegalArgumentException when taxpayer number is invalid.

    @Test
    public void testConstructor_InvalidTaxpayerNumber_ThrowsIllegalArgumentException() {
        // Arrange
        LocalDate birthdayDate = LocalDate.of(1990, 5, 15);
        LocalDate admissionDate = LocalDate.of(2020, 1, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Collaborator("John Doe",
                birthdayDate, admissionDate,
                "123 Main St", "123456789",
                "john.doe@example.com",
                -123456789, // Invalid taxpayer number (negative)
                12345678L, "Software Developer"));
    }


**Test 7:** Tests if the constructor throws IllegalArgumentException when BI number is invalid.

    @Test
    public void testConstructor_InvalidBINumber_ThrowsIllegalArgumentException() {
        // Arrange
        LocalDate birthdayDate = LocalDate.of(1990, 5, 15);
        LocalDate admissionDate = LocalDate.of(2020, 1, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Collaborator("John Doe",
                birthdayDate, admissionDate,
                "123 Main St", "123456789",
                "john.doe@example.com",
                123456789, // Valid taxpayer number
                1234567L, // Invalid BI number (less than 8 digits)
                "Software Developer"));
    }


**Test 8:**  Tests if the constructor throws IllegalArgumentException when job is null.

    @Test
    public void testConstructor_NullJob_ThrowsIllegalArgumentException() {
        // Arrange
        LocalDate birthdayDate = LocalDate.of(1990, 5, 15);
        LocalDate admissionDate = LocalDate.of(2020, 1, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Collaborator("John Doe",
                birthdayDate, admissionDate,
                "123 Main St", "123456789",
                "john.doe@example.com",
                123456789, 12345678L, null)); // Null job
    }



## 5. Construction (Implementation)

### Class CreateCollaboratorController

```java
public class CreateCollaboratorController {

    private final CollaboratorRepository collaboratorRepository;
    private final AuthenticationRepository authenticationRepository;

    /**
     * Constructs a new {@code CreateCollaboratorController} object.
     * Initializes the collaborator repository and authentication repository.
     */
    public CreateCollaboratorController() {
        Repositories repositories = Repositories.getInstance();
        this.collaboratorRepository = repositories.getCollaboratorRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
    }

    /**
     * Creates a new collaborator with the provided information.
     * Adds the collaborator to the repository if it is valid and does not already exist.
     *
     * @param name            The name of the collaborator.
     * @param birthdayDate    The birthday date of the collaborator.
     * @param admissionDate   The admission date of the collaborator.
     * @param address         The address of the collaborator.
     * @param phoneNumber     The phone number of the collaborator.
     * @param email           The email address of the collaborator.
     * @param taxpayerNumber  The taxpayer number of the collaborator.
     * @param BINumber        The BI number of the collaborator.
     * @param job             The job of the collaborator.
     * @return An optional containing the newly created collaborator, if successful.
     */
    public Optional<Collaborator> createCollaborator(String name, LocalDate birthdayDate, LocalDate admissionDate,
                                                     String address, String phoneNumber, String email,
                                                     int taxpayerNumber, long BINumber, String job) {
        Collaborator collaborator = new Collaborator(name, birthdayDate, admissionDate,
                address, phoneNumber, email, taxpayerNumber, BINumber, job);
        return collaboratorRepository.add(collaborator);
    }

    /**
     * Retrieves a list of all collaborators.
     *
     * @return A list of collaborators.
     */
    public static List<Collaborator> getCollaboratorsList() {
        return CollaboratorRepository.getCollaborators();
    }

    /**
     * Retrieves a list of available jobs for collaborators.
     *
     * @return A list of available jobs.
     */
    public List<Job> getAvailableJobs() {
        // Call a method from your Job controller to get the list of jobs
        return CreateJobController.getJobList();
    }

    /**
     * Checks if the provided taxpayer number already exists for another collaborator.
     *
     * @param taxpayerNumber The taxpayer number to check.
     * @return {@code true} if the taxpayer number is a duplicate, {@code false} otherwise.
     */
    public boolean isTaxpayerNumberDuplicate(int taxpayerNumber) {
        return collaboratorRepository.isTaxpayerNumberDuplicate(taxpayerNumber);
    }

    /**
     * Checks if the provided BI number already exists for another collaborator.
     *
     * @param biNumber The BI number to check.
     * @return {@code true} if the BI number is a duplicate, {@code false} otherwise.
     */
    public boolean isBINumberDuplicate(long biNumber) {
        return collaboratorRepository.isBINumberDuplicate(biNumber);
    }
}
```

### Class Collaborator

```java
public class Collaborator {

    private String name;
    private LocalDate birthdayDate;
    private LocalDate admissionDate;
    private String address;
    private String phoneNumber;
    private String email;
    private int taxpayerNumber;
    private long BINumber;
    private String job;

    /**
     * Constructs a new {@code Collaborator} object with the specified attributes.
     *
     * @param name           The name of the collaborator.
     * @param birthdayDate   The birthdate of the collaborator.
     * @param admissionDate  The admission date of the collaborator.
     * @param address        The address of the collaborator.
     * @param phoneNumber    The phone number of the collaborator.
     * @param email          The email address of the collaborator.
     * @param taxpayerNumber The taxpayer number of the collaborator.
     * @param BINumber       The BI (Identity Card) number of the collaborator.
     * @param job            The job position of the collaborator.
     * @throws IllegalArgumentException If any of the input parameters are invalid.
     */
    public Collaborator(String name, LocalDate birthdayDate, LocalDate admissionDate,
                        String address, String phoneNumber, String email,
                        int taxpayerNumber, long BINumber, String job) throws IllegalArgumentException {
        validateInputs(name, birthdayDate, admissionDate, address, phoneNumber, email, taxpayerNumber, BINumber, job);
        this.name = name;
        this.birthdayDate = birthdayDate;
        this.admissionDate = admissionDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.taxpayerNumber = taxpayerNumber;
        this.BINumber = BINumber;
        this.job = job;
    }

    /**
     * Validates the input parameters for creating a collaborator.
     * Throws an exception if any parameter is invalid.
     */
    private void validateInputs(String name, LocalDate birthdayDate, LocalDate admissionDate,
                                String address, String phoneNumber, String email,
                                int taxpayerNumber, long BINumber, String job) throws IllegalArgumentException {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Name must be provided.");
            return; // Exit method, allowing the user to correct the input
        }

        if (birthdayDate == null) {
            System.out.println("Birthday date must be provided.");
            return;
        }

        if (admissionDate == null) {
            System.out.println("Admission date must be provided.");
            return;
        }

        if (address == null || address.trim().isEmpty()) {
            System.out.println("Address must be provided.");
            return;
        }

        if (phoneNumber == null || !phoneNumber.matches("\\d{9}")) {
            System.out.println("Phone number must be 9 digits.");
            return;
        }

        if (email == null || !email.contains("@")) {
            System.out.println("Email must be provided and contain '@'.");
            return;
        }

        if (taxpayerNumber <= 0 || String.valueOf(taxpayerNumber).length() != 9) {
            System.out.println("Taxpayer number must be a positive integer of 9 digits.");
            return;
        }

        if (String.valueOf(BINumber).length() != 8) {
            System.out.println("BI number must be an integer of 8 digits.");
            return;
        }

        if (job == null || job.trim().isEmpty()) {
            System.out.println("Job must be provided.");
            return;
        }
    }


    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The object to compare this instance with.
     * @return {@code true} if the specified object is equal to this collaborator;
     *         {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collaborator that = (Collaborator) o;
        return taxpayerNumber == that.taxpayerNumber &&
                BINumber == that.BINumber &&
                Objects.equals(name, that.name) &&
                Objects.equals(birthdayDate, that.birthdayDate) &&
                Objects.equals(admissionDate, that.admissionDate) &&
                Objects.equals(address, that.address) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(email, that.email) &&
                Objects.equals(job, that.job);
    }





    /**
     * Creates and returns a new instance of the Collaborator class with the same attribute values as this instance.
     *
     * @return A new Collaborator object with the same attribute values as this instance.
     */
    @Override
    public Collaborator clone() {
        return new Collaborator(name, birthdayDate, admissionDate, address, phoneNumber, email, taxpayerNumber, BINumber,job);
    }


    /**
     * Returns the name of the collaborator.
     *
     * @return The name of the collaborator.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the birth date of the collaborator.
     *
     * @return The birth date of the collaborator.
     */
    public LocalDate getBirthdayDate() {
        return birthdayDate;
    }

    /**
     * Returns the admission date of the collaborator.
     *
     * @return The admission date of the collaborator.
     */
    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    /**
     * Returns the address of the collaborator.
     *
     * @return The address of the collaborator.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the phone number of the collaborator.
     *
     * @return The phone number of the collaborator.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns the email address of the collaborator.
     *
     * @return The email address of the collaborator.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the taxpayer number of the collaborator.
     *
     * @return The taxpayer number of the collaborator.
     */
    public int getTaxpayerNumber() {
        return taxpayerNumber;
    }

    /**
     * Returns the BI (Identity Card) number of the collaborator.
     *
     * @return The BI (Identity Card) number of the collaborator.
     */
    public long getBINumber() {
        return BINumber;
    }

    /**
     * Returns the job position of the collaborator.
     *
     * @return The job position of the collaborator.
     */
    public String getJob() {
        return job;
    }

}
```

### Class CollaboratorRepository

```java
public class CollaboratorRepository {

    private static List<Collaborator> collaborators;

    /**
     * Constructs a new {@code CollaboratorRepository} object.
     * Initializes the list of collaborators.
     */
    public CollaboratorRepository() {
        collaborators = new ArrayList<>();
    }

    /**
     * Adds a new collaborator to the repository.
     * Validates the collaborator before adding it to ensure it is unique.
     *
     * @param collaborator The collaborator to add.
     * @return An optional containing the added collaborator if successful.
     */
    public Optional<Collaborator> add(Collaborator collaborator) {
        Optional<Collaborator> newCollaborator = Optional.empty();
        if (validateCollaborator(collaborator)) {
            newCollaborator = Optional.of(collaborator.clone());
            collaborators.add(newCollaborator.get());
        }
        return newCollaborator;
    }

    /**
     * Validates a collaborator to ensure it does not already exist in the repository.
     *
     * @param collaborator The collaborator to validate.
     * @return {@code true} if the collaborator is valid (i.e., not a duplicate), {@code false} otherwise.
     */
    private boolean validateCollaborator(Collaborator collaborator) {
        // Check for duplicates based on taxpayer number or BI number
        return !isTaxpayerNumberDuplicate(collaborator.getTaxpayerNumber()) &&
                !isBINumberDuplicate(collaborator.getBINumber());
    }

    /**
     * Retrieves a list of all collaborators in the repository.
     *
     * @return A list of collaborators.
     */
    public static List<Collaborator> getCollaborators() {
        // Defensive copy to prevent modification from outside
        return List.copyOf(collaborators);
    }

    /**
     * Checks if a taxpayer number already exists for another collaborator in the repository.
     *
     * @param taxpayerNumber The taxpayer number to check.
     * @return {@code true} if the taxpayer number is a duplicate, {@code false} otherwise.
     */
    public boolean isTaxpayerNumberDuplicate(int taxpayerNumber) {
        return collaborators.stream()
                .anyMatch(collaborator -> collaborator.getTaxpayerNumber() == taxpayerNumber);
    }

    /**
     * Checks if a BI number already exists for another collaborator in the repository.
     *
     * @param biNumber The BI number to check.
     * @return {@code true} if the BI number is a duplicate, {@code false} otherwise.
     */
    public boolean isBINumberDuplicate(long biNumber) {
        return collaborators.stream()
                .anyMatch(collaborator -> collaborator.getBINumber() == biNumber);
    }

}

```


## 6. Integration and Demo

* A new option on the HRM menu options was added.

* For demo purposes some collaborators are bootstrapped while system starts.


## 7. Observations

n/a