package pt.ipp.isep.dei.esoft.project.domain;


public abstract class GreenSpace {
        private String name;
        private double area;
        private GreenSpaceType type;
        private String email;

        public GreenSpace(String name, double area, GreenSpaceType type, String email) {
            this.name = name;
            this.area = area;
            this.type = type;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public double getArea() {
            return area;
        }

        public GreenSpaceType getType() {
            return type;
        }
        public String getEmail() {
        return email;
    }

        public abstract void displayDetails();
}