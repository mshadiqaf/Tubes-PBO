package project.tubespbo.Models;

import java.util.List;

public class JurusanModel {

    private final String nama;
    private String deskripsi;
    private final List<ProdiModel> daftarProdi;

    public JurusanModel(String nama, String deskripsi, List<ProdiModel> daftarProdi) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.daftarProdi = daftarProdi;
    }

    public void addStudyPrograms(ProdiModel prodi) {
        daftarProdi.add(prodi);
    }

    public String getNama() {
        return nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public List<ProdiModel> getDaftarStudi() {
        return daftarProdi;
    }
}

