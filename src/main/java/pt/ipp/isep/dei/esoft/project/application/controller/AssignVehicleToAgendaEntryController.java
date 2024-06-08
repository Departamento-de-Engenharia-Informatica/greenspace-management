package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller for assigning vehicles to agenda entries.
 */
public class AssignVehicleToAgendaEntryController {
    private final Repositories repositories;
    private final AgendaRepository agendaRepository;
    private final VehicleRepository vehicleRepository;

    /**
     * Constructs a new AssignVehicleToAgendaEntryController.
     * Initializes the repositories needed to fetch and update agenda entries and vehicles.
     */
    public AssignVehicleToAgendaEntryController() {
        this.repositories = Repositories.getInstance();
        this.agendaRepository = repositories.getAgendaRepository();
        this.vehicleRepository = repositories.getVehicleRepository();
    }

    /**
     * Gets a list of available vehicles for the specified time range.
     *
     * @param startTime the start time of the availability period
     * @param endTime   the end time of the availability period
     * @return a list of available vehicles
     */
    public List<Vehicle> getAvailableVehicles(LocalDateTime startTime, LocalDateTime endTime) {
        return vehicleRepository.findAvailableVehicles(startTime, endTime);
    }

    /**
     * Assigns a list of vehicles to an agenda entry and saves the entry.
     *
     * @param entry    the agenda entry to which the vehicles will be assigned
     * @param vehicles the list of vehicles to assign
     */
    public void assignVehiclesToEntry(Agenda entry, List<Vehicle> vehicles) {
        entry.setVehicles(vehicles);
        agendaRepository.save(entry);
    }

    /**
     * Gets all agenda entries from the repository.
     *
     * @return a list of all agenda entries
     */
    public static List<Agenda> getAllAgendaEntries() {
        List<Agenda> agendas = AgendaRepository.getAll();
        System.out.println("Fetched agenda entries: " + agendas.size());
        return agendas;
    }
}
