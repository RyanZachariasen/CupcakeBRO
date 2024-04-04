package app.entities;

public class User {
    private int userID;
    private String email;
    private String password;
    private String role;
    private int wallet;

    public User(int userID, String email, String password, String role,  int wallet) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.role = role;
        this.wallet = wallet;
    }

    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userID +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", wallet=" + wallet +
                '}';
    }
}
