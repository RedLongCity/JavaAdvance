package course.advance.building;

import course.advance.exception.InterruptException;
import course.advance.worker.Worker;

import java.util.Collection;

public class Barracks implements Building, Runnable {

    private final int delay;
    private final Thread thread;
    private final Collection<Worker> workers;
    private boolean needSpawn;

    public Barracks(int delay, Collection<Worker> workers) {
        this.delay = delay;
        this.workers = workers;
        this.thread = new Thread(this::spawn);
    }

    @Override
    public void run() {
        this.thread.start();
    }

    private void spawn() {
        int workersCount = 1;

        while (needSpawn) {
            try {
                Thread.sleep(delay);
                Worker worker = new Worker("Worker " + workersCount);
                workers.add(worker);
                System.out.println(worker.getName() + " CREATED");
                workersCount++;
            } catch (InterruptedException ex) {
                throw new InterruptException("[BARRAKS SPAWN EX] :", ex);
            }
        }
    }

    @Override
    public void startSpawn() {
        this.needSpawn = true;
        run();
    }

    @Override
    public void stopSpawn() {
        this.needSpawn = false;
    }
}
