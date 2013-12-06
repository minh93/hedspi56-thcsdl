package Entities;

import java.util.Date;

/**
 *
 * @author Minh
 */
public class Student {

    private String studentID;
    private String F_Name;
    private String L_Name;
    private Date Birth;
    private Boolean Gender;
    private int Year;
    private String Tel;
    private String Mail;
    private String Address;
    private String ClassID;
    private String Description;

    public Student(String studentID, String F_Name, String L_Name, Date Birth,
            Boolean Gender, int Year, String Tel, String Mail, String Address, String ClassID, String Description) {
        this.studentID = studentID;
        this.F_Name = F_Name;
        this.L_Name = L_Name;
        this.Birth = Birth;
        this.Gender = Gender;
        this.Year = Year;
        this.Tel = Tel;
        this.Mail = Mail;
        this.Address = Address;
        this.ClassID = ClassID;
        this.Description = Description;
    }

    public Student() {
    }

    public String getStudentID() {
        return studentID;
    }

    public String getF_Name() {
        return F_Name;
    }

    public String getL_Name() {
        return L_Name;
    }

    public Date getBirth() {
        return Birth;
    }

    public void setYear(int Year) {
        this.Year = Year;
    }

    public Boolean getGender() {
        return Gender;
    }

    public String getTel() {
        return Tel;
    }

    public String getMail() {
        return Mail;
    }

    public String getAddress() {
        return Address;
    }

    public String getClassID() {
        return ClassID;
    }

    public String getDescription() {
        return Description;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setF_Name(String F_Name) {
        this.F_Name = F_Name;
    }

    public void setL_Name(String L_Name) {
        this.L_Name = L_Name;
    }

    public void setBirth(Date Birth) {
        this.Birth = Birth;
    }

    public void setGender(Boolean Gender) {
        this.Gender = Gender;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public int getYear() {
        return Year;
    }

    public void setMail(String Mail) {
        this.Mail = Mail;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setClassID(String ClassID) {
        this.ClassID = ClassID;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
        
}
