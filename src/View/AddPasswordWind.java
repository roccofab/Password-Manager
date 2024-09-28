package View;

import Controller.AppController;
import Model.Domainsdata;


import javax.swing.*;
import java.awt.*;

/**
 * AddPasswordWind is a specialized window for adding a domain and its corresponding password.
 * It extends BaseWindow to inherit usernameField and passwordField components and adds a domainField,
 * as well as Add and Close buttons for interaction.
 *
 * The window interacts with the existing MainWindow instance to update the list of domains
 * and passwords for the current user. The AppController instance, responsible for application logic,
 * is passed from MainWindow and used to add the new domain and password to the user's data.
 */
public class AddPasswordWind extends BaseWindow{
    private JTextField domainField;
    private MainWindow mainWindow;  //reference to mainWindow to keep the current contents and state of the window(domain names and passwords) saved for the current user using method getAppController
    private AppController appController;
    /**
     * Constructor for AddPasswordWind that takes the current MainWindow as an argument.
     * The window uses the existing AppController instance from MainWindow to manage domain and password data.
     *
     * @param mainWindow the MainWindow instance that the user is currently interacting with
     */
    public AddPasswordWind(MainWindow mainWindow) {
        super();
        this.mainWindow = mainWindow;
        appController = mainWindow.getAppController();  //The AddPasswordWind window use the already existing AppController instance
        setTitle("Add Password");
        JPanel panel = new JPanel(new GridBagLayout());
        super.placeComponents(panel);
        add(panel);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);  //set padding and spacing

        JLabel domainLabel = new JLabel("Domain");
        domainLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(domainLabel, gbc);

        JTextField domainField = new JTextField();
        domainField.setPreferredSize(new Dimension( 200, 30 ));  //set dimension for domainField
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(domainField, gbc);

        //set icon for the window
        ImageIcon icon = new ImageIcon( "C:\\Users\\lenovo\\Documents\\th.jpg" );
        setIconImage(icon.getImage());

        JButton addButton = new JButton( "ADD" );
        addButton.setPreferredSize(new Dimension( 100, 30 ));  //set dimension for add button
        addButton.setFont(new Font("Verdana", Font.BOLD, 10));  //set font for add button
        addButton.setBackground(new Color(200, 20, 50));  //set background color for add button
        addButton.setForeground(Color.WHITE);  //set foreground color for add button
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(addButton, gbc);

        //add ActionListener to add button
        addButton.addActionListener(e->{
            String password = new String(passwordField.getPassword());
            String domain = domainField.getText();

            try{
                /*
                Add domain and password to user's data using  method addCurrentDomain from AppController.
                Method addCurrentDomain use method addUserDomain from the class DomainsData.
                Method addUserDomain use property to set key that is given by username+ "." +domain with the corresponding password in Domains.xml.
                if key(username+ "." +domain) and password are correctly saved: Password saved successfully, else Error saving password.
                Passwords in Domains.xml are not hashed.
                 */
                appController.addCurrentDomain(domain, password);
                JOptionPane.showMessageDialog(this, "Password saved successfully.");

                //call methods from the class MainWindow on the current instance of MainWindow
                mainWindow.refreshDomainList();  //update list of domains
                mainWindow.setVisible(true);
                setVisible(false);  //hide AddPasswordWind

            } catch (Exception ex){
                JOptionPane.showMessageDialog(this, "Error saving password.");
                ex.printStackTrace();
            }
        });

        JButton closeButton = new JButton( "CLOSE" );
        closeButton.setPreferredSize(new Dimension(100, 30));  //set dimension for close button
        closeButton.setFont(new Font("Verdana", Font.BOLD, 10));  //set font for close button
        closeButton.setBackground(new Color(200, 20, 50));  //set background color for close button
        closeButton.setForeground(Color.WHITE);  //set foreground color for close button
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(closeButton, gbc);  //add close button to panel
        add(panel, BorderLayout.CENTER);  //add panel to the window and locate it to the center

        //add ActionListener to close button
        closeButton.addActionListener(e->{
            mainWindow.setVisible(true);  //return to the user current MainWindow
            setVisible(false);  //close addPassword window
        });
    }
}
