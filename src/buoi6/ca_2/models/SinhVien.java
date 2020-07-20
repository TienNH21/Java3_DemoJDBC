/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buoi6.ca_2.models;

/**
 *
 * @author tiennh
 */
public class SinhVien {
    protected int id, diem;
    protected String name, email, password, maSV;

    public SinhVien() {
    }

    public SinhVien(int id, int diem, String name, String email, String password, String maSV) {
        this.id = id;
        this.diem = diem;
        this.name = name;
        this.email = email;
        this.password = password;
        this.maSV = maSV;
    }

    public String getMaSV() {
        return maSV;
    }

    public int getId() {
        return id;
    }

    public int getDiem() {
        return diem;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
