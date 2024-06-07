package pt.ipp.isep.dei.esoft.project.application.controller.GUIController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pt.ipp.isep.dei.esoft.project.ui.gui.ControllerWithEmail;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CollaboratorUIMenuControllerGUI implements ControllerWithEmail {

    @FXML
    private Label emailLabel;

    private String userEmail;

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        // Set the email label text
        emailLabel.setText(userEmail);

    }


    @FXML
    private void handleListTasks(ActionEvent actionEvent){

    }


}
