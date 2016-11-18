/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitteranalysisapp;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mia
 */
public class Pembobotan extends javax.swing.JFrame {

    String[] kata;
    private DefaultTableModel model;
    private DefaultTableModel model2;

    void KumpulanKata() {
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            String sql10 = "SELECT token.kata, token.anotator, COUNT(token.kata) as jumlah_kata FROM token GROUP BY kata, anotator";
            ResultSet rr2 = s.executeQuery(sql10);
            while (rr2.next()) {
                int jumlah_kata = rr2.getInt("jumlah_kata");
                String kata2 = rr2.getString("kata");
                String anotasi = rr2.getString("anotator");
                System.out.println(kata2 + " : " + anotasi + " = " + jumlah_kata);

                //if (rr2.next()) {
                String sql = "insert into synset (kata, count, anotator) values (?,?,?)";
                PreparedStatement o = (PreparedStatement) c.prepareStatement(sql);
                o.setString(1, kata2);
                o.setInt(2, jumlah_kata);
                o.setString(3, anotasi);
                o.executeUpdate();
//                } else {
//                    Connection c2 = koneksi.getKoneksi();
//                    Statement s2 = c.createStatement();
//                    String sql2 = "update synset set count = count + 1 WHERE kata = '" + kata2 + "' AND  anotator = '" + anotasi + "'";
//                    ResultSet rr3 = s2.executeQuery(sql2);
//                }
            }
        } //                   }
        catch (SQLException ex) {
            Logger.getLogger(TextProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void BentukBobot() throws SQLException {
        Connection c2 = koneksi.getKoneksi();
        Statement s2 = c2.createStatement();
        String sql2 = "SELECT synset.kata, sum(synset.count) as total_kata from synset group by kata";
        ResultSet rr2 = s2.executeQuery(sql2);
        while (rr2.next()) {
            int count = rr2.getInt("total_kata");
            String kata = rr2.getString("kata");

            System.out.println("Hasil : " + kata + " = " + count);
            String jenisemosi[] = {"1.0", "2.0", "3.0", "4.0", "5.0", "6.0"};
            for (int i = 0; i < jenisemosi.length; i++) {
                Connection c3 = koneksi.getKoneksi();
                Statement s3 = c3.createStatement();
                String sql3 = "select * from synset where kata = '" + kata + "' and anotator = '" + jenisemosi[i] + "' ";
                ResultSet rs = s3.executeQuery(sql3);
                if (rs.next()) {
                    double bobot = (double) rs.getInt("count") / (double) count;
                    String sql = "insert into kamusemosi(kata, count, anotator, bobot) values (?,?,?,?)";
                    PreparedStatement o = (PreparedStatement) c3.prepareStatement(sql);
                    o.setString(1, kata);
                    o.setInt(2, rs.getInt("count"));
                    o.setString(3, jenisemosi[i]);
                    o.setDouble(4, bobot);
                    o.executeUpdate();
                    //System.out.println("Bobot " + kata + " - " + jenisemosi[i] + " : " + String.valueOf(bobot));
                } else {                    
                    String sql = "insert into kamusemosi(kata, count, anotator, bobot) values (?,?,?,?)";
                    PreparedStatement o = (PreparedStatement) c3.prepareStatement(sql);
                    o.setString(1, kata);
                    o.setInt(2, 0);
                    o.setString(3, jenisemosi[i]);
                    o.setDouble(4, 0);
                    o.executeUpdate();
                    //System.out.println("Bobot " + kata + " - " + jenisemosi[i] + " : " + "0");
                }


            }
        }
        
        Connection c4 = koneksi.getKoneksi();
        Statement s4 = c4.createStatement();
        String sql4 = "SELECT kata FROM kataemosi WHERE kata NOT IN (SELECT kata FROM synset GROUP BY kata)";
        ResultSet rr4 = s4.executeQuery(sql4);
        while (rr4.next()) {
            String kata3 = rr4.getString("kata");
            String jenisemosi[] = {"1.0", "2.0", "3.0", "4.0", "5.0", "6.0"};
            for (int i = 0; i < jenisemosi.length; i++) {                
                String sql = "insert into kamusemosi(kata, count, anotator, bobot) values (?,?,?,?)";
                PreparedStatement o = (PreparedStatement) c4.prepareStatement(sql);
                o.setString(1, kata3);
                o.setInt(2, 0);
                o.setString(3, jenisemosi[i]);
                o.setDouble(4, 0);
                o.executeUpdate();
            }
        }
    }
    
    void lihatTerm(){
        String kata, count, anotasi;
        jTable2.setModel(model);
 
        model.addColumn("kata");
        model.addColumn("jumlah");
        model.addColumn("anotasi");
        
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Connection cLihatTerm = koneksi.getKoneksi();
            Statement st = cLihatTerm.createStatement();
            String sql_LihatTerm = "select * from synset";
            ResultSet rs = st.executeQuery(sql_LihatTerm);
            while (rs.next()) {
                Object[] term = new Object[3];
                kata = rs.getString("kata");
                count = rs.getString("count");
                anotasi = rs.getString("anotator");
                term[0] = kata;
                term[1] = count;
                term[2] = anotasi;
                model.addRow(term);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pembobotan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    void LihatBobot(){
        String kata2;
        int count2, anotasi2;
        double bobot;
        jTable2.setModel(model2);

        model2.addColumn("kata");
        model2.addColumn("jumlah");
        model2.addColumn("bobot");
        model2.addColumn("anotasi");
        model2.getDataVector().removeAllElements();
        model2.fireTableDataChanged();
        
        try {
            Connection cLihatBobot = koneksi.getKoneksi();
            Statement stBobot =  cLihatBobot.createStatement();
            String sql_LihatBobot = "select * from kamusemosi";
            ResultSet rs = stBobot.executeQuery(sql_LihatBobot);
            while (rs.next()) {
                Object[] tblBobot = new Object[4];
                kata2 = rs.getString("kata");
                count2 = rs.getInt("count");
                bobot = rs.getDouble("bobot");
                anotasi2 = rs.getInt("anotator");
                tblBobot[0] = kata2;
                tblBobot[1] = count2;
                tblBobot[2] = bobot;
                tblBobot[3] = anotasi2;
                model2.addRow(tblBobot);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pembobotan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    /**
     * Creates new form Pembobotan
     */
    public Pembobotan() {
        initComponents();
        this.setLocationRelativeTo(null);
        model = new DefaultTableModel();
        model2 = new DefaultTableModel();
        lihatTerm();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButtonBobot = new javax.swing.JButton();
        jButtonHome = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TwitterAnalysisPembobotan");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setToolTipText("");

        jButtonBobot.setText("Bentuk Bobot");
        jButtonBobot.setToolTipText("");
        jButtonBobot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBobotActionPerformed(evt);
            }
        });

        jButtonHome.setText("Home");
        jButtonHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHomeActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonBobot, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jButtonBobot, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonHome, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(251, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBobotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBobotActionPerformed
//         KumpulanKata();
//        try {
//            BentukBobot();
//        } catch (SQLException ex) {
//            Logger.getLogger(Pembobotan.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
        LihatBobot();
    }//GEN-LAST:event_jButtonBobotActionPerformed

    private void jButtonHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHomeActionPerformed
        // TODO add your handling code here:
        new FormUtama().show();
        this.dispose();
    }//GEN-LAST:event_jButtonHomeActionPerformed

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
            java.util.logging.Logger.getLogger(Pembobotan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pembobotan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pembobotan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pembobotan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pembobotan().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBobot;
    private javax.swing.JButton jButtonHome;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
