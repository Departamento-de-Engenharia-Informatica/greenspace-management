package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.DisplayVehiclesUI;
import pt.ipp.isep.dei.esoft.project.ui.console.MaintenanceRegistrationUI;
import pt.ipp.isep.dei.esoft.project.ui.console.VehicleRegistrationUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ListVehiclesNeedingCheckupUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;



import java.util.ArrayList;
import java.util.List;

public class VFMUI implements Runnable {

    public VFMUI(){
    }

    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<>();
        VehicleRegistrationUI vehicleRegistrationUI = new VehicleRegistrationUI();
        MaintenanceRegistrationUI maintenanceRegistrationUI = new MaintenanceRegistrationUI();
        DisplayVehiclesUI displayVehiclesUI = new DisplayVehiclesUI();
        ListVehiclesNeedingCheckupUI listVehiclesNeedingCheckupUI = new ListVehiclesNeedingCheckupUI();

        options.add(new MenuItem("Register Vehicle", vehicleRegistrationUI));
        options.add(new MenuItem("List Vehicles", displayVehiclesUI));
        options.add(new MenuItem("Maintenance Management", maintenanceRegistrationUI));
        options.add(new MenuItem("List vehicles needing check-up", listVehiclesNeedingCheckupUI));

        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- Vehicle Fleet Management Menu -------------------------");

            if (option >= 0 && option < options.size()) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
