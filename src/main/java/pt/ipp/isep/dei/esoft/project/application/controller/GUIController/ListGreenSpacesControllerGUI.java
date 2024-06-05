package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.config.ConfigReader;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.ui.console.GreenSpaceSorter;
import pt.ipp.isep.dei.esoft.project.ui.gui.ControllerWithEmail;
import pt.ipp.isep.dei.esoft.project.ui.gui.GsmUIApplication;
import pt.ipp.isep.dei.esoft.project.ui.gui.SceneSwitcher;

import java.io.IOException;
import java.util.List;

public class ListGreenSpacesControllerGUI implements ControllerWithEmail {

    @FXML
    private ListView<String> greenSpaceListView;

    private String userEmail;

    @Override
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void initialize() {
        setUserEmail(GsmUIApplication.getUserEmail());
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
            for (GreenSpace greenSpace : greenSpaces) {
                if (greenSpace.getEmail().equals(getUserEmail())) {
                    greenSpaceListView.getItems().add(greenSpace.displayDetails());
                }
            }
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void handleBack(javafx.event.ActionEvent actionEvent) {

        try {
            SceneSwitcher.switchToScene("/fxml/GsmUIMenu.fxml", "Register Green Space", (Node) actionEvent.getSource(), userEmail);

        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the Main Menu.");
        }
    }
}
