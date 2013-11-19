package Entities;

/**
 *
 * @author PhamDucMinh
 */
public class Department {
    private String name;
    private String tel;
    private String mail;

    public Department(String name, String tel, String mail) {
        this.name = name;
        this.tel = tel;
        this.mail = mail;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
}
