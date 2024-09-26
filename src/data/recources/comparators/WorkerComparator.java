package data.recources.comparators;

import data.recources.Worker;

import java.util.Comparator;

public class WorkerComparator implements Comparator<Worker> {

    @Override
    public int compare(Worker o1, Worker o2) {
        return (int) (o1.getSalary() - o2.getSalary());
    }
}
