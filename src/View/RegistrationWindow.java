package View;

import Controller.AppController;
import Model.Login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class RegistrationWindow extends BaseWindow {
    private JPasswordField confirmPasswordField;
    private AppController appController;

    /**
     * Constructor for RegistrationWindow.
     * It takes an instance of AppController to handle the registration logic and window transitions using methods like setCurrentUser,
     * showMainWindow, showStartWindow.
     *
     * @param appController The AppController instance responsible for managing the application logic and state
     */
    public RegistrationWindow(AppController appController) {
        super();
        this.appController = appController;
        setTitle("Registration");
        JPanel panel = new JPanel(new GridBagLayout());
        super.placeComponents(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);  //set padding and spacing

        JLabel confirmPassLabel = new JLabel("Confirm Password:");
        confirmPassLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(confirmPassLabel, gbc);

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setPreferredSize(new Dimension(200, 30));  //set dimension for panel2
        panel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));  // Add some padding on the right

        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setPreferredSize(new Dimension(160, 30));  //set dimension for passwordField
        panel2.add(confirmPasswordField, BorderLayout.CENTER);

        // Show/Hide Password button
        JButton showHideButton = new JButton("Show");
        setupShowHideButton(showHideButton);
        panel2.add(showHideButton, BorderLayout.EAST);  //ShowHideButton is to the right of confirmPasswordField
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.CENTER;
        panel.add(panel2, gbc);   //add panel2 to panel

        JButton registerButton = new JButton("REGISTER");
        setupRegisterButton(registerButton);
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(registerButton, gbc);

        JButton closeButton = new JButton("CLOSE");
        setupCloseButton(closeButton);
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(closeButton, gbc);

        add(panel, BorderLayout.CENTER);
    }

    /**
     * Sets up the action listener for the Show/Hide button to toggle password visibility.
     */
    private void setupShowHideButton(JButton showHideButton) {
        showHideButton.setPreferredSize(new Dimension(70, 30));
        showHideButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        showHideButton.setBackground(new Color(238, 238, 238));  // set background for showHideButton to the same color of the swing window

        showHideButton.addActionListener(e -> {
            if (isVisible) {
                passwordField.setEchoChar('•');  // hide password
                showHideButton.setText("Show");
            } else {
                passwordField.setEchoChar((char) 0);  // show password
                showHideButton.setText("Hide");
            }
            isVisible = !isVisible;  // toggle flag
        });
    }

    /**
     * Sets up the action listener for the Register button.
     * Get username from usernameField and get password from passwordField and then use method registerUser from Login class.
     * Set the registered user for the current MainWindow using method setCurrentUser from appController class.
     * if the registration is successfully: Registration successful and open MainWindow using method showMainWindow from appController class,
     * else you get an  IOException.
     */
    private void setupRegisterButton(JButton registerButton) {
        registerButton.setPreferredSize(new Dimension(100, 30));
        registerButton.setFont(new Font("Verdana", Font.BOLD, 10));  //set font for registerButton
        registerButton.setBackground(new Color(200, 20, 50));  //set background color to red for registerButton
        registerButton.setForeground(Color.WHITE);  //set foreground color to white for registerButton

        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            // Check if the password from passwordField and confirmPasswordField match
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match.");
                return;
            }

            try {
                new Login().registerUser(username, password);  // Register the user and save credentials
                JOptionPane.showMessageDialog(this, "Registration successful.");
                appController.setCurrentUser(username);  // Set the current user
                dispose();  // Close the current window
                appController.showMainWindow();  // Show the main window for the current user
                setVisible(false);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Sets up the action listener for the Close button.
     * When close button is clicked: show startWindow using method showStartWindow from appController class and setVisible of the current window to false.
     */
    private void setupCloseButton(JButton closeButton) {
        closeButton.setPreferredSize(new Dimension(100, 30));  //set dimension for closeButton
        closeButton.setFont(new Font("Verdana", Font.BOLD, 10));  //set font for closeButton
        closeButton.setBackground(new Color(200, 20, 50));  //set background color to red for closeButton
        closeButton.setForeground(Color.WHITE);  //set foreground color for closeButton

        closeButton.addActionListener(e -> {
            appController.showStartWindow();  // Show the start window
            setVisible(false);  // Hide the registration window
        });
    }
}
