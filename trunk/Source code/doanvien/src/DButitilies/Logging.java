package DButitilies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Minh
 */
public class Logging {

    public static int login(String username) {
        ConnectFactory cf = new ConnectFactory();
        Connection conn = cf.getConn();
        Date now = new Date();
        now.getTime();
        int result = -1;
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO \"Log\" VALUES (?,?,?)");
            ps.setString(1, username);
            ps.setDate(2, new java.sql.Date(now.getTime()));
            ps.setString(3, "Login system");
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Logging.class.getName()).log(Level.SEVERE, null, ex);
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
            PreparedStatement ps = conn.prepareStatement("INSERT INTO \"Log\" VALUES (?,?,?)");
            ps.setString(1, username);            
            ps.setDate(2,  new java.sql.Date(now.getTime()));
            ps.setString(3, "Login out");
            result = ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Logging.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static void createLog(String userName,String action) {
        ConnectFactory cf = new ConnectFactory();
        Connection conn = cf.getConn();
        Date now = new Date();
        now.getTime();
        int result = -1;
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO \"Log\" VALUES (?,?,?)");
            ps.setString(1, userName);
            ps.setDate(2,  new java.sql.Date(now.getTime()));           
            ps.setString(3, action);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Logging.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
