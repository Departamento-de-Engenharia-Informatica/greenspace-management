package pt.ipp.isep.dei.esoft.project.domain;

/**
 * Represents a Garden which is a type of GreenSpace.
 */
public class Garden extends GreenSpace {

    /**
     * Constructs a Garden object with the specified name, area, and email.
     *
     * @param name  the name of the garden
     * @param area  the area of the garden in square meters
     * @param email the email associated with the garden
     */
    public Garden(String name, double area, String email) {
        super(name, area, GreenSpaceType.GARDEN, email);
    }

    /**
     * Displays the details of the garden, including its name and area.
     *
     * @return
     */
    @Override
    public String displayDetails() {
        System.out.println("Garden: " + getName() + ", Area: " + getArea() + " sqm" );
        return "Garden: " + getName() + ", Area: " + getArea() + " sqm" ;
    }
}

