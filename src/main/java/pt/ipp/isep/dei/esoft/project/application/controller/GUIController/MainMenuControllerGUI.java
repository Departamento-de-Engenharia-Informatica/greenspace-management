package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class MainMenuControllerGUI {

    @FXML
    private void handleDoLogin(ActionEvent event) {
        loadUI(event, "/fxml/AuthenticationUI.fxml", "Login");
    }

    @FXML
    private void handleDevTeam(ActionEvent event) {
        loadUI(event, "/fxml/DevTeamUI.fxml", "Development Team");
    }

    private void loadUI(ActionEvent event, String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
