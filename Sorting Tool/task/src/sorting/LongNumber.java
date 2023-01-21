package sorting;

public class LongNumber {
    private final int numberOfOccurrences;
    private final Long value;

    public LongNumber(int numberOfOccurrences, Long value) {
        this.numberOfOccurrences = numberOfOccurrences;
        this.value = value;
    }

    public int getNumberOfOccurrences() {
        return numberOfOccurrences;
    }

    public Long getValue() {
        return value;
    }
}
