package pt.ipp.isep.dei.esoft.project.domain;

public class LargeSizedPark extends GreenSpace {
    public LargeSizedPark(String name, double area) {
        super(name, area, GreenSpaceType.LARGE_SIZED_PARK);
    }

    @Override
    public void displayDetails() {
        System.out.println("Large-Sized Park: " + getName() + ", Area: " + getArea() + " sqm");
    }
}
