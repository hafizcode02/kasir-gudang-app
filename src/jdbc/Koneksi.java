/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Hafiz Caniago
 */
public class Koneksi {
    Connection con;
    public Koneksi(){
        String id,pass,driver,url;
        id="root";
        pass="";
        driver="com.mysql.jdbc.Driver";
        url="jdbc:mysql://localhost:3306/db_kasir";
        try{
            Class.forName(driver).newInstance();
            con=DriverManager.getConnection(url,id,pass);
            //JOptionPane.showMessageDialog(null,"koneksi berhasil");
        }
        catch(Exception e){
            System.out.println(""+e.getLocalizedMessage());
        }
    }
    public static void main(String[] args) {
        Koneksi k = new Koneksi();
    }
    
}
