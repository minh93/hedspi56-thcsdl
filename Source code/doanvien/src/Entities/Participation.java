package Entities;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Minh
 */
public class Participation {

    private String orgID;
    private String stuID;
    private String role;
    private Date dateStart;
    private Date dateEnd;
    private String des;
    private int status;
    private List<String> list = Arrays.asList("Pending", "Active", "Not active");

    ;

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrgID() {
        return orgID;
    }

    public String getStuID() {
        return stuID;
    }

    public String getRole() {
        return role;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public String getDes() {
        return des;
    }

    public int getStatus() {
        return status;
    }

    public String getRecord() {
        return list.get(status);
    }

    public Participation(String stuID, String orgID, String role, Date dateStart, Date dateEnd, String des, int status) {
        this.orgID = orgID;
        this.stuID = stuID;
        this.role = role;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.des = des;
        this.status = status;
    }
}
