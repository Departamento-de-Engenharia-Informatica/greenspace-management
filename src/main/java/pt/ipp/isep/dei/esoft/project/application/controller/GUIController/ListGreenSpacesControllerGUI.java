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
import javafx.scene.control.ChoiceBox;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ListGreenSpacesControllerGUI implements ControllerWithEmail {

    @FXML
    private ChoiceBox<String> sortingAlgorithmChoiceBox;

    @FXML
    private ListView<String> greenSpaceListView;

    private String userEmail;
    private GreenSpaceSorter sortingAlgorithm = ConfigReader.readSortingAlgorithm();


    @Override
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void initialize() {
        setUserEmail(GsmUIApplication.getUserEmail());
        setupSortingAlgorithmChoiceBox();
        displayGreenSpaces();
    }

    private void setupSortingAlgorithmChoiceBox() {
        ObservableList<String> sortingAlgorithms = FXCollections.observableArrayList(
                "Size Descending", // Update with more options as needed
                "Name Ascending"
        );
        sortingAlgorithmChoiceBox.setItems(sortingAlgorithms);
        sortingAlgorithmChoiceBox.setValue("Size Descending"); // Default value
    }

    private void displayGreenSpaces() {
        RegisterGreenSpaceController controller = new RegisterGreenSpaceController();
        List<GreenSpace> greenSpaces = controller.getAllGreenSpaces();

        if (sortingAlgorithm != null) {
            greenSpaces = sortingAlgorithm.sort(greenSpaces);
        } else {
            showAlert(AlertType.ERROR, "Error", "Sorting algorithm not available.");
            return;
        }

        if (greenSpaces.isEmpty()) {
            showAlert(AlertType.INFORMATION, "Informsation", "No green spaces registered.");
        } else {
            // Clear the ListView before adding sorted green spaces
            greenSpaceListView.getItems().clear();
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
    public void handleBack(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchToScene("/fxml/GsmUIMenu.fxml", "Register Green Space", (Node) actionEvent.getSource(), userEmail);
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the Main Menu.");
        }
    }

    @FXML
    public void updateSortingAlgorithm(ActionEvent actionEvent) {
        String selectedAlgorithm = sortingAlgorithmChoiceBox.getValue();
        String sortingAlgorithmClassName;
        switch (selectedAlgorithm) {
            case "Size Descending":
                sortingAlgorithmClassName = "pt.ipp.isep.dei.esoft.project.config.SizeDescendingSorter";
                break;
            case "Name Ascending":
                sortingAlgorithmClassName = "pt.ipp.isep.dei.esoft.project.config.NameAscendingSorter";
                break;
            default:
                showAlert(AlertType.ERROR, "Error", "Invalid sorting algorithm selected.");
                return;
        }
        // Update the sorting algorithm variable
        sortingAlgorithm = createSortingAlgorithmInstance(sortingAlgorithmClassName);
        // Clear the ListView and re-display green spaces after updating the sorting algorithm
        displayGreenSpaces();
    }

    private GreenSpaceSorter createSortingAlgorithmInstance(String className) {
        try {
            Class<?> sortingAlgorithmClass = Class.forName(className);
            return (GreenSpaceSorter) sortingAlgorithmClass.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e){
            return null;
        }
    }


}
