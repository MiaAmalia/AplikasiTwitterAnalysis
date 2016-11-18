/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package synesketch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import twitteranalysisapp.koneksi;

/**
 *
 * @author Mia
 */
public class AturanHeuristik {

    /**
     * Return 1 Jika dalam Tweet terdapat kata negasi
     */
    public int cekNegasi(String text) throws SQLException {
        String sql = "select * from katanegasi";
        Statement st = koneksi.getKoneksi().createStatement();
        ResultSet rs = st.executeQuery(sql);
        boolean cek = false;
        while (rs.next()) {
            String hsl = rs.getString("negasi");
            if (text.toLowerCase().indexOf(hsl.toLowerCase()) != -1) {
                //System.out.println(hsl);
                return 1;
            }
        }
        return 0;
    }

    /**
     * Return 1 Jika terdapat tanda kombinasi dalam Tweet
     */
    public int cekKombinasi(String text) {
        if (text.contains("?!")) {
            return 1;
        } else {
            return 0;
        }
    }

     public double Emoticon(String text) throws SQLException {
         String[] data3 = text.split(" ");
         int count = 0;
          for (int i = 0; i < data3.length; i++) {
              String text2 = data3[i];
              if (text2.length() > 2) {
                  //System.out.println(text2.substring(0, 3));
                  String sql = "select * from hitungemo where emo LIKE '" + text2.substring(0, 3) + "'";
                  Statement st = koneksi.getKoneksi().createStatement();
                  ResultSet rs = st.executeQuery(sql);

                  while (rs.next()) {
                      String emo = rs.getString("emo");
                      String letter = rs.getString("letter");

                      for (int j = 0; j < data3[i].length(); j++) {
                          if (String.valueOf(data3[i].charAt(j)).equals(letter)) {
                              count++;
                          }

                      }
                  }
              }

          }
         
          if(count>0){
              count--;
          }
          
        return count;
   }

    
    /**
     * Return 1 Jika terdapat tanda seru dalam Tweet
     */
    public int cekTandaSeru(String text) {
        if (text.contains("!")) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Return 1 Jika terdapat String dengan huruf kapital dalam Tweet
     */
    public int cekKapital(String text) {
        int status = 0;
        String[] data = text.trim().split(" ");
        for (int i = 0; i < data.length; i++) {
            if (Character.isLowerCase(data[i].charAt(i))){
                status = 0;
                break;
            }
            else {
                status = 1;
                 break;
            }   
        }

        return status;
        
    }

    
//    if (Character.isLowerCase(data[i].charAt(i))) {
//                status = 0;
//                break;
//            }
//            else {
//                status = 1;
//                 break;
//            }   
    
    
    /**
     * Return 1 Jika terdapat kata keterangan dalam Tweet cek kata dengan list
     * kata keterangan dalam basisdata
     */
    public int cekKeterangan(String kata) throws SQLException {
        String[] data2 = kata.split(" ");
        String sql = "select * from kataketerangan ";
        Statement st = koneksi.getKoneksi().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            String ket = rs.getString("keterangan");

            for (int i = 0; i < data2.length; i++) {
                if (data2[i].toLowerCase().indexOf(ket.toLowerCase()) != -1) {
                    // System.out.println(ket);
                    return 1;
                }
            }

        }
        return 0;
    }

    /**
     * Return 1 Jika terdapat tanda emoticon dalam Tweet cek tanda emoticon
     * dengan list yang ada dalam basisdata
     */
    public int cekEmoticon(String text) throws SQLException {
        String[] data3 = text.split(" ");
        int status = 0;
        String sql = "select * from kamusemoticon ";
        Statement st = koneksi.getKoneksi().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            String emo = rs.getString("emoticon");

            for (int i = 0; i < data3.length; i++) {
                if (data3[i].contains(emo)) {
                    //System.out.println(emo);
                    // if()
                    status =1;
                }
                else
                    status=0;
            }

        }
        return status ;
    }
}
