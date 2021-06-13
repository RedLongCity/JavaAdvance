package course.advance.source;

import course.advance.exception.InterruptException;

public class GoldMine implements Source {

    private final int delay;
    private int resourcesAmount;

    public GoldMine(int delay, int resourcesAmount) {
        this.delay = delay;
        this.resourcesAmount = resourcesAmount;
    }

    @Override
    public Resource extract() {
        try {
            Thread.sleep(delay);

            synchronized (this) {
                if (resourcesAmount <= 0)
                    return Resource.NOTHING;
                this.resourcesAmount--;
                System.out.println("GoldMine contains: " + this.resourcesAmount + " gold");

                return Resource.GOLD;
            }
        } catch (InterruptedException ex) {
            throw new InterruptException("[GOLDMINE EXTRACT EX] :", ex);
        }
    }
}