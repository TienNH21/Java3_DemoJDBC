/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de_luyen_tap.models;

/**
 *
 * @author tiennh
 */
public class DanhMuc {
    protected int id;
    protected String ten;

    public DanhMuc() {
    }

    public DanhMuc(int id, String ten) {
        this.id = id;
        this.ten = ten;
    }

    public int getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
    
    
}
