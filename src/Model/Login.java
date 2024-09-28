package Model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.*;

public class Login {
    public static final String filepath = "C:\\Users\\lenovo\\Desktop\\java\\AccountsManagementSystem\\Credentials.xml";
    public Properties pr = new Properties();

    public Login() {
        loadCredentials();
    }

    /**
     *  Create a new structured xml file if not exists of if it is empty
     *  If the xml file already exists, it will be loaded and the existing data will be preserved
     */
    private void createFileIfNotExists() {
        File file = new File(filepath);
        if (!file.exists()) {
            try (FileOutputStream fos = new FileOutputStream(filepath)) {
                pr.storeToXML(fos, "User credentials:");
            } catch (IOException e) {
                System.out.println("Error creating XML file: " + e.getMessage());
            }
        }
    }

    /**
     * load credentials from Credentials.xml using method loadFromXML from Properties class and FileInputStream class
     */
    public void loadCredentials(){
        try(FileInputStream fis = new FileInputStream(filepath)){
            pr.loadFromXML(fis);
        } catch (IOException e){
            System.out.println("Error reading credentials file: " + e.getMessage());
        }
    }

    /**
     * save credentials to Credentials.xml using method storeToXML from Properties class and FileOutputStream class
     * @throws IOException
     */
    public void saveCredentials() throws IOException {
        try(FileOutputStream fos = new FileOutputStream(filepath)){
            pr.storeToXML(fos, "User credentials:");
        } catch (IOException e){
            System.out.println("Error while saving to file: " +e.getMessage());
        }
    }
    private String hashPassword(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for(byte b : bytes)
                sb.append(String.format("%02x", b));  //convert string of bytes to hexadecimal string
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algorithm not supported: " +e.getMessage());
        }
    }

    /**
     * Register user with the specified username and password and saveCredentials to Credentials.xml.
     * Password is hashed before to be saved to Credentials.xml.
     * Save to Credentials.xml key username-hashed password using method setProperty from Properties class.
     * @param username of the new user
     * @param password hashed of the new user
     * @throws IOException
     */
    public void registerUser(String username, String password) throws IOException {
        if(pr.containsKey(username))
            System.out.println("Username already exists.");
        else {
            String encryptedPass = hashPassword(password);
            pr.setProperty(username + ".password", encryptedPass);
            saveCredentials( );
        }
    }

    /**
     * Login user with the specified username and password: First password is hashed and compared with the stored hashcode in Credentials.xml.
     * Use method containKey to search for the specific key(username)
     * and then get the value of key(encrypted password)using method getProperty.
     * @param username username to search for the specific key(username)
     * @param password  password to search for the specific key().
     * @return
     */
    public User login(String username, String password){
        String encryptedPass = hashPassword(password);
        if (pr.containsKey(username + ".password") && pr.getProperty(username + ".password").equals(encryptedPass)) {
            return new User(username, encryptedPass);
        }
        else
            System.out.println("Invalid credentials.");
        return null;
    }

    /**
     * Change password for the user with the specified username and old password:
     * use method setProperty to update the value of key(password).
     * Old password is first encrypted and then encryptedOldPassword is compared with password in Credentials.xml.
     * If Credentials.xml contains encryptedOldPassword then old password can be changed, else Invalid Old Credentials
     * @param username of the current user
     * @param oldPassword  to be compared with password in Credentials.xml
     * @throws IOException
     */
    public void changePassword(String username, String oldPassword) throws IOException {
        Scanner sc = new Scanner(System.in);
        String encryptedOldPassword = hashPassword(oldPassword);
        if(pr.containsKey(username) && pr.getProperty(username).equals(encryptedOldPassword)) {
            System.out.println("Enter new password: ");
            String newPassword = sc.nextLine();
            String newEncryptedPass = hashPassword(newPassword);
            pr.setProperty(username, newEncryptedPass);
            saveCredentials();
            System.out.println("Password changed successfully.");
        } else
            System.out.println("Invalid Old Credentials.");
    }
}
