package View;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BaseWindow extends JFrame{
    protected JTextField usernameField;
    protected JPasswordField passwordField;
    protected JPanel fieldPanel;
    protected boolean isVisible;  //flag to track password visibility in the window

    public BaseWindow() {
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        placeComponents(new JPanel(new GridBagLayout()));

        //set icon for the window
        ImageIcon icon = new ImageIcon( "C:\\Users\\lenovo\\Documents\\th.jpg" );
        setIconImage(icon.getImage());
    }

    /**
     * Panel use GridBagLayout, and it contains: Labels for Username Field and Labels for Password Field
     * <br>passwordPanel use BorderLayout and GridBagLayout, and it contains:password field and toggle Button
     * passwordField and toggle Button use BorderLayout so toggle button is located to the right of passwordField
     * set fonts for the labels
     * set Dimension for fields
     */
    protected void placeComponents(JPanel panel){
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 10, 5, 10);  //set padding and spacing
        gbc.fill = GridBagConstraints.CENTER;

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        usernameField.setPreferredSize(new Dimension( 200, 30 ));  //set dimension for usernameField
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;  // Allow the field to expand
        gbc.fill = GridBagConstraints.CENTER;  // Make the field stretch horizontally
        panel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(passwordLabel, gbc);

        //create a new panel to contain passwordField and Show/Hide button
        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.setPreferredSize(new Dimension(200, 30));  // Set the fixed size for the passwordPanel
        passwordPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));  // Add some padding on the right

        passwordField = new JPasswordField( 20);
        passwordField.setPreferredSize(new Dimension( 200, 30 ));  //set dimension for passwordField


        passwordPanel.add(passwordField, BorderLayout.CENTER);

        JButton showHideButton = new JButton( "Show" );
        showHideButton.setPreferredSize(new Dimension(70, 30));  //set dimension for showHideButton
        showHideButton.setBorder(new EmptyBorder(0,0,0,0));
        showHideButton.setBackground(new Color(238, 238, 238));   //set background for showHideButton to the same color of the swing window
        passwordPanel.add(showHideButton, BorderLayout.EAST);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.CENTER;
        panel.add(passwordPanel, gbc);

        //add action listener to show/hide password
        showHideButton.addActionListener(e->{
            if (isVisible) {
                passwordField.setEchoChar('•');  // hide password
                showHideButton.setText("Show");
            } else {
                passwordField.setEchoChar((char) 0);  // show password
                showHideButton.setText("Hide");  //set button text Hide
            }
            isVisible = !isVisible;  // toggle flag

        });

        add(panel, BorderLayout.CENTER);  //add panel to the center of the frame
    }
    public static void main(String[] args){
        JFrame frame = new BaseWindow();
        frame.setVisible(true);
        System.out.println(frame.getBackground());
    }
}
