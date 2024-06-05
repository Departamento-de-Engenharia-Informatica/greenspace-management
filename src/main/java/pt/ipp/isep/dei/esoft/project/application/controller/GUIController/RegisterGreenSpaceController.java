package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;

public class RegisterGreenSpaceController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField areaField;

    @FXML
    private ComboBox<String> typeComboBox;

    private final String userEmail;

    public RegisterGreenSpaceController() {
        // You may want to pass userEmail through the constructor or another method
        this.userEmail = "sample@example.com"; // Placeholder
    }

    @FXML
    private void initialize() {
        typeComboBox.setItems(FXCollections.observableArrayList("Garden", "Medium-Sized Park", "Large-Sized Park"));
    }

    @FXML
    private void handleRegister() {
        String name = nameField.getText();
        double area;
        try {
            area = Double.parseDouble(areaField.getText());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Area must be a number.");
            return;
        }

        String typeString = typeComboBox.getValue();
        GreenSpaceType type;
        switch (typeString) {
            case "Garden":
                type = GreenSpaceType.GARDEN;
                break;
            case "Medium-Sized Park":
                type = GreenSpaceType.MEDIUM_SIZED_PARK;
                break;
            case "Large-Sized Park":
                type = GreenSpaceType.LARGE_SIZED_PARK;
                break;
            default:
                showAlert(Alert.AlertType.ERROR, "Invalid Selection", "Please select a valid green space type.");
                return;
        }

        RegisterGreenSpaceController.registerGreenSpace(name, area, type, userEmail);
        showAlert(Alert.AlertType.INFORMATION, "Success", "Green space registered successfully.");
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

