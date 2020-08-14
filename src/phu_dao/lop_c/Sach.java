/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phu_dao.lop_c;

/**
 *
 * @author tiennh
 */
public class Sach {
    protected String ten, ma, ngayNhap;
    protected int soLuong, id, danhMucId;

    public Sach() {
    }

    public Sach(int id, String ten, String ma, int soLuong, String ngayNhap, int danhMucId) {
        this.ten = ten;
        this.ma = ma;
        this.soLuong = soLuong;
        this.ngayNhap = ngayNhap;
        this.id = id;
        this.danhMucId = danhMucId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public String getMa() {
        return ma;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
    
    
}
