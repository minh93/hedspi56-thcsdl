package DButitilies;

import Entities.ClassStu;
import Entities.Department;
import Entities.Event;
import Entities.LogRecord;
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
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11));
                list.add(s);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static ArrayList<ClassStu> getAllClass() {
        ArrayList<ClassStu> list = new ArrayList<>();
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement ps = conn.prepareCall("SELECT * FROM \"Class\" ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ClassStu s = new ClassStu(rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5));
                list.add(s);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static ArrayList<Event> getAllEvent() {
        ArrayList<Event> list = new ArrayList<>();
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement ps = conn.prepareCall("SELECT * FROM \"Event\" ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Event evt = new Event(rs.getString(1), rs.getString(2), rs.getString(3),
                        new java.util.Date(rs.getDate(4).getTime()), new java.util.Date(rs.getDate(5).getTime()), rs.getInt(6), rs.getInt(7));
                list.add(evt);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static ArrayList<Department> getAllDepartMent() {
        ArrayList<Department> list = new ArrayList<>();
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement ps = conn.prepareCall("SELECT * FROM \"Department\" ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Department dp = new Department(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(dp);
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
            PreparedStatement cs = conn.prepareCall("SELECT \"DeptName\" FROM \"Department\" ");
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

    public static int insertStudent(Student s) {
        int result = -1;
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            CallableStatement cs = conn.prepareCall("{call insertStudent(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, s.getStudentID());
            cs.setString(2, s.getF_Name());
            cs.setString(3, s.getL_Name());
            cs.setDate(4, new java.sql.Date(s.getBirth().getTime()));
            cs.setBoolean(5, s.getGender());
            cs.setInt(6, s.getYear());
            cs.setString(7, s.getTel());
            cs.setString(8, s.getMail());
            cs.setString(9, s.getAddress());
            cs.setString(10, s.getClassID());
            cs.setString(11, s.getDescription());
            result = cs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static boolean updateStudent(String studentID, String newTel, String newAddress, String newEmail, String newClaID, String newDes) {
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement cs = conn.prepareStatement("UPDATE \"Student\" SET \"Tel\"=?,\"Address\"=?,\"Mail\"=?,\"ClaID\" = ?,\"Des\" = ? WHERE \"StuID\"=?");
            cs.setString(6, studentID);
            cs.setString(1, newTel);
            cs.setString(2, newAddress);
            cs.setString(3, newEmail);
            cs.setString(4, newClaID);
            cs.setString(5, newDes);
            cs.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static int deleteStudentByID(String id, boolean mode) {

        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            CallableStatement cs;
            if (mode) {
                cs = conn.prepareCall("{call permanentlyDeleteStudent(?)}");
            } else {
                cs = conn.prepareCall("{call deleteStudent(?)}");
            }
            cs.setString(1, id);
            int result = cs.executeUpdate();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public static boolean insertDept(Department d) {
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement cs = conn.prepareStatement("INSERT INTO \"Departmant\" VALUES (?,?,?,?)");
            cs.setString(1, d.getID());
            cs.setString(2, d.getName());
            cs.setString(3, d.getTel());
            cs.setString(4, d.getMail());

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public static boolean updateDept(String deptID, String newTel, String newEmail) {
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement cs = conn.prepareStatement("UPDATE \"Department\" SET \"Tel\"=?, \"Mail\"=? WHERE \"DeptID\"=?");
            cs.setString(1, newTel);
            cs.setString(2, newEmail);
            cs.setString(3, deptID);


            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean deleteDeptByID(String deptID) {
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement cs = conn.prepareStatement("DELETE FROM \"Department\" WHERE \"DeptID\"=?");
            cs.setString(1, deptID);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public static boolean insertClass(ClassStu c) {
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement cs = conn.prepareStatement("INSERT INTO \"Class\" VALUES (?,?,?,?,?)");
            cs.setString(1, c.getClaID());
            cs.setString(2, c.getClaName());
            cs.setInt(3, c.getYear());
            cs.setString(4, c.getMoniterID());
            cs.setString(5, c.getDeptID());
            int result = cs.executeUpdate();
            if (result == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public static boolean updateClass(String classID, String newYear, String newMoniterID, String NewDeptID) {
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement cs = conn.prepareStatement("UPDATE \"Class\" SET \"ClaName\"=?, \"Year\"=?,\"MoniterID\"=?,\"DeptID\"=? WHERE \"ClaID\"=?");
            cs.setString(1, newYear);
            cs.setString(2, newMoniterID);
            cs.setString(3, NewDeptID);
            cs.setString(4, classID);


            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean deleteClassByID(String classID) {
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement cs = conn.prepareStatement("DELETE FROM \"Class\" WHERE \"ClaID\"=?");
            cs.setString(1, classID);
            cs.executeUpdate();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public static String getClassNameByID(String claID) {
        String claName = null;
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            CallableStatement cs = conn.prepareCall("SELECT \"ClaName\" FROM \"Class\" WHERE \"ClaID\" = ?");
            cs.setString(1, claID);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                claName = rs.getString(1);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return claName;
    }

    public static String getClassIDByName(String claName) {
        String claID = null;
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            CallableStatement cs = conn.prepareCall("SELECT \"ClaID\" FROM \"Class\" WHERE \"ClaName\" = ?");
            cs.setString(1, claName);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                claID = rs.getString(1);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return claID;
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
            cs.setString(3, u.getPassword());
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

    public static boolean insertEvent(Event c) {
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement cs = conn.prepareStatement("INSERT INTO \"Event\" VALUES (?,?,?,?,?,?,?)");
            cs.setString(1, c.getEvtID());
            cs.setString(2, c.getEvtName());
            cs.setString(3, c.getLocation());
            cs.setDate(4, new java.sql.Date(c.getStart().getTime()));
            cs.setDate(5, new java.sql.Date(c.getEnd().getTime()));
            cs.setInt(6, c.getNumOfPeople());
            cs.setInt(7, c.getRating());
            cs.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public static boolean deleteEvent(String EventID, String EventName) {
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement cs = conn.prepareStatement("DELETE FROM \"Event\" WHERE \"EventID\"=? OR \"EventName\"=?");
            cs.setString(1, EventID);
            cs.setString(2, EventName);
            cs.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean insertOrg(String OrgID, String OrgName, String Par, String Manager, String Mail, String Tel) {
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement cs = conn.prepareStatement("INSERT INTO \"Organization\" VALUES (?,?,?,?,?,?)");
            cs.setString(1, OrgID);
            cs.setString(2, OrgName);
            cs.setString(3, Par);
            cs.setString(4, Manager);
            cs.setString(5, Mail);
            cs.setString(6, Tel);
            cs.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public static boolean deleteOrg(String OrgID, String OrgName) {
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement cs = conn.prepareStatement("DELETE FROM \"Organization\" WHERE \"OrgtID\"=? OR \"OrgName\"=?");
            cs.setString(1, OrgID);
            cs.setString(2, OrgName);
            cs.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean insertPar(String StuID, String OrgID, String Role, Date Start, Date End, String Description) {
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement cs = conn.prepareStatement("INSERT INTO \"Participation\" VALUES (?,?,?,?,?,?)");
            cs.setString(1, StuID);
            cs.setString(2, OrgID);
            cs.setString(3, Role);
            cs.setDate(4, Start);
            cs.setDate(5, End);
            cs.setString(6, Description);
            cs.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public static boolean deleteOrg(String StuID) {
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement cs = conn.prepareStatement("DELETE FROM \"Participation\" WHERE \"StuID\"=?");
            cs.setString(1, StuID);
            cs.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static ArrayList<LogRecord> getLogRecords(java.util.Date start, java.util.Date end) {
        ArrayList<LogRecord> list = new ArrayList<>();

        return list;
    }

    public static ArrayList<LogRecord> getLogRecords(String userName) {
        ArrayList<LogRecord> list = new ArrayList<>();
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement ps = conn.prepareCall("SELECT * FROM \"Log\" WHERE \"UserName\" = ?");
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LogRecord lr = new LogRecord(rs.getString(1),
                        Utility.stringToDate(rs.getString(2), "hh-mm-ss"), rs.getString(3));
                list.add(lr);
            }
            conn.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
            return list;
        }
    }

    public static ArrayList<LogRecord> getLogRecords() {
        ArrayList<LogRecord> list = new ArrayList<>();
        try {
            ConnectFactory cf = new ConnectFactory();
            Connection conn = cf.getConn();
            PreparedStatement ps = conn.prepareCall("SELECT * FROM \"Log\"");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LogRecord lr = new LogRecord(rs.getString(1),
                        Utility.stringToDate(rs.getString(2), "hh-mm-ss"), rs.getString(3));
                list.add(lr);
            }
            conn.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
            return list;
        }
    }
}
