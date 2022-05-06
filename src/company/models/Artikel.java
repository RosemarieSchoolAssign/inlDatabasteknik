package company.models;

public class Artikel {


    int artikelnummer;
    Produkt produkt;
    int storlek;
    String faerg;
    int antal;

    public Artikel(int artikelnummer, Produkt produkt, int storlek, String faerg, int antal) {
        this.artikelnummer = artikelnummer;
        this.produkt = produkt;
        this.storlek = storlek;
        this.faerg = faerg;
        this.antal = antal;
    }

    public int getArtikelnummer() {
        return artikelnummer;
    }

    public void setArtikelnummer(int artikelnummer) {
        this.artikelnummer = artikelnummer;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public int getStorlek() {
        return storlek;
    }

    public void setStorlek(int storlek) {
        this.storlek = storlek;
    }

    public String getFaerg() {
        return faerg;
    }

    public void setFaerg(String faerg) {
        this.faerg = faerg;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public String toString() {
        return this.getProdukt().getMaerke() + " " + this.getProdukt().getBeskrivning() + " " + this.getFaerg();
    }
}
