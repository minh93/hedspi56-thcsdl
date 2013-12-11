package MVCmodel;

import Entities.LogRecord;
import Utilities.Utility;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Minh
 */
public class LogModel extends AbstractTableModel {

    private ArrayList<LogRecord> list = new ArrayList<>();

    public LogModel(ArrayList<LogRecord> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LogRecord lr = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return lr.getUsername();
            case 1:
                return lr.getAcction();
            case 2:
                return Utility.timeToString(lr.getTime(), "mm-hh-dd-MM-yyyy");
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "User";
            case 1:
                return "Action";
            case 2:
                return "Time";
            default:
                return null;
        }
    }
}
