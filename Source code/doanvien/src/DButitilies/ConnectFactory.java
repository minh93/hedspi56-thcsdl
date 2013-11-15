package DButitilies;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectFactory {

    private String connectionUri;

    public Connection ConnectionFactory(String address,
            String user,
            String password,
            String databaseName) {
        try {
            Class.forName("org.postgresql.Driver");

            connectionUri = "jdbc:postgresql://" + address + '/'+databaseName+','+ user + ',' + password;
            //System.out.println("Connect uri " + connectionUri);
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/qldv", "postgres", "1234");            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}