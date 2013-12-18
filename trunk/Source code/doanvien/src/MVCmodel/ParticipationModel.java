package MVCmodel;

import Entities.Participation;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Minh
 */
public class ParticipationModel extends AbstractTableModel {

    private ArrayList<Participation> list = new ArrayList<>();

    public ParticipationModel(ArrayList<Participation> list) {
        this.list = list;
    }

    public Participation getClassStu(int index) {
        return list.get(index);
    }

    public void remove(String stuID, String orgID) {
        for (Participation p : list) {
            if (p.getStuID().compareTo(stuID) == 0 && p.getOrgID().compareTo(orgID) == 0) {
                list.remove(p);
            }
        }
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Participation p = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getStuID();
            case 1:
                return p.getOrgID();
            case 2:
                return p.getRole();
            case 3:
                return Utilities.Utility.timeToString(p.getDateStart(), "dd-MM-yyyy");
            case 4:
                return p.getRecord();
            case 5:
                return p.getDes();
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
