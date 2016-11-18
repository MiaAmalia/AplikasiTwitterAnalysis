/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitteranalysisapp;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mia
 */
public class koneksi {

    private static Connection connect;
    private static String driverName = "com.mysql.jdbc.Driver"; // Driver Untuk Koneksi Ke MySQL  
    private static String jdbc = "jdbc:mysql://";
    private static String host = "localhost:"; // Bisa Menggunakan IP Anda, Cnth : 192.168.100.100  
    private static String port = "3306/"; // Port ini port MySQL  
    private static String database = "twitt"; // Ini Database yang akan digunakan  
    private static String url = jdbc + host + port + database;
    private static String username = "root"; // username default mysql  
    private static String password = "";

    public static Connection getKoneksi() throws SQLException {
        if (connect == null) {
            try {
                Class.forName(driverName);
                System.out.println("Class Driver Ditemukan");
                try {
                    connect = (Connection) DriverManager.getConnection(url, username, password);
                    System.out.println("Koneksi Database Sukses");
                } catch (SQLException se) {
                    System.out.println("Koneksi Database Gagal : " + se);
                    System.exit(0);
                }
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Class Driver Tidak Ditemukan, Terjadi Kesalahan Pada : " + cnfe);
                System.exit(0);
            }
        }
        return connect;
    }
}
