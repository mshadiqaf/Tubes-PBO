package project.tubespbo.Models;

import java.util.List;

public abstract class BangunanModel {

    protected final String nama, deskripsi;
    protected final List<RuanganModel> ruangan;

    public BangunanModel(String nama, String deskripsi, List<RuanganModel> ruangan) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.ruangan = ruangan;
    }

    public String getNama() {
        return nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public List<RuanganModel> getRuangan() {
        return ruangan;
    }

}
