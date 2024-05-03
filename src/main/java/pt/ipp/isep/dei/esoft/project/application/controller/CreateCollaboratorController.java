package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.application.controller.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The {@code CreateCollaboratorController} class represents a controller responsible for managing
 * the creation of collaborators in the application.
 * It interacts with the collaborator repository to add new collaborators and perform validation checks.
 */
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

