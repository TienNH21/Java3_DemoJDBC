/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de_luyen_tap.models;

import java.sql.Date;

/**
 *
 * @author tiennh
 */
public class Sach {
    protected int id, soLuong, danhMucId;
    protected String tenSach, maSach;
    protected Date ngayNhap;

    public Sach() {
    }

    public Sach(int id, int soLuong, int danhMucId, String tenSach, String maSach, Date ngayNhap) {
        this.id = id;
        this.soLuong = soLuong;
        this.danhMucId = danhMucId;
        this.tenSach = tenSach;
        this.maSach = maSach;
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

    public String getTenSach() {
        return tenSach;
    }

    public String getMaSach() {
        return maSach;
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

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
    
    
}
