package com.entities;

import java.io.Serializable;

public class Student implements Serializable {
    private String MaSV;
    private String TenSV;
    private double Diem;
    private String Gioitinh;
    private String Chuyennganh;

    public Student() {
    }

    public Student(String maSV, String tenSV, double diem, String gioitinh, String chuyennganh) {
        MaSV = maSV;
        TenSV = tenSV;
        Diem = diem;
        Gioitinh = gioitinh;
        Chuyennganh = chuyennganh;
    }

    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String maSV) {
        MaSV = maSV;
    }

    public String getTenSV() {
        return TenSV;
    }

    public void setTenSV(String tenSV) {
        TenSV = tenSV;
    }

    public double getDiem() {
        return Diem;
    }

    public void setDiem(double diem) {
        Diem = diem;
    }

    public String getGioitinh() {
        return Gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        Gioitinh = gioitinh;
    }

    public String getChuyennganh() {
        return Chuyennganh;
    }

    public void setChuyennganh(String chuyennganh) {
        Chuyennganh = chuyennganh;
    }
}
