package Model;
public class User {
    private String username;
    private String hashedPass;

    public User(String username, String hashedPass) {
        this.username = username;
        this.hashedPass = hashedPass;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPass() {
        return hashedPass;
    }
}
