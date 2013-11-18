package Entities;

/**
 *
 * @author Minh
 */
public class User {

    private String userName;
    private String contact;
    private int role;

    public User(String userName, String contact, int role) {
        this.userName = userName;
        this.contact = contact;
        this.role = role;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public String getContact() {
        return contact;
    }

    public int getRole() {
        return role;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getTypeAccount() {
        switch (this.role) {
            case 1:
                return "Admin";
            case 2:
                return "User";
            case 3:
                return "Guest";
            default:
                return "Unknow";
        }
    }
}
