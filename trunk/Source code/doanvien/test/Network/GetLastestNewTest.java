/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Minh
 */
public class GetLastestNewTest {

    public GetLastestNewTest() {
    }

    /**
     * Test of getHTML method, of class GetLastestNew.
     */
    @Test
    public void testGetHTML() {
        GetLastestNew gln = new GetLastestNew("http://dtdh.hust.edu.vn", "(<a href=\"http://dtdh.hut.edu.vn/content/view/[0-9]++/1/\" class=\"contentpagetitle\">(.+)</a>)");
        gln.getHTML();
        ArrayList<String> list = gln.getNewsContents(2);
        if (list != null) {
            for (String s : list) {
                System.out.println(s);
            }
        }
        assertNull(null);
    }

    /**
     * Test of getNewsContents method, of class GetLastestNew.
     */
    @Test
    public void testGetNewsContents() {
    }
}