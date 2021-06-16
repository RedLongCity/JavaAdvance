package course.advance;

import lombok.SneakyThrows;

public class Gates {

    private final Object startGate = new Object();
    private final Object finishGate = new Object();

    public void notifyStartGate() {
        synchronized (startGate) {
            startGate.notify();
        }
    }

    public void notifyFinishGate() {
        synchronized (finishGate) {
            finishGate.notify();
        }
    }

    @SneakyThrows
    public void waitFinishGate() {
        synchronized (finishGate) {
            finishGate.wait();
        }
    }

    @SneakyThrows
    public void waitStartGate() {
        synchronized (startGate) {
            startGate.wait();
        }
    }
}
