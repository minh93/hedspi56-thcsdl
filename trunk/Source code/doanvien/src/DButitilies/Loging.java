package DButitilies;

import Entities.LogRecord;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Minh
 */
public class Loging {

    public static int login(String username) {
        ConnectFactory cf = new ConnectFactory();
        Connection conn = cf.getConn();
        Date now = new Date();
        now.getTime();
        int result = -1;
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO \"Log\" VALUES (?,?,?,?)");
            ps.setString(1, username);
            ps.setDate(2, (java.sql.Date) now);
            ps.setDate(3, null);
            ps.setString(4, "Login system");
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Loging.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int logout(String username) {
        Date now = new Date();
        now.getTime();
        int result = -1;
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO \"Log\" VALUES (?,?,?,?)");
            ps.setString(1, username);
            ps.setDate(2, null);
            ps.setDate(3, (java.sql.Date) now);
            ps.setString(4, "Login out");
            result = ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Loging.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static ArrayList<LogRecord> getAllLogRecord() {
        ArrayList<LogRecord> logrecord = new ArrayList<>();


        return logrecord;
    }

    public static ArrayList<LogRecord> getLogRecordByUserName() {
        ArrayList<LogRecord> logrecord = new ArrayList<>();


        return logrecord;
    }

    public static ArrayList<LogRecord> getLogRecordByDate(Date start, Date end) {
        ArrayList<LogRecord> logrecord = new ArrayList<>();


        return logrecord;
    }
}
