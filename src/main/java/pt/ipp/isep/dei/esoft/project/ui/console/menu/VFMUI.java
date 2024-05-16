package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.MaintenanceRegistrationUI;
import pt.ipp.isep.dei.esoft.project.ui.console.VehicleRegistrationUI;
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

        options.add(new MenuItem("Vehicle Management", vehicleRegistrationUI));
        options.add(new MenuItem("Maintenance Management", maintenanceRegistrationUI));

        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- Vehicle Fleet Management Menu -------------------------");

            if (option >= 0 && option < options.size()) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
