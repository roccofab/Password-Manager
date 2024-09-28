package Model;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Backup extends Login{
    private static final String backUpDir = "C:\\Users\\lenovo\\Desktop\\java\\AccountsManagementSystem\\backup";
    public Backup() throws IOException {
        // Call the parent's constructor to initialize properties and load credentials
        super();
        createBackupDir();
    }

    private void createBackupDir() throws IOException {
        // Check if backup directory exists, if not, create it
        File dir = new File(backUpDir);
        if(!dir.exists())
            dir.mkdir();
        else
            System.out.println("Backup directory already exists.");
    }
    public void createBackup() throws IOException {
        // create backup file: this method will be called at the end of the execution of the program
        backUpFile();
    }

    /**
     * Create backup filename in the format Credentials_Date_Time.xml
     * for example: Credentials_20240812_161500.xml-file created 12-08-2024 at 16:15:00
     * <br>Create a file in the backUp directory using an object from FileOutputStream class
     * <br>Write content of Properties to backupFile using method storeToXML from the class Properties
     * @throws IOException
     */
    private void backUpFile() throws IOException{
        String filename = "Credentials_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".xml";
        File backupFile = new File(backUpDir, filename);

        try (FileOutputStream fos = new FileOutputStream(backupFile)) {
            pr.storeToXML(fos, "User credentials (Backup)");
            System.out.println("Backup created: " + backupFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error creating backup file: " + e.getMessage());
        }
    }
}
