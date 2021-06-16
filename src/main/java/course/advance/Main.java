package course.advance;

import course.advance.work.Unloading;
import course.advance.work.Driving;
import course.advance.work.WorkType;
import course.advance.work.Loading;

public class Main {

    public static void main(String[] args) {
        try {
            Basket basket = new Basket(10);
            Point pointA = new Point("Point A", 30);
            Point pointB = new Point("Point B", 0);
            Gates gates = new Gates();
            WorkType loading = new Loading(gates, 333, basket, pointA);
            WorkType unloading = new Unloading(gates, 500, basket, pointA, pointB);
            WorkType driving = new Driving(gates, 5000, basket, pointA);

            new Worker(loading);
            new Worker(unloading);
            new Worker(driving);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
