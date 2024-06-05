package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.config.ConfigReader;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.ui.console.GreenSpaceSorter;


import java.util.List;

public class ListGreenSpacesControllerGUI {

    @FXML
    private ListView<String> greenSpaceListView;

    private String userEmail;

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        displayGreenSpaces();
    }

    private void displayGreenSpaces() {
        RegisterGreenSpaceController controller = new RegisterGreenSpaceController();
        List<GreenSpace> greenSpaces = controller.getAllGreenSpaces();
        GreenSpaceSorter sortingAlgorithm = ConfigReader.readSortingAlgorithm();

        if (sortingAlgorithm != null) {
            greenSpaces = sortingAlgorithm.sort(greenSpaces);
        } else {
            showAlert(AlertType.ERROR, "Error", "Sorting algorithm not available.");
            return;
        }

        if (greenSpaces.isEmpty()) {
            showAlert(AlertType.INFORMATION, "Information", "No green spaces registered.");
        } else {
            boolean found = false;
            for (GreenSpace greenSpace : greenSpaces) {
                if (greenSpace.getEmail().equals(userEmail)) {
                    greenSpaceListView.getItems().add(greenSpace.displayDetails());
                    found = true;
                }
            }
            if (!found) {
                showAlert(AlertType.INFORMATION, "Information", "No green spaces registered for this user.");
            }
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}