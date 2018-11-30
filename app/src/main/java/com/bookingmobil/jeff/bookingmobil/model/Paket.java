package com.bookingmobil.jeff.bookingmobil.model;

public class Paket {

    private String pktId;
    private String carId;
    private String harga;
    private String nama;
    private String overtime;
    private String tipe;

    public Paket(){

    }

    public Paket(String pktId, String carId, String harga, String nama, String overtime, String tipe) {
        this.pktId = pktId;
        this.carId = carId;
        this.harga = harga;
        this.nama = nama;
        this.overtime = overtime;
        this.tipe = tipe;
    }

    public String getPktId() {
        return pktId;
    }

    public void setPktId(String pktId) {
        this.pktId = pktId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getOvertime() {
        return overtime;
    }

    public void setOvertime(String overtime) {
        this.overtime = overtime;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }
}
