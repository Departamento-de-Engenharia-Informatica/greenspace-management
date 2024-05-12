package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The {@code CollaboratorRepository} class represents a repository for managing collaborators.
 * It stores collaborator objects and provides methods to add new collaborators and perform validation checks.
 */
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
