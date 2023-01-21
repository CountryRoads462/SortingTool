import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Utils {

    public static List<Integer> sortOddEven(List<Integer> numbers) {
        List<Integer> oddList = new ArrayList<>();
        List<Integer> evenList = new ArrayList<>();
        for (Integer number :
                numbers) {
            if (number % 2 == 0) {
                evenList.add(number);
            } else {
                oddList.add(number);
            }
        }
        oddList.sort(new OddIntegerComparator());
        evenList.sort(new EvenIntegerComparator());
        oddList.addAll(evenList);
        return oddList;
    }

    static class OddIntegerComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer integer1, Integer integer2) {
            return integer1.compareTo(integer2);
        }
    }

    static class EvenIntegerComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer integer1, Integer integer2) {
            if (integer1 > integer2) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}