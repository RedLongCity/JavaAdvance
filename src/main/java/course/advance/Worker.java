package course.advance;

import course.advance.work.WorkType;

public class Worker implements Runnable {

    private final WorkType workType;

    public Worker(WorkType workType) {
        this.workType = workType;
        new Thread(this).start();
    }

    @Override
    public void run() {
        this.workType.doWork();
    }
}
