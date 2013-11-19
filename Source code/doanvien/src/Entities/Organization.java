package Entities;

/**
 *
 * @author PhamDucMinh
 */
public class Organization {
    private String orgID;
    private String orgName;
    private String parentID;
    private String orgManager;
    private String orgEmail;
    private String orgTel;

    public Organization(String orgID, String orgName, String parentID, String orgManager, String orgEmail, String orgTel) {
        this.orgID = orgID;
        this.orgName = orgName;
        this.parentID = parentID;
        this.orgManager = orgManager;
        this.orgEmail = orgEmail;
        this.orgTel = orgTel;
    }

    public String getOrgID() {
        return orgID;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getParentID() {
        return parentID;
    }

    public String getOrgManager() {
        return orgManager;
    }

    public String getOrgEmail() {
        return orgEmail;
    }

    public String getOrgTel() {
        return orgTel;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public void setOrgManager(String orgManager) {
        this.orgManager = orgManager;
    }

    public void setOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
    }

    public void setOrgTel(String orgTel) {
        this.orgTel = orgTel;
    }
    
}
