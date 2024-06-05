package pt.ipp.isep.dei.esoft.project.domain;

/**
 * Represents a type of green space.
 */
public abstract class GreenSpace {
    private String name;
    private double area;
    private GreenSpaceType type;
    private String email;

    /**
     * Constructs a GreenSpace object with the specified name, area, type, and email.
     *
     * @param name  the name of the green space
     * @param area  the area of the green space in square meters
     * @param type  the type of the green space
     * @param email the email associated with the green space
     */
    public GreenSpace(String name, double area, GreenSpaceType type, String email) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (area <= 0) {
            throw new IllegalArgumentException("Area must be positive");
        }
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }

        this.name = name;
        this.area = area;
        this.type = type;
        this.email = email;
    }


    /**
     * Gets the name of the green space.
     *
     * @return the name of the green space
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the area of the green space.
     *
     * @return the area of the green space in square meters
     */
    public double getArea() {
        return area;
    }

    /**
     * Gets the type of the green space.
     *
     * @return the type of the green space
     */
    public GreenSpaceType getType() {
        return type;
    }

    /**
     * Gets the email associated with the green space.
     *
     * @return the email associated with the green space
     */
    public String getEmail() {
        return email;
    }

    /**
     * Displays the details of the green space.
     *
     * @return
     */
    public abstract String displayDetails();
}
