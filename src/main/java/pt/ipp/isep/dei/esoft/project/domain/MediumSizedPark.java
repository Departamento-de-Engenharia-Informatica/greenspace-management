package pt.ipp.isep.dei.esoft.project.domain;

/**
 * Represents a Medium-Sized Park which is a type of GreenSpace.
 */
public class MediumSizedPark extends GreenSpace {

    /**
     * Constructs a MediumSizedPark object with the specified name, area, and email.
     *
     * @param name  the name of the medium-sized park
     * @param area  the area of the medium-sized park in square meters
     * @param email the email associated with the medium-sized park
     */
    public MediumSizedPark(String name, double area, String email) {
        super(name, area, GreenSpaceType.MEDIUM_SIZED_PARK, email);
    }

    /**
     * Displays the details of the medium-sized park, including its name and area.
     */
    @Override
    public void displayDetails() {
        System.out.println("Medium-Sized Park: " + getName() + ", Area: " + getArea() + " sqm");
    }
}
