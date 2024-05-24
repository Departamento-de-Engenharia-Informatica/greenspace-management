package pt.ipp.isep.dei.esoft.project.config;

import pt.ipp.isep.dei.esoft.project.ui.console.GreenSpaceSorter;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.List;

public class SizeDescendingSorter implements GreenSpaceSorter {
    @Override
    public List<GreenSpace> sort(List<GreenSpace> greenSpaces) {
        greenSpaces.sort((gs1, gs2) -> Double.compare(gs2.getArea(), gs1.getArea()));
        return greenSpaces;
    }
}
