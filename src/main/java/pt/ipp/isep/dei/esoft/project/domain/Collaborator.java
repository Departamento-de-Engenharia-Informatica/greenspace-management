package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.controller.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_COLLABORATOR, AuthenticationController.ROLE_COLLABORATOR);
        authenticationRepository.addUserWithRole(name, email, String.valueOf(BINumber), AuthenticationController.ROLE_COLLABORATOR);
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

        if (email == null || !email.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b")) {
            System.out.println("Email must be provided and in a valid format (something@something.something).");
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
    @Override
    public String toString() {
        return name;
    }

}
