package MVCmodel;

import Entities.Organization;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Minh
 */
public class OrganizationModel extends AbstractTableModel{
     private ArrayList<Organization> list = new ArrayList<>();

    public OrganizationModel(ArrayList<Organization> list) {
        this.list = list;
    }

    public Organization getOrganization(int index) {
        return this.list.get(index);
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
        Organization org = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return org.getOrgName();
            case 1:
                return org.getOrgManager();
            case 2:
                return org.getOrgTel();
            case 3:
                return org.getOrgEmail();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Name";
            case 1:
                return "Head Manager";
            case 2:
                return "Tel";
            case 3:
                return "Email";
            default:
                return null;
        }
    }
}
