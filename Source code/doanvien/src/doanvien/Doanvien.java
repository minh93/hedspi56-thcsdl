
package doanvien;

import DButitilies.ConnectFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Doanvien {
     public static void DocFile(){
        ConnectFactory cnn = new ConnectFactory();
        Properties pr = new Properties();       
        try {
            pr.load(new FileInputStream("src\\File\\Config.ini"));
        }catch (IOException e) {
            System.out.println("Không tìm thấy file Config ");
        }     
        cnn.Host = pr.getProperty("Localhost");
        cnn.DatabaseName = pr.getProperty("DatabaseName");
        cnn.NameLogin = pr.getProperty("LoginName");
        cnn.Pass=pr.getProperty("PassWord");       
    }  

}
