package MVCmodel;

import Entities.Department;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Minh
 */
public class DepartmentModel extends AbstractTableModel {

    private ArrayList<Department> list = new ArrayList<>();

    public DepartmentModel(ArrayList<Department> list) {
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
        Department cs = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cs.getID();
            case 1:
                return cs.getName();
            case 2:
                return cs.getTel();
            case 3:
                return cs.getMail();
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
                return "Dept Name";
            case 2:
                return "Contact";
            case 3:
                return "eMail";
            default:
                return null;
        }
    }
}
