/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitteranalysisapp;

import com.mysql.jdbc.PreparedStatement;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mia
 */
public class TextProcessingUji extends javax.swing.JFrame {

    int no;
    String twit, hasilcase, hasilnormalisasi, anotasi, potong, kalimat, data, hasil;
    String id, tweet;
    private String[] str;
    private DefaultTableModel model;

    /**
     * Creates new form TextProcessingUji
     */
    public TextProcessingUji() {
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setResizable(false);
        setVisible(true);

        model = new DefaultTableModel();
        jTable2.setModel(model);
        model.addColumn("Id Tweet");
        model.addColumn("Tweet");
        lihatdata();
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
        jButtonNormalisasi = new javax.swing.JButton();
        jButtonHome = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButtonKataEmosi = new javax.swing.JButton();
        jButtonStopword = new javax.swing.JButton();
        jButtonPraprocess = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setToolTipText("");

        jButtonNormalisasi.setText("Normalisasi");
        jButtonNormalisasi.setToolTipText("");
        jButtonNormalisasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonNormalisasiMouseClicked(evt);
            }
        });
        jButtonNormalisasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNormalisasiActionPerformed(evt);
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

        jButtonKataEmosi.setText("Kata Emosi");
        jButtonKataEmosi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonKataEmosiMouseClicked(evt);
            }
        });
        jButtonKataEmosi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKataEmosiActionPerformed(evt);
            }
        });

        jButtonStopword.setText("Stopword");
        jButtonStopword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonStopwordMouseClicked(evt);
            }
        });
        jButtonStopword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStopwordActionPerformed(evt);
            }
        });

        jButtonPraprocess.setText("Praproses Data");
        jButtonPraprocess.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonPraprocessMouseClicked(evt);
            }
        });
        jButtonPraprocess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPraprocessActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonNormalisasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonStopword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonKataEmosi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonPraprocess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jButtonNormalisasi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonStopword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonKataEmosi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonPraprocess, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonHome, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(146, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNormalisasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNormalisasiActionPerformed
    }//GEN-LAST:event_jButtonNormalisasiActionPerformed

    private void jButtonHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHomeActionPerformed
        // TODO add your handling code here:
        new FormUtama().show();
        this.dispose();
    }//GEN-LAST:event_jButtonHomeActionPerformed

    private void jButtonKataEmosiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKataEmosiActionPerformed
        //cariKata();
    }//GEN-LAST:event_jButtonKataEmosiActionPerformed

    private void jButtonStopwordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonStopwordMouseClicked
        try {
            // TODO add your handling code here:
            ProsesStopword();
        } catch (IOException ex) {
            Logger.getLogger(TextProcessingUji.class.getName()).log(Level.SEVERE, null, ex);
        }

        lihatstopword();
    }//GEN-LAST:event_jButtonStopwordMouseClicked

    private void jButtonStopwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStopwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonStopwordActionPerformed

    private void jButtonPraprocessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPraprocessActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPraprocessActionPerformed

    private void jButtonNormalisasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonNormalisasiMouseClicked
        try {
            // TODO add your handling code here:
            normalisasi();
        } catch (IOException ex) {
            Logger.getLogger(TextProcessingUji.class.getName()).log(Level.SEVERE, null, ex);
        }

        lihatnormalisasi();
    }//GEN-LAST:event_jButtonNormalisasiMouseClicked

    private void jButtonKataEmosiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonKataEmosiMouseClicked
        // TODO add your handling code here:
        //cariKata();
        lihatcleaning();
    }//GEN-LAST:event_jButtonKataEmosiMouseClicked

    private void jButtonPraprocessMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonPraprocessMouseClicked
        // TODO add your handling code here:
        //TokenKata();
        lihattoken();
    }//GEN-LAST:event_jButtonPraprocessMouseClicked

    /**
     * Proses Normalisasi Tweet Penghilangan Symbol, URL, one Character, Convert
     * Emoticon, Convert Kata Hasil Proses disimpan (SimpanHasilNormalisasi)
     */
    void normalisasi() throws IOException {
        try {
            Connection cnormmalisasi = koneksi.getKoneksi();
            Statement s1 = cnormmalisasi.createStatement();
            String sql = "select id_tweet, tweet from datauji";
            ResultSet rr2 = s1.executeQuery(sql);
            while (rr2.next()) {
                int x = 1;
                String nomor = rr2.getString("id_tweet");
                twit = rr2.getString("tweet");
                hasilcase = clear(twit);
                StringBuilder builder = new StringBuilder();
                builder.append("");
                String[] kata;
                kata = hasilcase.split(" ");

//                for (int i = 0; i < kata.length; i++) {
//                    Connection cConvertEmo = koneksi.getKoneksi();
//                    Statement sConvertEmo = cConvertEmo.createStatement();
//                    String sqlConvertEmo = "select kata from kamusemoticon where emoticon = '" + kata[i] + "'";
//                    ResultSet rst = sConvertEmo.executeQuery(sqlConvertEmo);
//                    while (rst.next()) {
//                        String kataemo = rst.getString("kata");
//                        kata[i] = kataemo;
//                        //System.out.println(kata[i]);
//                    }
//                     builder.append(kata[i]).append(" ");
//                }
                

                for (int i = 0; i < kata.length; i++) {
                    Connection cConvertEmo = koneksi.getKoneksi();
                    Statement sConvertEmo = cConvertEmo.createStatement();
                    String sqlConvertEmo = "select kata from kamusemoticon ";
                   
                    ResultSet rst = sConvertEmo.executeQuery(sqlConvertEmo);
                    while (rst.next()) {
                        String kataemo = rst.getString("kata");
                        if(kata[i].contains("emoticon"))
                        {
                        kata[i] = kataemo;
                        }
                        //System.out.println(kata[i]);
                    }
                     builder.append(kata[i]).append(" ");
                }
//

               String hasilConvertEmo = builder.toString();
                System.out.println(hasilConvertEmo);
//                String[] kata2 = hasilConvertEmo.split(" ");

                for (int i = 0; i < kata.length; i++) {
                    Connection cConvertEmo = koneksi.getKoneksi();
                    Statement sConvertEmo = cConvertEmo.createStatement();
                    String sqlConvertEmo = "select kata2 from katapadanan where kata1 = '" + kata[i] + "'";
                    ResultSet rst = sConvertEmo.executeQuery(sqlConvertEmo);
                    while (rst.next()) {
                        String kataganti = rst.getString("kata2");
                        kata[i] = kataganti;
                        //System.out.println(kata[i]);
                    }
                    builder.append(kata[i]).append(" ");
                }

                String hasilConvertKata = builder.toString();
                //System.out.println(hasilConvertKata);
                System.out.println("=================================");

                // simpanNormalisasi(nomor, hasilConvertKata);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TextProcessingUji.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static String removeSymbol(String text) {
        text = text.replaceAll("@\\w+|#\\w+|\\brt\\b", "")
                .replaceAll("\\n", " ")
                .replaceAll(" +", " ")
                .replaceAll("[0-9]", " ")
                .replaceAll("\\'", " ");
        return text;
    }

    public static String removeUrl(String text) {
        String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern p = Pattern.compile(urlPattern, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        int i = 0;
        while (m.find()) {
            text = text.replaceAll(m.group(i), "").trim();
            i++;
        }
        return text;
    }

    public static String removeExtraSpaces(String text) {
        text = text.trim().replaceAll("\\s+", " ");
        return text;
    }

    public static String clear(String text) {
        text = TextProcessing.removeSymbol(text);
        text = TextProcessing.removeUrl(text);
        text = TextProcessing.removeExtraSpaces(text);

        return text;
    }

    void simpanNormalisasi(String no, String hasilcase) throws SQLException {
        try {
            Connection c3 = koneksi.getKoneksi();
            Statement s3 = c3.createStatement();
            String sql3 = "insert into normalisasiuji2 (id_tweet, tweet) values (?,?)";
            PreparedStatement o3 = (PreparedStatement) c3.prepareStatement(sql3);
            o3.setString(1, no);
            o3.setString(2, hasilcase);
            o3.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TextProcessingUji.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        Connection cek = koneksi.getKoneksi();
//        Statement scek = cek.createStatement();
//        String sqlCek = "select * from normalisasiuji";
//        ResultSet rs = scek.executeQuery(sqlCek);
//        while (rs.next()) {
//            String id_tweetcek = rs.getString("id_tweet");
//
//            if (no.equals(id_tweetcek)) {
//                continue;
//            } else {
//                String sql = "insert into normalisasiuji(id_tweet, tweet) values (?,?)";
//                PreparedStatement o = (PreparedStatement) cek.prepareStatement(sql);
//                o.setString(1, no);
//                o.setString(2, hasilcase);
//                o.executeUpdate();
//            }
//        }       
    }

    void ProsesStopword() throws IOException {
        try {
            Connection c4 = koneksi.getKoneksi();
            Statement s4 = c4.createStatement();
            String sql4 = "select id_tweet, tweet from normalisasiuji2 ";
            ResultSet rr2 = s4.executeQuery(sql4);
            while (rr2.next()) {

                String id = rr2.getString("id_tweet");
                String tweet = rr2.getString("tweet");

                //String delims="[ \t.,;() ]+";
                String delims = "[\\W\\d]+";
                //StringBuilder builder = new StringBuilder();
                String[] stopkata = tweet.trim().split(" ");
                String proses = stopword(stopkata);

                // simpanHasilStopword(id, proses);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TextProcessingUji.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String stopword(String[] str) throws IOException {
        try {
            Connection c2 = koneksi.getKoneksi();
        } catch (SQLException ex) {
            Logger.getLogger(TextProcessingUji.class.getName()).log(Level.SEVERE, null, ex);
        }

        // String delims = "\\d+";
        String delims = "[\\W]+";

        ArrayList<String> wordsList = new ArrayList<>();
        String sCurrentLine;
        int k = 0;
        String[] stopwords = new String[2000];

        FileReader fr = null;
        fr = new FileReader("D:\\stopwordbaru.txt");
        BufferedReader br = new BufferedReader(fr);

        while ((sCurrentLine = br.readLine()) != null) {
            stopwords[k] = sCurrentLine;
            k++;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("");

        wordsList.addAll(Arrays.asList(str));
        for (int i = 0; i < wordsList.size(); i++) {
            for (int j = 0; j < k; j++) {
                if (stopwords[j].contains(wordsList.get(i))) {
                    wordsList.remove(i);
                    break;
                }
            }
        }

        builder.append(wordsList);
        kalimat = builder.toString().replaceAll(delims, " ");
        System.out.println(kalimat);

        return kalimat;

    }

    void simpanHasilStopword(String no, String hasilcase) {
        try {
            Connection c8 = koneksi.getKoneksi();
            Statement s8 = c8.createStatement();
            String sql8 = "insert into stopworduji2 (id_tweet, tweet) values (?,?)";
            PreparedStatement o3 = (PreparedStatement) c8.prepareStatement(sql8);
            o3.setString(1, no);
            o3.setString(2, hasilcase);
            o3.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TextProcessingUji.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void cariKata() {
        try {
            Connection c9 = koneksi.getKoneksi();

            Statement s9 = c9.createStatement();
            String sql9 = "select id_tweet, tweet from stopworduji2 ";
            ResultSet rr2 = s9.executeQuery(sql9);
            while (rr2.next()) {

                String idtweet = rr2.getString("id_tweet");
                String tweet = rr2.getString("tweet");
                //String anotasii = rr2.getString("anotasi");
                String delims = "[ \t.,;()]+";
                StringBuilder builder = new StringBuilder();
                String[] kataemosi = tweet.trim().split(delims);
                String hasilStopword = cekKata(kataemosi);
                simpanKataEmosi(idtweet, hasilStopword);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TextProcessingUji.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static String cekKata(String[] str) throws SQLException {
        StringBuilder builder = new StringBuilder();
        builder.append("");
        String delims = "[ \t.,;()]+";
        ArrayList<String> wordsList = new ArrayList<>();
        wordsList.addAll(Arrays.asList(str));
        // where kata ='"+ str +"'

        for (int i = 0; i < wordsList.size(); i++) {
            System.out.println(wordsList.get(i).toString());
            String sql = "select * from kataemosi where kata='" + wordsList.get(i).toString() + "'";
            Statement st = koneksi.getKoneksi().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                builder.append(wordsList.get(i)).append(" ");
            }
        }
//        while (rs.next()) {
//            String hsl = rs.getString("kata");
//            // System.out.println(hsl);
//            for (int i = 0; i < wordsList.size(); i++) {
//                if (hsl.equalsIgnoreCase(wordsList.get(i))) {
//                    builder.append(wordsList.get(i)).append(" ");
//                    //System.out.println(wordsList.get(i))
//                }
//            }
//        }
//                builder.append(wordsList);
        String kataemosi = builder.toString().replaceAll(" ", " ");
        System.out.println(kataemosi);

        return kataemosi;
    }

    void simpanKataEmosi(String no, String tweet) {
        try {
            Connection c10 = koneksi.getKoneksi();
            Statement s10 = c10.createStatement();
            String sql10 = "insert into cleaninguji2 (id_tweet, tweet) values (?,?)";
            PreparedStatement o10 = (PreparedStatement) c10.prepareStatement(sql10);
            o10.setString(1, no);
            o10.setString(2, tweet);

            o10.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TextProcessingUji.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void TokenKata() {
        try {
            Connection c10 = koneksi.getKoneksi();
            Statement s10 = c10.createStatement();
            String sql10 = "select id_tweet, tweet from cleaninguji2 ";
            ResultSet rr2 = s10.executeQuery(sql10);
            while (rr2.next()) {
                String nomor = rr2.getString("id_tweet");
                String tweet = rr2.getString("tweet");
                //String anotasi = rr2.getString("anotasi");
                String delims = "[ \t.,;()]+";
                StringBuilder builder = new StringBuilder();
                String[] tokenkata = tweet.trim().split(delims);
                // HitungTerm(kata);
                if (tokenkata.length > 0) {
                    for (int i = 0; i < tokenkata.length; i++) {
                        String tmpkata = tokenkata[i];
                        if (!tmpkata.equals("")) {
                            String sql = "insert into tokenuji2 (id_tweet,kata) values (?,?)";
                            PreparedStatement o = (PreparedStatement) c10.prepareStatement(sql);
                            o.setString(1, nomor);
                            o.setString(2, tmpkata);
                            o.executeUpdate();
                        }
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(TextProcessingUji.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void lihatdata() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Connection t1 = koneksi.getKoneksi();
            Statement st1 = t1.createStatement();
            String sql_tampildata = "select * from datauji";
            ResultSet rr2 = st1.executeQuery(sql_tampildata);
            while (rr2.next()) {
                Object[] o = new Object[3];
                id = rr2.getString("id_tweet");
                tweet = rr2.getString("tweet");
                o[0] = id;
                o[1] = tweet;
                model.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TextProcessingUji.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void lihatnormalisasi() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Connection t2 = koneksi.getKoneksi();
            Statement st2 = t2.createStatement();
            String sql_tampildata2 = "select * from normalisasiuji2";
            ResultSet rr2 = st2.executeQuery(sql_tampildata2);
            while (rr2.next()) {
                Object[] o = new Object[3];
                id = rr2.getString("id_tweet");
                tweet = rr2.getString("tweet");
                o[0] = id;
                o[1] = tweet;
                model.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TextProcessingUji.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void lihatstopword() {

        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Connection t1 = koneksi.getKoneksi();
            Statement st1 = t1.createStatement();
            String sql_tampildata = "select * from stopworduji2";
            ResultSet rr2 = st1.executeQuery(sql_tampildata);
            while (rr2.next()) {
                Object[] o = new Object[3];
                id = rr2.getString("id_tweet");
                tweet = rr2.getString("tweet");
                o[0] = id;
                o[1] = tweet;
                model.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TextProcessingUji.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void lihatcleaning() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Connection t1 = koneksi.getKoneksi();
            Statement st1 = t1.createStatement();
            String sql_tampildata = "select * from cleaninguji2";
            ResultSet rr2 = st1.executeQuery(sql_tampildata);
            while (rr2.next()) {
                Object[] o = new Object[3];
                id = rr2.getString("id_tweet");
                tweet = rr2.getString("tweet");
                o[0] = id;
                o[1] = tweet;
                model.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TextProcessingUji.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void lihattoken() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Connection t1 = koneksi.getKoneksi();
            Statement st1 = t1.createStatement();
            String sql_tampildata = "select * from tokenuji2";
            ResultSet rr2 = st1.executeQuery(sql_tampildata);
            while (rr2.next()) {
                Object[] o = new Object[3];
                id = rr2.getString("id_tweet");
                tweet = rr2.getString("kata");
                o[0] = id;
                o[1] = tweet;
                model.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TextProcessingUji.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TextProcessingUji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TextProcessingUji().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonHome;
    private javax.swing.JButton jButtonKataEmosi;
    private javax.swing.JButton jButtonNormalisasi;
    private javax.swing.JButton jButtonPraprocess;
    private javax.swing.JButton jButtonStopword;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
