package MVCmodel;

import Entities.ClassStu;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Minh
 */
public class ClassStudentModel extends AbstractTableModel {

    private ArrayList<ClassStu> list = new ArrayList<>();

    public ClassStudentModel(ArrayList<ClassStu> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ClassStu cs = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cs.getClaID();
            case 1:
                return cs.getClaName();
            case 2:
                return cs.getYear();
            case 3:
                return cs.getDeptID();
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
                return "Class Name";
            case 2:
                return "Year";
            case 3:
                return "Department";
            default:
                return null;
        }
    }
}
