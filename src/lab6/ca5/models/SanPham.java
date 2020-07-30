/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6.ca5.models;

import java.util.Date;

/**
 *
 * @author tiennh
 */
public class SanPham {
    protected int id, soLuong, danhMucId;
    protected String tenSP, maSP;
    protected Date ngayNhap;

    public SanPham() {
    }

    public SanPham(int id, int soLuong, int danhMucId, String tenSP, String maSP, Date ngayNhap) {
        this.id = id;
        this.soLuong = soLuong;
        this.danhMucId = danhMucId;
        this.tenSP = tenSP;
        this.maSP = maSP;
        this.ngayNhap = ngayNhap;
    }

    public int getId() {
        return id;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getDanhMucId() {
        return danhMucId;
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

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setDanhMucId(int danhMucId) {
        this.danhMucId = danhMucId;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
}
