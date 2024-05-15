package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Garden;
import pt.ipp.isep.dei.esoft.project.domain.MediumSizedPark;
import pt.ipp.isep.dei.esoft.project.domain.LargeSizedPark;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;

import java.util.List; // Adicionar a importação correta

public class RegisterGreenSpaceController {
    private GreenSpaceRepository greenSpaceRepository;

    public RegisterGreenSpaceController(GreenSpaceRepository greenSpaceRepository) {
        this.greenSpaceRepository = greenSpaceRepository;
    }

    public void registerGreenSpace(String name, double area, GreenSpaceType type) {
        GreenSpace greenSpace;
        switch (type) {
            case GARDEN:
                greenSpace = new Garden(name, area);
                break;
            case MEDIUM_SIZED_PARK:
                greenSpace = new MediumSizedPark(name, area);
                break;
            case LARGE_SIZED_PARK:
                greenSpace = new LargeSizedPark(name, area);
                break;
            default:
                throw new IllegalArgumentException("Invalid Green Space Type");
        }
        greenSpaceRepository.addGreenSpace(greenSpace);
    }

    public List<GreenSpace> getAllGreenSpaces() { // Corrigir a importação
        return greenSpaceRepository.getGreenSpaces();
    }
}
