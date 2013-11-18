package MVCmodel;

import Entities.User;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Minh
 */
public class UserModel extends AbstractTableModel {

    private ArrayList<User> list;

    public UserModel(ArrayList<User> list) {
        this.list = list;
    }

    public void addUser(User u) {
        this.list.add(u);
        fireTableDataChanged();
    }

    public User getUser(int index) {
        return this.list.get(index);
    }

    public void removeStudent(int index) {
        list.remove(index);
        fireTableDataChanged();
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
        User u = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return u.getUserName();
            case 1:
                return u.getContact();
            case 2:
                return u.getTypeAccount();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "User ";
            case 1:
                return "Contact";
            case 2:
                return "Role";           
            default:
                return null;
        }
    }
}
