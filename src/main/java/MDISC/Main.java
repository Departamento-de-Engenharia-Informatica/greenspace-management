package MDISC;

import java.util.List;
import java.util.*;



public class Main {

    public static void main(String[] args) {
        // Example usage
        RouteImporter importer = new RouteImporter();
        List<Route> routes = importer.importRoutes("C:\\Users\\David\\Downloads\\US13_JardimDosSentimentos.csv");

        // Display imported data
        for (Route route : routes) {
            System.out.println("Water Point X: " + route.getWaterPointX());
            System.out.println("Water Point Y: " + route.getWaterPointY());
            System.out.println("Distance: " + route.getDistance());
            System.out.println("---------------------");
        }
    }



}
