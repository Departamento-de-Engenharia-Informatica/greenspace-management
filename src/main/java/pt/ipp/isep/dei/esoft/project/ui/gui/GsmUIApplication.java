package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.GUIController.GsmUIMenuController;

public class GsmUIApplication extends Application {
    static String userEmail;


    public static void setUserEmail(String email) {
        userEmail = email; // Store the user's email

    }

    public static String getUserEmail() {

        return userEmail; // Retrieve the user's email
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GsmUIMenu.fxml"));
        Scene scene = new Scene(loader.load());

        GsmUIMenuController controller = loader.getController();
        controller.setUserEmail(userEmail);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Green Space Manager");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
