package pt.ipp.isep.dei.esoft.project.domain;


public abstract class GreenSpace {
        private String name;
        private double area;
        private GreenSpaceType type;

        public GreenSpace(String name, double area, GreenSpaceType type) {
            this.name = name;
            this.area = area;
            this.type = type;
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

        public abstract void displayDetails();
}