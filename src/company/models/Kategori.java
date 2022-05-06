package company.models;

public class Kategori {

    int katID;
    String kategori;

    public Kategori(int katID, String kategori) {
        this.katID = katID;
        this.kategori = kategori;
    }

    public int getKatID() {
        return katID;
    }

    public void setKatID(int katID) {
        this.katID = katID;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
