package company.models;

import java.util.ArrayList;

public class Kund {

    int kundID;
    String password;
    String namn;
    String adress;
    String ort;
    ArrayList<Order> ordrar;

    public Kund(int kundID, String password, String namn, String adress, String ort) {
        this.kundID = kundID;
        this.password = password;
        this.namn = namn;
        this.adress = adress;
        this.ort = ort;
    }

    public int getKundID() {
        return kundID;
    }

    public void setKundID(int kundID) {
        this.kundID = kundID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public void addOrders(ArrayList<Order> ordrar) {
        this.ordrar = ordrar;
    }

    public ArrayList<Order> getOrders() {
        return ordrar;
    }
}
