package File;

import Entities.Student;
import Utilities.Utility;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PhamDucMinh
 */
public class FileIO {

    public static boolean saveToFile(ArrayList<Student> list, String fileName) {
        try {
            CSVWriter csv = new CSVWriter(new FileWriter(fileName), ',');
            String[] title = "ID,F_Name,L_Name,Birth,Gender,Tel,Mail,Address,ClaID,Des".split(",");
            //System.out.println(title+"");
            csv.writeNext(title);
            for (Student s : list) {
                String entry = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                        s.getStudentID(), s.getF_Name(), s.getL_Name(),
                        Utility.timeToString(s.getBirth(), "yyyy-MM-dd"), s.getGender(), s.getYear(),
                        s.getTel(), s.getMail(), s.getAddress(), s.getClassID(), s.getDescription());

                csv.writeNext(entry.split(","));

            }
            csv.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean loadFromFile(ArrayList<Student> list, String fileName) {
        try {
            CSVReader csv = new CSVReader(new FileReader(fileName), ',');
            String[] nextLine;
            csv.readNext();
            while ((nextLine = csv.readNext()) != null) {
                Student s = new Student(nextLine[0], nextLine[1], nextLine[2], Utility.stringToDate(nextLine[3], "yyyy-MM-dd"),
                        Boolean.parseBoolean(nextLine[4]), Integer.parseInt(nextLine[5]),nextLine[6], nextLine[7], nextLine[8], nextLine[9], nextLine[10]);
                list.add(s);
            }
            csv.close();
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
