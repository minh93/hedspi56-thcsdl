package DButitilies;

import Entities.ClassStu;
import Entities.Department;
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
        ConnectFactory cf = new ConnectFactory();
        Connection conn = cf.getConn();
        try {
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

    public static ArrayList<Student> getStudent(String studentID) {
        ArrayList<Student> list = new ArrayList<>();

        return list;
    }

    public static ArrayList<String> getAllClassName() {
        ArrayList<String> list = new ArrayList<>();

        return list;
    }

    public static ArrayList<String> getAllDeptName() {
        ArrayList<String> list = new ArrayList<>();

        return list;
    }

    public boolean insertStudent(Student s) {

        return true;
    }

    public boolean updateStudent(String studentID, String newTel, String newAddress, String newEmail) {

        return true;
    }

    public boolean deleteStudentByID(String id) {

        return true;
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
}
