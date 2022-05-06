package company.repositories;

import company.models.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

public class Repository {

    Properties p = new Properties();

    public Repository() {
        try {
            p.load(new FileInputStream("src/settings.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkUserAndPassword(String user, String password) {
        String pw = "";
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("select kund.passw from kund where namn=?");
        ) {
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                pw = rs.getString("passw");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pw.equals(password);
    }

    public Kund getCustomer(String user) {
        int id = 0;
        String pw = null;
        String namn = null;
        String adress = null;
        String ort = null;
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("select kund.kundID, kund.passw, kund.namn," +
                     "kund.adress, kund.ort from kund where namn=?");
        ) {
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("kundID");
                pw = rs.getString("passw");
                namn = rs.getString("namn");
                adress = rs.getString("adress");
                ort = rs.getString("ort");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Kund(id, pw, namn, adress, ort);
    }

    public ArrayList<Order> getCustomerOrders(int kundID) {
        ArrayList<Order> orders = new ArrayList<>();
        int ordernummer = 0;
        String datum = null;
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("select orders.ordernummer, orders.datum " +
                     "from orders where kundID=?");
        ) {
            stmt.setInt(1, kundID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ordernummer = rs.getInt("ordernummer");
                datum = rs.getString("datum");
                orders.add(new Order(ordernummer, kundID, datum));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orders;
    }

    public ArrayList<OrderDel> getCustomerOrdersDetails(int ordernummer) {
        ArrayList<OrderDel> orderDelar = new ArrayList<>();
        int artikelnummer = 0;
        int antal = 0;
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("select orderDel.artikelnummer, orderDel.antal " +
                     "from orderDel where ordernummer=?");
        ) {
            stmt.setInt(1, ordernummer);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                artikelnummer = rs.getInt("artikelnummer");
                antal = rs.getInt("antal");
                orderDelar.add(new OrderDel(ordernummer, artikelnummer, antal));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderDelar;
    }

    public boolean userNotTaken(String user) {
        int userID = 0;
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("select kund.kundID from kund where namn=?");
        ) {
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();
            while (rs != null && rs.next()) {
                userID = rs.getInt("kundID");
                System.out.println("kund id: " + userID);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userID==0;
    }

    public ArrayList<Betyg> loadBetyg(){
        ArrayList<Betyg> betygMedNummer = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("select * from betyg");
        ) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int betygID = rs.getInt("betygID");
                String betyg = rs.getString("betyg");
                betygMedNummer.add(new Betyg(betygID, betyg));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return betygMedNummer;
    }

    public ArrayList<String> getCommentsForProduct(Produkt produkt){
        ArrayList<String> comments = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("select prodBet.kommentar from prodBet where prodID=?");
        ) {
            stmt.setInt(1, produkt.getProdID());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String kommentar = rs.getString("kommentar");
                comments.add(kommentar);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return comments;
    }

    public ArrayList<Produkt> loadProducts(){
        ArrayList<Produkt> products = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("select * from produkt");
        ) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int prodID = rs.getInt("prodID");
                String beskrivning = rs.getString("beskrivning");
                String maerke = rs.getString("märke");
                int pris = rs.getInt("pris");
                products.add(new Produkt(prodID, beskrivning, maerke, pris));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    public void loadArticlesToProducts(Produkt produkt){
        int artikelnummer = 0;
        int storlek = 0;
        String faerg = null;
        int antal = 0;
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("select artikel.artikelnummer, artikel.storlek," +
                     "artikel.färg, artikel.antal from artikel where prodID=?");
        ) {
            stmt.setInt(1, produkt.getProdID());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                artikelnummer = rs.getInt("artikelnummer");
                storlek = rs.getInt("storlek");
                faerg = rs.getString("färg");
                antal = rs.getInt("antal");
                produkt.addArtikel(new Artikel(artikelnummer, produkt, storlek, faerg, antal));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //INSERT - returnerar true om kunden har registrerats
    public boolean addUser(String password, String user, String adress, String ort) {
        String query = "insert into kund (passw, namn, adress, ort) values (?, ?, ?, ?)";
        int rowChanged = 0;

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement(query)){

            stmt.setString(1, password);
            stmt.setString(2, user);
            stmt.setString(3, adress);
            stmt.setString(4, ort);
            rowChanged = stmt.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rowChanged==1;
    }

    public int getMeanRatingForProduct(Produkt produkt){
        int mean = 0;
        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             CallableStatement stmt = con.prepareCall("{? = call calculate_mean(?)}");
        ){
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setInt(2, produkt.getProdID());
            stmt.executeUpdate();
            mean = stmt.getInt(1);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return mean;
    }

    public void addRating(int prodID, int betygID, int kundID, String kommentar) {
        int rowChanged = 0;
        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             CallableStatement stmt = con.prepareCall("call Rate(?,?,?,?)")
        ){
            stmt.setInt(1, prodID);
            stmt.setInt(2, betygID);
            stmt.setInt(3, kundID);
            stmt.setString(4, kommentar);
            rowChanged = stmt.executeUpdate();  // returnerar berörda poster
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getLatestOrder(int kundID){
        ArrayList<Integer> allaOrdernummer = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("select orders.ordernummer from orders where kundID=?");
        ) {
            stmt.setInt(1, kundID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int o = rs.getInt("ordernummer");
                allaOrdernummer.add(o);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.max(allaOrdernummer);
    }

    public String addOrder(int kundID, int ordernummer, int artikelnummer, int antal){
        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             CallableStatement stmt = con.prepareCall("call AddToCart2(?,?,?,?)")
        ){
            stmt.setInt(1, kundID);
            stmt.setInt(2, ordernummer);
            stmt.setInt(3, artikelnummer);
            stmt.setInt(4, antal);
            ResultSet rs = stmt.executeQuery();
        }
        catch (SQLException e){
            return "Beställningen kunde inte genomföras";
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "Beställningen har genomförts";
    }




}



