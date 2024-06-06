package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.config.ConfigReader;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;
import pt.ipp.isep.dei.esoft.project.ui.console.GreenSpaceSorter;
import pt.ipp.isep.dei.esoft.project.ui.gui.ControllerWithEmail;
import pt.ipp.isep.dei.esoft.project.ui.gui.GsmUIApplication;
import pt.ipp.isep.dei.esoft.project.ui.gui.SceneSwitcher;

import java.io.IOException;
import java.util.List;

public class ListToDoListEntriesControllerGUI  implements ControllerWithEmail {

    @FXML
    private ListView<String> toDoListView;

    private String userEmail;

    @Override
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void initialize() {
        setUserEmail(GsmUIApplication.getUserEmail());
        displayToDoList();
    }

    private void displayToDoList() {
        ToDoListController controller = new ToDoListController();
        List<ToDoList> toDoLists = controller.getAllToDoListEntries();

        if (toDoLists.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Information", "No To-Do list entries registered.");
        } else {
            for (ToDoList toDoList : toDoLists) {
                if (toDoList.getUserEmail().equals(getUserEmail())) {
                    toDoListView.getItems().add(toDoList.toString());
                }
            }
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