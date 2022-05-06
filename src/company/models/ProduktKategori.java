package company.models;

public class ProduktKategori {

    int prodID;
    int katID;

    public ProduktKategori(int prodID, int katID) {
        this.prodID = prodID;
        this.katID = katID;
    }

    public int getProdID() {
        return prodID;
    }

    public void setProdID(int prodID) {
        this.prodID = prodID;
    }

    public int getKatID() {
        return katID;
    }

    public void setKatID(int katID) {
        this.katID = katID;
    }
}
