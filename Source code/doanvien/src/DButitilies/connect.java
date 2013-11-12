package DButitilies;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
public class connect {
    public static Connection myCon=null;
    public static Statement stmt = null;
    public static ResultSet rs=null;
    public static String Host;
    public static String DatabaseName;
    public static String NameLogin;
    public static String Pass;
    public void ketnoi()
    {
         String LopHoTro="org.postgresql.Driver";
         try{
              Class.forName(LopHoTro);
         }catch(ClassNotFoundException ex){
              JOptionPane.showMessageDialog(null,"Lỗi tên lớp hỗ trợ SQL");
         }
         try {
             
              myCon = DriverManager.getConnection("jdbc:postgresql://localhost:5432/qldv", "postgres","linh2396157");
              System.out.println("Kết nối thành công");
         } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null,"Không thể kết nối CSDL");
         }
    }

    public static void ThucThiSql(String sql) throws SQLException
    {
        Statement statement = null;
        statement = myCon.createStatement();
        try {
            rs = statement.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Lỗi câu lệnh truy vấn ");            
        }           
    }
    
    //Hàm đóng kết nối
    
    public static void CloseDatabase()
    {
        try {
            myCon.close();
        } catch (SQLException ex) {
            Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}