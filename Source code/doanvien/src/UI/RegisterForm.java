package UI;

import DButitilies.RetrieveData;
import Entities.Participation;
import java.util.Date;
import javax.swing.JOptionPane;

public class RegisterForm extends javax.swing.JDialog {

    private String stuID;
    private String orgID;

    public RegisterForm(java.awt.Frame parent, boolean modal, String stuID, String orgID) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        btnAccept.setEnabled(false);
        this.stuID = stuID;
        this.orgID = orgID;
        this.setTitle("Register Confimation");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        cbAccept = new javax.swing.JCheckBox();
        btnAccept = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("Điều kiện tham dự tổ chức:\n\n*Lorem ipsum dolosit amet\n*Lorem ipsum dolosit amet\n*Lorem ipsum dolosit amet\n*Lorem ipsum dolosit amet");
        jScrollPane1.setViewportView(jTextArea1);

        cbAccept.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cbAccept.setText("I've read terms and conditions.");
        cbAccept.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbAcceptMouseClicked(evt);
            }
        });

        btnAccept.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnAccept.setText("I accept");
        btnAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbAccept)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                        .addComponent(btnAccept)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbAccept)
                    .addComponent(btnAccept))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbAcceptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbAcceptMouseClicked
        if (cbAccept.isSelected()) {
            btnAccept.setEnabled(true);
        } else {
            btnAccept.setEnabled(false);
        }
    }//GEN-LAST:event_cbAcceptMouseClicked

    private void btnAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcceptActionPerformed
        Date d = new Date();
        Participation p = new Participation(stuID, orgID, null, d, d, stuID, 0);
        System.out.println("Test " + p.getStuID());
        if (RetrieveData.insertPar(p)) {
            JOptionPane.showMessageDialog(this, "Action done !");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error has occured !");
        }
    }//GEN-LAST:event_btnAcceptActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccept;
    private javax.swing.JCheckBox cbAccept;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
