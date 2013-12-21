package UI;

import DButitilies.RetrieveData;
import Entities.Participation;
import Entities.Student;
import MVCmodel.ParticipationModel;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class RequestForm extends javax.swing.JDialog {

    private ArrayList<Participation> list;
    private ParticipationModel pm;

    public RequestForm(java.awt.Frame parent, boolean modal, ArrayList<Participation> list) {
        super(parent, modal);
        initComponents();
        this.list = list;
        this.setLocationRelativeTo(null);
        this.setTitle("Org Request");

        loadData();
    }

    private void loadData() {
        pm = new ParticipationModel(list);
        pm.filterByStatus(0);
        tblParticipation.setModel(pm);
        initTable();

    }

    private void initTable() {
        tblParticipation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    runOption();
                }
            }
        });
    }

    private void runOption() {
        int index = tblParticipation.getSelectedRow();
        Participation p = pm.getParticipation(index);
        MainForm mf = (MainForm) this.getParent();
        ArrayList<Student> list = mf.getListStudent();
        for (Student s : list) {
            if (s.getStudentID().compareTo(p.getStuID()) == 0) {
                txtStuName.setText(s.getF_Name() + " " + s.getL_Name());
            }
        }
        txtStuID.setText(p.getStuID());
        txtOrgID.setText(p.getOrgID());
        txtRole.setText(p.getRole());
        txtStartDate.setText(Utilities.Utility.timeToString(p.getDateStart(), "yyyy-MM-dd"));
        txtEndDate.setText(Utilities.Utility.timeToString(p.getDateEnd(), "yyyy-MM-dd"));
        txtDescription.setText(p.getDes());
        cbxUpdateStatus.setSelectedIndex(p.getStatus());
        CardLayout cl = (CardLayout) panelMain.getLayout();
        cl.next(panelMain);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelMain = new javax.swing.JPanel();
        panelShow = new javax.swing.JPanel();
        btnRefresh = new javax.swing.JButton();
        cbxViewChooser = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblParticipation = new javax.swing.JTable();
        panelUpdate = new javax.swing.JPanel();
        txtStuName = new javax.swing.JLabel();
        txtStuID = new javax.swing.JLabel();
        txtOrgID = new javax.swing.JLabel();
        cbxUpdateStatus = new javax.swing.JComboBox();
        btnBack = new javax.swing.JButton();
        txtUpdate = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtRole = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtStartDate = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEndDate = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Participation");

        panelMain.setLayout(new java.awt.CardLayout());

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        cbxViewChooser.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pending", "Active", "Not active", "All ..." }));
        cbxViewChooser.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxViewChooserItemStateChanged(evt);
            }
        });

        tblParticipation.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tblParticipation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblParticipation);

        javax.swing.GroupLayout panelShowLayout = new javax.swing.GroupLayout(panelShow);
        panelShow.setLayout(panelShowLayout);
        panelShowLayout.setHorizontalGroup(
            panelShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShowLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                    .addGroup(panelShowLayout.createSequentialGroup()
                        .addComponent(cbxViewChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefresh)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelShowLayout.setVerticalGroup(
            panelShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxViewChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh))
                .addContainerGap())
        );

        panelMain.add(panelShow, "card2");

        txtStuName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtStuName.setText("Student Name");

        txtStuID.setText("Student ID");

        txtOrgID.setText("Organization ID");

        cbxUpdateStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pending", "Active", "Not Active", "Delete" }));

        btnBack.setText("<< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        txtUpdate.setText("Update");
        txtUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUpdateActionPerformed(evt);
            }
        });

        jLabel2.setText("Role: ");

        jLabel3.setText("Start Date:");

        jLabel4.setText("End Date:");

        jLabel5.setText("Require: yyyy-MM-dd");

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane1.setViewportView(txtDescription);

        javax.swing.GroupLayout panelUpdateLayout = new javax.swing.GroupLayout(panelUpdate);
        panelUpdate.setLayout(panelUpdateLayout);
        panelUpdateLayout.setHorizontalGroup(
            panelUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUpdateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(panelUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtStuName)
                        .addComponent(txtStuID)
                        .addGroup(panelUpdateLayout.createSequentialGroup()
                            .addComponent(txtOrgID)
                            .addGap(71, 71, 71)
                            .addComponent(cbxUpdateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelUpdateLayout.createSequentialGroup()
                            .addGroup(panelUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnBack)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4))
                            .addGap(45, 45, 45)
                            .addGroup(panelUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtRole)
                                .addComponent(txtUpdate)
                                .addComponent(txtStartDate)
                                .addComponent(txtEndDate, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)))))
                .addGap(32, 32, 32)
                .addComponent(jLabel5)
                .addContainerGap(135, Short.MAX_VALUE))
        );
        panelUpdateLayout.setVerticalGroup(
            panelUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUpdateLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtStuName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtStuID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOrgID)
                    .addComponent(cbxUpdateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(txtUpdate))
                .addContainerGap())
        );

        panelMain.add(panelUpdate, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxViewChooserItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxViewChooserItemStateChanged
        if (cbxViewChooser.getSelectedIndex() == 3) {
            pm.filterByStatus(-1);
        } else {
            pm.filterByStatus(cbxViewChooser.getSelectedIndex());
        }
    }//GEN-LAST:event_cbxViewChooserItemStateChanged

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        CardLayout cl = (CardLayout) panelMain.getLayout();
        cl.next(panelMain);
    }//GEN-LAST:event_btnBackActionPerformed

    private void txtUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUpdateActionPerformed
        if (cbxUpdateStatus.getSelectedIndex() == 3) {
            if (RetrieveData.deleteParticipation(txtStuID.getText(), txtOrgID.getText())) {
                JOptionPane.showMessageDialog(this, "Permanently delete");
            } else {
                JOptionPane.showMessageDialog(this, "An error has occured !");
            }
        } else {
            Participation p = new Participation(txtStuID.getText(), txtOrgID.getText(), txtRole.getText(),
                    Utilities.Utility.stringToDate(txtStartDate.getText(), "yyyy-MM-dd"),
                    Utilities.Utility.stringToDate(txtEndDate.getText(), "yyyy-MM-dd"),
                    txtDescription.getText(), cbxUpdateStatus.getSelectedIndex());
            if (RetrieveData.updatePar(txtStuID.getText(), txtOrgID.getText(), p)) {
                JOptionPane.showMessageDialog(this, "Update successful !");
            } else {
                JOptionPane.showMessageDialog(this, "An error has occured !");
            }
        }
    }//GEN-LAST:event_txtUpdateActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        if (cbxUpdateStatus.getSelectedIndex() != 4) {
            pm = new ParticipationModel(RetrieveData.getAllParticipation());
            pm.filterByStatus(cbxUpdateStatus.getSelectedIndex());
            tblParticipation.setModel(pm);
        }
    }//GEN-LAST:event_btnRefreshActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox cbxUpdateStatus;
    private javax.swing.JComboBox cbxViewChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelShow;
    private javax.swing.JPanel panelUpdate;
    private javax.swing.JTable tblParticipation;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtEndDate;
    private javax.swing.JLabel txtOrgID;
    private javax.swing.JTextField txtRole;
    private javax.swing.JTextField txtStartDate;
    private javax.swing.JLabel txtStuID;
    private javax.swing.JLabel txtStuName;
    private javax.swing.JButton txtUpdate;
    // End of variables declaration//GEN-END:variables
}
