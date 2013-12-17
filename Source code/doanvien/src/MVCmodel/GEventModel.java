package MVCmodel;

import Entities.GEvent;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class GEventModel extends AbstractTableModel {

    private ArrayList<GEvent> list;

    public GEventModel(ArrayList<GEvent> list) {
        this.list = list;
    }

    public void addGEvent(GEvent u) {
        this.list.add(u);
        fireTableDataChanged();
    }

    public GEvent getGEvent(int index) {
        return this.list.get(index);
    }


    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        GEvent u = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return u.GDate();
            case 1:
                return u.GSum();
            default:
                return null;
        }
    }
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "DATE";
            case 1:
                return "VALUE";          
            default:
                return null;
        }
    }
}
