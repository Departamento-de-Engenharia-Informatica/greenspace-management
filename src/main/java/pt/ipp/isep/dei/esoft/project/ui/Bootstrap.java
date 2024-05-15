package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.time.LocalDate;

/**
 * The {@code Bootstrap} class initializes the application by adding initial data to repositories.
 * It adds task categories, organizations, users, jobs, and collaborators.
 */
public class Bootstrap implements Runnable {

    /**
     * Runs the bootstrap process to initialize the application.
     * Adds task categories, organizations, users, jobs, and collaborators.
     */
    @Override
    public void run() {
        addTaskCategories();
        addOrganization();
        addUsers();
        addJobs();
        addSkills();
        addCollaborators();

    }

    /**
     * Adds an organization to the repository.
     */
    private void addOrganization() {
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        Organization organization = new Organization("This Company");
        organization.addEmployee(new Employee("admin@this.app"));
        organization.addEmployee(new Employee("employee@this.app"));
        organization.addEmployee(new Employee("vfm@this.app"));
        organization.addEmployee(new Employee("hrm@this.app"));
        organization.addEmployee(new Employee("gsu@this.app"));
        organization.addEmployee(new Employee("gsm@this.app"));
        organization.addEmployee(new Employee("qam@this.app"));

        organizationRepository.add(organization);
    }

    /**
     * Adds task categories to the repository.
     */
    private void addTaskCategories() {
        TaskCategoryRepository taskCategoryRepository = Repositories.getInstance().getTaskCategoryRepository();
        taskCategoryRepository.add(new TaskCategory("Analysis"));
        taskCategoryRepository.add(new TaskCategory("Design"));
        taskCategoryRepository.add(new TaskCategory("Implementation"));
        taskCategoryRepository.add(new TaskCategory("Development"));
        taskCategoryRepository.add(new TaskCategory("Testing"));
        taskCategoryRepository.add(new TaskCategory("Deployment"));
        taskCategoryRepository.add(new TaskCategory("Maintenance"));
    }

    /**
     * Adds jobs to the repository.
     */
    private void addJobs() {
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();
        jobRepository.add(new Job("Gardener"));
        jobRepository.add(new Job("Arborist"));
        jobRepository.add(new Job("Park Ranger"));
        jobRepository.add(new Job("Urban Planner"));
    }

    /**
     * Adds collaborators to the repository.
     */
    private void addCollaborators() {
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        collaboratorRepository.add(new Collaborator("João Silva", LocalDate.of(1985, 3, 15), LocalDate.of(2010, 7, 10), "Rua da Praia, nº 123, 4400-001 Porto", "912345678", "joao.silva@gmail.com", 123456789, 12345678L, "Gardener"));
        collaboratorRepository.add(new Collaborator("Ana Santos", LocalDate.of(1978, 1, 25), LocalDate.of(2005, 9, 5), "Avenida Central, nº 456, 1000-200 Lisboa", "934567890", "ana.santos@outlook.com", 987654321, 78901234L, "Arborist"));
        collaboratorRepository.add(new Collaborator("Marta Oliveira", LocalDate.of(1992, 11, 10), LocalDate.of(2017, 4, 20), "Travessa das Flores, nº 789, 3000-400 Coimbra", "920987654", "marta.oliveira@gmail.com", 246810753, 45678901L, "Urban Planner"));
        collaboratorRepository.add(new Collaborator("Pedro Ferreira", LocalDate.of(1980, 9, 5), LocalDate.of(2012, 3, 15), "Rua dos Bosques, nº 234, 5000-600 Vila Real", "917654321", "pedro.ferreira@outlook.pt", 369258147, 21098765L, "Park Ranger"));
        collaboratorRepository.add(new Collaborator("Sofia Rodrigues", LocalDate.of(1987, 7, 20), LocalDate.of(2015, 2, 25), "Largo do Parque, nº 567, 2000-300 Santarém", "925432109", "sofia.rodrigues@live.com.pt", 582037469, 87654321L, "Park Ranger"));
    }
    /**
     * Adds skills to the repository.
     */
    private void addSkills() {
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();
        skillRepository.addSkill("Java Programming");
        skillRepository.addSkill("Web Development");
        skillRepository.addSkill("Database Management");
        skillRepository.addSkill("Project Management");
        skillRepository.addSkill("Communication Skills");
    }

    /**
     * Adds users to the repository.
     */
    private void addUsers() {
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE, AuthenticationController.ROLE_EMPLOYEE);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_VFM, AuthenticationController.ROLE_VFM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_GSM, AuthenticationController.ROLE_GSM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_GSU, AuthenticationController.ROLE_GSU);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_QAM, AuthenticationController.ROLE_QAM);

        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Employee", "employee@this.app", "pwd",
                AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserWithRole("VFM", "vfm@this.app", "pwd",
                AuthenticationController.ROLE_VFM);

        authenticationRepository.addUserWithRole("HRM", "hrm@this.app", "pwd",
                AuthenticationController.ROLE_HRM);

        authenticationRepository.addUserWithRole("GSM", "gsm@this.app", "pwd",
                AuthenticationController.ROLE_GSM);

        authenticationRepository.addUserWithRole("GSU", "gsu@this.app", "pwd",
                AuthenticationController.ROLE_GSU);

        authenticationRepository.addUserWithRole("QAM", "qam@this.app", "pwd",
                AuthenticationController.ROLE_QAM);
    }
}
