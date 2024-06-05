package pt.ipp.isep.dei.esoft.project.ui.gui;


import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {

    public static void switchToScene(String fxmlFile, String title, Node node, String userEmail) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(fxmlFile));
        Parent root = loader.load();

        // Pass user email to the controller of the new scene, if applicable
        if (loader.getController() instanceof ControllerWithEmail) {
            ((ControllerWithEmail) loader.getController()).setUserEmail(userEmail);
        }
        System.out.println("Testazadas " +userEmail);

        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

}

