package DButitilies;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectFactory {

    private String dbAddress;
    private String dbName;    
    private String dbUsrName;
    private String dbUsrPass;
    private Connection conn;

    public ConnectFactory() {
        try {
            Properties p = new Properties();
            p.load(new FileInputStream("config.properties"));
            dbAddress = p.getProperty("ServerName");
            dbName = p.getProperty("DBName");
            dbUsrName = p.getProperty("User");
            dbUsrPass = p.getProperty("Password");
            
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://" + dbAddress + '/' + dbName, dbUsrName, dbUsrPass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectFactory.class.getName()).log(Level.SEVERE, null, ex);
            conn = null;
        } catch (IOException ex) {
            Logger.getLogger(ConnectFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConn() {
        return conn;
    }
    
}