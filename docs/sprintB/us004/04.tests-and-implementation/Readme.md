# US004 - Assign a Skill to a Collaborator

## 4. Tests

**Test 1:** Check that you can assign a skill to a Collaborator.

    @Test
    void testAddSkillToCollaborator() {
        // Adiciona o colaborador ao repositório
        collaboratorRepository.add(testCollaborator);

        // Adiciona a habilidade ao repositório
        skillRepository.addSkill("Test Skill");

        // Verifica se o colaborador não tem a habilidade antes de atribuí-la
        assertFalse(testCollaborator.hasSkill(testSkill));

        // Atribui a habilidade ao colaborador
        boolean result = assignSkillController.assignSkillToCollaborator(testCollaborator, testSkill);

        // Verifica se a atribuição foi bem-sucedida
        assertTrue(result);
        assertTrue(testCollaborator.hasSkill(testSkill));
    }


**Test 2:** Check that you can remove a skill to a Collaborator.

    @Test
    void testRemoveSkillFromCollaborator() {
        // Adiciona o colaborador ao repositório
        collaboratorRepository.add(testCollaborator);

        // Adiciona a habilidade ao repositório
        skillRepository.addSkill("Test Skill");

        // Atribui a habilidade ao colaborador
        assignSkillController.assignSkillToCollaborator(testCollaborator, testSkill);

        // Verifica se o colaborador tem a habilidade antes de removê-la
        assertTrue(testCollaborator.hasSkill(testSkill));

        // Remove a habilidade do colaborador
        boolean result = assignSkillController.removeSkillFromCollaborator(testCollaborator, testSkill);

        // Verifica se a remoção foi bem-sucedida
        assertTrue(result);
        assertFalse(testCollaborator.hasSkill(testSkill));
    }


## 5. Construction (Implementation)


### Class AssignSkillController

```java
public class AssignSkillController {
    private final CollaboratorRepository collaboratorRepository;
    private final SkillRepository skillRepository;

    /**
     * Constructs an instance of the AssignSkillController.
     *
     * @param collaboratorRepository the collaborator repository
     * @param skillRepository        the skill repository
     */
    public AssignSkillController(CollaboratorRepository collaboratorRepository, SkillRepository skillRepository) {
        this.collaboratorRepository = collaboratorRepository;
        this.skillRepository = skillRepository;
    }

    /**
     * Retrieves all collaborators.
     *
     * @return a list of collaborators
     */
    public List<Collaborator> getAllCollaborators() {
        return collaboratorRepository.getCollaborators();
    }

    /**
     * Retrieves all skills.
     *
     * @return a list of skills
     */
    public List<Skill> getAllSkills() {
        return skillRepository.getSkills();
    }

    /**
     * Assigns a skill to a collaborator.
     *
     * @param collaborator the collaborator to whom the skill will be assigned
     * @param skill        the skill to be assigned
     * @return true if the assignment is successful, false otherwise
     */
    public boolean assignSkillToCollaborator(Collaborator collaborator, Skill skill) {
        // Checks if the collaborator already has the skill assigned
        if (collaborator.hasSkill(skill)) {
            System.out.println("Collaborator already has this skill.");
            return false;
        }

        // Adds the skill to the collaborator
        collaborator.addSkill(skill);

        // Creates a new skill assignment
        SkillAssignment assignment = new SkillAssignment(collaborator, skill);

        // Adds the new assignment to the skill assignment repository
        Repositories.getInstance().getSkillAssignmentRepository().addSkillAssignment(assignment);

        System.out.println("Skill assigned successfully to collaborator: " + collaborator.getName() +
                ", Skill: " + skill.getSkillName());

        // Returns true to indicate the assignment was successful
        return true;
    }

    /**
     * Removes a skill from a collaborator.
     *
     * @param collaborator the collaborator from whom the skill will be removed
     * @param skill        the skill to be removed
     * @return true if the removal is successful, false otherwise
     */
    public boolean removeSkillFromCollaborator(Collaborator collaborator, Skill skill) {
        // Checks if the collaborator has the skill to be removed
        if (!collaborator.hasSkill(skill)) {
            System.out.println("Collaborator does not have this skill.");
            return false;
        }

        // Removes the skill from the collaborator's list of skills
        collaborator.removeSkill(skill);

        System.out.println("Skill removed successfully from collaborator: " + collaborator.getName() +
                ", Skill: " + skill.getSkillName());

        // Returns true to indicate the removal was successful
        return true;
    }
}
    

```

### Class SkillRepository

```java
public void addSkill(String skillName) {
    if (skillName == null || !skillName.matches("[a-zA-Z ]+")) {
        System.out.println("Special characters or digits are not allowed in the skill name.");
    } else {
        Skill skill = new Skill(skillName);
        skills.add(skill);
    }
}

public void removeSkill(int skillId) {
    Skill skillToRemove = findSkillById(skillId);
    if (skillToRemove != null) {
        skills.remove(skillToRemove);
    }
}

public void updateSkill(int skillId, String newSkillName) {
    Skill skillToUpdate = findSkillById(skillId);
    if (skillToUpdate != null) {
        skillToUpdate.setSkillName(newSkillName);
    }
}

```
### Class Skill
```java
public class Skill {
    private static int nextId = 1;
    private int id;
    private String skillName;

    public Skill(String skillName) {
        this.id = nextId++;
        this.skillName = skillName;
    }

    public int getId() {
        return id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}

```

### Class Collaborator
```java

/**
 * The {@code Collaborator} class represents a collaborator entity in the system.
 * It contains information about a collaborator such as name, birth date, admission date, contact details, and job.
 */
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
    private List<Skill> skills;

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
        this.skills = new ArrayList<>();
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public List<Skill> getSkills() {
        return new ArrayList<>(skills);
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

    public boolean hasSkill(Skill skill) {
        return skills.contains(skill);
    }
    public void removeSkill(Skill skill) {
        skills.remove(skill);
    }

}


```
### Class Collaborator Repository
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

    public List<Collaborator> getAll() {
        return new ArrayList<>(collaborators);
    }

}
```

### SkillAssignment
```java
package pt.ipp.isep.dei.esoft.project.domain;

/**
 * Represents an assignment of a skill to a collaborator.
 */
public class SkillAssignment {
    private Collaborator collaborator;
    private Skill skill;

    /**
     * Constructs a new SkillAssignment with the specified collaborator and skill.
     *
     * @param collaborator the collaborator to whom the skill is assigned
     * @param skill        the skill that is assigned to the collaborator
     */
    public SkillAssignment(Collaborator collaborator, Skill skill) {
        this.collaborator = collaborator;
        this.skill = skill;
    }

    /**
     * Gets the collaborator associated with this skill assignment.
     *
     * @return the collaborator
     */
    public Collaborator getCollaborator() {
        return collaborator;
    }

    /**
     * Gets the skill associated with this skill assignment.
     *
     * @return the skill
     */
    public Skill getSkill() {
        return skill;
    }
}
```

### CollaboratorSkillsController
```java
public class ShowCollaboratorSkillsController {
    private final CollaboratorRepository collaboratorRepository;

    /**
     * Constructs an instance of {@code ShowCollaboratorSkillsController} with the provided collaborator repository.
     *
     * @param collaboratorRepository the collaborator repository
     */
    public ShowCollaboratorSkillsController(CollaboratorRepository collaboratorRepository) {
        this.collaboratorRepository = collaboratorRepository;
    }

    /**
     * Retrieves all collaborators.
     *
     * @return a list of collaborators
     */
    public List<Collaborator> getAllCollaborators() {
        return collaboratorRepository.getAll();
    }

    /**
     * Retrieves the skills of a specific collaborator.
     *
     * @param collaborator the collaborator whose skills will be retrieved
     * @return a list of skills of the specified collaborator
     */
    public List<Skill> getCollaboratorSkills(Collaborator collaborator) {
        List<Skill> skills = collaborator.getSkills();
        System.out.println("Retrieved skills for collaborator: " + collaborator.getName());
        return skills;
    }
}
```
### SkillAssignmentRepository
```java
public class SkillAssignmentRepository {
    private List<SkillAssignment> skillAssignments;

    /**
     * Constructs a new SkillAssignmentRepository.
     */
    public SkillAssignmentRepository() {
        this.skillAssignments = new ArrayList<>();
    }

    /**
     * Adds a skill assignment to the repository.
     *
     * @param assignment the skill assignment to be added
     */
    public void addSkillAssignment(SkillAssignment assignment) {
        skillAssignments.add(assignment);
    }

    /**
     * Retrieves all skill assignments from the repository.
     *
     * @return a list of all skill assignments
     */
    public List<SkillAssignment> getAllSkillAssignments() {
        return new ArrayList<>(skillAssignments);
    }
}
```
### SkillAssignmentRepository
```java
public class SkillRepository {
    private List<Skill> skills;

    /**
     * Constructs a new SkillRepository.
     */
    public SkillRepository() {
        this.skills = new ArrayList<>();
    }

    /**
     * Adds a new skill to the repository if it does not already exist.
     *
     * @param skillName The name of the skill to be added.
     */
    public void addSkill(String skillName) {
        if (skillName == null || !skillName.matches("[a-zA-Z ]+")) {
            System.out.println("Special characters or digits are not allowed in the skill name.");
        } else if (skillExists(skillName)) {
            System.out.println("Skill already exists in the system.");
        } else {
            Skill skill = new Skill(skillName);
            skills.add(skill);
        }
    }

    /**
     * Checks if a skill with the given name already exists in the repository.
     *
     * @param skillName The name of the skill to check.
     * @return {@code true} if the skill already exists, {@code false} otherwise.
     */
    private boolean skillExists(String skillName) {
        for (Skill skill : skills) {
            if (skill.getSkillName().equalsIgnoreCase(skillName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a skill from the repository.
     *
     * @param skillId The ID of the skill to be removed.
     */
    public void removeSkill(int skillId) {
        for (Skill skill : this.skills) {
            if (skill.getId() == skillId) {
                this.skills.remove(skill);
                break;
            }
        }
    }
    public List<Skill> getSkills() {
        return new ArrayList<>(skills);
    }

    /**
     * Updates the name of a skill in the repository.
     *
     * @param skillId      The ID of the skill to be updated.
     * @param newSkillName The new name for the skill.
     */
    public void updateSkill(int skillId, String newSkillName) {
        Skill skillToUpdate = findSkillById(skillId);
        if (skillToUpdate != null) {
            skillToUpdate.setSkillName(newSkillName);
        }
    }

    /**
     * Retrieves a list of all skills in the repository.
     *
     * @return A list containing all skills in the repository.
     */
    public List<Skill> getAllSkills() {
        return new ArrayList<>(skills);
    }

    /**
     * Finds a skill in the repository by its ID.
     *
     * @param skillId The ID of the skill to find.
     * @return The skill with the specified ID, or {@code null} if not found.
     */
    private Skill findSkillById(int skillId) {
        for (Skill skill : skills) {
            if (skill.getId() == skillId) {
                return skill;
            }
        }
        return null;
    }


}
```
### AssignSkillsUI
```java
public class AssignSkillUI implements Runnable {
    private final AssignSkillController controller;
    private final Scanner scanner;
    private Collaborator selectedCollaborator;

    /**
     * Constructs an instance of {@code AssignSkillUI}.
     */
    public AssignSkillUI() {
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();
        this.controller = new AssignSkillController(collaboratorRepository, skillRepository);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Runs the AssignSkillUI, allowing users to assign skills to collaborators.
     */
    @Override
    public void run() {
        System.out.println("--- Assign Skill to Collaborator ---");
        Collaborator collaborator = selectCollaborator();
        if (collaborator != null) {
            chooseAction(collaborator);
        } else {
            addSkillToSelectedCollaborator();
        }
    }

    private void addSkillToSelectedCollaborator() {
        System.out.println("You haven't selected a collaborator yet. Do you want to add a skill without selecting a collaborator?");
        System.out.print("Enter 'Y' for Yes or 'N' for No: ");
        String choice = scanner.nextLine().trim().toUpperCase();
        if (choice.equals("Y")) {
            Collaborator collaborator = selectedCollaborator;
            Skill skill = selectSkill();
            if (skill != null) {
                if (controller.assignSkillToCollaborator(collaborator, skill)) {
                    System.out.println("Skill assigned successfully.");
                } else {
                    System.out.println("Failed to assign skill. Please try again.");
                }
            }
        }
    }

    private Collaborator selectCollaborator() {
        List<Collaborator> collaborators = controller.getAllCollaborators();
        System.out.println("Select a collaborator:");
        for (int i = 0; i < collaborators.size(); i++) {
            System.out.println((i + 1) + ". " + collaborators.get(i).getName());
        }
        System.out.print("Enter collaborator number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        if (choice >= 1 && choice <= collaborators.size()) {
            selectedCollaborator = collaborators.get(choice - 1);
            return selectedCollaborator;
        } else {
            System.out.println("Invalid choice.");
            return null;
        }
    }

    private Skill selectSkill() {
        List<Skill> skills = controller.getAllSkills();
        System.out.println("Select a skill:");
        for (int i = 0; i < skills.size(); i++) {
            System.out.println((i + 1) + ". " + skills.get(i).getSkillName());
        }
        System.out.print("Enter skill number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        if (choice >= 1 && choice <= skills.size()) {
            return skills.get(choice - 1);
        } else {
            System.out.println("Invalid choice.");
            return null;
        }
    }

    private void chooseAction(Collaborator collaborator) {
        System.out.println("Choose action:");
        System.out.println("1. Add skill");
        System.out.println("2. Remove skill");
        System.out.print("Enter action number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        switch (choice) {
            case 1:
                Skill skillToAdd = selectSkill();
                if (skillToAdd != null) {
                    if (controller.assignSkillToCollaborator(collaborator, skillToAdd)) {
                        System.out.println("Skill added successfully.");
                    } else {
                        System.out.println("Failed to add skill. Please try again.");
                    }
                }
                break;
            case 2:
                List<Skill> collaboratorSkills = collaborator.getSkills();
                System.out.println("Select a skill to remove:");
                for (int i = 0; i < collaboratorSkills.size(); i++) {
                    System.out.println((i + 1) + ". " + collaboratorSkills.get(i).getSkillName());
                }
                System.out.print("Enter skill number to remove: ");
                int skillToRemoveIndex = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                if (skillToRemoveIndex >= 1 && skillToRemoveIndex <= collaboratorSkills.size()) {
                    Skill skillToRemove = collaboratorSkills.get(skillToRemoveIndex - 1);
                    if (controller.removeSkillFromCollaborator(collaborator, skillToRemove)) {
                        System.out.println("Skill removed successfully.");
                    } else {
                        System.out.println("Failed to remove skill. Please try again.");
                    }
                } else {
                    System.out.println("Invalid choice.");
                }
                break;
            default:
                System.out.println("Invalid action.");
        }
    }
}

```
### ShowCollaboratorSkillsUI
```java
public class ShowCollaboratorSkillsUI implements Runnable {
    private final ShowCollaboratorSkillsController controller;
    private final Scanner scanner;

    /**
     * Constructs an instance of {@code ShowCollaboratorSkillsUI}.
     */
    public ShowCollaboratorSkillsUI() {
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        this.controller = new ShowCollaboratorSkillsController(collaboratorRepository);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Runs the ShowCollaboratorSkillsUI, allowing users to view collaborator skills.
     */
    @Override
    public void run() {
        System.out.println("--- Show Collaborator Skills ---");
        Collaborator collaborator = selectCollaborator();
        if (collaborator != null) {
            List<Skill> skills = controller.getCollaboratorSkills(collaborator);
            System.out.println("Skills of " + collaborator.getName() + ":");
            for (Skill skill : skills) {
                System.out.println(skill.getSkillName());
            }
        }
    }

    private Collaborator selectCollaborator() {
        List<Collaborator> collaborators = controller.getAllCollaborators();
        System.out.println("Select a collaborator:");
        for (int i = 0; i < collaborators.size(); i++) {
            System.out.println((i + 1) + ". " + collaborators.get(i).getName());
        }
        System.out.print("Enter collaborator number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        if (choice >= 1 && choice <= collaborators.size()) {
            return collaborators.get(choice - 1);
        } else {
            System.out.println("Invalid choice.");
            return null;
        }
    }
}

```
## 6. Integration and Demo

* A new option on the HRM menu options was added.

* For demo purposes some skills and collaborators are bootstrapped while system starts.


## 7. Observations

n/a