package company;

import company.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ui {

    Scanner scan;

    public ui() {
        scan = new Scanner(System.in);
    }

    public String welcome() {
        boolean noAnswer = true;
        String answer = "";
        while (noAnswer) {
            System.out.println("1. Logga in");
            System.out.println("2. Ny kund");
            System.out.println("3. Exit");
            System.out.println("Välj: ");
            answer = scan.next();
            if (answer.equalsIgnoreCase("1") || answer.equalsIgnoreCase("2") || answer.equalsIgnoreCase("3")) {
                noAnswer = false;
            }
        }
        return answer;
    }

    public ArrayList<String> logIn() {
        ArrayList<String> userAndPassword = new ArrayList<>();
        System.out.println("Användarnamn: ");
        userAndPassword.add(scan.next());
        System.out.println("Lösenord: ");
        userAndPassword.add(scan.next());
        return userAndPassword;
    }

    public String registerNewUserName() {
        System.out.println("Välj ett användarnamn: ");
        return scan.next();
    }

    public String registerPassword() {
        System.out.println("Välj ett lösenord: ");
        return scan.next();
    }

    public String registerAddress() {
        System.out.println("Skriv in din adress: ");
        return scan.next();
    }

    public String registerOrt() {
        System.out.println("Skriv in din ort: ");
        return scan.next();
    }

    public void say(String msg) {
        System.out.println(msg);
    }

    public String whenLoggedIn() {
        boolean noAnswer = true;
        String answer = "";
        while (noAnswer) {
            System.out.println("\n");
            System.out.println("1. Gör ny beställning");
            System.out.println("2. Se tidigare beställningar");
            System.out.println("3. Betygsätt produkt");
            System.out.println("4. Se betyg för produkt");
            System.out.println("5. Logga ut");
            answer = scan.next();
            if (answer.equals("1") || answer.equals("2") || answer.equals("3") || answer.equals("4") || answer.equals("5")) {
                noAnswer = false;
            }
        }
        return answer;
    }

    public Map<Artikel, Integer> newOrder(Map<Integer, Artikel> artiklarAvailable) {
        Map<Artikel, Integer> artiklarToOrder = new HashMap<>();
        boolean noOrder = true;
        while (noOrder) {
            for (Map.Entry<Integer, Artikel> entry : artiklarAvailable.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue().toString());
            }
            System.out.println("Välj produkt: ");
            int artikel = scan.nextInt();
            System.out.println("Antal: ");
            int antal = scan.nextInt();
            artiklarToOrder.put(artiklarAvailable.get(artikel), antal);
            System.out.println("1. Fortsätt handla");
            System.out.println("2. Slutför beställning");
            if (scan.next().equals("2")) {
                noOrder = false;
            }
        }
        return artiklarToOrder;
    }

    public void displayPreviousOrders(ArrayList<Order> orders, Map<Integer, Artikel> artiklar) {
        for (Order order : orders
        ) {
            System.out.println(order.toString());
            for (OrderDel orderDel : order.getOrderDelar()) {
                System.out.println(orderDel.toString(artiklar.get(orderDel.getArtikelnummer())));
            }
        }
        boolean noAnswer = true;
        String answer = "";
        while (noAnswer) {
            System.out.println("1. Gå tillbaka");
            answer = scan.next();
            if (answer.equals("1")) {
                noAnswer = false;
            }
        }
    }

    public Artikel findProductToRate(Map<Integer, Artikel> artiklar) {
        for (Map.Entry<Integer, Artikel> entry : artiklar.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().toString());
        }
        boolean noAnswer = true;
        Artikel a = null;
        while (noAnswer) {
            System.out.println("Välj produkt att betygsätta: ");
            int answer = scan.nextInt();
            a = artiklar.get(answer);
            if (a != null) {
                noAnswer = false;
            }
        }
        return a;
    }

    public int selectRating(ArrayList<Betyg> betyg) {
        int answer = 0;
        for (Betyg b : betyg)
            System.out.println(b.getBetygID() + " " + b.getBetyg());
        System.out.println("Välj betyg: ");
        answer = scan.nextInt();
        return answer;
    }

    public String selectComment() {
        String answer = null;
        System.out.println("Kommentar: ");
        answer = scan.next();
        return answer;
    }

    public Produkt selectProduct(Map<Integer, Produkt> products){
        for (Map.Entry<Integer, Produkt> entry : products.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().toString());
        }
        boolean noAnswer = true;
        Produkt p = null;
        while (noAnswer) {
            System.out.println("Välj produkt och se dess betyg: ");
            int answer = scan.nextInt();
            p = products.get(answer);
            if (p != null) {
                noAnswer = false;
            }
        }
        return p;
    }

    public void displayRating(Produkt p, int mean, ArrayList<String> comments) {
        System.out.println(p.toString());
        System.out.println("Medelbetyg: " + mean);
        System.out.println("Sagt om produkt:");
        for (String c:comments) {
            System.out.println(c);
        }
    }


}

