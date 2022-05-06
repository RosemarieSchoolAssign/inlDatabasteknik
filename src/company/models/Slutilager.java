package company.models;

public class Slutilager {

    int artikelnummer;
    String datum;

    public Slutilager(int artikelnummer, String datum) {
        this.artikelnummer = artikelnummer;
        this.datum = datum;
    }

    public int getArtikelnummer() {
        return artikelnummer;
    }

    public void setArtikelnummer(int artikelnummer) {
        this.artikelnummer = artikelnummer;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
}
