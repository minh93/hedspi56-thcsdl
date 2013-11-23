package Entities;

import java.util.Date;

/**
 *
 * @author Minh
 */
public class LogRecord {

    private String username;
    private Date time;
    private String acction;

    public LogRecord(String username, Date time, String acction) {
        this.username = username;
        this.time = time;
        this.acction = acction;
    }

    public LogRecord() {
    }

    public String getUsername() {
        return username;
    }

    public Date getTime() {
        return time;
    }

    public String getAcction() {
        return acction;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAcction(String acction) {
        this.acction = acction;
    }
}
