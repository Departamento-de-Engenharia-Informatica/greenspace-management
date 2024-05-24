package pt.ipp.isep.dei.esoft.project.domain;

public class Garden extends GreenSpace {
    public Garden(String name, double area, String email) {
        super(name, area, GreenSpaceType.GARDEN, email);
    }

    @Override
    public void displayDetails() {
        System.out.println("Garden: " + getName() + ", Area: " + getArea() + " sqm" + "Email" + getEmail());
    }
}

