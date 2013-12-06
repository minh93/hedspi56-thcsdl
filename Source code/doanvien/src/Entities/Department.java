package Entities;

/**
 *
 * @author PhamDucMinh
 */
public class Department {
    private String ID;
    private String name;
    private String tel;
    private String mail;

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public String getMail() {
        return mail;
    }

    public Department(String ID, String name, String tel, String mail) {
        this.ID = ID;
        this.name = name;
        this.tel = tel;
        this.mail = mail;
    }

}