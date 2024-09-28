package View;

import Controller.AppController;
import javax.swing.*;
import java.awt.*;

/*
LogWind implements usernameField and passwordField from BaseWindow and specialize BaseWindow with login button and close button
 */
public class LogWind extends BaseWindow {
    private AppController appController;

    public LogWind(AppController appController) {  // pass reference to AppController in the constructor
        super();
        this.appController = appController;
        setTitle("Login");

        JPanel panel = new JPanel(new GridBagLayout());
        super.placeComponents(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);


        //LOG IN button style and layout
        JButton logButton = new JButton("LOG IN");
        logButton.setPreferredSize(new Dimension(100, 30));  //set dimension for login button
        logButton.setFont(new Font("Verdana", Font.BOLD, 10));  //set font for login button
        logButton.setBackground(new Color(200, 20, 50));  //set background for login button
        logButton.setForeground(Color.WHITE);  //set foreground color for login button
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(logButton, gbc);

        //close button style and layout
        JButton closeButton = new JButton( "CLOSE" );
        closeButton.setPreferredSize(new Dimension( 100, 30 ));  //set dimension for close button
        closeButton.setFont(new Font("Verdana", Font.BOLD, 10));  //set font for close button
        closeButton.setBackground(new Color(200, 20, 50));  //set background color for close button
        closeButton.setForeground(Color.WHITE);  //set foreground color for close button
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(closeButton, gbc);

       /*
       Add ActionListener to login button
       Call method login from appController: method login from appController use method login from Login class.
       Method login in the login class first input password from passwordField and then compare it with hashed password stored in Credentials.xml file for the specific username,
       if credentials are correct then show MainWindow, else show message "Login Failed".
        */
        logButton.addActionListener(e -> {
            String username = usernameField.getText();  //get username from usernameField field that is defined in BaseWindow
            String password = new String(passwordField.getPassword());  //get password from passwordField field that is defined in BaseWindow

            if(appController.login(username, password)) {
                appController.showMainWindow();   //open MainWindow if credentials are correct else show message "Login Failed"
                setVisible(false);   //hide Login Window
            } else {
                JOptionPane.showMessageDialog(null, "Login failed!");
            }
        });

        //add ActionListener to closeButton
        closeButton.addActionListener(e -> {
            StartWindow sw = new StartWindow(appController);
            sw.setVisible(true);
            setVisible(false);
        });
    }
}
