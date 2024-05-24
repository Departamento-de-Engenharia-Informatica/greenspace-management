package pt.ipp.isep.dei.esoft.project.domain;

public class MediumSizedPark extends GreenSpace {
    public MediumSizedPark(String name, double area, String email) {
        super(name, area, GreenSpaceType.MEDIUM_SIZED_PARK, email);
    }

    @Override
    public void displayDetails() {
        System.out.println("Medium-Sized Park: " + getName() + ", Area: " + getArea() + " sqm" + "Email" + getEmail());
    }
}
