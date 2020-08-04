/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6.ca3.views;

import java.util.ArrayList;
import lab6.ca3.models.DanhMuc;
import lab6.ca3.models.SanPham;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tiennh
 */
public class QLSP extends javax.swing.JFrame {
    protected ArrayList<DanhMuc> listDanhMuc;
    protected ArrayList<SanPham> listSanPham;
    protected Connection conn;

    /**
     * Creates new form QLSP
     */
    public QLSP() {
        initComponents();
        
        this.listDanhMuc = new ArrayList<>();
        this.listSanPham = new ArrayList<>();
        
        // Tạo connection vào db
        this.conn = this.initConnection();

        // Query lấy ra toàn bộ danh mục
        this.listDanhMuc = this.fetchListDanhMuc();
        this.loadDanhMuc(this.listDanhMuc);
        
        String danhMucDuocChon = this.cbDanhMucFilter.getSelectedItem().toString();
        
        DanhMuc danhMuc = this.timDanhMucTheoTen(danhMucDuocChon);
        
        this.listSanPham = this.fetchListSanPham(danhMuc.getId());
        
        this.renderJTable(this.listSanPham);
    }
    
    private void renderJTable(ArrayList<SanPham> data) {
        DefaultTableModel model = (DefaultTableModel) this.tblListSP.getModel();
        model.setRowCount(0);
        for (int i = 0; i < data.size(); i++) {
            SanPham sp = data.get(i);
            model.addRow(new Object[] {
                sp.getMaSP(),
                sp.getTenSP(),
                sp.getSoLuong(),
                sp.getNgapNhap()
            });
        }
    }

    protected ArrayList<DanhMuc> fetchListDanhMuc()
    {
        ArrayList<DanhMuc> data = new ArrayList<DanhMuc>();
        
        String query = "SELECT * FROM danh_muc";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                
                DanhMuc danhMuc = new DanhMuc(id, ten);
                
                data.add(danhMuc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return data;
    }
    
    protected ArrayList<SanPham> fetchListSanPham(int danhMucId)
    {
        ArrayList<SanPham> data = new ArrayList<>();
        
        String query = "SELECT * FROM san_pham WHERE danh_muc_id = ?";

        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setInt(1, danhMucId);

            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String tenSP = rs.getString("ten");
                String maSP = rs.getString("ma_sp");
                int soLuong = rs.getInt("so_luong");
                Date ngayNhap = rs.getDate("ngay_nhap");
                
                SanPham sp = new SanPham(id, soLuong, danhMucId, tenSP, maSP, ngayNhap);

                data.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QLSP.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }
    
    protected Connection initConnection()
    {
        Connection conn = null;
        
        try {
            // Bước 1: Load Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            // Bước 2: Khai báo thông tin db
            // url: "<protocol>://<dbHost>:<dbPort>;databaseName=abc"
            String url = "jdbc:sqlserver://localhost:1433;databaseName=lab56_qlsp",
                dbUsername = "sa",
                dbPassword= "Aa@123456";
            
            // Bước 3: Tạo connection
            conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            JOptionPane.showMessageDialog(this, "Kết nối DB thành công!");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return conn;
    }
    
    protected void loadDanhMuc(ArrayList<DanhMuc> listDanhMuc)
    {
        for (int i = 0; i < listDanhMuc.size(); i++) {
            DanhMuc danhMuc = listDanhMuc.get(i);
            
            this.cbDanhMuc.addItem(danhMuc.getTen());
            this.cbDanhMucFilter.addItem(danhMuc.getTen());
        }
    }

    protected DanhMuc timDanhMucTheoTen(String ten)
    {
        DanhMuc danhMuc = null;
        
        for (int i = 0; i < this.listDanhMuc.size(); i++) {
            if (this.listDanhMuc.get(i).getTen().equals(ten)) {
                danhMuc = this.listDanhMuc.get(i);
                break;
            }
        }
        
        return danhMuc;
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        txtMaSP = new javax.swing.JTextField();
        txtNgayNhap = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        cbDanhMuc = new javax.swing.JComboBox<>();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        cbDanhMucFilter = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListSP = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Tên SP");

        jLabel3.setText("Mã SP");

        jLabel4.setText("Danh Mục");

        jLabel6.setText("Số Lượng");

        jLabel7.setText("Ngày Nhập");

        btnDelete.setText("Delete");

        btnClear.setText("Clear");

        btnUpdate.setText("Update");

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbDanhMuc, 0, 90, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                    .addComponent(txtMaSP))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                        .addComponent(txtSoLuong))
                    .addComponent(btnClear))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnClear))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cbDanhMucFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDanhMucFilterActionPerformed(evt);
            }
        });

        jLabel2.setText("Danh Mục");

        tblListSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Số Lượng", "Ngày Nhập"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListSP);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cbDanhMucFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbDanhMucFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        String tenSP = this.txtTenSP.getText();
        String maSP = this.txtMaSP.getText();
        String soLuongStr = this.txtSoLuong.getText();
        String ngayNhapStr = this.txtNgayNhap.getText();
        String tenDanhMuc = this.cbDanhMuc.getSelectedItem().toString();
        DanhMuc danhMuc = this.timDanhMucTheoTen(tenDanhMuc);

        if (
            tenSP.length() == 0 ||
            maSP.length() == 0 ||
            soLuongStr.length() == 0 ||
            ngayNhapStr.length() == 0
        ) {
            JOptionPane.showMessageDialog(this, "Không được để trống!");
            return ;
        }
        
        int soLuong = 0;
        try {
            soLuong = Integer.parseInt(soLuongStr);
            
            if (soLuong <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0!");
                return;
            }
        } catch ( NumberFormatException e ) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên!");
            return;
        }
        
        Date ngayNhap = null;
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        try {
            ngayNhap = sdf.parse(ngayNhapStr);
        } catch (ParseException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ngày nhập không hợp lệ!");
            return;
        }

        String query = "INSERT INTO san_pham(ten, ma_sp, ngay_nhap, so_luong, danh_muc_id)"
            + " OUTPUT INSERTED.ID "
            + " VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            
            ps.setString(1, tenSP);
            ps.setString(2, maSP);
            ps.setDate(3, new java.sql.Date(ngayNhap.getTime()));
            ps.setInt(4, soLuong);
            ps.setInt(5, danhMuc.getId());
            
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            rs.next();
            int id = rs.getInt(1);

            SanPham sp = new SanPham(id, soLuong, danhMuc.getId(), tenSP,
                maSP, new java.sql.Date(ngayNhap.getTime()));

            this.listSanPham.add(sp);
            this.renderJTable(this.listSanPham);

            JOptionPane.showMessageDialog(this, "Thêm thành công!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void cbDanhMucFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDanhMucFilterActionPerformed
        // TODO add your handling code here:
        String tenDanhMuc = this.cbDanhMucFilter.getSelectedItem().toString();
        DanhMuc danhMuc = this.timDanhMucTheoTen(tenDanhMuc);
        
        ArrayList<SanPham> data = this.fetchListSanPham(danhMuc.getId());
        this.renderJTable(data);
    }//GEN-LAST:event_cbDanhMucFilterActionPerformed

    private void tblListSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListSPMouseClicked
        int row = this.tblListSP.getSelectedRow();
        
        if (row == -1) {
            return ;
        }
        
        String maSP = this.tblListSP.getValueAt(row, 0).toString();
        String tenSP = this.tblListSP.getValueAt(row, 1).toString();
        String soLuongStr = this.tblListSP.getValueAt(row, 2).toString();
        String ngayNhapStr = this.tblListSP.getValueAt(row, 3).toString();
        int danhMucIndex = this.cbDanhMuc.getSelectedIndex();
        
        this.txtTenSP.setText(tenSP);
        this.txtMaSP.setText(maSP);
        this.txtSoLuong.setText(soLuongStr);
        this.txtNgayNhap.setText(ngayNhapStr);
        this.cbDanhMuc.setSelectedIndex(danhMucIndex);
    }//GEN-LAST:event_tblListSPMouseClicked

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
            java.util.logging.Logger.getLogger(QLSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLSP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbDanhMuc;
    private javax.swing.JComboBox<String> cbDanhMucFilter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblListSP;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtNgayNhap;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
