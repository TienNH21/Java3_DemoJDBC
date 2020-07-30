/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6.ca2.models;

import java.util.Date;

/**
 *
 * @author tiennh
 */
public class SanPham {
    protected int id, danhMucId, soLuong;
    protected String tenSP, maSP;
    protected Date ngayNhap;

    public SanPham(int id, int danhMucId, int soLuong, String tenSP, String maSP, Date ngayNhap) {
        this.id = id;
        this.danhMucId = danhMucId;
        this.soLuong = soLuong;
        this.tenSP = tenSP;
        this.maSP = maSP;
        this.ngayNhap = ngayNhap;
    }

    public SanPham() {
    }

    public int getId() {
        return id;
    }

    public int getDanhMucId() {
        return danhMucId;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getTenSP() {
        return tenSP;
    }

    public String getMaSP() {
        return maSP;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDanhMucId(int danhMucId) {
        this.danhMucId = danhMucId;
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
    
    
}
