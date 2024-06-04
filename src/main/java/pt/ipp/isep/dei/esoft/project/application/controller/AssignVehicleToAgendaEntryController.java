package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The AssignVehicleToAgendaEntryController class is responsible for controlling the process of assigning vehicles to agenda entries.
 */
public class AssignVehicleToAgendaEntryController {
    private final Repositories repositories;
    private final AgendaRepository agendaRepository;
    private final VehicleRepository vehicleRepository;

    /**
     * Constructs an AssignVehicleToAgendaEntryController and initializes the repositories.
     */
    public AssignVehicleToAgendaEntryController() {
        this.repositories = Repositories.getInstance();
        this.agendaRepository = repositories.getAgendaRepository();
        this.vehicleRepository = repositories.getVehicleRepository();
    }

    /**
     * Retrieves a list of available vehicles for the specified time period.
     *
     * @param startTime The start time of the period.
     * @param endTime   The end time of the period.
     * @return A list of available vehicles.
     */
    public List<Vehicle> getAvailableVehicles(LocalDateTime startTime, LocalDateTime endTime) {
        return vehicleRepository.findAvailableVehicles(startTime, endTime);
    }

    /**
     * Assigns the specified list of vehicles to the given agenda entry and saves the entry.
     *
     * @param entry    The agenda entry to which the vehicles will be assigned.
     * @param vehicles The list of vehicles to assign.
     */
    public void assignVehiclesToEntry(Agenda entry, List<Vehicle> vehicles) {
        entry.setVehicles(vehicles);
        agendaRepository.save(entry);
    }

    /**
     * Retrieves all agenda entries from the repository.
     *
     * @return A list of all agenda entries.
     */
    public static List<Agenda> getAllAgendaEntries() {
        return AgendaRepository.getAll();
    }
}
