package com.bookingmobil.jeff.bookingmobil.model;

import java.io.Serializable;

public class Booking implements Serializable {

    private String id;
    private String carID;
    private String showroomID;
    private String paketId;
    private String tgl_mulai;
    private String tgl_akhir;
    private String status;
    private String userId;

    private String carName;
    private String showroomName;
    private String paketName;

    public Booking(String id, String carID, String showroomID, String paketId, String tgl_mulai, String tgl_akhir, String status, String userId, String carName, String showroomName, String paketName) {
        this.id = id;
        this.carID = carID;
        this.showroomID = showroomID;
        this.paketId = paketId;
        this.tgl_mulai = tgl_mulai;
        this.tgl_akhir = tgl_akhir;
        this.status = status;
        this.userId = userId;
        this.carName = carName;
        this.showroomName = showroomName;
        this.paketName = paketName;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getShowroomName() {
        return showroomName;
    }

    public void setShowroomName(String showroomName) {
        this.showroomName = showroomName;
    }

    public String getPaketName() {
        return paketName;
    }

    public void setPaketName(String paketName) {
        this.paketName = paketName;
    }

    public Booking(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getShowroomID() {
        return showroomID;
    }

    public void setShowroomID(String showroomID) {
        this.showroomID = showroomID;
    }

    public String getTgl_mulai() {
        return tgl_mulai;
    }

    public void setTgl_mulai(String tgl_mulai) {
        this.tgl_mulai = tgl_mulai;
    }

    public String getTgl_akhir() {
        return tgl_akhir;
    }

    public void setTgl_akhir(String tgl_akhir) {
        this.tgl_akhir = tgl_akhir;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaketId() {
        return paketId;
    }

    public void setPaketId(String paketId) {
        this.paketId = paketId;
    }
}
