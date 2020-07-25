/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_lab_5.ca_3;

import java.util.Date;

/**
 *
 * @author tiennh
 */
public class SanPham {
    protected int id, soLuong;
    protected String maSP, tenSP;
    protected Date ngayNhap;

    public SanPham() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public SanPham(int id, int soLuong, String maSP, String tenSP, Date ngayNhap) {
        this.id = id;
        this.soLuong = soLuong;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.ngayNhap = ngayNhap;
    }

    public int getId() {
        return id;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }
    
    
}
