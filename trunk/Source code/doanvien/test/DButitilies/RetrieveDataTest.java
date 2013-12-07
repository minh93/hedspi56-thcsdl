package DButitilies;

import Entities.ClassStu;
import Entities.Student;
import Utilities.Utility;
import java.util.ArrayList;
import java.util.Date;
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
        String pass = Utility.checksumGen("1234", "md5", true);
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

    @Test
    public void testgetAllStudent() {
        ArrayList<Student> list = RetrieveData.getAllStudent();
        System.out.println("Get all student resutl");
        for (Student s : list) {
            System.out.println(s.getF_Name() + s.getL_Name() + "");
        }
        assertNull(null);
    }

    @Test
    public void testInsertClass() {
        ClassStu cs = new ClassStu("VN-6C", "Viet Nhat", 2011, "20042152", "CK");
        boolean result = RetrieveData.insertClass(cs);
        boolean expresult = true;
        RetrieveData.deleteClassByID("VN-6C");
        assertEquals(expresult, result);
    }

    @Test
    public void testInsertStudent() {
        System.out.println("Test insert student");
        RetrieveData.deleteStudentByID("20112440");
        Student s = new Student("20112440", "Tran", "Cong", Utility.stringToDate("1993-02-03", "yyyy-MM-dd"), Boolean.TRUE, 2011, "0211521", "mail@gmail.com", "Ha Noi", "DT2", "Con hoc");
        int result = RetrieveData.insertStudent(s);
        int expResult = 1;
        RetrieveData.deleteStudentByID("20112440");
        assertEquals(expResult, result);
    }
}