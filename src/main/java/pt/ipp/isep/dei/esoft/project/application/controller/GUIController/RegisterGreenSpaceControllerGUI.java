package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.ui.gui.ControllerWithEmail;
import pt.ipp.isep.dei.esoft.project.ui.gui.SceneSwitcher;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class RegisterGreenSpaceControllerGUI  implements ControllerWithEmail {

    @FXML
    private TextField nameField;

    @FXML
    private TextField areaField;

    @FXML
    private ComboBox<String> typeComboBox;



    private String userEmail;
    @Override
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getUserEmail() {

        return userEmail; // Retrieve the user's email
    }

    @FXML
    public void initialize() {
        typeComboBox.getItems().addAll("Garden", "Medium-Sized Park", "Large-Sized Park");
    }

    @FXML
    private void handleRegister() {
        String name = nameField.getText();
        String areaText = areaField.getText();
        String type = typeComboBox.getValue();

        if (!validateName(name)) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Name cannot be null or contain special characters.");
            return;
        }

        if (!validateArea(areaText)) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Area must be a valid number.");
            return;
        }

        if (type == null) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "You must select a type of green space.");
            return;
        }

        double area = Double.parseDouble(areaText);
        GreenSpaceType greenSpaceType = getGreenSpaceType(type);

        RegisterGreenSpaceController.registerGreenSpace(name, area, greenSpaceType, userEmail);

        showAlert(Alert.AlertType.INFORMATION, "Success", "Green space registered successfully.");
    }



    private boolean validateName(String name) {
        return name != null && name.matches("[a-zA-Z0-9 ]+");
    }

    private boolean validateArea(String area) {
        try {
            Double.parseDouble(area);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private GreenSpaceType getGreenSpaceType(String type) {
        switch (type) {
            case "Garden":
                return GreenSpaceType.GARDEN;
            case "Medium-Sized Park":
                return GreenSpaceType.MEDIUM_SIZED_PARK;
            case "Large-Sized Park":
                return GreenSpaceType.LARGE_SIZED_PARK;
            default:
                throw new IllegalArgumentException("Invalid green space type: " + type);
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
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

