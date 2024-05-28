package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class GreenSpaceRepository {
    private static List<GreenSpace> greenSpaces = new ArrayList<>();

    public static void addGreenSpace(GreenSpace greenSpace) {

        greenSpaces.add(greenSpace);

    }

    public static List<GreenSpace> getGreenSpaces() {
        return new ArrayList<>(greenSpaces);
    }

    public List<GreenSpace> getGreenSpacesByEmail(String email) {
        return greenSpaces.stream()
                .filter(greenSpace -> greenSpace.getEmail().equals(email))
                .collect(Collectors.toList());
    }



}
