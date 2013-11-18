package MVCmodel;

import Entities.Student;
import Utilities.Utility;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Minh
 */
public class StudentModel extends AbstractTableModel {

    private ArrayList<Student> list;
    private int currentPage;
    private int numOfPage;

    public StudentModel(ArrayList<Student> list) {
        this.list = list;
        if (list.size() > 20) {
            numOfPage = (int) list.size() / 20 + 1;
        } else {
            numOfPage = 1;
        }
        currentPage = 1;
    }

    public int getNumOfPage() {
        return numOfPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public boolean setCurrentPage(int currentPage) {
        if (currentPage < numOfPage && currentPage > 1) {
            this.currentPage = currentPage;
            fireTableDataChanged();
            return true;
        } else {
            return false;
        }
    }

    public void addStudent(Student b) {
        this.list.add(b);
        fireTableDataChanged();
    }

    public int nextPage() {
        if (numOfPage > 1 && currentPage < numOfPage) {
            currentPage++;
            fireTableDataChanged();
            return currentPage;
        } else {
            return 1;
        }
    }

    public int previousPage() {
        if (numOfPage > 1 && currentPage > 1) {
            currentPage--;
            fireTableDataChanged();
            return currentPage;
        } else {
            return 1;
        }
    }

    public Student getStudent(int currentIndex, int currentPage) {
        int index = currentIndex + (currentPage - 1) * 20;
        if (index < list.size()) {
            return list.get(index);
        } else {
            return null;
        }
    }

    public Student removeStudent(int currentIndex, int currentPage) {
        int index = currentIndex + (currentPage - 1) * 20;
        if (index < list.size()) {
            Student b = list.get(index);
            list.remove(index);
            fireTableDataChanged();
            return b;
        } else {
            return null;
        }
    }

    @Override
    public int getRowCount() {
        if (list.size() > 20 && currentPage < numOfPage) {
            return 20;
        } else if (currentPage == numOfPage) {
            return list.size() - (currentPage - 1) * 20;
        } else {
            return list.size();
        }
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student b = list.get(rowIndex + (currentPage - 1) * 20);
        switch (columnIndex) {
            case 0:
                return b.getStudentID();
            case 1:
                return b.getF_Name()+' '+b.getL_Name();
            case 2:
                return b.getAddress();
            case 3:
                return b.getTel();
            case 4:
                return Utility.timeToString(b.getBirth(), "dd/mm/yyyy");
            case 5:
                return b.getMail();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Name";
            case 2:
                return "Address";
            case 3:
                return "Tel";
            case 4:
                return "Birth";
            case 5:
                return "eMail";
            default:
                return null;
        }
    }
}
