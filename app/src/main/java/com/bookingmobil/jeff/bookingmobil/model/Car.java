package com.bookingmobil.jeff.bookingmobil.model;

public class Car {

    private String id;
    private String name;
    private String model;
    private String warna;
    private String gambar;
    private String fitur_tambahan;
    private String transmisi;
    private String bahan_bakar;
    private String kapasitas_mesin;

    public Car(){

    }

    public Car(String id, String name, String model, String warna, String gambar, String fitur_tambahan, String transmisi, String bahan_bakar, String kapasitas_mesin) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.warna = warna;
        this.gambar = gambar;
        this.fitur_tambahan = fitur_tambahan;
        this.transmisi = transmisi;
        this.bahan_bakar = bahan_bakar;
        this.kapasitas_mesin = kapasitas_mesin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getFitur_tambahan() {
        return fitur_tambahan;
    }

    public void setFitur_tambahan(String fitur_tambahan) {
        this.fitur_tambahan = fitur_tambahan;
    }

    public String getTransmisi() {
        return transmisi;
    }

    public void setTransmisi(String transmisi) {
        this.transmisi = transmisi;
    }

    public String getBahan_bakar() {
        return bahan_bakar;
    }

    public void setBahan_bakar(String bahan_bakar) {
        this.bahan_bakar = bahan_bakar;
    }

    public String getKapasitas_mesin() {
        return kapasitas_mesin;
    }

    public void setKapasitas_mesin(String kapasitas_mesin) {
        this.kapasitas_mesin = kapasitas_mesin;
    }
}
