package MVCmodel;

import Entities.Event;
import Utilities.Utility;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Minh
 */
public class EventModel extends AbstractTableModel{
    private ArrayList<Event> list = new ArrayList<>();

    public EventModel(ArrayList<Event> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Event cs = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cs.getEvtName();
            case 1:
                return cs.getLocation();
            case 2:
                return Utility.timeToString(cs.getStart(), "yyyy-MM-dd");
            case 3:
                return cs.getNumOfPeople()+"";
            case 4:
                return cs.getRating()+"";
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Event Name";
            case 1:
                return "Location";
            case 2:
                return "Start Time";
            case 3:
                return "Number";
            case 4:
                return "Rating";
            default:
                return null;
        }
    }
}
