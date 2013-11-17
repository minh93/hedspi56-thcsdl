/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DButitilies;

import Utilities.Utility;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Minh
 */
public class RetrieveDataTest {
    
    public RetrieveDataTest() {
    }

    /**
     * Test of getRole method, of class RetrieveData.
     */
    @Test
    public void testGetRole() {
        //csdl da co san data
        String pass = Utility.checksumGen("1234", "md5",true);
        int role = RetrieveData.getRole("admin", pass);
        int expectResult = 1;
        assertEquals(expectResult, role);
    }

    /**
     * Test of runSqlStatement method, of class RetrieveData.
     */
    @Test
    public void testRunSqlStatement() {
    }
}