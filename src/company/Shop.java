package company;

import company.models.*;
import company.repositories.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Shop {

    Repository r = new Repository();
    Kund kund;
    ArrayList<Produkt> products;

    public Shop() {}

    public boolean addUser(String password, String user, String adress, String ort) {
        return r.addUser(password, user, adress, ort);
    }

    public boolean userNotTaken(String user) {
        return r.userNotTaken(user);
    }

    public boolean userAndPasswordCorrect(ArrayList<String> userAndPassword) {
        return r.checkUserAndPassword(userAndPassword.get(0), userAndPassword.get(1));
    }

    public Kund getCustomer(String user) {
        return r.getCustomer(user);
    }

    // returnerar artikelobjekten som motsvarar orderdelarna kund beställt
    public Map<Integer, Artikel> getArtiklarOrdered(ArrayList<Order> orders, ArrayList<Produkt> products) {
        Map<Integer, Artikel> artiklarOrdered = new HashMap<>();
        for (Order order : orders) {
            for (OrderDel orderDel : order.getOrderDelar()) {
                int a = orderDel.getArtikelnummer();
                for (Produkt produkt : products) {
                    for (Artikel artikel : produkt.getArtiklar()) {
                        if (a == artikel.getArtikelnummer()) {
                            artiklarOrdered.put(a, artikel);
                        }
                    }
                }
            }
        }
        return artiklarOrdered;
    }

    public Map<Integer, Artikel> getArtiklarOrderedForRating(ArrayList<Order> orders, ArrayList<Produkt> products) {
        Map<Integer, Artikel> artiklarOrdered = new HashMap<>();
        int count = 1;
        for (Order order : orders) {
            for (OrderDel orderDel : order.getOrderDelar()) {
                int a = orderDel.getArtikelnummer();
                for (Produkt produkt : products) {
                    for (Artikel artikel : produkt.getArtiklar()) {
                        if (a == artikel.getArtikelnummer()) {
                            artiklarOrdered.put(count, artikel);
                            count++;
                        }
                    }
                }
            }
        }
        return artiklarOrdered;
    }

    public Map<Integer, Artikel> getArtiklarAvailableToOrder(ArrayList<Produkt> products) {
        Map<Integer, Artikel> artiklarAvailable = new HashMap<>();
        int count = 1;
                for (Produkt produkt : products) {
                    for (Artikel artikel : produkt.getArtiklar()) {
                        artiklarAvailable.put(count, artikel);
                            count++;
                    }
                }
        return artiklarAvailable;
    }

    public Map<Integer, Produkt> getProductsToDisplayRating(ArrayList<Produkt> products) {
        Map<Integer, Produkt> productsForRating = new HashMap<>();
        int count = 1;
        for (Produkt produkt : products) {
            productsForRating.put(count, produkt);
            count++;
        }
        return productsForRating;
    }

    public void updateCustomerOrders() {
        this.kund.addOrders(this.loadCustomerOrders(this.kund.getKundID()));
        this.loadCustomersOrderDetails(this.kund);
    }

    public ArrayList<Order> loadCustomerOrders(int kundID) {
        return r.getCustomerOrders(kundID);
    }

    public void loadCustomersOrderDetails(Kund kund) {
        ArrayList<Order> orders = kund.getOrders();
        for (Order order : orders) {
            order.setOrderDelar(r.getCustomerOrdersDetails(order.getOrdernummer()));
        }
    }

    public ArrayList<Produkt> loadProducts() {
        return r.loadProducts();
    }

    public void loadArticlesToProducts(ArrayList<Produkt> products) {
        for (Produkt produkt:products) {
            r.loadArticlesToProducts(produkt);
        }
    }

    // kundid ordernummer artikelnummer antal
    public String addOrder(int kundID, Map<Artikel, Integer> artiklarToOrder) {
        int latestOrder = 0;
        int count = 0;
        String msg = null;
        for (Map.Entry<Artikel, Integer> entry : artiklarToOrder.entrySet()) {
            if (count == 0) {
                msg = r.addOrder(kundID, 0, entry.getKey().getArtikelnummer(), entry.getValue());
                count++;
            }
            else if (count == 1) {
                latestOrder = this.getLatestOrder(kundID);
                msg = r.addOrder(kundID, latestOrder, entry.getKey().getArtikelnummer(), entry.getValue());
            }
            else {
                msg =  r.addOrder(kundID, latestOrder, entry.getKey().getArtikelnummer(), entry.getValue());
            }
        }
        return msg;
    }

    public String createErrorInOrder() {
        return r.addOrder(this.kund.getKundID(), 0, 0, 1);
    }

    public int getLatestOrder(int kundID) {
        return r.getLatestOrder(kundID);
    }

    public ArrayList<Betyg> loadBetyg(){
        return r.loadBetyg();
    }

    public void addRating(int prodID, int betygID, int kundID, String kommentar){
        r.addRating(prodID, betygID, kundID, kommentar);
    }

    public ArrayList<String> getCommentsForProduct(Produkt p){
        return r.getCommentsForProduct(p);
    }

    public int getMeanRatingForProduct(Produkt produkt) {
        return r.getMeanRatingForProduct(produkt);
    }


    public static void main(String[] args) {
        Shop shop = new Shop();
        ui ui = new ui();
        boolean running = true;
        while (running) {
            String answer = ui.welcome();
            switch (answer) {
                case "1":
                    ArrayList<String> userAndPassword = ui.logIn();
                    if (shop.userAndPasswordCorrect(userAndPassword)) {
                        shop.kund = shop.getCustomer(userAndPassword.get(0));
                        shop.products = shop.loadProducts();
                        shop.loadArticlesToProducts(shop.products);
                        shop.updateCustomerOrders();
                        boolean loggedIn = true;
                        while (loggedIn) {
                            answer = ui.whenLoggedIn();
                            switch (answer) {
                                case "1":
                                    Map<Artikel, Integer> artiklarToOrder = ui.newOrder(shop.getArtiklarAvailableToOrder(shop.products));
                                    ui.say(shop.addOrder(shop.kund.getKundID(), artiklarToOrder));
                                    shop.updateCustomerOrders();
                                    //ui.say(shop.createErrorInOrder());
                                    break;
                                case "2":
                                    ui.displayPreviousOrders(shop.kund.getOrders(),
                                            shop.getArtiklarOrdered(shop.kund.getOrders(), shop.products));
                                    break;
                                case "3":
                                    Artikel a = ui.findProductToRate(shop.getArtiklarOrderedForRating(shop.kund.getOrders(), shop.products));
                                    int betygID = ui.selectRating(shop.loadBetyg());
                                    String kommentar = ui.selectComment();
                                    shop.addRating(a.getProdukt().getProdID(), betygID, shop.kund.getKundID(), kommentar);
                                    break;
                                case "4":
                                    Produkt p = ui.selectProduct(shop.getProductsToDisplayRating(shop.products));
                                    ArrayList<String> comments = shop.getCommentsForProduct(p);
                                    int mean = shop.getMeanRatingForProduct(p);
                                    ui.displayRating(p, mean, comments);
                                    break;
                                case "5":
                                    ui.say("Loggar ut...\n\n");
                                    loggedIn = false;
                                    shop.products = null;
                                    shop.kund = null;
                                    break;
                            }
                        }
                    } else {
                        ui.say("Fel användarnamn eller lösenord");
                    }
                    break;
                case "2":
                    String user = ui.registerNewUserName();
                    if (shop.userNotTaken(user)) {
                        if (shop.addUser(ui.registerPassword(), user, ui.registerAddress(), ui.registerOrt())) {
                            ui.say(user + " har registrerats");
                        } else {
                            ui.say("Kunde inte registrera ny kund. Försök igen");
                        }
                    } else {
                        ui.say(user + " är upptaget");
                    }
                    break;
                case "3":
                    running = false;
            }
        }
    }


}
