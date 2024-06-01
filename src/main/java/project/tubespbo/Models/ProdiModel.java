package project.tubespbo.Models;

public class ProdiModel {

    private final String nama, deskripsi;
    private final int nomorProdi;

    public ProdiModel(String nama, int nomorProdi, String deskripsi) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.nomorProdi = nomorProdi;
    }

    public String getNama() {
        return nama;
    }

    public int getNomorProdi() {
        return nomorProdi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }
}
