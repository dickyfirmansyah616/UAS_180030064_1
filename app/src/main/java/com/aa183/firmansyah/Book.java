package com.aa183.firmansyah;

import java.util.Date;

public class Book {

    private int idBook;
    private String gambarBook;
    private String judulBook;
    private String penulis;
    private String genre;
    private Date tanggal;
    private String sinopsisBook;

    public Book(int idBook, String gambarBook, String judulBook, String penulis, String genre, Date tanggal, String sinopsisBook) {
        this.idBook = idBook;
        this.gambarBook = gambarBook;
        this.judulBook = judulBook;
        this.penulis = penulis;
        this.genre = genre;
        this.tanggal = tanggal;
        this.sinopsisBook = sinopsisBook;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getGambarBook() {
        return gambarBook;
    }

    public void setGambarBook(String gambarBook) {
        this.gambarBook = gambarBook;
    }

    public String getJudulBook() {
        return judulBook;
    }

    public void setJudulBook(String judulBook) {
        this.judulBook = judulBook;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getSinopsisBook() {
        return sinopsisBook;
    }

    public void setSinopsisBook(String sinopsisBook) {
        this.sinopsisBook = sinopsisBook;
    }
}


