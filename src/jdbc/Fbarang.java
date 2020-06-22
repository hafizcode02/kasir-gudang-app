/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Hafiz Caniago
 */
public class Fbarang extends javax.swing.JFrame {
    String ID = userlogin.getUserLogin();
    String uid = iduser.getIdUser();
    DefaultTableModel data;
    ResultSet rs;
    Statement st;
    Koneksi koneksi;
    Login login;
    JasperReport jasrep;
    JasperDesign jasdes;
    JasperPrint jaspri;
    Map param = new HashMap();

    /**
     * Creates new form Fbarang 
     */
    public Fbarang() {
        initComponents();
        koneksi = new Koneksi();
        load_data();
        AutoID();
        pegawai.setText(ID);
        userid.setText(uid);
        setLocationRelativeTo(this);
    }
    private void load_data(){
    Object header[]={"ID_BARANG","NAMA","KATEGORI","HARGA_JUAL","HARGA_BELI","STOK","PEGAWAI"};
    data = new DefaultTableModel(null,header);
    
    String sql = "SELECT tbl_barang.kd_barang, tbl_barang.nama_barang, tbl_barang.kategori, tbl_barang.harga_jual, tbl_barang.harga_beli, tbl_barang.stok, tbl_barang.NAMA_USER FROM tbl_barang;";
    
    try{
        if (tablebox.getSelectedItem().equals("-All-")) {
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
    }
    catch(Exception e){
       JOptionPane.showMessageDialog(null, e);
    }
    
        try {
            String sql1 = "SELECT * FROM tbl_barang WHERE kategori='Makanan'";
            String sql2 = "SELECT * FROM tbl_barang WHERE kategori='Minuman'";
            String sql3 = "SELECT * FROM tbl_barang WHERE kategori='Pakaian'";
            String sql4 = "SELECT * FROM tbl_barang WHERE kategori='Alat Tulis'";
            if (tablebox.getSelectedItem().equals("Makanan")) {
                st = koneksi.con.createStatement();
                rs = st.executeQuery(sql1);
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
            if (tablebox.getSelectedItem().equals("Minuman")) {
                st = koneksi.con.createStatement();
                rs = st.executeQuery(sql2);
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
                if (tablebox.getSelectedItem().equals("Pakaian")) {
                st = koneksi.con.createStatement();
                rs = st.executeQuery(sql3);
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
                if (tablebox.getSelectedItem().equals("Alat Tulis")) {
                st = koneksi.con.createStatement();
                rs = st.executeQuery(sql4);
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
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
    
    
}
    
    public void level(){
        try {
            st=koneksi.con.createStatement();
            String query = "SELECT * FROM tbl_kategori";
            rs=st.executeQuery(query);
            while (rs.next()) {                
               kbox.addItem(rs.getString("nama_kategori"));
            }
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void AutoID(){
        try {
            st = koneksi.con.createStatement();
            String sql = "SELECT * FROM tbl_barang WHERE kd_barang LIKE '%MK%' ORDER BY kd_barang DESC";
            rs = st.executeQuery(sql);
            if (rs.next()) {
                String ID = rs.getString("kd_barang").substring(4);
                if (kbox.getSelectedItem().equals("Makanan")) {
                String NO = ""+(Integer.parseInt(ID)+1);
                String NOL = "000";
                kdbarang.setText("MK"+NOL+NO);
                }
            }
            else{
                if (kbox.getSelectedItem().equals("Makanan")) {
                kdbarang.setText("MK0001");    
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        try {
            st = koneksi.con.createStatement();
                String sql = "SELECT * FROM tbl_barang WHERE kd_barang LIKE '%MI%' ORDER BY kd_barang DESC";
            rs = st.executeQuery(sql);
            if (rs.next()) {
                String ID = rs.getString("kd_barang").substring(4);
                if (kbox.getSelectedItem().equals("Minuman")) {
                String NO = ""+(Integer.parseInt(ID)+1);
                String NOL = "000";
                kdbarang.setText("MI"+NOL+NO);
                }
            }else{
                if (kbox.getSelectedItem().equals("Minuman")) {
                    kdbarang.setText("MI0001");
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        try {
            st = koneksi.con.createStatement();
            String sql = "SELECT * FROM tbl_barang WHERE kd_barang LIKE '%PK%' ORDER BY kd_barang DESC";
            rs = st.executeQuery(sql);
            if (rs.next()) {
                String ID = rs.getString("kd_barang").substring(4);
                if (kbox.getSelectedItem().equals("Pakaian")) {
                String NO = ""+(Integer.parseInt(ID)+1);
                String NOL = "000";
                kdbarang.setText("PK"+NOL+NO);
                }
            }else{
                if (kbox.getSelectedItem().equals("Pakaian")) {
                    kdbarang.setText("PK0001");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
            try {
            st = koneksi.con.createStatement();
            String sql = "SELECT * FROM tbl_barang WHERE kd_barang LIKE '%AT%' ORDER BY kd_barang DESC";
            rs = st.executeQuery(sql);
            if (rs.next()) {
                String ID = rs.getString("kd_barang").substring(4);
                if (kbox.getSelectedItem().equals("Alat Tulis")) {
                String NO = ""+(Integer.parseInt(ID)+1);
                String NOL = "000";
                kdbarang.setText("AT"+NOL+NO);
                }
            }else{
                if (kbox.getSelectedItem().equals("Alat Tulis")) {
                    kdbarang.setText("AT0001");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public void inputdata(){
        try {
            st = koneksi.con.createStatement();
            String sql = "INSERT INTO tbl_barang values('"+kdbarang.getText()+"','"+namabarang.getText()+"','"+kbox.getSelectedItem()+"','"+hargaj.getText()+"','"+hargab.getText()+"','"+stok.getText()+"','"+pegawai.getText()+"','"+userid.getText()+"')";
            st.execute(sql);
            JOptionPane.showMessageDialog(null, "data berhasil dimasukan");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    public void bersih(){
        kdbarang.setText("");
        kbox.setSelectedItem("-PILIH-");
        namabarang.setText("");
        hargaj.setText("");
        hargab.setText("");
        stok.setText("");
    }
    
    private void edit(){
        try {
            koneksi.con.createStatement().executeUpdate("UPDATE tbl_barang set nama_barang='"+namabarang.getText()+"',harga_jual='"+hargaj.getText()+"',harga_beli='"+hargab.getText()+"',stok='"+stok.getText()+"' WHERE kd_barang='"+kdbarang.getText()+"'");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void pencarian(){
        if (combocari.getSelectedItem().equals("Nama_Barang")) {
        Object header[]={"ID_BARANG","NAMA","KATEGORI","HARGA_JUAL","HARGA_BELI","STOK","PEGAWAI"};
        data = new DefaultTableModel(null,header);
        String sql5 = "SELECT * FROM tbl_barang WHERE nama_barang LIKE '%"+Search.getText()+"%';";
        try {
            st = koneksi.con.createStatement();
            rs = st.executeQuery(sql5);
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }      
        }
        if (combocari.getSelectedItem().equals("ID_Barang")) {
       Object header[]={"ID_BARANG","NAMA","KATEGORI","HARGA_JUAL","HARGA_BELI","STOK","PEGAWAI"};
        data = new DefaultTableModel(null,header);
        String sql5 = "SELECT * FROM tbl_barang WHERE kd_barang LIKE '%"+Search.getText()+"%';";
        try {
            st = koneksi.con.createStatement();
            rs = st.executeQuery(sql5);
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }

    }
    
    public void pilih_laporan(){
        try {
         if (Cetakbox.getSelectedItem().equals("All")) {
           try {
            File file = new File("src\\jdbc\\LaporanGudang.jrxml");
            jasdes = JRXmlLoader.load(file);
            param.clear();
            jasrep = JasperCompileManager.compileReport(jasdes);
            jaspri = JasperFillManager.fillReport(jasrep, param, koneksi.con);
            JasperViewer.viewReport(jaspri,false);
        } catch (Exception e) {
            e.printStackTrace();
           }
       }
            
         if (Cetakbox.getSelectedItem().equals("Makanan")) {
           try {
            File file = new File("src\\jdbc\\LaporanGudangMakanan.jrxml");
            jasdes = JRXmlLoader.load(file);
            param.clear();
            jasrep = JasperCompileManager.compileReport(jasdes);
            jaspri = JasperFillManager.fillReport(jasrep, param, koneksi.con);
            JasperViewer.viewReport(jaspri,false);
        } catch (Exception e) {
            e.printStackTrace();
           }
       }
            
        if (Cetakbox.getSelectedItem().equals("Minuman")) {
           try {
            File file = new File("src\\jdbc\\LaporanGudangMinuman.jrxml");
            jasdes = JRXmlLoader.load(file);
            param.clear();
            jasrep = JasperCompileManager.compileReport(jasdes);
            jaspri = JasperFillManager.fillReport(jasrep, param, koneksi.con);
            JasperViewer.viewReport(jaspri,false);
        } catch (Exception e) {
            e.printStackTrace();
           }
       }
        
        if (Cetakbox.getSelectedItem().equals("Pakaian")) {
           try {
            File file = new File("src\\jdbc\\LaporanGudangPakaian.jrxml");
            jasdes = JRXmlLoader.load(file);
            param.clear();
            jasrep = JasperCompileManager.compileReport(jasdes);
            jaspri = JasperFillManager.fillReport(jasrep, param, koneksi.con);
            JasperViewer.viewReport(jaspri,false);
        } catch (Exception e) {
            e.printStackTrace();
           }
       }
        
        if (Cetakbox.getSelectedItem().equals("Alat Tulis")) {
           try {
            File file = new File("src\\jdbc\\LaporanGudangATK.jrxml");
            jasdes = JRXmlLoader.load(file);
            param.clear();
            jasrep = JasperCompileManager.compileReport(jasdes);
            jaspri = JasperFillManager.fillReport(jasrep, param, koneksi.con);
            JasperViewer.viewReport(jaspri,false);
        } catch (Exception e) {
            e.printStackTrace();
           }
       }
        
        
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        namabarang = new javax.swing.JTextField();
        kbox = new javax.swing.JComboBox<>();
        stok = new javax.swing.JTextField();
        kdbarang = new javax.swing.JTextField();
        hargab = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        gtable = new javax.swing.JTable();
        userid = new javax.swing.JTextField();
        simpan = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        hargaj = new javax.swing.JTextField();
        Pencarian = new javax.swing.JLabel();
        Search = new javax.swing.JTextField();
        tablebox = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        Cetakbox = new javax.swing.JComboBox<>();
        cetak = new javax.swing.JButton();
        combocari = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pegawai = new javax.swing.JTextField();
        chartna = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(102, 102, 102));

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("APLIKASI GUDANG");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(jLabel2)
                .addContainerGap(300, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 70));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Harga Beli");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Nama Barang");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Kategori");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Stok Barang");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 70, 20));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Kode Barang");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 20));
        getContentPane().add(namabarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 150, -1));

        kbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-PILIH-","Makanan","Minuman","Pakaian","Alat Tulis"}));
        kbox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                kboxItemStateChanged(evt);
            }
        });
        kbox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kboxMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                kboxMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                kboxMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                kboxMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                kboxMouseReleased(evt);
            }
        });
        kbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kboxActionPerformed(evt);
            }
        });
        getContentPane().add(kbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, -1, -1));
        getContentPane().add(stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 60, -1));

        kdbarang.setEditable(false);
        kdbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kdbarangActionPerformed(evt);
            }
        });
        getContentPane().add(kdbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 90, -1));
        getContentPane().add(hargab, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 150, -1));

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
        gtable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gtableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(gtable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 830, 250));

        userid.setEditable(false);
        userid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useridActionPerformed(evt);
            }
        });
        getContentPane().add(userid, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 90, 90, -1));

        simpan.setText("SIMPAN");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });
        getContentPane().add(simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        edit.setText("EDIT");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        getContentPane().add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, -1, -1));

        hapus.setText("HAPUS");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });
        getContentPane().add(hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("Pegawai");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 120, 60, 20));

        jButton4.setText("LOGOUT");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 80, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Harga Jual");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, 20));
        getContentPane().add(hargaj, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 150, -1));

        Pencarian.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Pencarian.setText("Pencarian :");
        getContentPane().add(Pencarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 80, 20));

        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });
        Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchKeyReleased(evt);
            }
        });
        getContentPane().add(Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, 220, -1));

        tablebox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-All-", "Makanan", "Minuman", "Pakaian", "Alat Tulis" }));
        tablebox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tableboxItemStateChanged(evt);
            }
        });
        tablebox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tableboxMouseEntered(evt);
            }
        });
        getContentPane().add(tablebox, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 300, -1, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel10.setText("Laporan");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, -1, 20));

        Cetakbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih-", "All", "Makanan", "Minuman", "Pakaian", "Alat Tulis" }));
        Cetakbox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CetakboxItemStateChanged(evt);
            }
        });
        getContentPane().add(Cetakbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, -1, -1));

        cetak.setText("Cetak");
        cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakActionPerformed(evt);
            }
        });
        getContentPane().add(cetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 170, -1, -1));

        combocari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih-", "ID_Barang", "Nama_Barang" }));
        combocari.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combocariItemStateChanged(evt);
            }
        });
        combocari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combocariActionPerformed(evt);
            }
        });
        getContentPane().add(combocari, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, -1, -1));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Created By : Hafiz Caniago");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 590, 160, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel9.setText("ID User");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 90, -1, 20));

        pegawai.setEditable(false);
        pegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pegawaiActionPerformed(evt);
            }
        });
        getContentPane().add(pegawai, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, 90, -1));

        chartna.setText("Chart");
        chartna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chartnaActionPerformed(evt);
            }
        });
        getContentPane().add(chartna, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kboxActionPerformed

    private void useridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useridActionPerformed
 
        // TODO add your handling code here:
    }//GEN-LAST:event_useridActionPerformed

    private void kdbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kdbarangActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_kdbarangActionPerformed

    private void kboxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kboxMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_kboxMouseClicked

    private void kboxMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kboxMousePressed

        // TODO add your handling code here:
    }//GEN-LAST:event_kboxMousePressed

    private void kboxMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kboxMouseReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_kboxMouseReleased

    private void kboxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kboxMouseEntered

        // TODO add your handling code here:
    }//GEN-LAST:event_kboxMouseEntered

    private void kboxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kboxMouseExited

        // TODO add your handling code here:
    }//GEN-LAST:event_kboxMouseExited

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        if (namabarang.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nama Barang Tidak Boleh Kosong");
        }
        if (hargaj.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Harga Jual tidak boleh kosong");
        }
        if (hargab.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Harga Beli Tidak Boleh Kosong");
        }
        if (stok.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Stok Tidak Boleh Kosong");
        }else{
            int pilih = JOptionPane.showConfirmDialog(null,"Apakah Data Sudah Benar?"+"Simpan?","Simpan Data",JOptionPane.YES_NO_OPTION);
            if (pilih == JOptionPane.YES_OPTION) {
                inputdata();
                load_data();
                bersih();
                AutoID();
            }
            else if(pilih == JOptionPane.NO_OPTION){
                bersih();
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_simpanActionPerformed

    private void gtableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gtableMouseClicked
 int cok = gtable.getSelectedRow();
 String a = gtable.getValueAt(cok, 0).toString();
 String b = gtable.getValueAt(cok, 1).toString();
 String c = gtable.getValueAt(cok, 2).toString();
 String d = gtable.getValueAt(cok, 3).toString();
 String e = gtable.getValueAt(cok, 4).toString();
 String f = gtable.getValueAt(cok, 5).toString();
 String g = gtable.getValueAt(cok, 6).toString();
 kdbarang.setText(a);
 kbox.setSelectedItem(c);
 namabarang.setText(b);
 hargaj.setText(d);
 hargab.setText(e);
 stok.setText(f);
 userid.setText(g);
 
        // TODO add your handling code here:
    }//GEN-LAST:event_gtableMouseClicked

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
     int pilih = JOptionPane.showConfirmDialog(null, "apakah anda yakin ingin menghapus?","hapus data",JOptionPane.YES_NO_OPTION);
        if (pilih == JOptionPane.YES_OPTION) {
      try {
          koneksi.con.createStatement().executeUpdate("DELETE FROM tbl_barang WHERE kd_barang='"+kdbarang.getText()+"'");
          JOptionPane.showMessageDialog(null, "Hapus Data Berhasil");
      } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Gagal");
      }
        }
        else{
            System.exit(0);
        }
        load_data();
        bersih();
        AutoID();
        // TODO add your handling code here:
    }//GEN-LAST:event_hapusActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
  int pilih = JOptionPane.showConfirmDialog(null, "apakah anda yakin ingin merubah?","ubah data",JOptionPane.YES_NO_OPTION);
        if (pilih == JOptionPane.YES_OPTION) {
      try {
          edit();
          JOptionPane.showMessageDialog(null, "Ubah Data Berhasil");
      } catch (Exception ex) {
          JOptionPane.showMessageDialog(null, "Gagal");
      }
        }
        else{
            bersih();
        }
        load_data();
        bersih();
        AutoID();
        // TODO add your handling code here:
    }//GEN-LAST:event_editActionPerformed

    private void tableboxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableboxMouseEntered

        // TODO add your handling code here:
    }//GEN-LAST:event_tableboxMouseEntered

    private void tableboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tableboxItemStateChanged
load_data();
        // TODO add your handling code here:
    }//GEN-LAST:event_tableboxItemStateChanged

    private void kboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_kboxItemStateChanged
AutoID();
        // TODO add your handling code here:
    }//GEN-LAST:event_kboxItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Login a = new Login();
        a.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void CetakboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CetakboxItemStateChanged

        // TODO add your handling code here:
    }//GEN-LAST:event_CetakboxItemStateChanged

    private void cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakActionPerformed
pilih_laporan();
        // TODO add your handling code here:
    }//GEN-LAST:event_cetakActionPerformed

    private void SearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyReleased
pencarian();
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchKeyReleased

    private void combocariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combocariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combocariActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_SearchActionPerformed

    private void combocariItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combocariItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_combocariItemStateChanged

    private void pegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pegawaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pegawaiActionPerformed

    private void chartnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chartnaActionPerformed
String sql = "SELECT tbl_barang.kd_barang, tbl_barang.nama_barang, tbl_barang.kategori, tbl_barang.harga_jual, tbl_barang.harga_beli, tbl_barang.stok, tbl_barang.NAMA_USER FROM tbl_barang;";
        try {
            st = koneksi.con.createStatement();
            rs = st.executeQuery(sql);
            DefaultCategoryDataset objaa = new DefaultCategoryDataset();
            while (rs.next()) {                
                objaa.setValue(Integer.parseInt(rs.getString("stok")), rs.getString("kategori"), rs.getString("nama_barang"));
            }
            JFreeChart chart = ChartFactory.createBarChart3D("Data Barang", null, null, objaa);
CategoryPlot obja = chart.getCategoryPlot();
obja.setRangeGridlinePaint(Color.BLACK);
ChartFrame frame = new ChartFrame("Grafik Barang", chart);
frame.setSize(400,400);
frame.setVisible(true);
        } catch (Exception e) {
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_chartnaActionPerformed

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
            java.util.logging.Logger.getLogger(Fbarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fbarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fbarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fbarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Fbarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Cetakbox;
    private javax.swing.JLabel Pencarian;
    private javax.swing.JTextField Search;
    private javax.swing.JButton cetak;
    private javax.swing.JButton chartna;
    private javax.swing.JComboBox<String> combocari;
    private javax.swing.JButton edit;
    private javax.swing.JTable gtable;
    private javax.swing.JButton hapus;
    private javax.swing.JTextField hargab;
    private javax.swing.JTextField hargaj;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> kbox;
    private javax.swing.JTextField kdbarang;
    private javax.swing.JTextField namabarang;
    private javax.swing.JTextField pegawai;
    private javax.swing.JButton simpan;
    private javax.swing.JTextField stok;
    private javax.swing.JComboBox<String> tablebox;
    private javax.swing.JTextField userid;
    // End of variables declaration//GEN-END:variables
}
