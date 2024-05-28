package pt.ipp.isep.dei.esoft.project.config;

import pt.ipp.isep.dei.esoft.project.ui.console.GreenSpaceSorter;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.List;

/**
 * Sorter implementation for sorting GreenSpace objects by name in ascending order.
 */
public class NameAscendingSorter implements GreenSpaceSorter {

    /**
     * Sorts the list of GreenSpace objects by name in ascending order.
     *
     * @param greenSpaces the list of GreenSpace objects to be sorted
     * @return the sorted list of GreenSpace objects
     */
    @Override
    public List<GreenSpace> sort(List<GreenSpace> greenSpaces) {
        greenSpaces.sort((gs1, gs2) -> gs1.getName().compareToIgnoreCase(gs2.getName()));
        return greenSpaces;
    }
}
