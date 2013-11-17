package DButitilies;

import java.sql.Connection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Minh
 */
public class ConnectFactoryTest {
    
    public ConnectFactoryTest() {
    }
    
   
    @Test
    public void testConnectionFactory() {
        System.out.println("ConnectionFactory");
        //Các file config duoc tao san
        ConnectFactory instance = new ConnectFactory();
        Connection insConnection = instance.getConn();
        assertNotNull(insConnection);
    }
}