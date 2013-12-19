package MVCmodel;

import Entities.Participation;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Minh
 */
public class ParticipationModel extends AbstractTableModel {

    private ArrayList<Participation> listOrgin = new ArrayList<>();
    private ArrayList<Participation> listToUse = new ArrayList<>();

    public ParticipationModel(ArrayList<Participation> list) {
        this.listOrgin = list;
        this.listToUse = (ArrayList<Participation>) list.clone();
    }

    public Participation getParticipation(int index) {
        return listToUse.get(index);
    }

    public void remove(String stuID, String orgID) {
        for (Participation p : listOrgin) {
            if (p.getStuID().compareTo(stuID) == 0 && p.getOrgID().compareTo(orgID) == 0) {
                listOrgin.remove(p);
            }
        }
        fireTableDataChanged();
    }

    public void filterByStatus(int status) {
        listToUse.clear();
        if (status == -1) {
            listToUse = (ArrayList<Participation>) listOrgin.clone();
        } else {
            for (Participation p : listOrgin) {
                if (p.getStatus() == status) {
                    listToUse.add(p);
                }
            }
        }
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return listToUse.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Participation p = listToUse.get(rowIndex);
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
                return p.getDes();
            case 5:
                return p.getRecord();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "StuID";
            case 1:
                return "Org ID";
            case 2:
                return "Role";
            case 3:
                return "Start";
            case 4:
                return "Description";
            case 5:
                return "Status";
            default:
                return null;
        }
    }
}
