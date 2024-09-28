package Model;

import java.io.*;
import java.util.Properties;

public class Domainsdata {
    private Properties pr;
    private String filepath = "C:\\Users\\lenovo\\Desktop\\java\\AccountsManagementSystem\\Domains.xml";

    /**
     * Initialize a new Property list.
     * Check if the file Domains.xml exists or if it is empty else create a new structured Domains.xml file.
     * <br>load data from Domains.xml
     */
    public Domainsdata() {
        pr = new Properties();
        createFileIfNotExists();
        loadDomains();
    }
    /*Create a new structured xml file if not exists of if it is empty
     * If the xml file already exists, it will be loaded and the existing data will be preserved
     */
    private void createFileIfNotExists() {
        File file = new File(filepath);
        if (!file.exists() || file.length() == 0) {
            try (FileOutputStream fos = new FileOutputStream(filepath)) {
                // Create an empty xml file
                pr.storeToXML(fos, "Domain-Password");
            } catch (IOException e) {
                System.out.println("Creation File Error: " + e.getMessage());
            }
        }
    }
    private void loadDomains() {
        //Load the domain-password data from the xml file using FileInputStream class
        try(FileInputStream fis = new FileInputStream(filepath)){
            pr.loadFromXML(fis);
            System.out.println("Loaded domains successfully.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void saveDomains(){
        //save Domain-Password to Domains.xml using FileOutputStream class
        try(FileOutputStream fos = new FileOutputStream(filepath)){
            pr.storeToXML(fos, "Domain-Password");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Add a new domain-password in the file Domains.xml for the logged-in user using method setProperty from Properties class.
     * The key associated with the password is given by username.Domain because the correct identification of the username in method getUserDomain.
     * @param username
     * @param domain
     * @param password
     */
    public void addUserDomain(String username,String domain,String password){
        String key = username + "." + domain;
        pr.setProperty(key, password);
        System.out.println("Saving domain for user: " + key + " -> " + password); // Debug
        saveDomains();
    }

    /**
     * Get domain and password for a specific username.
     * Properties list userDomain store the domain and password that are retrieved from the instance of Properties list(pr) that store data to Domains.xml.
     * <br>Method iterates over all key-value pairs that are stored in Domains.xml.
     * <br>Keys are converted into strings and if the string contain "username.domain" then the domain and password are extracted and saved to Properties list userDomain.
     * @param username
     * @return userDomain
     */
    public Properties getUserDomain(String username){
        Properties userDomain = new Properties();
        pr.forEach((key,value) -> {
            String keyStr = (String) key;
            System.out.println("Checking key: " + keyStr); // Debug
            if(((String) key).startsWith(username+ ".")){
                String domain = keyStr.substring(username.length() + 1);
                userDomain.setProperty(domain, (String) value);
            }
        });
        return userDomain;
    }
}
