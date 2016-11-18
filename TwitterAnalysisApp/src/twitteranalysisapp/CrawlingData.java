/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitteranalysisapp;

import com.mysql.jdbc.PreparedStatement;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import javax.swing.*;

/**
 *
 * @author Mia
 */
public class CrawlingData extends javax.swing.JFrame {

    private DefaultTableModel model;

    /**
     * Creates new form CrawmingData
     */
    public CrawlingData() {
        initComponents();
        this.setLocationRelativeTo(null);

        //model buat tabel
        model = new DefaultTableModel();
        jTable2.setModel(model);
        model.addColumn("id");
        model.addColumn("Twitt");
        
        //pemanggilan fungsi menampilkan data ke tabel
        lihatdata();

        ButtonExport.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        ButtonCrawling = new javax.swing.JButton();
        ButtonHome = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        ButtonExport = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TwitterAnalysisCrawling");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setToolTipText("");

        ButtonCrawling.setText("Crawling Tweet Baru");
        ButtonCrawling.setToolTipText("");
        ButtonCrawling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCrawlingActionPerformed(evt);
            }
        });

        ButtonHome.setText("Home");
        ButtonHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonHomeActionPerformed(evt);
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

        ButtonExport.setText("export");
        ButtonExport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonExportMouseClicked(evt);
            }
        });
        ButtonExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonExportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(152, Short.MAX_VALUE)
                .addComponent(ButtonCrawling)
                .addGap(18, 18, 18)
                .addComponent(ButtonHome, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(ButtonExport, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(ButtonCrawling, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonHome, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonExport, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonHomeActionPerformed
        // TODO add your handling code here:
        new FormUtama().show();
        this.dispose();
    }//GEN-LAST:event_ButtonHomeActionPerformed

    private void ButtonCrawlingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCrawlingActionPerformed
        // TODO add your handling code here:
        boolean hasil = cekInet();
        ButtonExport.setVisible(true);
        try {
            Crawling();

        } catch (TwitterException | ParseException | SQLException ex) {
            Logger.getLogger(CrawlingData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ButtonCrawlingActionPerformed

    private void ButtonExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonExportActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonExportActionPerformed

    private void ButtonExportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonExportMouseClicked
        try {
            // TODO add your handling code here:
            simpantabel();
        } catch (IOException ex) {
            Logger.getLogger(CrawlingData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ButtonExportMouseClicked

    void lihatdata() {
        long no;
        String kal;
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        Connection tampil1;
        try {
            tampil1 = koneksi.getKoneksi();
            Statement st1 = tampil1.createStatement();
            String sql_tampil = "select * from datatwit";
            ResultSet rr2 = st1.executeQuery(sql_tampil);
            while (rr2.next()) {
                Object[] o = new Object[2];
                no = rr2.getLong("id_twit");
                kal = rr2.getString("tweet");
                o[0] = no;
                o[1] = kal;
                model.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrawlingData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void Crawling() throws TwitterException, ParseException, SQLException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("VoCH7Vi9gyXdngcSoDngL5HoW")
                .setOAuthConsumerSecret("8Wmwym58P7VaTXhHTAiEFU4UXMcZmkk7PR9boXLsHAIPfllBtG")
                .setOAuthAccessToken("4780636297-A8WRDcYlT7fpqByZfZTWSqiSTqo3LxV65nIDFAN")
                .setOAuthAccessTokenSecret("Xf7UPTF8dmvo9OBcRSJFfef8oVuhlIt9avhdiUAT58pPl");

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        // ngambil user mention
        ResponseList<Status> statuss;
        String line;
        Paging page = new Paging(1, 200);
        String bahasa, tanggal, ket, teks;
        long id;


        //String tes = "#SurveyIF";
//        statuss = twitter.getMentionsTimeline(page);
//        int cnt = 0;
//        for (Status statusa : statuss) {
//            System.out.println(cnt + "-" + statusa.getId() + ":" + statusa.getUser().getName() + ":" + statusa.getText() + "(" + statusa.getCreatedAt() + ")");
//            cnt++;
//            Date a = statusa.getCreatedAt();
//            tanggal = a.toString();
//            bahasa = statusa.getLang();
//            id = statusa.getId();
//            teks = statusa.getText();
//            if (statusa.getUser() != null) {
//                ket = "mention";
//            } else {
//                ket = "post";
//            }
//            //sourcesimpan(id,teks, tanggal, bahasa,ket);
//        }

        // ngambil if_sakti
        ResponseList<Status> status2;
        String line2;
        Paging page2 = new Paging(5, 200);
        String tes2 = "ridwankamil";
        status2 = twitter.getUserTimeline(tes2, page2);
        int count = 0;
        for (Status statusb : status2) {
            System.out.print(count + "- ");
            System.out.println(statusb.getId() + ":"+ statusb.getText() + "(" + statusb.getCreatedAt() + ")");
            count++;

            Date a = statusb.getCreatedAt();
            tanggal = a.toString();
            bahasa = statusb.getLang();
            id = statusb.getId();
            teks = statusb.getText();
            if (statusb.isRetweet() == true) {
                ket = "retweet";
            } else if (statusb.getInReplyToScreenName() != null) {
                ket = "balasan";
            } else {
                ket = "post";
            }

            //sourcesimpan(id,teks, tanggal, bahasa,ket);
        }


//        ResponseList<Status> status3;
//        String line3;
//        int count1 = 0;
//        for (int i = 1; i <= 14; i++) {
//            Paging page3 = new Paging(i, 200);
//            String tes3 = "if_sakti";
//            status3 = twitter.getUserTimeline(tes3, page3);
//            for (Status statusc : status3) {
//                System.out.print(count1 + "- ");
//                System.out.println(statusc.getText() + "(" + statusc.getCreatedAt() + ")");
//                count1++;
//                Date a = statusc.getCreatedAt();
//                tanggal = a.toString();
//                bahasa = statusc.getLang();
//                id = statusc.getId();
//                teks = statusc.getText();
//                if (statusc.isRetweet() == true) {
//                    ket = "retweet";
//                } else if (statusc.getInReplyToScreenName() != null) {
//                    ket = "balasan";
//                } else {
//                    ket = "post";
//                }
//                //  simpan(statusc.getId(),statusc.getText());
//                //sourcesimpan(id,teks, tanggal, bahasa,ket);
//            }
//        }


    }

    boolean cekInet() {
        boolean connectivity;
        try {
            URL url = new URL("http://twitter.com");
            URLConnection conn = url.openConnection();
            conn.connect();
            connectivity = true;
            System.out.println("\nBerhasil Konek internet");
        } catch (Exception e) {
            connectivity = false;
            System.out.println("\nGagal Konek internet");
        }
        return connectivity;
    }

    void simpan(long a, String b) {
        Connection c3;
        try {
            c3 = koneksi.getKoneksi();
            Statement statement = c3.createStatement();
            String sql = "insert into datatwit (id, twit) values (?,?)";
            PreparedStatement o3 = (PreparedStatement) c3.prepareStatement(sql);
            o3.setLong(1, a);
            o3.setString(2, b);
            o3.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CrawlingData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void simpan2(long a, String b, String c) {
        Connection c3;
        try {
            c3 = koneksi.getKoneksi();
            Statement statement = c3.createStatement();
            String sql = "insert into datatwit (id_twit, twit, tanggal) values (?,?,?)";
            PreparedStatement o3 = (PreparedStatement) c3.prepareStatement(sql);
            o3.setLong(2, a);
            o3.setString(3, b);
            o3.setString(4, c);
            o3.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CrawlingData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void sourcesimpan(long a, String tw, String tgl, String bhs, String ket) {
        Connection c5;
        try {
            c5 = koneksi.getKoneksi();
            PreparedStatement preparedStatement =
                    (PreparedStatement) c5.prepareStatement("insert into dataali (id_twit, tweet, tanggal, bahasa, ket ) VALUES (?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, a);
            preparedStatement.setString(2, tw);
            preparedStatement.setString(3, tgl);
            preparedStatement.setString(4, bhs);
            preparedStatement.setString(5, ket);
            preparedStatement.executeUpdate();

            ResultSet tableKeys = preparedStatement.getGeneratedKeys();
            tableKeys.next();
            int autoGeneratedID = tableKeys.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(CrawlingData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void simpantabel() throws IOException {
        Connection c6;
        try {
            c6 = koneksi.getKoneksi();
            Statement st = c6.createStatement();
            FileOutputStream fileOut;
            try {
                fileOut = new FileOutputStream("E:/DataTweetFilter.xls");
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet worksheet = workbook.createSheet("Sheet 0");

                Row row1 = worksheet.createRow((short) 0);
                row1.createCell(0).setCellValue("id");
                row1.createCell(1).setCellValue("id_tweet");
                row1.createCell(2).setCellValue("tweet");
                row1.createCell(3).setCellValue("tanggal");
                row1.createCell(4).setCellValue("bahasa");
                row1.createCell(5).setCellValue("keterangan");

                Row row2;
                try (ResultSet rs = st.executeQuery("SELECT * FROM dataali")) {
                    while (rs.next()) {
                        int a = rs.getRow();
                        row2 = worksheet.createRow((short) a);
                        row2.createCell(0).setCellValue(rs.getString(1));
                        row2.createCell(1).setCellValue(rs.getString(2));
                        row2.createCell(2).setCellValue(rs.getString(3));
                        row2.createCell(3).setCellValue(rs.getString(4));
                        row2.createCell(4).setCellValue(rs.getString(5));
                        row2.createCell(5).setCellValue(rs.getString(6));
                    }

                    workbook.write(fileOut);
                    fileOut.flush();
                    fileOut.close();
                }
                st.close();
                c6.close();
                System.out.println("Ekport Sukses");


            } catch (FileNotFoundException ex) {
                Logger.getLogger(CrawlingData.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CrawlingData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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
            java.util.logging.Logger.getLogger(CrawlingData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrawlingData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrawlingData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrawlingData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CrawlingData().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonCrawling;
    private javax.swing.JButton ButtonExport;
    private javax.swing.JButton ButtonHome;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}