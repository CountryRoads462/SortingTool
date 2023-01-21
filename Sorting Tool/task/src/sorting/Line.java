package sorting;

public class Line {
    private final int numberOfOccurrences;
    private final String line;

    public Line(int numberOfOccurrences, String line) {
        this.numberOfOccurrences = numberOfOccurrences;
        this.line = line;
    }

    public int getNumberOfOccurrences() {
        return numberOfOccurrences;
    }

    public String getLine() {
        return line;
    }
}
