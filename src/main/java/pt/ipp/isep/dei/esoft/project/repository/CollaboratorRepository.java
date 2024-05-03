package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollaboratorRepository {

    private static  List<Collaborator> collaborators;

    public CollaboratorRepository() {
        collaborators = new ArrayList<>();
    }

    public Optional<Collaborator> add(Collaborator collaborator) {
        Optional<Collaborator> newCollaborator = Optional.empty();
        if (validateCollaborator(collaborator)) {
            newCollaborator = Optional.of(collaborator.clone());
            collaborators.add(newCollaborator.get());
        }
        return newCollaborator;
    }

    private boolean validateCollaborator(Collaborator collaborator) {
        // Implement your validation logic here
        // For example, ensure that the collaborator does not already exist in the repository
        return !collaborators.contains(collaborator);
    }

    public static List<Collaborator> getCollaborators() {
        // Defensive copy to prevent modification from outside
        return List.copyOf(collaborators);
    }

    // Additional methods can be added as needed
}
