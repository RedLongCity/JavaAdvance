package course.advance;

import course.advance.exception.InterruptException;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
public class MacDac {

    private final Collection<Buyer> queue;

    public synchronized boolean service() {
        Buyer buyer = queue.stream().filter(Buyer::isNotServiced).findAny().orElse(null);
        if (buyer == null)
            return false;
        try {
            Thread.sleep(calculateDelay());
        } catch (InterruptedException ex) {
            throw new InterruptException("[MACDAC SERVICE Ex]: ", ex);
        }
        buyer.setServiced(true);
        System.out.println(buyer.getName() + " was serviced");
        if (isMacDacClosed()) {
            System.out.println("MackDac Closed!!");
            return false;
        }
        return true;
    }

    private int calculateDelay() {
        return (int) ((Math.random() * 8) + 3) * 1000;
    }

    private boolean isMacDacClosed() {
        return (int) (Math.random() * 2) == 1;
    }
}
