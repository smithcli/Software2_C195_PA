package GCScheduler.controller;

import GCScheduler.GCScheduler;
import GCScheduler.model.Scheduler;
import GCScheduler.model.User;
import GCScheduler.utilities.DateTimeConv;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.*;
import java.text.Collator;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
    private final String LOCATION = ZoneId.systemDefault().getId();

    /**
     * Method calls initial data collection, sets fields to accommodate english and french users, and displays user's location.
     */
    public void initialize() {
        MenuBarController.refreshData();
        locationLabel.setText(RB.getString("YourLocation") + LOCATION);
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
        int attemptValue = 0;
        switch (userValidation(userName,password)) {
            case 1:
                attemptValue = 1;
                loadScheduler();
                break;
            case 2:
                loginErrorLabel.setText(RB.getString("Error2"));
                loginErrorLabel.setVisible(true);
                attemptValue = 2;
                break;
            case 3:
                loginErrorLabel.setText(RB.getString("Error3"));
                loginErrorLabel.setVisible(true);
                attemptValue = 2;
        }
        logAttempt(userName, attemptValue);
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
     * @return 1=Correct, 2=Username Correct, 3=Username not found.
     */
    protected int userValidation(String userName, String password) {
        for (User user : Scheduler.getAllUsers()) {
            Collator collator = Collator.getInstance(Locale.ROOT);
            if (collator.equals(userName.toLowerCase(Locale.ROOT),user.getUserName().toLowerCase(Locale.ROOT))) {
                collator.setStrength(Collator.IDENTICAL);
                if (collator.equals(password,user.getPassword())) {
                    //Username and Password correct.
                    user.setActive(true);
                    Scheduler.setActiveUser(user);
                    return 1;
                }
                //Only Username correct.
                return 2;
            }
        }
        //Username not found.
        return 3;
    }

    /**
     * Method logs the login attempt as successful or invalid to the root folder in file named login_activity.txt.
     * @param username Username used to attempt login
     * @param attemptValue 1=valid login log to file, 2=invalid login log to file.
     * @throws IOException creates if not exists and writes to login_activity.txt
     */
    protected void logAttempt(String username, int attemptValue) throws IOException {
        String fileName = "login_activity.txt", log;
        ZonedDateTime now = DateTimeConv.localToUTC(ZonedDateTime.now());
        //Determine output of log.
        if (attemptValue == 1) {
            log = "User '" + username + "' successful login at " + now + " from " + LOCATION + "\n";
        } else {
            log = "User '" + username + "' invalid login at " + now + " from " + LOCATION + "\n";
        }
        //Append to log.
        File file = new File(fileName);
        if (!file.exists()) {file.createNewFile();}
        FileWriter fileWriter = new FileWriter(fileName,true);
        fileWriter.write(log);
        fileWriter.close();
    }
}
