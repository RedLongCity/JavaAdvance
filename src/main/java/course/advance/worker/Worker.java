package course.advance.worker;

import course.advance.source.Resource;
import course.advance.source.Source;

import static course.advance.source.Resource.NOTHING;

public class Worker implements Runnable {

    private final String name;
    private final Thread thread;
    private Source source;
    private boolean isWorking;
    private int extracted;

    public Worker(String name) {
        this.name = name;
        this.thread = new Thread(this::doWork);
    }

    @Override
    public void run() {
        thread.start();
    }

    private void doWork() {
        while (isExtractResources(this.source.extract())) {
            extracted++;
        }
        this.isWorking = false;
        System.out.println("Worker: " + name + " finished a work!");
    }

    public String getName() {
        return name;
    }

    public synchronized boolean needWork() {
        return this.source == null;
    }

    public synchronized boolean isUnemployed() {
        return source != null && !isWorking;
    }

    public synchronized void startWorking(Source mine) {
        this.source = mine;
        this.isWorking = true;
        run();
    }

    public void showResult() {
        System.out.println(name + " Extracted: " + extracted + " Resources");
    }

    private boolean isExtractResources(Resource resource) {
        return resource != NOTHING;
    }
}
