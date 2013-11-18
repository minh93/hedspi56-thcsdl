package DButitilies;

import Entities.Student;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Minh
 */
public class RetrieveData {

    public static int getRole(String userName, String password) {
        int role = -1;
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            CallableStatement cs = conn.prepareCall("{call getRole(?,?)}");
            cs.setString(1, userName);
            cs.setString(2, password);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                role = rs.getInt(1);
            }
            return role;
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public static ResultSet runSqlStatement(String sql) {
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement ps = conn.prepareCall(sql);
            return ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static ArrayList<Student> getAllStudent(){
        ArrayList<Student> list = new ArrayList<>();
        
        return list;
    }
}
