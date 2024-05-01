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
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int waterPointX = Integer.parseInt(parts[0]);
                    int waterPointY = Integer.parseInt(parts[1]);
                    double distance = Double.parseDouble(parts[2]);
                    Route route = new Route(waterPointX, waterPointY, distance);
                    routes.add(route);
                } else {
                    System.out.println("Invalid line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return routes;
    }
}
