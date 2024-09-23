package com.example.kampusku.model;

import java.util.HashMap;

public class Data extends HashMap<String, String> {
    private int nomor;
    private String nama;
    private String tanggallahir;
    private String jeniskelamin;
    private String alamat;

    public Data(int nomor, String nama, String tanggallahir, String jeniskelamin, String alamat) {
        this.nomor = nomor;
        this.nama = nama;
        this.tanggallahir = tanggallahir;
        this.jeniskelamin = jeniskelamin;
        this.alamat = alamat;
    }

    public Data() {
    }

    public int getNomor() {
        return nomor;
    }

    public void setNomor(int nomor) { this.nomor = nomor;}

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggallahir() {
        return tanggallahir;
    }

    public void setTanggallahir(String tanggallahir) {
        this.tanggallahir = tanggallahir;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }

    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
