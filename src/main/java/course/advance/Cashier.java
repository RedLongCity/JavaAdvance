package course.advance;

public class Cashier implements Runnable {

    private final MacDac macDac;
    private final Thread thread;

    public Cashier(MacDac macDac) {
        this.macDac = macDac;
        this.thread = new Thread(this::service);
    }

    @Override
    public void run() {
        this.thread.start();
    }

    private void service() {
        boolean mackDacAvailable = true;
        while (mackDacAvailable) {
            mackDacAvailable = macDac.service();
        }
        System.out.println("Cashier stopped the working");
    }

}
