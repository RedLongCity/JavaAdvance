package course.advance.work;


import course.advance.Basket;
import course.advance.Gates;
import course.advance.Point;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class Driving implements WorkType {

    private final Gates gates;
    private final int delay;
    private final Basket basket;
    private final Point point;

    @Override
    @SneakyThrows
    public void doWork() {
        while (isNeedWaiting()) {

            System.out.println("Driver is waiting...");
            gates.waitStartGate();

            drive();
            gates.notifyFinishGate();

            System.out.println("Driver is waiting...");
            gates.waitFinishGate();

            if (!isNeedWaiting())
                break;
            drive();
            gates.notifyStartGate();
        }
        System.out.println("DRIVER IS FINISHED");
    }

    @SneakyThrows
    private void drive() {
        System.out.println("Driver is working...");
        Thread.sleep(delay);
    }

    private boolean isNeedWaiting() {
        return !point.isEmpty() || basket.isNotEmpty();
    }
}
