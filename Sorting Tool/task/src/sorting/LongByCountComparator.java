package sorting;

import java.util.Comparator;

public class LongByCountComparator implements Comparator<LongNumber> {

    @Override
    public int compare(LongNumber number1, LongNumber number2) {
        int numOfOcc1 = number1.getNumberOfOccurrences();
        int numOfOcc2 = number2.getNumberOfOccurrences();
        if (numOfOcc1 == numOfOcc2) {
            return Long.compare(number1.getValue(), number2.getValue());
        } else {
            return Integer.compare(numOfOcc1, numOfOcc2);
        }
    }
}
