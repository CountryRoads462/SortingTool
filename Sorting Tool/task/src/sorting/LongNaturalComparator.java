package sorting;

import java.util.Comparator;

public class LongNaturalComparator implements Comparator<Long> {

    @Override
    public int compare(Long long1, Long long2) {
        return long1.compareTo(long2);
    }
}
