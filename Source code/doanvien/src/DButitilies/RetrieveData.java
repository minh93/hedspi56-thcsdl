package DButitilies;

import Entities.ClassStu;
import Entities.Department;
import Entities.Student;
import Entities.User;
import Utilities.Utility;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
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
        ArrayList<Student> list = new ArrayList<>();
        ConnectFactory cf = new ConnectFactory();
        Connection conn = cf.getConn();
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            conn.close();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<Student> getAllStudent() {
        ArrayList<Student> list = new ArrayList<>();
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement ps = conn.prepareCall("SELECT * FROM \"Student\" WHERE \"Status\" = 1 ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getBoolean(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
                list.add(s);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public static ArrayList<String> getAllClassName() {
        ArrayList<String> list = new ArrayList<>();
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement cs = conn.prepareCall("SELECT \"ClaName\" FROM \"Class\" ");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static ArrayList<String> getAllDeptName() {
        ArrayList<String> list = new ArrayList<>();
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement cs = conn.prepareCall("SELECT \"DeptName\" FROM \"Class\" ");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int insertStudent(Student s) {
        int result = -1;
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            CallableStatement cs = conn.prepareCall("{call insertStudent(?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, s.getStudentID());
            cs.setString(2, s.getF_Name());
            cs.setString(3, s.getL_Name());
            cs.setDate(4, (Date) s.getBirth());
            cs.setString(5, s.getTel());
            cs.setString(6, s.getMail());
            cs.setString(7, s.getAddress());
            cs.setString(8, s.getClassID());
            cs.setString(9, s.getDescription());
            ResultSet rs = cs.executeQuery();
            result = cs.executeUpdate();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
            return result;
        }

    }

    public boolean updateStudent(String studentID, String newTel, String newAddress, String newEmail) {

        return true;
    }

    public int deleteStudentByID(String id) {
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement cs = conn.prepareStatement("DELETE FROM \"Student\" WHERE \"StuID\" = ?");
            int result = cs.executeUpdate();

            return result;
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public boolean insertDept(Department d) {

        return true;
    }

    public boolean updateDept(String deptID, String newTel, String newEmail) {

        return true;
    }

    public boolean deleteDeptByID(String deptID) {

        return true;
    }

    public boolean insertClass(ClassStu c) {

        return true;
    }

    public boolean updateClass(String classID, String newTel, String newEmail, String newMoniterID) {

        return true;
    }

    public boolean deleteClassByID(String classID) {

        return true;
    }

    public static ArrayList<User> getAllUser() {
        ArrayList<User> list = new ArrayList<>();
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement ps = conn.prepareCall("SELECT * FROM \"Account\" ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                list.add(u);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public static int createUser(User u) {
        int result = -1;
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            CallableStatement cs = conn.prepareCall("{call insertUser(?,?,?,?)}");
            cs.setString(1, u.getUserName());
            cs.setString(2, u.getContact());
            cs.setString(3, Utility.checksumGen(u.getPassword(), "md5", true));
            cs.setInt(4, u.getRole());
            result = cs.executeUpdate();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
            return result;
        }
    }

    public static int deleteUser(String userName) {
        int result = -1;
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement ps = conn.prepareCall("DELETE FROM \"Account\" WHERE \"UserName\" = ?");
            ps.setString(1, userName);
            result = ps.executeUpdate();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
            return result;
        }
    }
}
