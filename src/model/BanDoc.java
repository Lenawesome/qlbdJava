/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.io.Serializable;


public class BanDoc implements Serializable{
    private int ma;
    private String ten,diaChi;
    private long soDienThoai;

    public BanDoc(int ma, String ten, String diaChi, long soDienThoai) {
        this.ma = ma;
        this.ten = ten;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public long getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(long soDienThoai) {
        this.soDienThoai = soDienThoai;
    }    
    public Object[] toObject() {
        return new Object[]{ma,ten,diaChi,soDienThoai};
    }
    
}
