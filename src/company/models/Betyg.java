package company.models;

public class Betyg {

    int betygID;
    String betyg;

    public Betyg(int betygID, String betyg) {
        this.betygID = betygID;
        this.betyg = betyg;
    }

    public int getBetygID() {
        return betygID;
    }

    public void setBetygID(int betygID) {
        this.betygID = betygID;
    }

    public String getBetyg() {
        return betyg;
    }

    public void setBetyg(String betyg) {
        this.betyg = betyg;
    }
}
