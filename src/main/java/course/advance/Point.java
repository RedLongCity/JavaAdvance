package course.advance;

public class Point {

    private final String name;
    private int amt;

    public Point(String name, int amt) {
        this.name = name;
        this.amt = amt;
    }

    public synchronized int pull() {
        if (amt <= 0)
            return 0;
        amt--;
        System.out.println("Current amt in " + name + " : " + this.amt);
        return 1;
    }

    public synchronized void push(int amt) {
        this.amt += amt;
        System.out.println("Current amt in " + name + " : " + this.amt);
    }

    public synchronized boolean isEmpty() {
        return this.amt <= 0;
    }
}
