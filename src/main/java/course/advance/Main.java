package course.advance;

import course.advance.exception.InterruptException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        try {
            MacDac macDac = new MacDac(initBuyers());
            Cashier cashier = new Cashier(macDac);
            cashier.run();
        } catch (InterruptException ex) {
            ex.printStackTrace();
        }
    }

    private static Collection<Buyer> initBuyers() {
        Collection<Buyer> buyers = Collections.synchronizedCollection(
                new ArrayList<>());
        buyers.add(new Buyer("Buyer 1"));
        buyers.add(new Buyer("Buyer 2"));
        buyers.add(new Buyer("Buyer 3"));
        buyers.add(new Buyer("Buyer 4"));
        buyers.add(new Buyer("Buyer 5"));
        return buyers;
    }
}
