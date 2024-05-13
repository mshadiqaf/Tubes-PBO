package project.tubespbo.Models;

import java.util.ArrayList;
import java.util.List;

public class JurusanModel {

    private final String nama;
    private final List<ProdiModel> daftarProdi;

    public JurusanModel(String nama) {
        this.nama = nama;
        this.daftarProdi = new ArrayList<>();
    }

    public void addStudyPrograms(ProdiModel prodi) {
        daftarProdi.add(prodi);
    }

    public String getNama() {
        return nama;
    }

    public List<ProdiModel> getDaftarStudi() {
        return daftarProdi;
    }
}

