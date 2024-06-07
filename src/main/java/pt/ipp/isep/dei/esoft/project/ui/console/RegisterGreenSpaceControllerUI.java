package pt.ipp.isep.dei.esoft.project.ui.console;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;

import java.util.List;

/**
 * Controller for registering green spaces.
 */
public class RegisterGreenSpaceControllerUI {

    @FXML
    private TextField nameField;

    @FXML
    private TextField areaField;

    @FXML
    private ChoiceBox<String> typeChoiceBox;

    @FXML
    private Label statusLabel;

    private String userEmail;

    /**
     * Initializes the controller.
     */
    @FXML
    private void initialize() {
        typeChoiceBox.setItems(FXCollections.observableArrayList("Garden", "Medium-Sized Park", "Large-Sized Park"));
    }

    /**
     * Sets the user email.
     *
     * @param userEmail the email of the logged-in user
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * Handles the register button action.
     */
    @FXML
    private void handleRegisterButtonAction() {
        String name = nameField.getText();
        double area;
        try {
            area = Double.parseDouble(areaField.getText());
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid area value.");
            return;
        }

        GreenSpaceType type;
        switch (typeChoiceBox.getValue()) {
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
                statusLabel.setText("Invalid choice");
                return;
        }

        RegisterGreenSpaceController.registerGreenSpace(name, area, type, userEmail);
        statusLabel.setText("Green space registered successfully.");

        // Display all registered green spaces
        List<GreenSpace> greenSpaces = RegisterGreenSpaceController.getAllGreenSpaces();
        for (GreenSpace greenSpace : greenSpaces) {
            System.out.println(greenSpace.displayDetails());
        }
    }
}
