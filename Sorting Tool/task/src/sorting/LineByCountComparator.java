package sorting;

import java.util.Comparator;

public class LineByCountComparator implements Comparator<Line> {

    @Override
    public int compare(Line line1, Line line2) {
        int number1 = line1.getNumberOfOccurrences();
        int number2 = line2.getNumberOfOccurrences();
        if (number1 == number2) {
            return String.CASE_INSENSITIVE_ORDER.compare(line1.getLine(), line2.getLine());
        } else {
            return Integer.compare(number1, number2);
        }
    }
}
