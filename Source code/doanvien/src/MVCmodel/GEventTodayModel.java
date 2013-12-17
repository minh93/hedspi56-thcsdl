package MVCmodel;

import Entities.Event;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class GEventTodayModel extends AbstractTableModel {

    private ArrayList<Event> list;

    public GEventTodayModel(ArrayList<Event> list) {
        this.list = list;
    }

    public void addGEventToday(Event u) {
        this.list.add(u);
        fireTableDataChanged();
    }

    public Event getGEvent(int index) {
        return this.list.get(index);
    }


    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Event u = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return u.getEvtID();
            case 1:
                return u.getEvtName();
            case 2:
                return u.getLocation();
            case 3:
                return u.getStart();
            case 4:
                return u.getEnd();
            case 5:
                return u.getNumOfPeople();
            case 6:
                return u.getRating();    
            default:
                return null;
        }
    }
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "EVENT ID";
            case 1:
                return "EVENT NAME";   
            case 2:
                return "LOCATION";   
            case 3:
                return "START";   
            case 4:
                return "END";   
            case 5:
                return "NUMBER OF PEOPLE ";   
            case 6:
                return "RATING";       
            default:
                return null;
        }
    }
}
