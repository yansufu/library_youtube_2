package com.example.library_youtube;

import java.io.Serializable;

public class Pesanan implements Serializable {
    public String uid;
    public String namaPesanan;
    public int hargaPesanan;
    public int jumlahPesanan;

    public Pesanan() {
    }

    public Pesanan(String namaPesanan, int hargaPesanan, int jumlahPesanan) {

        this.namaPesanan = namaPesanan;
        this.hargaPesanan = hargaPesanan;
        this.jumlahPesanan = jumlahPesanan;
    }
}
