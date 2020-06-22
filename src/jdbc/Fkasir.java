/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.awt.HeadlessException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Hafiz Caniago
 */
public class Fkasir extends javax.swing.JFrame {

    /**
     * Creates new form FormKasir
     */
    public boolean databaru;
    DefaultTableModel data;
    ResultSet rs;
    Statement st;
    Koneksi koneksi;
    String ID = userlogin.getUserLogin();
    JasperReport jasrep;
    JasperDesign jasdes;
    JasperPrint jaspri;
    Map param = new HashMap();
    public Fkasir() {
        initComponents();
        koneksi = new Koneksi();
        waktu();
        nama.setText(ID);
        setLocationRelativeTo(this);
        load_data();
        tampilid();
        tampilnota();
        load_datap();
    }
       private void load_data(){
    Object header[]={"ID_BARANG","NAMA","KATEGORI","HARGA_JUAL","HARGA_BELI","STOK","PEGAWAI"};
    data = new DefaultTableModel(null,header);
    
    String sql = "SELECT tbl_barang.kd_barang, tbl_barang.nama_barang, tbl_barang.kategori, tbl_barang.harga_jual, tbl_barang.harga_beli, tbl_barang.stok, tbl_barang.NAMA_USER FROM tbl_barang;";
    
    try{
        st = koneksi.con.createStatement();
        rs = st.executeQuery(sql);
        while (rs.next()) {            
            String k1 = rs.getString(1);
            String k2 = rs.getString(2);
            String k3 = rs.getString(3);
            String k4 = rs.getString(4);
            String k5 = rs.getString(5);
            String k6 = rs.getString(6);
            String k7 = rs.getString(7);
            String k[]={k1,k2,k3,k4,k5,k6,k7};
            data.addRow(k);
        }
        gtable.setModel(data);          
    }
    catch(Exception e){
       JOptionPane.showMessageDialog(null, e);
    }
       }
       
       private void load_datap(){
           Object header[]={"ID","Kode Barang","Nama Barang","Harga","Jumlah","Total"};
           data = new DefaultTableModel(null,header);
           String sql1 = "select tbl_detailtransaksi.id_transaksi, tbl_detailtransaksi.kd_barang, tbl_barang.nama_barang, tbl_barang.harga_jual, tbl_transaksi.jumlah_barang, tbl_transaksi.harga_total FROM tbl_detailtransaksi join tbl_barang on tbl_detailtransaksi.kd_barang = tbl_barang.kd_barang join tbl_transaksi on tbl_detailtransaksi.id_transaksi = tbl_transaksi.id_transaksi WHERE tbl_transaksi.kode_transaksi='"+nota1.getText()+"'";
           try {
               st = koneksi.con.createStatement();
               rs = st.executeQuery(sql1);
               while (rs.next()) {                   
                   String k1 = rs.getString(1);
                   String k2 = rs.getString(2);
                   String k3 = rs.getString(3);
                   String k4 = rs.getString(4);
                   String k5 = rs.getString(5);
                   String k6 = rs.getString(6);
                   String k[] = {k1,k2,k3,k4,k5,k6};
                   data.addRow(k);
               }
               gtable1.setModel(data);
           } catch (Exception e) {
               JOptionPane.showMessageDialog(null, e);
           }
       }

         public void waktu(){              
                Thread p = new Thread(){
                  public void run(){
                      for(;;){
                Calendar kal = new GregorianCalendar();
                int tahun = kal.get(Calendar.YEAR);
                int bulan = kal.get(Calendar.MONTH)+1;
                int hari = kal.get(Calendar.DAY_OF_MONTH);
                int jam = kal.get(Calendar.HOUR_OF_DAY);
                int menit = kal.get(Calendar.MINUTE);
                int detik = kal.get(Calendar.SECOND);
                String tanggal = tahun +"/"+bulan+"/"+hari+"  "+jam+":"+menit+":"+detik;
                tgl.setText(tanggal);
                          try {
                              sleep(1000);
                          } catch (Exception e) {
                              JOptionPane.showMessageDialog(null, e);
                          }
                      }
                  }
                };
                p.start();
                
        }
         
        public void tampilid(){
                try {
                st = koneksi.con.createStatement();
                String sql1 = "SELECT MAX(id_transaksi) FROM tbl_transaksi";
                rs = st.executeQuery(sql1);
                    while (rs.next()) {                        
                        int a = rs.getInt(1);
                        idt.setText(Integer.toString(a+1));
                    }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        
        } 

        public void tampilnota(){
            try {
                st = koneksi.con.createStatement();
                String sql = "SELECT MAX(kode_transaksi) FROM tbl_transaksi";
                rs = st.executeQuery(sql);
                    while (rs.next()) {                        
                        int a = rs.getInt(1);
                        nota1.setText(Integer.toString(a+1));
                    }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        public void tampildata(){
            String sql = "SELECT * FROM tbl_barang WHERE kd_barang = '"+kdbarang.getText()+"'";
            try {
                st = koneksi.con.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {                    
                    nabar.setText(rs.getString("nama_barang"));
                    harju.setText(rs.getString("harga_jual"));
                    //tampilid();
                    
                }
            } catch (Exception e) {
            }
        }
        
        public void bersih(){
            kdbarang.setText("");
            nabar.setText("");
            harju.setText("");
            jmlbarang.setText("");
            harjubar.setText("");
        }
        
        public void inputdata(){
            try {
                st = koneksi.con.createStatement();
                String sql = "INSERT INTO tbl_transaksi "
                        +"values('"+idt.getText()
                        +"','"+nota1.getText()
                        +"','"+nama.getText()
                        +"',"+"NOW()"
                        +",'"+harjubar.getText()
                        +"','"+jmlbarang.getText()+"')";
                st.execute(sql);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            try {
                st = koneksi.con.createStatement();
                String sql1 = "INSERT INTO tbl_detailtransaksi values('"+idt.getText()+"','"+kdbarang.getText()+"')";
                st.execute(sql1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        
        public void lapor(){
            try {
            param.put("kembali", kembalian.getText());
            param.put("total", harga.getText());
            param.put("bayar", bayar.getText());
            param.put("kd_transaksi", nota1.getText());
            File file = new File("src\\jdbc\\struk.jrxml");
            jasdes = JRXmlLoader.load(file);
            jasrep = JasperCompileManager.compileReport(jasdes);
            jaspri = JasperFillManager.fillReport(jasrep, param, koneksi.con);
            JasperViewer.viewReport(jaspri,false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
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
        jLabel1 = new javax.swing.JLabel();
        tgl = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        kdbarang = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        nama = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        harju = new javax.swing.JTextField();
        jmlbarang = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        gtable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        nabar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        nota1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        gtable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        harga = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        bayar = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        kembalian = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        idt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        harjubar = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("APLIKASI KASIR");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 10, 200, 40));

        tgl.setEditable(false);
        tgl.setBackground(new java.awt.Color(255, 51, 51));
        tgl.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tgl.setForeground(new java.awt.Color(255, 255, 255));
        tgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglActionPerformed(evt);
            }
        });
        jPanel1.add(tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 10, 150, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1110, 60));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Kode Barang");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 30));

        kdbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kdbarangActionPerformed(evt);
            }
        });
        kdbarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kdbarangKeyReleased(evt);
            }
        });
        getContentPane().add(kdbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 110, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("ID TRANSAKSI");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 80, 20));

        nama.setEditable(false);
        getContentPane().add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 80, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Harga Barang");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, 30));

        harju.setEditable(false);
        getContentPane().add(harju, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 110, 30));

        jmlbarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jmlbarangMouseEntered(evt);
            }
        });
        jmlbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmlbarangActionPerformed(evt);
            }
        });
        jmlbarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jmlbarangKeyReleased(evt);
            }
        });
        getContentPane().add(jmlbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 50, 30));

        gtable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(gtable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 590, 200));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Nama Barang");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, 30));

        nabar.setEditable(false);
        getContentPane().add(nabar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 110, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("KODE_TRANSAKSI");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 110, 20));

        nota1.setEditable(false);
        nota1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nota1ActionPerformed(evt);
            }
        });
        getContentPane().add(nota1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 80, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("NAMA");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, -1, 20));

        jButton1.setText("OK");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 70, 30));

        gtable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(gtable1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 340, 590, 200));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("DATA PEMBELIAN");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 310, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("DATA BARANG");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 70, -1, -1));

        jLabel8.setBackground(new java.awt.Color(51, 51, 51));
        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("TOTAL HARGA");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, 40));

        harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaActionPerformed(evt);
            }
        });
        harga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                hargaKeyReleased(evt);
            }
        });
        getContentPane().add(harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, 160, 40));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("TOTAL BAYAR");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, -1, 39));

        bayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bayarMouseEntered(evt);
            }
        });
        bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayarActionPerformed(evt);
            }
        });
        bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bayarKeyReleased(evt);
            }
        });
        getContentPane().add(bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 420, 160, 40));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("KEMBALIAN");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, -1, 39));
        getContentPane().add(kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 470, 160, 40));

        jLabel14.setText("------------------------------------------------------------------------------------------------------------------------");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 1100, 40));

        jLabel15.setText("------------------------------------------------------------------------------------------------------------------------");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, -1));

        idt.setEditable(false);
        idt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idtActionPerformed(evt);
            }
        });
        getContentPane().add(idt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 80, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Jumlah Barang");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, 30));

        harjubar.setEditable(false);
        harjubar.setBackground(new java.awt.Color(51, 51, 51));
        harjubar.setForeground(new java.awt.Color(51, 51, 51));
        harjubar.setCaretColor(new java.awt.Color(51, 51, 51));
        harjubar.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        harjubar.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        harjubar.setSelectionColor(new java.awt.Color(51, 51, 51));
        getContentPane().add(harjubar, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 560, 110, 30));

        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 80, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_tglActionPerformed

    private void kdbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kdbarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdbarangActionPerformed

    private void kdbarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdbarangKeyReleased
String as = kdbarang.getText();
        if (as.equals("")) {
            bersih();
        }else{
            tampildata();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_kdbarangKeyReleased

    private void jmlbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmlbarangActionPerformed
String a = jmlbarang.getText();
        if (a.equals("")) {
            bayar.setText("");
        }else{
            int hg = Integer.parseInt(harju.getText());
            int jml = Integer.parseInt(jmlbarang.getText());
            int th = hg * jml;
            String total = Integer.toString(th);
            harjubar.setText(total);
                int baris = gtable1.getRowCount();
                int totalbiaya = Integer.parseInt(total);
                int jumlah_barang, harga_barang;
                TableModel tabelmodel;
                tabelmodel = gtable1.getModel();
                for (int i = 0; i < baris; i++) {
                    jumlah_barang = Integer.parseInt(tabelmodel.getValueAt(i,4).toString());
                    harga_barang = Integer.parseInt(tabelmodel.getValueAt(i,3).toString());
                    totalbiaya = totalbiaya + (jumlah_barang*harga_barang);
                }
                harga.setText(String.valueOf(totalbiaya));
            inputdata();
        }
        load_datap();
        bersih();
        tampilid();
        // TODO add your handling code here:
    }//GEN-LAST:event_jmlbarangActionPerformed

    private void jmlbarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jmlbarangKeyReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_jmlbarangKeyReleased

    private void hargaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hargaKeyReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_hargaKeyReleased

    private void bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayarKeyReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_bayarKeyReleased

    private void bayarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bayarMouseEntered

        // TODO add your handling code here:
    }//GEN-LAST:event_bayarMouseEntered

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
String a = jmlbarang.getText();
        if (a.equals("")) {
            bayar.setText("");
        }else{
            int hg = Integer.parseInt(harju.getText());
            int jml = Integer.parseInt(jmlbarang.getText());
            int th = hg * jml;
            String total = Integer.toString(th);
            harjubar.setText(total);
                int baris = gtable1.getRowCount();
                int totalbiaya = Integer.parseInt(total);
                int jumlah_barang, harga_barang;
                TableModel tabelmodel;
                tabelmodel = gtable1.getModel();
                for (int i = 0; i < baris; i++) {
                    jumlah_barang = Integer.parseInt(tabelmodel.getValueAt(i,4).toString());
                    harga_barang = Integer.parseInt(tabelmodel.getValueAt(i,3).toString());
                    totalbiaya = totalbiaya + (jumlah_barang*harga_barang);
                }
                harga.setText(String.valueOf(totalbiaya));
            inputdata();
        }
        load_datap();
        bersih();
        tampilid();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jmlbarangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmlbarangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jmlbarangMouseEntered

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarActionPerformed
String a = bayar.getText();
        if (a.equals("")) {
            kembalian.setText("");
        }else{
            int a1 = Integer.parseInt(bayar.getText());
            int a2 = Integer.parseInt(harga.getText());
            if (a1<a2) {
                JOptionPane.showMessageDialog(null, "Maaf uang anda tidak cukup");
            }else{
            int t = a1 - a2;
            String kembali = Integer.toString(t);
            kembalian.setText(kembali);
            JOptionPane.showMessageDialog(null,"KEMBALIAN ANDA "+"RP:"+kembali);
            lapor();   
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_bayarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
harga.setText("");
bayar.setText("");
kembalian.setText("");
tampilid();
tampilnota();
load_data();
load_datap();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void idtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idtActionPerformed

    private void nota1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nota1ActionPerformed
tampilnota();
        // TODO add your handling code here:
    }//GEN-LAST:event_nota1ActionPerformed

    private void hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaActionPerformed

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
            java.util.logging.Logger.getLogger(Fkasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fkasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fkasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fkasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Fkasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField bayar;
    private javax.swing.JTable gtable;
    private javax.swing.JTable gtable1;
    public javax.swing.JTextField harga;
    private javax.swing.JTextField harju;
    private javax.swing.JTextField harjubar;
    private javax.swing.JTextField idt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jmlbarang;
    private javax.swing.JTextField kdbarang;
    public javax.swing.JTextField kembalian;
    private javax.swing.JTextField nabar;
    private javax.swing.JTextField nama;
    public javax.swing.JTextField nota1;
    private javax.swing.JTextField tgl;
    // End of variables declaration//GEN-END:variables
}
