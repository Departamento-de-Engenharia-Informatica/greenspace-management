package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.application.controller.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;
import java.util.Optional;

public class CreateCollaboratorController {

    private final CollaboratorRepository collaboratorRepository;
    private final AuthenticationRepository authenticationRepository;

    public CreateCollaboratorController() {
        Repositories repositories = Repositories.getInstance();
        this.collaboratorRepository = repositories.getCollaboratorRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
    }

    public Optional<Collaborator> createCollaborator(String name, String birthdayDate, String admissionDate,
                                                     String address, String phoneNumber, String email,
                                                     String taxpayerNumber, String BINumber, String job) {
        Collaborator collaborator = new Collaborator(name, birthdayDate, admissionDate,
                address, phoneNumber, email, taxpayerNumber, BINumber,job);
        return collaboratorRepository.add(collaborator);
    }

    public static List<Collaborator> getCollaboratorsList() {
        // Assuming jobRepository is an instance variable of the class
        // You need to access the jobRepository instance to retrieve the job list
        return CollaboratorRepository.getCollaborators();
    }
    public List<Job> getAvailableJobs() {
        // Call a method from your Job controller to get the list of jobs
        return CreateJobController.getJobList();
    }
}
