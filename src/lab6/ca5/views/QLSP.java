/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6.ca5.views;

import java.util.ArrayList;
import lab6.ca5.models.DanhMuc;
import lab6.ca5.models.SanPham;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    ArrayList<SanPham> listSP;
    ArrayList<DanhMuc> lisDanhMuc;
    Connection conn;
    DanhMuc danhMucDangChon;

    /**
     * Creates new form QLSP
     */
    public QLSP() {
        initComponents();
        
        this.listSP = new ArrayList<>();
        this.lisDanhMuc = new ArrayList<>();

        this.conn = this.initConnection();
        this.lisDanhMuc = this.fetchListDanhMuc();
        this.loadDanhMuc(this.lisDanhMuc);
        
        DanhMuc danhMucDuocChon = this.timDanhMucTheoTen(
            this.cbDanhMucFilter.getSelectedItem().toString()
        );
        
        this.danhMucDangChon = danhMucDuocChon;
        
        this.listSP = this.fetchListSanPham(danhMucDuocChon.getId());
        
        this.renderJTable(this.listSP);
    }
    
    protected Connection initConnection()
    {
        Connection conn = null;
        try {
            // Bước 1: Load Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            // Bước 2: Khai báo thông tin db
            // url = "<protocol>://<dbHost>:<dbPort>;databaseName=<dbName>";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=lab56_qlsp",
                dbUsername = "sa",
                dbPassword = "Aa@123456";
            
            // Bước 3: Tạo Connection
            conn = DriverManager.getConnection(url, dbUsername, dbPassword);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return conn;
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
        ArrayList<SanPham> data = new ArrayList<SanPham>();
        
        String query = "SELECT * FROM san_pham WHERE danh_muc_id = ?";
        
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);

            ps.setInt(1, danhMucId);

            ResultSet rs = ps.executeQuery();

            while ( rs.next() ) {
                int id = rs.getInt("id");
                int soLuong = rs.getInt("so_luong");
                
                String tenSP = rs.getString("ten");
                String maSP = rs.getString("ma_sp");

                java.util.Date ngayNhap = rs.getDate("ngay_nhap");
                
                SanPham sp = new SanPham(id, soLuong, danhMucId, tenSP, maSP, ngayNhap);
                
                data.add(sp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return data;
    }
    
    protected DanhMuc timDanhMucTheoTen(String ten)
    {
        DanhMuc danhMuc = null;
        for (int i = 0; i < this.lisDanhMuc.size(); i++) {
            if ( this.lisDanhMuc.get(i).getTen().equals(ten) ) {
                danhMuc = this.lisDanhMuc.get(i);
                break;
            }
        }
    
        return danhMuc;
    }

    protected void loadDanhMuc(ArrayList<DanhMuc> data)
    {
        for (int i = 0; i < data.size(); i++) {
            this.cbDanhMucFilter.addItem( data.get(i).getTen() );
            this.cbDanhMuc.addItem( data.get(i).getTen() );
        }
    }
    
    protected void renderJTable(ArrayList<SanPham> data)
    {
        DefaultTableModel model = (DefaultTableModel) this.tblSanPham.getModel();
        model.setRowCount(0);
        for (int i = 0; i < data.size(); i++) {
            SanPham sp = data.get(i);
            
            model.addRow(new Object[] {
                sp.getTenSP(),
                sp.getMaSP(),
                sp.getSoLuong(),
                sp.getNgayNhap(),
            });
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

        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        cbDanhMuc = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNgayNhap = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        cbDanhMucFilter = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel3.setText("Tên SP");

        jLabel4.setText("Mã SP");

        jLabel5.setText("Danh mục");

        jLabel6.setText("Số Lượng");

        jLabel7.setText("Ngày Nhập");

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClear))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(28, 28, 28)
                                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(33, 33, 33)
                                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(41, 41, 41)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtNgayNhap))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
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

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên SP", "Mã SP", "Số Lượng", "Ngày Nhập"
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
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        jLabel1.setText("Danh mục");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbDanhMucFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbDanhMucFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(191, 191, 191))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbDanhMucFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDanhMucFilterActionPerformed
        String tenDanhMuc = this.cbDanhMucFilter.getSelectedItem().toString();
        DanhMuc danhMucDuocChon = this.timDanhMucTheoTen( tenDanhMuc );

        this.listSP = this.fetchListSanPham(danhMucDuocChon.getId());

        this.danhMucDangChon = danhMucDuocChon;
        this.renderJTable(this.listSP);
    }//GEN-LAST:event_cbDanhMucFilterActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String tenSP = this.txtTenSP.getText();
        String maSP = this.txtMaSP.getText();
        String soLuongStr = this.txtSoLuong.getText();
        String ngayNhapStr = this.txtNgayNhap.getText();

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

            if (soLuong < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ!");
                return ;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ!");
            e.printStackTrace();
            return ;
        }

        java.util.Date ngayNhap = null;
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        try {
            ngayNhap = sdf.parse(ngayNhapStr);
        } catch (ParseException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ngày nhập không hợp lệ!");
        }

        String query = "INSERT INTO san_pham(ten, ma_sp, so_luong, ngay_nhap, danh_muc_id)"
            + " OUTPUT INSERTED.ID VALUES(?, ?, ?, ?, ?)";

        String tenDanhMuc = this.cbDanhMuc.getSelectedItem().toString();
        DanhMuc danhMuc = this.timDanhMucTheoTen(tenDanhMuc);
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);

            ps.setString(1, tenSP);
            ps.setString(2, maSP);
            ps.setInt(3, soLuong);

            // Tham số truyền vào setDate phải là object của class java.sql.Date
            java.sql.Date ngayNhapSql = new java.sql.Date(ngayNhap.getTime());
            ps.setDate(4, ngayNhapSql);

            ps.setInt(5, danhMuc.getId());

            ps.execute();
            JOptionPane.showMessageDialog(this, "Thêm thành công!");

            ResultSet rs = ps.getResultSet();
            rs.next();
            int id = rs.getInt(1);

            this.listSP.add( new SanPham(id, soLuong, danhMuc.getId(), tenSP, maSP, ngayNhapSql) );

            this.renderJTable( this.listSP );
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int row = this.tblSanPham.getSelectedRow();

        if (row == -1) {
            return ;
        }
        
        String tenSP = this.tblSanPham.getValueAt(row, 0).toString();
        String maSP = this.tblSanPham.getValueAt(row, 1).toString();
        String soLuongStr = this.tblSanPham.getValueAt(row, 2).toString();
        String ngayNhapStr = this.tblSanPham.getValueAt(row, 3).toString();
        
        this.txtTenSP.setText(tenSP);
        this.txtMaSP.setText(maSP);
        this.txtNgayNhap.setText(ngayNhapStr);
        this.txtSoLuong.setText(soLuongStr);
        this.cbDanhMuc.setSelectedIndex( this.cbDanhMucFilter.getSelectedIndex() );
        
        this.txtMaSP.setEditable(false);
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int row = this.tblSanPham.getSelectedRow();

        if (row == -1) {
            return ;
        }

        String maSP = this.tblSanPham.getValueAt(row, 1).toString();
        String query = "DELETE FROM san_pham WHERE ma_sp = ?";

        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setString(1, maSP);
            ps.execute();

            JOptionPane.showMessageDialog(this, "Xóa thành công!");
            
            this.listSP = this.fetchListSanPham(this.danhMucDangChon.getId());
            this.renderJTable(this.listSP);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int row = this.tblSanPham.getSelectedRow();
        
        if (row == -1) {
            return ;
        }
        
        String tenSP = this.txtTenSP.getText();
        String maSP = this.txtMaSP.getText();
        String soLuongStr = this.txtSoLuong.getText();
        String ngayNhapStr = this.txtNgayNhap.getText();

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

            if (soLuong < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ!");
                return ;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ!");
            e.printStackTrace();
            return ;
        }

        java.util.Date ngayNhap = null;
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        try {
            ngayNhap = sdf.parse(ngayNhapStr);
        } catch (ParseException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ngày nhập không hợp lệ!");
        }
        
        String tenDanhMuc = this.cbDanhMuc.getSelectedItem().toString();
        DanhMuc danhMuc = this.timDanhMucTheoTen(tenDanhMuc);
        
        String query = "UPDATE san_pham SET ten = ?, ngay_nhap = ?, so_luong = ? WHERE ma_sp = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            
            ps.setString(1, tenSP);
            ps.setDate(2, new java.sql.Date(ngayNhap.getTime()) );
            ps.setInt(3, soLuong);
            ps.setString(4, maSP);

            ps.execute();
            JOptionPane.showMessageDialog(this, "Update thành công!");
            
            this.listSP = this.fetchListSanPham(this.danhMucDangChon.getId());
            this.renderJTable(this.listSP);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

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
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtNgayNhap;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
