package View;
import Controller.AppController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {
    private JList<String> passList;
    private DefaultListModel<String> listModel;
    private JButton addButton;
    private JButton logButton;
    private String username;
    private String password;
    private AppController appController;

    public MainWindow(AppController ac){
        super("Password Manager");
        this.appController = ac;
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon( "C:\\Users\\lenovo\\Documents\\th.jpg" );
        setIconImage(icon.getImage());

        initializeComponents();
        layoutComponents();
        setButtonActions();
        setVisible(true);
    }

    /**
     * Method to initialize graphical contents of the window:
     * DefaultListModel can make you add-remove-modify strings from the graphical list(JList).
     * JList allows you to display a list of strings in the window.
     * addButton-open AddPasswordWind
     *logButton-open StartWindow
     */
    private void initializeComponents(){
        listModel = new DefaultListModel<>();
        passList = new JList<>(listModel);
        addButton = new JButton( "Add" );
        addButton.setBackground(Color.WHITE);
        logButton = new JButton( "Log Out" );
        logButton.setBackground(Color.WHITE);
    }

    /**
     * Method to layout Components of the Window:
     * JScrollPanel to the center
     * Buttons to south
     */
    private void layoutComponents(){
        JPanel panel = new JPanel( new BorderLayout());
        panel.add( new JScrollPane(passList), BorderLayout.CENTER );
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(logButton);
        panel.add( buttonPanel, BorderLayout.SOUTH );
        setContentPane(panel);
    }

    /**
     * Method to add ActionListener to buttons:
     * addButton open a new AddPasswordWind and close the current window
     * logButton open a new StartWind and close the current window
     */
    private void setButtonActions(){
        addButton.addActionListener(e-> openAddPasswordWind());
        logButton.addActionListener(e->logOut());
    }
    private void openAddPasswordWind(){
        AddPasswordWind adw = new AddPasswordWind(this);  //pass the current instance of MainWindow into the  constructor of AddPasswordWind
        adw.setVisible(true);
        setVisible(false);
    }
    private void logOut(){
        StartWindow sw = new StartWindow(appController);
        sw.setVisible(true);
        setVisible(false);
    }
    public void refreshDomainList() {
        listModel.clear();
        appController.loadUserDomain(listModel);
    }
    public AppController getAppController() {
        return appController;
    }
}
