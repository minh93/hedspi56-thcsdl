package DButitilies;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Minh
 */
public class RetrieveData {

    public static int getRole() {
        ConnectFactory conn = new ConnectFactory();
        CallableStatement cs = 
        return 0;
    }

    public static void ThucThiSql(String sql) throws SQLException {
        ConnectFactory cf = new ConnectFactory();
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("config.properties"));
            Connection conn = cf.ConnectionFactory(prop.getProperty("ServerName"),
                    prop.getProperty("User"),
                    prop.getProperty("Password"),
                    prop.getProperty("DBName"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
