package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.time.format.DateTimeFormatter;



/**
 * The {@code Bootstrap} class initializes the application by adding initial data to repositories.
 * It adds task categories, organizations, users, jobs, and collaborators.
 */
public class Bootstrap implements Runnable {

    /**
     * Runs the bootstrap process to initialize the application.
     * Adds task categories, organizations, users, jobs, and collaborators.
     */



    /**
     * Constructs a new {@code Bootstrap} object with the user's email.
     */
    public Bootstrap() {

    }
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
        addTeamProposals();
        addGreenspaces();
//        addToDoListEntries();
        //addAgendaEntries();
        assignTeam();
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
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_COLLABORATOR, AuthenticationController.ROLE_COLLABORATOR);
        collaboratorRepository.add(new Collaborator("João Silva", LocalDate.of(1985, 3, 15), LocalDate.of(2010, 7, 10), "Rua da Praia, n 123, 4400-001 Porto", "912345678", "rdm061sendemail@gmail.com", 123456789, 12345678, "Gardener"));
        authenticationRepository.addUserWithRole("Collaborator", "rdm061sendemail@gmail.com", "12345678",
                AuthenticationController.ROLE_COLLABORATOR);
        collaboratorRepository.add(new Collaborator("Ana Santos", LocalDate.of(1978, 1, 25), LocalDate.of(2005, 9, 5), "Avenida Central, n 456, 1000-200 Lisboa", "934567890", "tugahdchester@gmail.com", 987654321, 78901234, "Arborist"));
        authenticationRepository.addUserWithRole("Collaborator", "tugahdchester@gmail.com", "78901234",
                AuthenticationController.ROLE_COLLABORATOR);
        collaboratorRepository.add(new Collaborator("Marta Oliveira", LocalDate.of(1992, 11, 10), LocalDate.of(2017, 4, 20), "Travessa das Flores, n 789, 3000-400 Coimbra", "920987654", "eduramos015.pt@gmail.com", 246810753, 45678901, "Urban Planner"));
        authenticationRepository.addUserWithRole("Collaborator", "eduramos015.pt@gmail.com", "45678901",
                AuthenticationController.ROLE_COLLABORATOR);
        collaboratorRepository.add(new Collaborator("Pedro Ferreira", LocalDate.of(1980, 9, 5), LocalDate.of(2012, 3, 15), "Rua dos Bosques, n 234, 5000-600 Vila Real", "917654321", "pedro.ferreira@outlook.pt", 369258147, 21098765, "Park Ranger"));
        authenticationRepository.addUserWithRole("Collaborator", "pedro.ferreira@outlook.pt", "21098765",
                AuthenticationController.ROLE_COLLABORATOR);
        collaboratorRepository.add(new Collaborator("Sofia Rodrigues", LocalDate.of(1987, 7, 20), LocalDate.of(2015, 2, 25), "Largo do Parque, n 567, 2000-300 Santarem", "925432109", "sofia.rodrigues@live.com.pt", 582037469, 87654321, "Park Ranger"));
        authenticationRepository.addUserWithRole("Collaborator", "sofia.rodrigues@live.com.pt", "87654321",
                AuthenticationController.ROLE_COLLABORATOR);
        collaboratorRepository.add(new Collaborator("Sofia pedro", LocalDate.of(1987, 7, 20), LocalDate.of(2015, 2, 25), "Largo do Parque, n 567, 2000-300 Santarem", "925432108", "sofia.rodrigues1@live.com.pt", 582037468, 87654322, "Park Ranger"));
        authenticationRepository.addUserWithRole("Collaborator", "sofia.rodrigues1@live.com.pt", "87654322",
                AuthenticationController.ROLE_COLLABORATOR);
        collaboratorRepository.add(new Collaborator("Sofia gomes", LocalDate.of(1987, 7, 20), LocalDate.of(2015, 2, 25), "Largo do Parque, n 567, 2000-300 Santarem", "925432107", "sofia.rodrigues2@live.com.pt", 582037467, 87654323, "Park Ranger"));
        authenticationRepository.addUserWithRole("Collaborator", "sofia.rodrigues2@live.com.pt", "87654323",
                AuthenticationController.ROLE_COLLABORATOR);
        collaboratorRepository.add(new Collaborator("Sofia ribeiro", LocalDate.of(1987, 7, 20), LocalDate.of(2015, 2, 25), "Largo do Parque, n 567, 2000-300 Santarem", "925432106", "sofia.rodrigues3@live.com.pt", 582037466, 87654324, "Park Ranger"));
        authenticationRepository.addUserWithRole("Collaborator", "sofia.rodrigues3@live.com.pt", "87654324",
                AuthenticationController.ROLE_COLLABORATOR);

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
        skillRepository.addSkill("license");


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
        authenticationRepository.addUserRole(AuthenticationController.ROLE_COLLABORATOR, AuthenticationController.ROLE_COLLABORATOR);

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
        authenticationRepository.addUserWithRole("David", "Davidgsm@this.app", "pwd",
                AuthenticationController.ROLE_GSM);
        authenticationRepository.addUserWithRole("Edu", "Edugsm@this.app", "pwd",
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
        Skill license = skillRepository.getSkillByName("license");


        Collaborator joaoSilva = collaboratorRepository.getCollaboratorByName("João Silva").orElse(null);
        Collaborator anaSantos = collaboratorRepository.getCollaboratorByName("Ana Santos").orElse(null);
        Collaborator pedroFerreira = collaboratorRepository.getCollaboratorByName("Pedro Ferreira").orElse(null);
        Collaborator sofiaRodrigues = collaboratorRepository.getCollaboratorByName("Sofia Rodrigues").orElse(null);
        Collaborator sofiaPedro = collaboratorRepository.getCollaboratorByName("Sofia pedro").orElse(null);
        Collaborator sofiaGomes = collaboratorRepository.getCollaboratorByName("Sofia gomes").orElse(null);


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

            SkillAssignment treePrunerAssignment3 = new SkillAssignment(sofiaPedro, treePruner);
            skillAssignmentRepository.addSkillAssignment(treePrunerAssignment3);

            SkillAssignment treePrunerAssignment4 = new SkillAssignment(sofiaGomes, license);
            skillAssignmentRepository.addSkillAssignment(treePrunerAssignment4);
        }
    }
    private static void addTeamProposals() {
        TeamProposalRepository teamProposalRepository = Repositories.getInstance().getTeamProposalRepository();
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();

        // Fetch required skills
        Skill skillJava = skillRepository.getSkillByName("Java Programming");
        Skill skillWebDev = skillRepository.getSkillByName("Web Development");
        Skill skilltreepruner = skillRepository.getSkillByName("Tree Pruner");

        // Fetch some collaborators
        Collaborator joaoSilva = collaboratorRepository.getCollaboratorByName("João Silva").orElse(null);
        Collaborator anaSantos = collaboratorRepository.getCollaboratorByName("Ana Santos").orElse(null);
        Collaborator martaOliveira = collaboratorRepository.getCollaboratorByName("Marta Oliveira").orElse(null);
        Collaborator pedroFerreira = collaboratorRepository.getCollaboratorByName("Pedro Ferreira").orElse(null);


        // Create sets of required skills for team proposals
        Set<Skill> requiredSkills1 = new HashSet<>(Arrays.asList(skillJava, skillWebDev));
        Set<Skill> requiredSkills2 = new HashSet<>(Arrays.asList(skilltreepruner));

        // Create lists of selected collaborators for team proposals
        List<Collaborator> selectedCollaborators1 = Arrays.asList(joaoSilva, anaSantos);
        List<Collaborator> selectedCollaborators2 = Arrays.asList(martaOliveira, pedroFerreira);

        // Create team proposal instances
        TeamProposal teamProposal1 = new TeamProposal(3, 2, requiredSkills1, selectedCollaborators1);
        TeamProposal teamProposal2 = new TeamProposal(4, 3, requiredSkills2, selectedCollaborators2);

        // Add team proposals to the repository
        teamProposalRepository.addTeamProposal(teamProposal1);
        teamProposalRepository.addTeamProposal(teamProposal2);
    }

    private void addGreenspaces() {
        GreenSpaceRepository greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();


        greenSpaceRepository.addGreenSpace(new Garden("Jardim do Covelo", 1000, "Davidgsm@this.app"));
        GreenSpaceRepository.addGreenSpace(new MediumSizedPark("Parque das Planicies", 10313200, "Davidgsm@this.app"));
        GreenSpaceRepository.addGreenSpace(new LargeSizedPark("Parque das virtudes", 123100, "Davidgsm@this.app"));

        GreenSpaceRepository.addGreenSpace(new Garden("Exposende", 103200, "gsm@this.app"));
        GreenSpaceRepository.addGreenSpace(new MediumSizedPark("Parque Urbano de ermesinde", 1000, "gsm@this.app"));
        GreenSpaceRepository.addGreenSpace(new LargeSizedPark("parque gigante", 324242, "gsm@this.app"));

    }

    private void assignTeam() {
        TeamProposalRepository teamProposalRepository = Repositories.getInstance().getTeamProposalRepository();
        AgendaRepository agendaRepository = Repositories.getInstance().getAgendaRepository();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        List<TeamProposal> teamProposals = teamProposalRepository.getAllTeamProposals();

        if (teamProposals.isEmpty()) {
            System.out.println("No team proposals found.");
            return;
        }

        TeamProposal selectedTeamProposal = teamProposals.get(0);
        TeamProposal selectedTeamProposal1 = teamProposals.get(1);

        Agenda agenda1 = new Agenda("coirtar relva", "Jardim do Covelo", LocalDate.parse("10-01-2023", formatter), "Done", selectedTeamProposal);
        Agenda agenda2 = new Agenda("coirtar relva2", "Jardim do Covelo", LocalDate.parse("10-10-2020", formatter), "Planned", selectedTeamProposal);
        Agenda agenda3 = new Agenda("coirtar relva3", "Parque das Planicies", LocalDate.parse("10-04-2021", formatter), "Postponed", selectedTeamProposal);
        Agenda agenda4 = new Agenda("coirtar relva4", "Parque das Planicies", LocalDate.parse("10-01-2024", formatter), "Postponed", selectedTeamProposal);
        Agenda agenda5 = new Agenda("coirtar relva5", "Parque das Planicies", LocalDate.parse("10-12-2019", formatter), "Planned", selectedTeamProposal);
        Agenda agenda6 = new Agenda("coirtar relva6", "Parque Urbano de ermesinde", LocalDate.parse("10-08-2022", formatter), "Done", selectedTeamProposal);
        Agenda agenda7 = new Agenda("coirtar relva7", "Parque Urbano de ermesinde", LocalDate.parse("10-09-2017", formatter), "Planned", selectedTeamProposal);
        Agenda agenda8 = new Agenda("coirtar relva8", "Jardim do Covelo", LocalDate.parse("10-03-2020", formatter), "Done", selectedTeamProposal);
        Agenda agenda9 = new Agenda("coirtar relva9", "Parque das Planicies", LocalDate.parse("10-06-2021", formatter), "Planned", selectedTeamProposal);
        Agenda agenda10 = new Agenda("coirtar relva10", "parque gigante", LocalDate.parse("10-02-2024", formatter), "Canceled", selectedTeamProposal);
        Agenda agenda11 = new Agenda("coirtar relva11", "parque gigante", LocalDate.parse("10-03-2024", formatter), "Postponed", selectedTeamProposal);

        Agenda agenda12 = new Agenda("coirtar relva12", "parque gigante", LocalDate.parse("01-04-2024", formatter), "Postponed", selectedTeamProposal1);

        agendaRepository.add(agenda1);
        agendaRepository.add(agenda2);
        agendaRepository.add(agenda3);
        agendaRepository.add(agenda4);
        agendaRepository.add(agenda5);
        agendaRepository.add(agenda6);
        agendaRepository.add(agenda7);
        agendaRepository.add(agenda8);
        agendaRepository.add(agenda9);
        agendaRepository.add(agenda10);
        agendaRepository.add(agenda11);
        agendaRepository.add(agenda12);

    }



//    private void addToDoListEntries() {
//        ToDoListRepository toDoListRepository = Repositories.getInstance().getToDoListRepository();
//        GreenSpaceRepository greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
//
//        // ToDoList entries for Davidgsm's parks
//        addToDoListEntriesForGSM(toDoListRepository, greenSpaceRepository, "Davidgsm@this.app");
//
//        // ToDoList entries for gsm's parks
//        addToDoListEntriesForGSM(toDoListRepository, greenSpaceRepository, "gsm@this.app");
//    }
//
//    private void addToDoListEntriesForGSM(ToDoListRepository toDoListRepository, GreenSpaceRepository greenSpaceRepository, String gsmEmail) {
//        List<GreenSpace> greenSpaces = greenSpaceRepository.getGreenSpacesByEmail(gsmEmail);
//        System.out.println(gsmEmail);
//
//        for (GreenSpace greenSpace : greenSpaces) {
//            if (greenSpace.getEmail().equals(gsmEmail)) {
//                toDoListRepository.add(new ToDoList("Plant trees in " + greenSpace.getName(), "High", 120, greenSpace.getName(), "Not Started"));
//                toDoListRepository.add(new ToDoList("Water plants in " + greenSpace.getName(), "Medium", 60, greenSpace.getName(), "In Progress"));
//                toDoListRepository.add(new ToDoList("Prune shrubs in " + greenSpace.getName(), "Low", 90, greenSpace.getName(), "Completed"));
//                toDoListRepository.add(new ToDoList("Clean park in " + greenSpace.getName(), "High", 180, greenSpace.getName(), "Not Started"));
//                toDoListRepository.add(new ToDoList("Install benches in " + greenSpace.getName(), "Medium", 240, greenSpace.getName(), "In Progress"));
//                toDoListRepository.add(new ToDoList("Repair fountain in " + greenSpace.getName(), "High", 120, greenSpace.getName(), "Completed"));
//            }
//        }
//    }

}
