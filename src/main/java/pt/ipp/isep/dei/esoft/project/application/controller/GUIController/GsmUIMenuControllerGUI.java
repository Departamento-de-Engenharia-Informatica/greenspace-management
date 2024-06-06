package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.ui.console.ListAgendaEntries;
import pt.ipp.isep.dei.esoft.project.ui.gui.ControllerWithEmail;

import java.io.IOException;

public class GsmUIMenuControllerGUI implements ControllerWithEmail {


    @FXML
    private Label emailLabel;

    private String userEmail;


    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        // Set the email label text
        emailLabel.setText(userEmail);

    }

    @FXML
    private void handleRegisterGreenSpace(ActionEvent event) {
        try {
            // Load the FXML file for the Register Green Space form
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterGreenSpace.fxml"));

            Parent root = loader.load();

            RegisterGreenSpaceControllerGUI controller = loader.getController();
            controller.setUserEmail(userEmail);

            // Get the current stage (window)
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene to the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Register Green Space");

            // Show the new stage
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the Register Green Space form.");
            e.printStackTrace();
        }

    }



    @FXML
    private void handleListGreenSpaces(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListGreenSpaces.fxml"));
            Parent root = loader.load();

            ListGreenSpacesControllerGUI controller = loader.getController();
            controller.setUserEmail(userEmail);


            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("List Green Spaces");
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the List Green Spaces form.");
        }
    }

    @FXML
    private void handleToDoList(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ToDoList.fxml"));
            Parent root = loader.load();

            ToDoListControllerGUI controller = loader.getController();
            controller.setUserEmail(userEmail);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("To-Do List");
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the To-Do List.");
        }
    }

    @FXML
    private void handleAgenda(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Agenda.fxml"));
            Parent root = loader.load();

            AgendaControllerGUI controller = loader.getController();

            controller.setAgendaController(new AgendaController());
            controller.setToDoListController(new ToDoListController());
            controller.setUserEmail(userEmail);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Agenda");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the Agenda. See console for details.");
        }
    }

    @FXML
    private void handleListAgendaEntries(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListAgendaEntries.fxml"));
            Parent root = loader.load();

            ListAgendaEntriesControllerGUI controller = loader.getController();

//            controller.setAgendaController(new AgendaController());
//            controller.setToDoListController(new ToDoListController());
//            controller.setUserEmail(userEmail);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Agenda");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the Agenda. See console for details.");
        }
    }

    @FXML
    private void handleChangeAgendaStatus(ActionEvent event) {

    }

    @FXML
    private void handleAssignVehicle(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AssignVehicleToAgendaEntry.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Assign Vehicle to Agenda Entry");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAssignTeam(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AssignTeamToAgenda.fxml"));
            Parent root = loader.load();

            AssignTeamToAgendaControllerGUI controller = loader.getController();


            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Agenda");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the Agenda. See console for details.");
        }
    }

    private void showAlert(String title) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(title + " button clicked!");
        alert.showAndWait();
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
