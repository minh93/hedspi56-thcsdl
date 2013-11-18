package Entities;

import java.util.Date;

/**
 *
 * @author Minh
 */
public class LogRecord {
    private String username;
    private Date logintime;
    private Date logouttime;
    private String acction;

    public LogRecord(String username, Date logintime, Date logouttime, String acction) {
        this.username = username;
        this.logintime = logintime;
        this.logouttime = logouttime;
        this.acction = acction;
    }

    public LogRecord() {
    }

    public String getUsername() {
        return username;
    }

    public Date getLogintime() {
        return logintime;
    }

    public Date getLogouttime() {
        return logouttime;
    }

    public String getAcction() {
        return acction;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public void setLogouttime(Date logouttime) {
        this.logouttime = logouttime;
    }

    public void setAcction(String acction) {
        this.acction = acction;
    }
    
}
