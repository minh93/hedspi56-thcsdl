package Entities;

/**
 *
 * @author PhamDucMinh
 */
public class ClassStu {
    private String claID;
    private String claName;
    private int year;
    private String moniterID;
    private String deptID;

    public ClassStu(String claID, String claName, int year, String moniterID, String deptID) {
        this.claID = claID;
        this.claName = claName;
        this.year = year;
        this.moniterID = moniterID;
        this.deptID = deptID;
    }

    public String getClaID() {
        return claID;
    }

    public String getClaName() {
        return claName;
    }

    public int getYear() {
        return year;
    }

    public String getMoniterID() {
        return moniterID;
    }

    public String getDeptID() {
        return deptID;
    }

    public void setClaID(String claID) {
        this.claID = claID;
    }

    public void setClaName(String claName) {
        this.claName = claName;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMoniterID(String moniterID) {
        this.moniterID = moniterID;
    }

    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }
    
}
