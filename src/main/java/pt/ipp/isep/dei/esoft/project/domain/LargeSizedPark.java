package pt.ipp.isep.dei.esoft.project.domain;

/**
 * Represents a Large-Sized Park which is a type of GreenSpace.
 */
public class LargeSizedPark extends GreenSpace {

    /**
     * Constructs a LargeSizedPark object with the specified name, area, and email.
     *
     * @param name  the name of the large-sized park
     * @param area  the area of the large-sized park in square meters
     * @param email the email associated with the large-sized park
     */
    public LargeSizedPark(String name, double area, String email) {
        super(name, area, GreenSpaceType.LARGE_SIZED_PARK, email);
    }

    /**
     * Displays the details of the large-sized park, including its name and area.
     *
     * @return
     */
    @Override
    public String displayDetails() {
        System.out.println("Large-Sized Park: " + getName() + ", Area: " + getArea() + " sqm");
        return "Large-Sized Park: " + getName() + ", Area: " + getArea() + " sqm";
    }
}
