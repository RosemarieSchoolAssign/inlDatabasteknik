package company.models;

import company.models.Artikel;

import java.util.ArrayList;

public class Produkt {

    int prodID;
    String beskrivning;
    String maerke;
    int pris;
    ArrayList<Artikel> artiklar = new ArrayList<>();

    public Produkt(int prodID, String beskrivning, String maerke, int pris) {
        this.prodID = prodID;
        this.beskrivning = beskrivning;
        this.maerke = maerke;
        this.pris = pris;
    }

    public int getProdID() {return prodID;}

    public void setProdID(int prodID) {
        this.prodID = prodID;
    }

    public String getBeskrivning() {
        return beskrivning;
    }

    public void setBeskrivning(String beskrivning) {
        this.beskrivning = beskrivning;
    }

    public String getMaerke() {
        return maerke;
    }

    public void setMaerke(String maerke) {
        this.maerke = maerke;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public void setArtiklar(ArrayList<Artikel> artiklar) { this.artiklar = artiklar;}

    public void addArtikel(Artikel artikel) {
        this.artiklar.add(artikel);
    }

    public ArrayList<Artikel> getArtiklar() { return this.artiklar;}

    public String toString(){
        return this.getMaerke() + " " + this.getBeskrivning() + " " + this.getPris() + " kr";
    }
}
