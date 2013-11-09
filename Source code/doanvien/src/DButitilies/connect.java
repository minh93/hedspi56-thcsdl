package DButitilies;

import java.sql.*;
import javax.swing.JOptionPane;

public class connect {
    public static Connection myCon=null;
    public static Statement stmt = null;
    public static ResultSet rs=null;
  
    public void ketnoi()
    {
         try{
              Class.forName("org.postgresql.Driver");
         }catch(ClassNotFoundException ex){
              JOptionPane.showMessageDialog(null,"Lỗi tên lớp hỗ trợ SQL");
         }
         
         try {
              myCon = DriverManager.getConnection("jdbc:postgresql://localhost:5432/qlkh2", "postgres","linh2396157");
              JOptionPane.showMessageDialog(null,"Kết nối thành công");
         } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null,"Không thể kết nối CSDL    ");
         }
    }
    
  
    public static void ThucThiSql(String sql)
    {
        try{
            stmt=myCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Lỗi Startment");
        }
        try {
            rs=stmt.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Lỗi câu lệnh truy vấn ");            
        }           
    }

    
    public static void CloseDatabase()
    {
        try {
            myCon.close();
        } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,"Lỗi CloseDatabase");
        }
    }
}

