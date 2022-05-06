package company.models;

import java.util.ArrayList;

public class Order {

    int ordernummer;
    int kundID;
    String datum;
    ArrayList<OrderDel> orderDelar = new ArrayList<>();

    public Order(int ordernummer, int kundID, String datum) {
        this.ordernummer = ordernummer;
        this.kundID = kundID;
        this.datum = datum;
    }

    public int getOrdernummer() {
        return ordernummer;
    }

    public void setOrdernummer(int ordernummer) {
        this.ordernummer = ordernummer;
    }

    public int getKundID() {
        return kundID;
    }

    public void setKundID(int kundID) {
        this.kundID = kundID;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public ArrayList<OrderDel> getOrderDelar(){
        return orderDelar;
    }

    public void setOrderDelar(ArrayList<OrderDel> orderDelar) {
        this.orderDelar = orderDelar;
    }

    public String toString(){
        return "Order: " + this.ordernummer + " Datum " + this.datum;
    }
}

