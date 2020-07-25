/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_lab_5.ca_5;

/**
 *
 * @author tiennh
 */
public class SanPham {
    protected int id, soLuong;
    protected String tenSP, maSP;
    protected String ngayNhap;

    public SanPham() {
    }

    public SanPham(int id, int soLuong, String tenSP, String maSP, String ngayNhap) {
        this.id = id;
        this.soLuong = soLuong;
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

    public String getTenSP() {
        return tenSP;
    }

    public String getMaSP() {
        return maSP;
    }

    public String getNgayNhap() {
        return ngayNhap;
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

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
}
