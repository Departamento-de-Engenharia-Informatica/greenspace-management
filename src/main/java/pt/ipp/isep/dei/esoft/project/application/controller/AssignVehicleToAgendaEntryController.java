package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.time.LocalDateTime;
import java.util.List;

public class AssignVehicleToAgendaEntryController {
    private final Repositories repositories;
    private final AgendaRepository agendaRepository;
    private final VehicleRepository vehicleRepository;

    public AssignVehicleToAgendaEntryController() {
        this.repositories = Repositories.getInstance();
        this.agendaRepository = repositories.getAgendaRepository();
        this.vehicleRepository = repositories.getVehicleRepository();
    }

    public List<Vehicle> getAvailableVehicles(LocalDateTime startTime, LocalDateTime endTime) {
        return vehicleRepository.findAvailableVehicles(startTime, endTime);
    }

    public void assignVehiclesToEntry(Agenda entry, List<Vehicle> vehicles) {
        entry.setVehicles(vehicles);
        agendaRepository.save(entry);
    }
    public static List<Agenda> getAllAgendaEntries() {
        return AgendaRepository.getAll();
    }
}
