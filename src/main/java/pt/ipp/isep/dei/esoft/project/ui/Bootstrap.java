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
        addColaboratorsWithSkills();
        addVehicles();
        addMaintenance();

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
        collaboratorRepository.add(new Collaborator("João Silva", LocalDate.of(1985, 3, 15), LocalDate.of(2010, 7, 10), "Rua da Praia, n 123, 4400-001 Porto", "912345678", "joao.silva@gmail.com", 123456789, 12345678, "Gardener"));
        collaboratorRepository.add(new Collaborator("Ana Santos", LocalDate.of(1978, 1, 25), LocalDate.of(2005, 9, 5), "Avenida Central, n 456, 1000-200 Lisboa", "934567890", "ana.santos@outlook.com", 987654321, 78901234, "Arborist"));
        collaboratorRepository.add(new Collaborator("Marta Oliveira", LocalDate.of(1992, 11, 10), LocalDate.of(2017, 4, 20), "Travessa das Flores, n 789, 3000-400 Coimbra", "920987654", "marta.oliveira@gmail.com", 246810753, 45678901, "Urban Planner"));
        collaboratorRepository.add(new Collaborator("Pedro Ferreira", LocalDate.of(1980, 9, 5), LocalDate.of(2012, 3, 15), "Rua dos Bosques, n 234, 5000-600 Vila Real", "917654321", "pedro.ferreira@outlook.pt", 369258147, 21098765, "Park Ranger"));
        collaboratorRepository.add(new Collaborator("Sofia Rodrigues", LocalDate.of(1987, 7, 20), LocalDate.of(2015, 2, 25), "Largo do Parque, n 567, 2000-300 Santarem", "925432109", "sofia.rodrigues@live.com.pt", 582037469, 87654321, "Park Ranger"));
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
        skillRepository.addSkill("tree pruner");

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

    private void addVehicles(){
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
        vehicleRepository.addVehicle(new Vehicle("20-AA-BB", "BMW", "Sedan", 0, 3000, 30000, "12-03-2019", "19-08-2020", 50000, 0));
        vehicleRepository.addVehicle(new Vehicle("77-AO-KP", "Mercedes", "Truck", 5000, 6000, 100000, "19-10-2007", "13-10-2020", 70000, 50000));
        vehicleRepository.addVehicle(new Vehicle("LL-99-BS", "BMW", "Sedan", 0, 2800, 70000, "11-01-2020", "25-09-2022", 50000, 20000));
        vehicleRepository.addVehicle(new Vehicle("MR-AD-81", "Volvo", "Coupe", 0, 2200, 65000, "21-12-2015", "09-07-2017", 30000, 40000));
        vehicleRepository.addVehicle(new Vehicle("ZZ-99-00", "Porsche", "SUV", 0, 1800, 15000, "09-12-2022", "01-01-2024", 20000, 0));
    }

    private void addMaintenance(){
        MaintenanceRepository maintenanceRepository = Repositories.getInstance().getMaintenanceRepository();
        maintenanceRepository.addMaintenance(new Maintenance(0, "12-07-2019", "20-AA-BB"));
        maintenanceRepository.addMaintenance(new Maintenance(0, "20-07-2010", "77-AO-KP"));
        maintenanceRepository.addMaintenance(new Maintenance(50000, "12-07-2015", "77-AO-KP"));
        maintenanceRepository.addMaintenance(new Maintenance(0, "12-01-2020", "LL-99-BS"));
        maintenanceRepository.addMaintenance(new Maintenance(20000, "29-10-2021", "LL-99-BS"));
        maintenanceRepository.addMaintenance(new Maintenance(10000, "01-04-2016", "MR-AD-81"));
        maintenanceRepository.addMaintenance(new Maintenance(40000, "12-07-2019", "MR-AD-81"));
        maintenanceRepository.addMaintenance(new Maintenance(0, "10-12-2022", "ZZ-99-00"));

    }

    private void addColaboratorsWithSkills() {
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        SkillAssignmentRepository skillAssignmentRepository = Repositories.getInstance().getSkillAssignmentRepository();

        Skill javaProgramming = skillRepository.getSkillByName("Java Programming");
        Skill webDevelopment = skillRepository.getSkillByName("Web Development");
        Skill treePruner = skillRepository.getSkillByName("tree pruner");

        Collaborator joaoSilva = collaboratorRepository.getCollaboratorByName("João Silva").orElse(null);
        Collaborator anaSantos = collaboratorRepository.getCollaboratorByName("Ana Santos").orElse(null);
        Collaborator pedroFerreira = collaboratorRepository.getCollaboratorByName("Pedro Ferreira").orElse(null);
        Collaborator sofiaRodrigues = collaboratorRepository.getCollaboratorByName("Sofia Rodrigues").orElse(null);

        if (javaProgramming != null && anaSantos != null) {
            SkillAssignment javaAssignment = new SkillAssignment(anaSantos, javaProgramming);
            skillAssignmentRepository.addSkillAssignment(javaAssignment);
        }

        if (webDevelopment != null && joaoSilva != null) {
            SkillAssignment webDevAssignment = new SkillAssignment(joaoSilva, webDevelopment);
            skillAssignmentRepository.addSkillAssignment(webDevAssignment);
        }

        if (treePruner != null && pedroFerreira != null && sofiaRodrigues != null) {
            SkillAssignment treePrunerAssignment1 = new SkillAssignment(pedroFerreira, treePruner);
            skillAssignmentRepository.addSkillAssignment(treePrunerAssignment1);

            SkillAssignment treePrunerAssignment2 = new SkillAssignment(sofiaRodrigues, treePruner);
            skillAssignmentRepository.addSkillAssignment(treePrunerAssignment2);
        }
    }
}
