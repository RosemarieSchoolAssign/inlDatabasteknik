package company.models;

import company.models.Artikel;

public class OrderDel {


   int ordernummer;
   int artikelnummer;
   int antal;

    public OrderDel(int ordernummer, int artikelnummer, int antal) {
        this.ordernummer = ordernummer;
        this.artikelnummer = artikelnummer;
        this.antal = antal;
    }

    public int getOrdernummer() {
        return ordernummer;
    }

    public void setOrdernummer(int ordernummer) {
        this.ordernummer = ordernummer;
    }

    public int getArtikelnummer() {
        return artikelnummer;
    }

    public void setArtikelnummer(int artikelnummer) {
        this.artikelnummer = artikelnummer;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public String toString(Artikel artikel){
        return artikel.getProdukt().getMaerke() + " " + artikel.getProdukt().getBeskrivning() +
                " " + artikel.getFaerg() + " " + this.getAntal() + " st";
    }
}
