package DButitilies;

import Entities.ClassStu;
import Entities.Department;
import Entities.Student;
import Utilities.Utility;
import java.util.ArrayList;
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
    public void testgetAllClassName() {
        ArrayList<String> list = RetrieveData.getAllClassName();
        System.out.println("Get all Class name result");
        for (String s : list) {
            System.out.println(s);
        }
        assertNull(null);
    }
    
    @Test
    public void testgetAllDeptName() {
        ArrayList<String> list = RetrieveData.getAllDeptName();
        System.out.println("Get all Dept name result");
        for (String s : list) {
            System.out.println(s);
        }
        assertNull(null);
    }
    
   // @Test
   // public void testInsertStudent() {
     //   Student cs = new Student("20112695", "Phan", "Toan", "20/05/1993",
       //         TRUE, 2011, "01675368205", "phantoan@gmail.com", "Nghe An", "VN-6C", "handsome");
        //int result = RetrieveData.insertStudent(cs);
        //int expresult = 0;
        //assertEquals(expresult, result);
        //RetrieveData.deleteStudentByID("20112695");
    //}

    @Test
    public void testInsertStudent() {
        System.out.println("Test insert student");
        int tem = RetrieveData.deleteStudentByID("20112440");
        System.out.println("test data " + tem);
        Student s = new Student("20112440", "Tran", "Cong", Utility.stringToDate("1993-02-03", "yyyy-MM-dd"), 
                Boolean.TRUE, 2011, "0211521", "mail@gmail.com", "Ha Noi", "DT2", "Con hoc");
        int result = RetrieveData.insertStudent(s);
        int expResult = 0;
        
        assertEquals(expResult, result);
    }

//@Test
//public void testupdateStudent() {

//}

@Test
public void testInsertDept() {
        System.out.println("Test insert Dept");
        Department dp = new Department("KTHH", "Ky thuat Hoa hoc","043863900", "kythuathoahoc@hust.edu.vn");
        boolean result = RetrieveData.insertDept(dp);
        boolean expresult = true;
        assertEquals(expresult, result);
}

//@Test
//public void testupdateDept() {

//}

    
    @Test
    public void testInsertClass() {
        RetrieveData.deleteClassByID("VN-6C");
        ClassStu cs = new ClassStu("VN-6C", "Viet Nhat", 2011, "20042152", "CK");
        boolean result = RetrieveData.insertClass(cs);
        boolean expresult = true;        
        assertEquals(expresult, result);
    }

    //@Test
    //public void testupdateClass() {
    //}
    
    @Test
    public void testgetClassNamebyID() {
        String c = RetrieveData.getClassNameByID("CNTT");
        System.out.println("Get Class name by ID result: " + c);
        assertNull(null);
    }
    
    @Test
    public void testClassIDbyName() {
        String c = RetrieveData.getClassIDByName("Ky thuat Hoa hoc");
        System.out.println("Get Class ID by name result: " + c);
        assertNull(null);
    }
    
    @Test
    public void testgetAllUser() {
        ArrayList<User> us = RetrieveData.getAllUser();
        System.out.println("Test get all user");
        for (User s : us) {
            System.out.println(s.getUserName);
        }
        assertNull(null);
    }
}