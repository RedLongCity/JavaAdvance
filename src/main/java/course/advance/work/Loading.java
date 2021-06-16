package course.advance.work;

import course.advance.Gates;
import course.advance.Point;
import course.advance.Basket;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class Loading implements WorkType {

    private final Gates gates;
    private final int delay;
    private final Basket basket;
    private final Point point;

    @Override
    @SneakyThrows
    public void doWork() {
        while (needWaiting()) {
            this.load();

            gates.notifyStartGate();

            if (!needWaiting())
                break;

            System.out.println("Loader is waiting...");
            gates.waitStartGate();
        }
        System.out.println("LOADER IS FINISHED");
    }

    @SneakyThrows
    private void load() {
        System.out.println("Loader is working...");
        while (!basket.isFull() && !point.isEmpty()) {
            Thread.sleep(delay);
            basket.push(point.pull());
        }
    }

    private boolean needWaiting() {
        return !point.isEmpty();
    }
}
