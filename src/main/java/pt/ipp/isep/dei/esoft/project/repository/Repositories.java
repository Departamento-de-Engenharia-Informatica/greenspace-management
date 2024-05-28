package pt.ipp.isep.dei.esoft.project.repository;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;

public class Repositories {

    private static Repositories instance;
    private final OrganizationRepository organizationRepository;
    private final TaskCategoryRepository taskCategoryRepository;
    private final AuthenticationRepository authenticationRepository;
    private final JobRepository jobRepository;
    private final CollaboratorRepository collaboratorRepository;
    private final SkillRepository skillRepository;
    private final SkillAssignmentRepository skillAssignmentRepository;
    private final TeamProposalRepository teamProposalRepository;
    private final VehicleRepository vehicleRepository;
    private final MaintenanceRepository maintenanceRepository;
    private final GreenSpaceRepository greenSpaceRepository;
    private final ToDoListRepository toDoListRepository;
    private final AgendaRepository agendaRepository;


    private Repositories() {
        organizationRepository = new OrganizationRepository();
        taskCategoryRepository = new TaskCategoryRepository();
        authenticationRepository = new AuthenticationRepository();
        jobRepository = new JobRepository();
        collaboratorRepository = new CollaboratorRepository();
        skillRepository = new SkillRepository();
        skillAssignmentRepository = new SkillAssignmentRepository();
        teamProposalRepository = new TeamProposalRepository();
        vehicleRepository = new VehicleRepository();
        maintenanceRepository = new MaintenanceRepository();
        greenSpaceRepository = new GreenSpaceRepository();
        toDoListRepository = new ToDoListRepository();
        agendaRepository = new AgendaRepository();
    }

    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
    }

    public TaskCategoryRepository getTaskCategoryRepository() {
        return taskCategoryRepository;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    public JobRepository getJobRepository() {
        return jobRepository;
    }

    public CollaboratorRepository getCollaboratorRepository() { return collaboratorRepository; }

    public SkillRepository getSkillRepository() {
        return skillRepository;
    }
    public SkillAssignmentRepository getSkillAssignmentRepository() {
        return skillAssignmentRepository;
    }
    public TeamProposalRepository getTeamProposalRepository() {
        return teamProposalRepository;
    }
    public VehicleRepository getVehicleRepository() {
        return vehicleRepository;
    }
    public MaintenanceRepository getMaintenanceRepository() {
        return maintenanceRepository;
    }
    public GreenSpaceRepository getGreenSpaceRepository() {
        return greenSpaceRepository;
    }
    public ToDoListRepository getToDoListRepository() {
        return toDoListRepository;
    }
    public AgendaRepository getAgendaRepository() {return agendaRepository;}


}
