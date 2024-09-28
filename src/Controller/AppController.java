package Controller;

import Model.Domainsdata;
import Model.Login;
import Model.User;
import View.LogWind;
import View.MainWindow;
import View.RegistrationWindow;
import View.StartWindow;

import javax.swing.*;
import java.util.Properties;

/**
 * appController is the most important component of the application, it acts as a controller for the application logic and UI components.
 * It manages interactions between components of the Model (Login, DomainsData, User) and the View components (LogWind, MainWindow, RegistrationWindow, StartWindow).
 * It manages the currently logged-in user, handles domain data, and controls window visibility transitions.
 */
public class AppController {
    private Domainsdata domainsData;  //reference to DomainsData class instance to manages domain-password data for the logged user
    private Login login;  //reference to Login class instance to handles login logic and registration logic
    private String user;  //variable to keep track of the username for the current user
    private JFrame startWindow;
    /**
     * Constructor for AppController initializes the application's core models (Login, DomainsData)
     * and stores a reference to the start window.
     *
     * @param startWindow The initial JFrame of the application
     */

    public AppController(JFrame startWindow) {
        this.startWindow = startWindow;
        domainsData = new Domainsdata();
        login = new Login();
    }
    /**
     * Sets the current user by storing their username.
     * This method is called after a successful login or registration.
     *
     * @param username The username of the current user
     */
    public void setCurrentUser(String username){
        this.user = username;
    }
    //get current user username
    public String getUser(){
        return user;
    }
    /**
     * Adds a new domain and password for the current user.
     * This method stores domain-password pairs associated with the logged-in user.
     *
     * @param domain   The domain name (www.facebook.com,...) to be saved
     * @param password The password associated with the domain
     */
    public void addCurrentDomain(String domain, String password){
        domainsData.addUserDomain(user, domain, password);
    }
    /**
     * Loads the saved domains and passwords for the current user into a DefaultListModel.
     * This method populates the list of saved domains in the MainWindow.
     *
     * @param listModel The DefaultListModel used to display the domain-password pairs in MainWindow's JList
     */
    public void loadUserDomain(DefaultListModel<String> listModel){
        Properties userDomains = domainsData.getUserDomain(user);
        listModel.clear();
        if (userDomains.isEmpty()) {
            System.out.println("No domains found for user: " + user);  // Debugging
        }
        // Populate the list model with domain-password pairs
        userDomains.forEach((domain,password)->{
            listModel.addElement(domain + ": " + password);
        });
    }
    /**
     * Attempts to log in a user using  username and password from input.
     * It checks the credentials and if valid, sets the current user and returns true.
     * Method use the method login from class Login to check for credentials validity{@see Login#login(String, String)}
     *
     * @param username The username entered by the user
     * @param password The password entered by the user
     * @return true if the login is successful, false otherwise
     */
    public boolean login(String username, String password){
        User user = login.login(username, password);
        if(user!=null) {
            setCurrentUser(user.getUsername());
            return true;
        }
        return false;
    }
    /**
     * Shows the RegistrationWindow and hides the StartWindow.
     * This method is called when the user chooses to register a new account.
     */
    public void showRegistrationWindow() {
        RegistrationWindow registrationWindow = new RegistrationWindow(this);  // pass appController to RegistrationWindow
        registrationWindow.setVisible(true);
        startWindow.setVisible(false);
    }
    /**
     * Shows the LogWind and hides the StartWindow.
     * This method is called when the user chooses to log in to an existing account.
     */
    public void showLoginWindow() {
        LogWind loginWindow = new LogWind(this);  // create new LogWind instance and pass appController as reference
        loginWindow.setVisible(true);    // show loginWindow
        startWindow.setVisible(false);  // hide StartWindow
    }
    /**
     * Shows the MainWindow (user dashboard) and hides the StartWindow.
     * It also loads the saved domain-password pairs for the current user into the MainWindow's JList.
     * This method is called in RegistrationWindow and LogWind
     */
    public void showMainWindow(){
        MainWindow mainWindow = new MainWindow(this);  // create new MainWindow instance and pass appController as reference
        mainWindow.refreshDomainList();  // load user domains in the JList of MainWindow
        mainWindow.setVisible(true);    // show MainWindow
        startWindow.setVisible(false);  // hide StartWindow
    }
    /**
     * Shows the StartWindow.
     * This method is called in LogWind, RegistrationWind and in the MainWindow if the user log out.
     */
    public void showStartWindow(){
        startWindow.setVisible(true);
    }
}
