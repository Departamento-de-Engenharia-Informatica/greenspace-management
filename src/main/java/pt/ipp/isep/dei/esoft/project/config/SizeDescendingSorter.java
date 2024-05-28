package pt.ipp.isep.dei.esoft.project.config;

import pt.ipp.isep.dei.esoft.project.ui.console.GreenSpaceSorter;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.List;

/**
 * Sorter implementation for sorting GreenSpace objects by size in descending order.
 */
public class SizeDescendingSorter implements GreenSpaceSorter {

    /**
     * Sorts the list of GreenSpace objects by size in descending order.
     *
     * @param greenSpaces the list of GreenSpace objects to be sorted
     * @return the sorted list of GreenSpace objects
     */
    @Override
    public List<GreenSpace> sort(List<GreenSpace> greenSpaces) {
        greenSpaces.sort((gs1, gs2) -> Double.compare(gs2.getArea(), gs1.getArea()));
        return greenSpaces;
    }
}
