package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.ArrayList;
import java.util.List;

public class GreenSpaceRepository {
    private List<GreenSpace> greenSpaces = new ArrayList<>();

    public void addGreenSpace(GreenSpace greenSpace) {

        greenSpaces.add(greenSpace);

    }

    public List<GreenSpace> getGreenSpaces() {
        return new ArrayList<>(greenSpaces);
    }
}
