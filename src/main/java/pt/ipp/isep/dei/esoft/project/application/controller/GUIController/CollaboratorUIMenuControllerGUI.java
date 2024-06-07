package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.ControllerWithEmail;

import java.io.IOException;

public class CollaboratorUIMenuControllerGUI implements ControllerWithEmail {

    @FXML
    private Label emailLabel;

    private String userEmail;

    @Override
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        emailLabel.setText(userEmail);
    }

    @FXML
    private void handlelistTasks() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListTasks.fxml"));
            Parent root = loader.load();

            ListTaksControllerGUI controller = loader.getController();
            controller.setUserEmail(userEmail);

            Stage stage = (Stage) emailLabel.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("List Tasks");
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the List Tasks form.");
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
