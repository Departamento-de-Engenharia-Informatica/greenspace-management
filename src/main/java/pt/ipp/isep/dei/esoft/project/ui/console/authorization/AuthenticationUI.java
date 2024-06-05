package pt.ipp.isep.dei.esoft.project.ui.console.authorization;

import pt.ipp.isep.dei.esoft.project.application.controller.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.application.controller.GUIController.GsmUIMenuController;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.ipp.isep.dei.esoft.project.ui.gui.GsmUIApplication;
import pt.ipp.isep.dei.esoft.project.ui.gui.MainMenu;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import pt.ipp.isep.dei.esoft.project.ui.gui.MainMenu;
import javafx.application.Application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class AuthenticationUI implements Runnable {
    private final AuthenticationController ctrl;

    public AuthenticationUI() {
        ctrl = new AuthenticationController();
    }

    public void run() {
        boolean success = doLogin();

        if (success) {
            String userEmail = ctrl.getCurrentUserEmail(); // Get the logged-in user's email
            List<UserRoleDTO> roles = this.ctrl.getUserRoles();
            if ((roles == null) || (roles.isEmpty())) {
                System.out.println("No role assigned to user.");
            } else {
                UserRoleDTO role = selectsRole(roles);
                if (!Objects.isNull(role)) {
                    List<MenuItem> rolesUI = getMenuItemForRoles(userEmail);
                    this.redirectToRoleUI(rolesUI, role, userEmail); // Pass the userEmail to redirectToRoleUI
                } else {
                    System.out.println("No role selected.");
                }
            }
        }
        this.logout();
    }

    private List<MenuItem> getMenuItemForRoles(String userEmail) {
        List<MenuItem> rolesUI = new ArrayList<>();
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_ADMIN, new AdminUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_HRM, new HrmUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_VFM, new VFMUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_GSM, new GsmUI(userEmail)));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_COLLABORATOR, new ColaboratorUI()));

        // TODO: Complete with other user roles and related RoleUI
        return rolesUI;
    }

    private boolean doLogin() {
        System.out.println("\n\n--- LOGIN UI ---------------------------");

        int maxAttempts = 3;
        boolean success = false;
        do {
            maxAttempts--;
            String id = Utils.readLineFromConsole("Enter UserId/Email: ");
            String pwd = Utils.readLineFromConsole("Enter Password: ");

            success = ctrl.doLogin(id, pwd);
            if (!success) {
                System.out.println("Invalid UserId and/or Password. \n You have  " + maxAttempts + " more attempt(s).");
            }

        } while (!success && maxAttempts > 0);
        return success;
    }

    private void logout() {
        ctrl.doLogout();
    }

    private void redirectToRoleUI(List<MenuItem> rolesUI, UserRoleDTO role, String userEmail) {
        boolean found = false;
        Iterator<MenuItem> it = rolesUI.iterator();
        while (it.hasNext() && !found) {
            MenuItem item = it.next();
            found = item.hasDescription(role.getDescription());
            if (found) {
                // Check if the role requires the JavaFX UI
                if (role.getDescription().equals(AuthenticationController.ROLE_GSM)) {
                    launchJavaFXUI(userEmail);
                } else {
                    item.run();
                }
            }
        }
        if (!found) {
            System.out.println("There is no UI for users with role '" + role.getDescription() + "'");
        }
    }

    private UserRoleDTO selectsRole(List<UserRoleDTO> roles) {
        if (roles.size() == 1) {
            return roles.get(0);
        } else {
            return (UserRoleDTO) Utils.showAndSelectOne(roles, "Select the role you want to adopt in this session:");
        }
    }

    // Inside your login UI class (either console or JavaFX)
    private void launchJavaFXUI(String userEmail) {
        GsmUIApplication.setUserEmail(userEmail); // Pass the user's email to GsmUIApplication

        new Thread(() -> Application.launch(GsmUIApplication.class)).start(); // Launch the JavaFX application

    }

}
