package MDISC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RouteImporter {
    public List<Route> importRoutes(String filePath) {
        List<Route> routes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    try {
                        int waterPointX = Integer.parseInt(parts[0].trim());
                        int waterPointY = Integer.parseInt(parts[1].trim());
                        double distance = Double.parseDouble(parts[2].trim());
                        Route route = new Route(waterPointX, waterPointY, distance);
                        routes.add(route);
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing line: " + line);
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Invalid line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return routes;
    }

    public static void main(String[] args) {
        // Example usage
        RouteImporter importer = new RouteImporter();
        List<Route> routes = importer.importRoutes("path/to/your/file.csv");

        // Display imported data
        for (Route route : routes) {
            System.out.println("Water Point X: " + route.getWaterPointX());
            System.out.println("Water Point Y: " + route.getWaterPointY());
            System.out.println("Distance: " + route.getDistance());
            System.out.println("---------------------");
        }
    }
}
