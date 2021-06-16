package course.advance;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Basket {

    private final int limit;
    private int amt;

    public synchronized int pull() {
        if (amt <= 0) {
            System.out.println("basket is Empty");
            return 0;
        }
        amt--;
        System.out.println("Current amt in basket: " + this.amt);
        return 1;
    }

    public synchronized void push(int amt) {
        this.amt += amt;
        System.out.println("Current amt in basket: " + this.amt);
    }

    public synchronized boolean isFull() {
        return this.amt == limit;
    }

    public synchronized boolean isNotEmpty() {
        return this.amt != 0;
    }

}
