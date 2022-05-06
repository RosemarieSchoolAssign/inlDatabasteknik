package company.models;

public class ProduktBetyg {

    int id;
    int prodID;
    int betygID;
    int kundID;
    String kommentar;

    public ProduktBetyg(int id, int prodID, int betygID, int kundID, String kommentar) {
        this.id = id;
        this.prodID = prodID;
        this.betygID = betygID;
        this.kundID = kundID;
        this.kommentar = kommentar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProdID() {
        return prodID;
    }

    public void setProdID(int prodID) {
        this.prodID = prodID;
    }

    public int getBetygID() {
        return betygID;
    }

    public void setBetygID(int betygID) {
        this.betygID = betygID;
    }

    public int getKundID() {
        return kundID;
    }

    public void setKundID(int kundID) {
        this.kundID = kundID;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }
}
