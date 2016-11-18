/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitteranalysisapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author Mia
 */
public class import_data {

    Connection connect;
    PreparedStatement ps;
    private Statement stat;

    public Connection koneksi() throws SQLException {

        String driverName = "com.mysql.jdbc.Driver"; // Driver Untuk Koneksi Ke MySQL  
        String jdbc = "jdbc:mysql://";
        String host = "localhost:"; // Bisa Menggunakan IP Anda, Cnth : 192.168.100.100  
        String port = "3306/"; // Port ini port MySQL  
        String database = "twitt"; // Ini Database yang akan digunakan  
        String url = jdbc + host + port + database;
        String username = "root"; // username default mysql  
        String password = "";
        
        connect = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, username, password);
        System.out.println("Koneksi Database Sukses");

        return connect;
    }

    public static ArrayList readExcelFile(String fileName) throws IOException {
        ArrayList cellArrayListHolder = new ArrayList();
        try {
            FileInputStream myInput = new FileInputStream(fileName);
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);

            Iterator rowIter = mySheet.rowIterator();
            while (rowIter.hasNext()) {
                HSSFRow myRow = (HSSFRow) rowIter.next();
                Iterator cellIter = myRow.cellIterator();
                ArrayList cellStoreArrayList = new ArrayList();

                while (cellIter.hasNext()) {
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    cellStoreArrayList.add(myCell);
                }
                cellArrayListHolder.add(cellStoreArrayList);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(import_data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cellArrayListHolder;
    }

    public void importexcel() throws SQLException {
        String fileName = "D:/DataTweetFilter1 fix annotator.xls";
        ArrayList dataHolder;
        try {
            dataHolder = readExcelFile(fileName);
            String query1 = "insert into anotasi (id, id_tweet, tweet, tanggal, bahasa, keterangan, emosi, annotator) values (?, ?, ?, ?, ?, ?, ?,?)";

            ps = this.koneksi().prepareStatement(query1);

            int count = 0;
            ArrayList cellStoreArrayList = null;

            for (int i = 1; i < dataHolder.size(); i++) {
                cellStoreArrayList = (ArrayList) dataHolder.get(i);
                ps.setString(1, ((HSSFCell) cellStoreArrayList.get(0)).toString());
                //System.out.println((HSSFCell)cellStoreArrayList.get(0));
                ps.setString(2, ((HSSFCell) cellStoreArrayList.get(1)).toString());
                ps.setString(3, ((HSSFCell) cellStoreArrayList.get(2)).toString());
                ps.setString(4, ((HSSFCell) cellStoreArrayList.get(3)).toString());
                ps.setString(5, ((HSSFCell) cellStoreArrayList.get(4)).toString());
                ps.setString(6, ((HSSFCell) cellStoreArrayList.get(5)).toString());
                ps.setString(7, ((HSSFCell) cellStoreArrayList.get(6)).toString());
                ps.setString(8, ((HSSFCell) cellStoreArrayList.get(7)).toString());

                count = ps.executeUpdate();
            }
            System.out.print("Import Sukses !");
        } catch (IOException ex) {
            Logger.getLogger(import_data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
