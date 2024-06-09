package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.GUIController.CollaboratorUIMenuControllerGUI;

public class CollaboratorUIApplication extends Application {
    private static String userEmail;

    public static void setUserEmail(String email) {
        userEmail = email;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CollaboratorUIMenu.fxml"));
        Scene scene = new Scene(loader.load());

        CollaboratorUIMenuControllerGUI controller = loader.getController();
        controller.setUserEmail(userEmail);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Collaborator");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
