package View;

import Controller.AppController;

import javax.swing.*;
import java.awt.*;

public class StartWindow extends JFrame {
    JButton registrationButton;
    JButton loginButton;
    JButton exitButton;
    private AppController appController;

    public StartWindow(AppController appController) {
        setTitle("Password Manager");
        this.appController =  new AppController(this);
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon( "C:\\Users\\lenovo\\Documents\\th.jpg" );
        setIconImage(icon.getImage());

        initComponents();
        layoutComponents();
        setVisible(true);
    }

    // Method to initialize the components
    private void initComponents() {
        registrationButton = createButton("REGISTER", e -> openRegistrationWindow());
        loginButton = createButton("LOGIN", e -> openLoginWindow());
        exitButton = createButton("EXIT", e -> System.exit(0));
    }

    // Method to create buttons
    private JButton createButton(String text, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("MONOSPACED", Font.BOLD, 20));
        button.setBackground(new Color(200, 20, 50));
        button.setForeground(Color.WHITE);
        button.addActionListener(action);
        return button;
    }

    // Method to layout the components
    private void layoutComponents() {
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.add(registrationButton);
        buttonPanel.add(loginButton);
        buttonPanel.add(exitButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(buttonPanel, BorderLayout.CENTER);
        add(panel);
    }


    private void openRegistrationWindow() {
        RegistrationWindow rw = new RegistrationWindow(appController);  // Pass appController instance
        rw.setVisible(true);
        setVisible(false);
    }

    private void openLoginWindow() {
        appController.showLoginWindow();
    }
}
