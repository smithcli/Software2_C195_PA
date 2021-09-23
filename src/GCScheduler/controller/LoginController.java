package GCScheduler.controller;

import GCScheduler.GCScheduler;
import GCScheduler.model.Scheduler;
import GCScheduler.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.text.Collator;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller for Login.fxml, Prompts User to use credentials to login to application.
 */
public class LoginController {

    @FXML private TextField userNameField;
    @FXML private Label locationLabel;
    @FXML private Button loginButton;
    @FXML private Label loginErrorLabel;
    @FXML private PasswordField passwordField;
    public static final ResourceBundle RB = ResourceBundle.getBundle("GCScheduler/utilities/language/GCBundle",Locale.getDefault());

    /**
     * Method calls initial data collection, sets fields to accommodate english and french users, and displays user's location.
     */
    public void initialize() {
        MenuBarController.refreshData();
        String location = ZoneId.systemDefault().getId();
        locationLabel.setText(RB.getString("YourLocation") + location);
        loginButton.setText(RB.getString("Login"));
        userNameField.setPromptText(RB.getString("Username"));
        passwordField.setPromptText(RB.getString("Password"));
    }

    /**
     * Method collects username and password, calls validation method, determines outcome with switch, and calls log method.
     * @param event login button press
     * @throws IOException loading Scheduler.fxml
     */
    @FXML void loginButtonListener(ActionEvent event) throws IOException {
        String userName = userNameField.getText();
        String password = passwordField.getText();
        switch (userValidation(userName,password)) {
            case 1:
                loadScheduler();
                break;
            case 2:
                loginErrorLabel.setText(RB.getString("Error2"));
                loginErrorLabel.setVisible(true);
                break;
            case 3:
                loginErrorLabel.setText(RB.getString("Error3"));
                loginErrorLabel.setVisible(true);
        }
        // Add logging method call
    }

    /**
     * Method Listens for ENTER key release in the username field and calls the login method.
     * @param event ENTER key release.
     */
    @FXML void usernameListener(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            loginButton.fire();
        }
    }

    /**
     * Method Listens for ENTER key release in password field and calls the login method.
     * @param event ENTER key release.
     */
    @FXML void passwordListener(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            loginButton.fire();
        }
    }

    /**
     * Method loads Schedule.fxml and places a it in a new Scene on Primary Stage.
     * @throws IOException loading the Schedule.fxml
     */
    protected void loadScheduler() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/GCScheduler/view/Schedule.fxml")));
        GCScheduler.getPrimaryStage().setScene(new Scene(root));
    }

    /**
     * Method searches for User and Password matching the fields and returns an integer for the login method to determine outcome.
     * @param userName String from Username field.
     * @param password String from Password field.
     * @return 1&equals;Correct, 2&equals;Username Correct, 3&equals;Username not found.
     */
    protected int userValidation(String userName, String password) {
        for (User user : Scheduler.getAllUsers()) {
            Collator collator = Collator.getInstance(Locale.ROOT);
            if (collator.equals(userName.toLowerCase(Locale.ROOT),user.getUserName().toLowerCase(Locale.ROOT))) {
                collator.setStrength(Collator.IDENTICAL);
                if (collator.equals(password,user.getPassword())) {
                    //Username and Password correct.
                    return 1;
                }
                //Only Username correct.
                return 2;
            }
        }
        //Username not found.
        return 3;
    }
}
