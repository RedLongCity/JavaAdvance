package course.advance.work;

import course.advance.Basket;
import course.advance.Gates;
import course.advance.Point;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class Unloading implements WorkType {

    private final Gates gates;
    private final int delay;
    private final Basket basket;
    private final Point point;
    private final Point point2;

    @Override
    public void doWork() {
        while (isNeedWaiting()) {
            System.out.println("Unloader is waiting...");
            gates.waitFinishGate();
            this.unload();
            gates.notifyFinishGate();

            if (!isNeedWaiting())
                break;
        }
        System.out.println("UNLOADER IS FINISHED");
    }

    @SneakyThrows
    private void unload() {
        System.out.println("Unloader is working...");
        while (basket.isNotEmpty()) {
            Thread.sleep(delay);
            point2.push(basket.pull());
        }
    }

    private boolean isNeedWaiting() {
        return !point.isEmpty() || basket.isNotEmpty();
    }
}
