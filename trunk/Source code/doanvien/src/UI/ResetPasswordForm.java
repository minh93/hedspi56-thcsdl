package UI;

import DButitilies.RetrieveData;
import Entities.User;
import Utilities.Utility;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ResetPasswordForm extends javax.swing.JDialog {

    private String usrName;

    /**
     * Creates new form ResetPasswordForm
     */
    public ResetPasswordForm(java.awt.Frame parent, boolean modal, String usrName, int role) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.usrName = usrName;
        this.setTitle("Confirm reset password " + usrName);
        this.setResizable(false);
        if (role == 1) {
            cbReset.setVisible(true);
        } else {
            cbReset.setVisible(false);
        }
    }

    private User getUser(String usrName) {
        ArrayList<User> list = RetrieveData.getAllUser();
        for (User u : list) {
            if (u.getUserName().compareTo(usrName) == 0) {
                return u;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtOldPass = new javax.swing.JPasswordField();
        txtNewPass = new javax.swing.JPasswordField();
        txtRePass = new javax.swing.JPasswordField();
        btnOK = new javax.swing.JButton();
        cbReset = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel1.setText("Old password:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel2.setText("New password:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel3.setText("Re-Type :");

        txtNewPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNewPassActionPerformed(evt);
            }
        });

        btnOK.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        cbReset.setText("Reset password only (default 0000)");
        cbReset.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbResetItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbReset)
                        .addGap(0, 44, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtOldPass)
                            .addComponent(txtNewPass)
                            .addComponent(txtRePass))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnOK)
                .addGap(104, 104, 104))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtOldPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRePass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(cbReset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOK)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        if (cbReset.isSelected()) {
            String newPass = new String(txtNewPass.getPassword());
            if (RetrieveData.updatePassword(usrName, Utility.checksumGen(newPass, "MD5", true)) == 1) {
                JOptionPane.showMessageDialog(this, "User " + usrName + "has new password !");
                this.dispose();

            } else {
                JOptionPane.showMessageDialog(this, "Some error has occured !");
            }
        } else {
            resetPassword();
        }
    }//GEN-LAST:event_btnOKActionPerformed

    private void resetPassword() {
        boolean check = false;
        String oldPass = new String(txtOldPass.getPassword());
        String oldPassMD5 = Utilities.Utility.checksumGen(oldPass, "MD5", true);
        String newPass = new String(txtNewPass.getPassword());
        String newRePass = new String(txtRePass.getPassword());
        User u = getUser(usrName);
        if (u.getPassword().compareTo(oldPassMD5) == 0) {
            check = true;
        } else {
            check = false;
            JOptionPane.showMessageDialog(this, "This old password doesn't match");
        }
        if (newRePass.compareTo(newPass) == 0 && newRePass.compareTo("") != 0) {
            check = true;
            txtRePass.setBackground(Color.WHITE);
        } else {
            check = false;
            txtRePass.setBackground(Color.red);
            txtRePass.setToolTipText("Re-typed password doesn't match !");
        }
        if (check) {
            RetrieveData.updatePassword(usrName, Utility.checksumGen(newPass, "MD5", true));
            JOptionPane.showMessageDialog(this, "User " + usrName + "has new password !");
            this.dispose();
        }
    }

    private void txtNewPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNewPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNewPassActionPerformed

    private void cbResetItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbResetItemStateChanged
        if (cbReset.isSelected()) {
            txtOldPass.setEnabled(false);
            txtRePass.setEnabled(false);
        } else {
            txtOldPass.setEnabled(true);
            txtRePass.setEnabled(true);
        }
    }//GEN-LAST:event_cbResetItemStateChanged
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOK;
    private javax.swing.JCheckBox cbReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField txtNewPass;
    private javax.swing.JPasswordField txtOldPass;
    private javax.swing.JPasswordField txtRePass;
    // End of variables declaration//GEN-END:variables
}
