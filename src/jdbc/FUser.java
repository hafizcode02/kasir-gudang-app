/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Hafiz Caniago
 */
public class FUser extends javax.swing.JFrame {
DefaultTableModel data;
    /**
     * Creates new form FUser
     */
    Statement st;
    ResultSet rs;
    Koneksi koneksi;
    JasperReport jasrep;
    JasperPrint jaspri;
    JasperDesign jasdep;
    Map Param = new HashMap();
    public FUser() {
        koneksi = new Koneksi();
        initComponents();
        load_data();
        IDotomatis();
        level();
        setLocationRelativeTo(this);
    }
    
        private String validatePath(String invalidPath){
        String validPath;
        validPath = invalidPath.replace('\\','/');
        return validPath;
    }
        
    private void load_data(){
    Object header[]={"ID USER","LEVEL","NAMA USER","JENIS KELAMIN","TANGGAL LAHIR","NO HANDPHONE","USERNAME","PASSWORD"};
    data = new DefaultTableModel(null,header);
    
    String sql = "SELECT tbl_user.ID_USER, tbl_level.LEVEL, tbl_user.NAMA_USER, tbl_user.JK, tbl_user.TANGGAL_LAHIR, tbl_user.NOPE, tbl_user.USERNAME,"+"tbl_user.PASSWORD FROM tbl_user INNER JOIN tbl_level ON tbl_user.ID_LEVEL=tbl_level.ID_LEVEL;";
    
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
            String k8 = rs.getString(8);
            String k[]={k1,k2,k3,k4,k5,k6,k7,k8};
            data.addRow(k);
        }
        TUser.setModel(data);
        
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}
    private void loaddata1(){
        koneksi = new Koneksi();
        st =null;
        String path = validatePath(textpath.getText().toString());
        final String delimiter = ";";
        final String query = "LOAD DATA INFILE'"+path+"'INTO TABLE tbl_user FIELDS TERMINATED BY '"+delimiter+"'"+ "LINES TERMINATED BY '\n' (ID_USER,ID_LEVEL,NAMA_USER,JK,TANGGAL_LAHIR,NOPE,USERNAME,PASSWORD);";
        try {
            st = koneksi.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "sukses");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    private void IDotomatis(){
        try {
            st = koneksi.con.createStatement();
            String sql = "SELECT * FROM tbl_user order by ID_USER desc";
            rs = st.executeQuery(sql);
            if(rs.next()){
                String ID = rs.getString("ID_USER").substring(4);
                String NO = ""+(Integer.parseInt(ID)+1);
                String Nol="";
                if(NO.length()==1){
                    Nol="000";
                }
                else if(NO.length()==2){
                    Nol="00";
                }
                iduser.setText("USER"+Nol+NO);
            }
            else{
                iduser.setText("USER0001");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void level(){
        try {
            st = koneksi.con.createStatement();
            String query = "SELECT * FROM tbl_level";
            rs = st.executeQuery(query);
            while(rs.next()){
                clevel.addItem(rs.getString("LEVEL"));
            }
            rs.close();
        } catch (SQLException e) {
        }
    }
    
    public void input_data(){
        try {
            String jk="";
            if(jkl.isSelected()){
                jk=jkl.getText();
            }else{
                jk=jkp.getText();
            }
            
            SimpleDateFormat dformat = new  SimpleDateFormat("yyyy-MM-dd");
            String date = dformat.format(jDateChooser1.getDate());
            
            
            st = koneksi.con.createStatement();
            String id = "";
            String query = "SELECT ID_LEVEL FROM tbl_level WHERE LEVEL='"+clevel.getSelectedItem()+"'";
            rs = st.executeQuery(query);
            if(rs.next()){
                id = rs.getString("ID_LEVEL");
            }
            
            String sql = "INSERT INTO tbl_user values('"+iduser.getText()
                    +"','"+id
                    +"','"+nama.getText()
                    +"','"+jk
                    +"','"+date
                    +"','"+nope.getText()
                    +"','"+us.getText()
                    +"','"+pw.getText()
                    +"')";
            st.execute(sql);
            JOptionPane.showMessageDialog(null, "Data Barang Berhasil Dimasukan");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void bersih(){
        iduser.setText("");
        nama.setText("");
        nope.setText("");
        us.setText("");
        pw.setText("");
        jDateChooser1.setDate(null);
        clevel.setSelectedItem("-PILIH-");
        jkl.setSelected(false);
        jkp.setSelected(false);
    }
    
    private void edit(){
        try {
            String jk="";
            if(jkl.isSelected()){
                jk=jkl.getText();
            }else{
                jk=jkp.getText();
            }
            
            SimpleDateFormat dformat = new  SimpleDateFormat("yyyy-MM-dd");
            String date = dformat.format(jDateChooser1.getDate());
            
            
            st = koneksi.con.createStatement();
            String id="";
            String ab = "SELECT ID_LEVEL FROM tbl_level WHERE LEVEL='"+clevel.getSelectedItem()+"'";
            rs = st.executeQuery(ab);
            if (rs.next()) {
                id = rs.getString("ID_LEVEL");
            }
            koneksi.con.createStatement().executeUpdate("UPDATE tbl_user SET ID_LEVEL='"+id+"',NAMA_USER='"+nama.getText()+"',JK='"+jk+"',TANGGAL_LAHIR='"+date+"',NOPE='"+nope.getText()+"',USERNAME='"+us.getText()+"',PASSWORD='"+pw.getText()+"' WHERE ID_USER='"+iduser.getText()+"'");
           
        } catch (Exception e) {
        }
    }
    
    private void browse_csv(){
        JFileChooser jfc = new JFileChooser();
FileFilter filefilter = new FileFilter(){
    @Override
    public boolean accept(File f) {
        if(f.getName().toLowerCase().endsWith(".csv")|| f.isDirectory()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "*.csv";
    }
    
};
jfc.addChoosableFileFilter(filefilter);
jfc.setMultiSelectionEnabled(false);
jfc.showOpenDialog(this);
String path = jfc.getSelectedFile().getAbsolutePath();
textpath.setText(path);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.  
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Gjk = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        TUser = new javax.swing.JTable();
        pw = new javax.swing.JPasswordField();
        us = new javax.swing.JTextField();
        nope = new javax.swing.JTextField();
        jkp = new javax.swing.JRadioButton();
        jkl = new javax.swing.JRadioButton();
        nama = new javax.swing.JTextField();
        clevel = new javax.swing.JComboBox<>();
        iduser = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        simpan = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        report = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        textpath = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TUser.setModel(new javax.swing.table.DefaultTableModel(
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
        TUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TUserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TUser);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 770, 290));

        pw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pwActionPerformed(evt);
            }
        });
        getContentPane().add(pw, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 160, -1));

        us.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usActionPerformed(evt);
            }
        });
        getContentPane().add(us, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, 160, -1));

        nope.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nopeActionPerformed(evt);
            }
        });
        nope.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nopeKeyTyped(evt);
            }
        });
        getContentPane().add(nope, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 160, -1));

        Gjk.add(jkp);
        jkp.setText("P");
        jkp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jkpActionPerformed(evt);
            }
        });
        getContentPane().add(jkp, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, -1, -1));

        Gjk.add(jkl);
        jkl.setText("L");
        jkl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jklActionPerformed(evt);
            }
        });
        getContentPane().add(jkl, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, -1, -1));

        nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaActionPerformed(evt);
            }
        });
        nama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                namaKeyTyped(evt);
            }
        });
        getContentPane().add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 160, -1));

        clevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-PILIH-" }));
        clevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clevelActionPerformed(evt);
            }
        });
        getContentPane().add(clevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, -1, -1));

        iduser.setEditable(false);
        iduser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iduserActionPerformed(evt);
            }
        });
        getContentPane().add(iduser, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 160, -1));

        jLabel9.setText("PASSWORD");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, -1, 20));

        jLabel8.setText("USERNAME");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, -1, 20));

        jLabel7.setText("NO HP");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, 20));

        jLabel6.setText("JENIS KELAMIN");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, 20));

        jLabel5.setText("NAMA USER");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, 20));

        jLabel4.setText("LEVEL");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, 20));

        jLabel3.setText("ID USER");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, 20));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("FORM INPUT DATA");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 150, 30));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("DATA USER");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 70, 90, 30));

        simpan.setText("SIMPAN");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });
        getContentPane().add(simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, -1));

        edit.setText("EDIT");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        getContentPane().add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, 60, -1));

        hapus.setText("HAPUS");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });
        getContentPane().add(hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 70, -1));

        jLabel11.setText("TANGGAL LAHIR");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, 20));
        getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, -1, -1));

        report.setText("REPORT");
        report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportActionPerformed(evt);
            }
        });
        getContentPane().add(report, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, -1, -1));
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 0, 40, 380));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("DATA USER APLIKASI KASIR");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 340, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 0, 1240, 60));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 320, 20));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 430, 390, 20));

        jLabel12.setText("IMPORT CSV");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 400, 90, 20));

        textpath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textpathActionPerformed(evt);
            }
        });
        getContentPane().add(textpath, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 400, 180, -1));

        jButton1.setText("IMPORT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 440, -1, -1));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 320, 20));

        jButton2.setText("Browse");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 400, -1, -1));

        jMenu1.setText("Menu");

        jMenuItem1.setText("Logout");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jkpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jkpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jkpActionPerformed

    private void pwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pwActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_pwActionPerformed

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
        IDotomatis();
        // TODO add your handling code here:
    }//GEN-LAST:event_editActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
  int pilih = JOptionPane.showConfirmDialog(null, "apakah anda yakin ingin menghapus?","hapus data",JOptionPane.YES_NO_OPTION);
        if (pilih == JOptionPane.YES_OPTION) {
      try {
          koneksi.con.createStatement().executeUpdate("DELETE FROM tbl_user WHERE ID_USER='"+iduser.getText()+"'");
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
        IDotomatis();
// TODO add your handling code here:
    }//GEN-LAST:event_hapusActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        if(nama.getText().equals("")){
    JOptionPane.showMessageDialog(null, "nama tidak boleh kosong");
        }
         if(pw.getText().equals("")){
    JOptionPane.showMessageDialog(null, "password tidak boleh kosong");
        }
             if(nope.getText().equals("")){
    JOptionPane.showMessageDialog(null, "no hp tidak boleh kosong");
        }
             if(us.getText().equals("")){
    JOptionPane.showMessageDialog(null, "username tidak boleh kosong");
        }else{
        int pilih = JOptionPane.showConfirmDialog(null,"Apakah Data Sudah Benar?"+"Simpan?","Simpan Data",JOptionPane.YES_NO_OPTION);
        if (pilih == JOptionPane.YES_OPTION) {
          input_data();
          load_data();
          bersih();
          IDotomatis();
          
        }
        else if(pilih == JOptionPane.NO_OPTION){
           System.exit(0);
        }
             }
        // TODO add your handling code here:
    }//GEN-LAST:event_simpanActionPerformed

    private void TUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TUserMouseClicked

try{
int cok = TUser.getSelectedRow();
String a = TUser.getValueAt(cok, 0).toString();
String b = TUser.getValueAt(cok, 1).toString();
String c = TUser.getValueAt(cok, 2).toString();
String d = TUser.getValueAt(cok, 3).toString();
Date e = new SimpleDateFormat("yyyy-MM-dd").parse((String)data.getValueAt(cok, 4));
String f = TUser.getValueAt(cok, 5).toString();
String g = TUser.getValueAt(cok, 6).toString();
String h = TUser.getValueAt(cok, 7).toString();

iduser.setText(a);
clevel.setSelectedItem(b);
nama.setText(c);
jDateChooser1.setDate(e);
nope.setText(f);
us.setText(g);
pw.setText(h);

        if ("L".equals(d)) {
            jkl.setSelected(true);
            jkp.setSelected(false);
        }
        else{
            jkl.setSelected(false);
            jkp.setSelected(true);
        }
        simpan.setEnabled(true);
        edit.setEnabled(true);
        hapus.setEnabled(true);
}
catch(ParseException e){
    
}

        // TODO add your handling code here:
    }//GEN-LAST:event_TUserMouseClicked

    private void iduserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iduserActionPerformed
IDotomatis();
        // TODO add your handling code here:
    }//GEN-LAST:event_iduserActionPerformed

    private void nopeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nopeActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_nopeActionPerformed

    private void nopeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nopeKeyTyped
        char enter = evt.getKeyChar();
    if((Character.isAlphabetic(enter))){
    evt.consume();
    JOptionPane.showMessageDialog(null, "data tidak valid");   
}

        // TODO add your handling code here:
    }//GEN-LAST:event_nopeKeyTyped

    private void clevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clevelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clevelActionPerformed

    private void namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_namaActionPerformed

    private void jklActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jklActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jklActionPerformed

    private void usActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_usActionPerformed

    private void namaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namaKeyTyped
        char enter = evt.getKeyChar();
    if((Character.isDigit(enter))){
    evt.consume();
    JOptionPane.showMessageDialog(null, "data tidak valid");   
}
        // TODO add your handling code here:
    }//GEN-LAST:event_namaKeyTyped

    private void reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportActionPerformed

        try {
            File file = new File("src\\jdbc\\LaporanFuser.jrxml");
            jasdep = JRXmlLoader.load(file);
            Param.clear();
            jasrep = JasperCompileManager.compileReport(jasdep);
            jaspri = JasperFillManager.fillReport(jasrep, Param, koneksi.con);
            JasperViewer.viewReport(jaspri,false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_reportActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
Login a = new Login();
a.setVisible(true);
dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
browse_csv();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void textpathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textpathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textpathActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         if (textpath.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "harap isi");
        }else{
            loaddata1();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Gjk;
    private javax.swing.JTable TUser;
    private javax.swing.JComboBox<String> clevel;
    private javax.swing.JButton edit;
    private javax.swing.JButton hapus;
    private javax.swing.JTextField iduser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JRadioButton jkl;
    private javax.swing.JRadioButton jkp;
    private javax.swing.JTextField nama;
    private javax.swing.JTextField nope;
    private javax.swing.JPasswordField pw;
    private javax.swing.JButton report;
    private javax.swing.JButton simpan;
    private javax.swing.JTextField textpath;
    private javax.swing.JTextField us;
    // End of variables declaration//GEN-END:variables
}
