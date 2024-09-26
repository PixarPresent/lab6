package data.recources.comparators;

import data.recources.Status;

import java.util.Comparator;

public class StatusComparator implements Comparator<Status> {
    @Override
    public int compare(Status o1, Status o2) {
        return o1.toString().length() - o2.toString().length();
    }
}
