package course.advance;

import course.advance.building.Barracks;
import course.advance.building.Building;
import course.advance.exception.InterruptException;
import course.advance.source.GoldMine;
import course.advance.source.Source;
import course.advance.worker.Worker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        Collection<Worker> workers = Collections.synchronizedCollection(new ArrayList<>());

        Source mine = new GoldMine(333, 1000);
        Building barracks = new Barracks(5000, workers);

        try {
            barracks.startSpawn();

            while (!checkAllFinished(workers)) {
                employWorkers(workers, mine);
            }

            barracks.stopSpawn();
            workers.forEach(Worker::showResult);
        } catch (InterruptException ex) {
            ex.printStackTrace();
        }
    }

    private static void employWorkers(Collection<Worker> workers, Source source) {
        workers.stream().filter(Worker::needWork).forEach(w -> w.startWorking(source));
    }

    private static boolean checkAllFinished(Collection<Worker> workers) {
        return !workers.isEmpty() && workers.stream().allMatch(Worker::isUnemployed);
    }


}
