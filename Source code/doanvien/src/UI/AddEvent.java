package UI;

import DButitilies.RetrieveData;
import Entities.Event;
import static Utilities.Utility.stringToDate;
import java.util.Date;
import javax.swing.JOptionPane;

public class AddEvent extends javax.swing.JDialog {

    /**
     * Creates new form AddEvent
     */
    public AddEvent(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Add Event");
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
        jbl2 = new javax.swing.JLabel();
        jbl1 = new javax.swing.JLabel();
        jbl3 = new javax.swing.JLabel();
        jbl4 = new javax.swing.JLabel();
        jbl5 = new javax.swing.JLabel();
        jbl6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jtf1 = new javax.swing.JTextField();
        jtf2 = new javax.swing.JTextField();
        jtf3 = new javax.swing.JTextField();
        jtf4 = new javax.swing.JTextField();
        jtf5 = new javax.swing.JTextField();
        jtf6 = new javax.swing.JTextField();
        jtf7 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jbl2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/user_close_add.png"))); // NOI18N

        jbl1.setText("Event ID");

        jbl3.setText("Event Name");

        jbl4.setText("Location");

        jbl5.setText("Start");

        jbl6.setText("End");

        jLabel1.setText("People");

        jLabel2.setText("Ratting");

        jButton1.setText("Create Event");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jtf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf1ActionPerformed(evt);
            }
        });

        jtf2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf2ActionPerformed(evt);
            }
        });

        jtf3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf3ActionPerformed(evt);
            }
        });

        jtf4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf4ActionPerformed(evt);
            }
        });

        jtf5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf5ActionPerformed(evt);
            }
        });

        jtf6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf6ActionPerformed(evt);
            }
        });

        jtf7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbl2)
                    .addComponent(jbl1)
                    .addComponent(jbl3)
                    .addComponent(jbl4)
                    .addComponent(jbl5)
                    .addComponent(jbl6)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtf7, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf6, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf5, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbl1)
                    .addComponent(jtf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbl3))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbl4))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbl5))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbl6))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean creatEvent() {
        StringBuilder msg = new StringBuilder();
        
        if (jtf1.getText().compareTo("") == 0) {
            msg.append("\n- Don't blank Event ID");
        }
        if (jtf2.getText().compareTo("") == 0) {
            msg.append("\n- Don't blank Event Name");
        }
        if (jtf6.getText().compareTo("") == 0) {
            msg.append("\n- Don't blank Number of People");
        }
        if (msg.toString().compareTo("") == 0) {
            int n1 = Integer.parseInt(jtf6.getText());
            int n2 = Integer.parseInt(jtf7.getText());
            Date d1 = stringToDate(jtf4.getText(), "yyyy-MM-dd");
            Date d2 = stringToDate(jtf5.getText(), "yyyy-MM-dd");
            Event ev = new Event(jtf1.getText(), jtf2.getText(), jtf3.getText(), d1, d2, n1, n2);
            
            if (RetrieveData.insertEvent(ev)) {
                JOptionPane.showMessageDialog(this, "Create new event success !");
            } else {
                JOptionPane.showMessageDialog(this, "An error has occured !");
            }
            return true;
        } else {
            JOptionPane.showMessageDialog(null,
                    msg.toString(),
                    "Fail",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
    }
    
    private void jtf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf1ActionPerformed
        creatEvent();
    }//GEN-LAST:event_jtf1ActionPerformed
    
    private void jtf3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf3ActionPerformed
        creatEvent();
    }//GEN-LAST:event_jtf3ActionPerformed
    
    private void jtf5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf5ActionPerformed
        creatEvent();
    }//GEN-LAST:event_jtf5ActionPerformed
    
    private void jtf7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf7ActionPerformed
        creatEvent();
    }//GEN-LAST:event_jtf7ActionPerformed
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        creatEvent();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void jtf2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf2ActionPerformed
        creatEvent();
    }//GEN-LAST:event_jtf2ActionPerformed
    
    private void jtf4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf4ActionPerformed
        creatEvent();
    }//GEN-LAST:event_jtf4ActionPerformed
    
    private void jtf6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf6ActionPerformed
        creatEvent();
    }//GEN-LAST:event_jtf6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddEvent dialog = new AddEvent(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jbl1;
    private javax.swing.JLabel jbl2;
    private javax.swing.JLabel jbl3;
    private javax.swing.JLabel jbl4;
    private javax.swing.JLabel jbl5;
    private javax.swing.JLabel jbl6;
    private javax.swing.JTextField jtf1;
    private javax.swing.JTextField jtf2;
    private javax.swing.JTextField jtf3;
    private javax.swing.JTextField jtf4;
    private javax.swing.JTextField jtf5;
    private javax.swing.JTextField jtf6;
    private javax.swing.JTextField jtf7;
    // End of variables declaration//GEN-END:variables
}
