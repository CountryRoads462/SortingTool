package sorting;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static java.lang.System.exit;

public class Main {

    static class Configuration {
        private DataType dataType;
        private SortingType sortingType;

        public Configuration(DataType dataType, SortingType sortingType) {
            this.dataType = dataType;
            this.sortingType = sortingType;
        }
    }

    enum DataType {
        LONG,
        LINE,
        WORD
    }

    enum SortingType {
        NATURAL,
        BY_COUNT
    }

    public static void main(final String[] args) {
        List<String> inValidArguments = new ArrayList<>();
        String inputFileName = "";
        String outputFileName = "";
        Configuration configuration = new Configuration(DataType.WORD, SortingType.NATURAL);
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-sortingType":
                    if (i + 1 < args.length) {
                        if (args[i + 1].equals("natural")) {
                            configuration.sortingType = (SortingType.NATURAL);
                            i++;
                        } else if (args[i + 1].equals("byCount")){
                            configuration.sortingType = (SortingType.BY_COUNT);
                            i++;
                        }
                    } else {
                        System.out.println("No sorting type defined!");
                        exit(0);
                    }
                    break;
                case "-dataType":
                    if (i + 1 < args.length) {
                        switch (args[i + 1]) {
                            case "word":
                                configuration.dataType = (DataType.WORD);
                                i++;
                                break;
                            case "long":
                                configuration.dataType = (DataType.LONG);
                                i++;
                                break;
                            default:
                                configuration.dataType = (DataType.LINE);
                                i++;
                                break;
                        }
                    } else {
                        System.out.println("No data type defined!");
                        exit(0);
                    }
                    break;
                case "-inputFile":
                    inputFileName = args[i + 1];
                    i++;
                    break;
                case "-outputFile":
                    outputFileName = args[i + 1];
                    i++;
                    break;
                default:
                    inValidArguments.add(args[i]);
            }
        }
        for (String inValidArg :
                inValidArguments) {
            System.out.printf("\"%s\" is not a valid parameter. It will be skipped.\n", inValidArg);
        }

        File inputFile = new File(inputFileName);
        File outputFile = new File("");
        if (!outputFileName.equals("")) {
            outputFile = new File(outputFileName);
        }

        Scanner scanner = new Scanner(System.in);
        int total;
        switch (configuration.dataType) {
            case LINE:
                if (configuration.sortingType == SortingType.NATURAL) {
                    List<String> listOfLines = new ArrayList<>();

                    if (!inputFileName.equals("")) {
                        try (Scanner fileScanner = new Scanner(inputFile)) {
                            while (fileScanner.hasNextLine()) {
                                listOfLines.add(fileScanner.nextLine());
                            }
                        } catch (IOException ignored) {
                        }
                    } else {
                        while (scanner.hasNextLine()) {
                            listOfLines.add(scanner.nextLine());
                        }
                    }


                    listOfLines.sort(new LineNaturalComparator());

                    if (!outputFileName.equals("")){
                        try (PrintWriter printWriter = new PrintWriter(outputFile)){
                            printWriter.println("Total lines: " + listOfLines.size());
                            printWriter.println("Sorted data:");
                            for (String line :
                                    listOfLines) {
                                printWriter.println(line);
                            }
                        } catch (IOException ignored) {
                        }
                    } else {
                        System.out.println("Total lines: " + listOfLines.size());
                        System.out.println("Sorted data:");
                        for (String line :
                                listOfLines) {
                            System.out.println(line);
                        }
                    }
                } else {
                    Map<String, Integer> mapOfLines = new HashMap<>();
                    total = 0;

                    if (!inputFileName.equals("")) {
                        try (Scanner fileScanner = new Scanner(inputFile)) {
                            while (fileScanner.hasNextLine()) {
                                String line = fileScanner.nextLine();
                                total++;
                                if (mapOfLines.containsKey(line)) {
                                    int num = mapOfLines.get(line);
                                    mapOfLines.put(line, num + 1);
                                } else {
                                    mapOfLines.put(line, 1);
                                }
                            }
                        } catch (IOException ignored) {
                        }
                    } else {
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            total++;
                            if (mapOfLines.containsKey(line)) {
                                int num = mapOfLines.get(line);
                                mapOfLines.put(line, num + 1);
                            } else {
                                mapOfLines.put(line, 1);
                            }
                        }
                    }

                    List<Line> listOfLines = new ArrayList<>();

                    for (var entry :
                            mapOfLines.entrySet()) {
                        Line line = new Line(entry.getValue(), entry.getKey());
                        listOfLines.add(line);
                    }

                    listOfLines.sort(new LineByCountComparator());

                    if (!outputFileName.equals("")){
                        try (PrintWriter printWriter = new PrintWriter(outputFile)){
                            printWriter.println("Total lines: " + total + ".");
                            for (Line line :
                                    listOfLines) {
                                printWriter.printf("%s: %d time(s), %d", line.getLine(), line.getNumberOfOccurrences(), line.getNumberOfOccurrences() * 100 / total);
                                printWriter.println("%");
                            }
                        } catch (IOException ignored) {
                        }
                    } else {
                        System.out.println("Total lines: " + total + ".");
                        for (Line line :
                                listOfLines) {
                            System.out.printf("%s: %d time(s), %d", line.getLine(), line.getNumberOfOccurrences(), line.getNumberOfOccurrences() * 100 / total);
                            System.out.println("%");
                        }
                    }
                }
                break;
            case LONG:
                if (configuration.sortingType == SortingType.NATURAL) {
                    List<Long> listOfLongs = new ArrayList<>();
                    List<String> notValidParameters = new ArrayList<>();

                    if (!inputFileName.equals("")) {
                        try (Scanner fileScanner = new Scanner(inputFile)) {
                            while (fileScanner.hasNext()) {
                                long longNumber;
                                String longAsString = fileScanner.next();
                                if (longAsString.matches("-?[0-9]+")) {
                                    longNumber = Long.parseLong(longAsString);
                                    listOfLongs.add(longNumber);
                                } else {
                                    notValidParameters.add(longAsString);
                                }
                            }
                        } catch (IOException ignored) {
                        }
                    } else {
                        while (scanner.hasNext()) {
                            long longNumber;
                            String longAsString = scanner.next();
                            if (longAsString.matches("-?[0-9]+")) {
                                longNumber = Long.parseLong(longAsString);
                                listOfLongs.add(longNumber);
                            } else {
                                notValidParameters.add(longAsString);
                            }
                        }
                    }

                    listOfLongs.sort(new LongNaturalComparator());

                    if (!outputFileName.equals("")){
                        try (PrintWriter printWriter = new PrintWriter(outputFile)){
                            for (String parameter :
                                    notValidParameters) {
                                printWriter.printf("\"%s\" is not a long. It will be skipped.\n", parameter);
                            }

                            printWriter.println("Total numbers: " + listOfLongs.size() + ".");
                            printWriter.print("Sorted data: ");
                            for (Long elem :
                                    listOfLongs) {
                                printWriter.print(elem + " ");
                            }
                        } catch (IOException ignored) {
                        }
                    } else {
                        for (String parameter :
                                notValidParameters) {
                            System.out.printf("\"%s\" is not a long. It will be skipped.\n", parameter);
                        }

                        System.out.println("Total numbers: " + listOfLongs.size() + ".");
                        System.out.print("Sorted data: ");
                        for (Long elem :
                                listOfLongs) {
                            System.out.print(elem + " ");
                        }
                    }
                } else {
                    Map<Long, Integer> mapOfLongs = new HashMap<>();
                    total = 0;

                    if (!inputFileName.equals("")) {
                        try (Scanner fileScanner = new Scanner(inputFile)) {
                            while (fileScanner.hasNextLong()) {
                                Long longNumber = fileScanner.nextLong();
                                total++;
                                if (mapOfLongs.containsKey(longNumber)) {
                                    int num = mapOfLongs.get(longNumber);
                                    mapOfLongs.put(longNumber, num + 1);
                                } else {
                                    mapOfLongs.put(longNumber, 1);
                                }
                            }
                        } catch (IOException ignored) {
                        }
                    } else {
                        while (scanner.hasNextLong()) {
                            Long longNumber = scanner.nextLong();
                            total++;
                            if (mapOfLongs.containsKey(longNumber)) {
                                int num = mapOfLongs.get(longNumber);
                                mapOfLongs.put(longNumber, num + 1);
                            } else {
                                mapOfLongs.put(longNumber, 1);
                            }
                        }
                    }

                    List<LongNumber> listOfLongs = new ArrayList<>();

                    for (var entry :
                            mapOfLongs.entrySet()) {
                        LongNumber longNumber = new LongNumber(entry.getValue(), entry.getKey());
                        listOfLongs.add(longNumber);
                    }

                    listOfLongs.sort(new LongByCountComparator());

                    if (!outputFileName.equals("")){
                        try (PrintWriter printWriter = new PrintWriter(outputFile)){
                            printWriter.println("Total numbers: " + total + ".");
                            for (LongNumber longNumber :
                                    listOfLongs) {
                                printWriter.printf("%s: %d time(s), %d", longNumber.getValue(), longNumber.getNumberOfOccurrences(), longNumber.getNumberOfOccurrences() * 100 / total);
                                printWriter.println("%");
                            }
                        } catch (IOException ignored) {
                        }
                    } else {
                        System.out.println("Total numbers: " + total + ".");
                        for (LongNumber longNumber :
                                listOfLongs) {
                            System.out.printf("%s: %d time(s), %d", longNumber.getValue(), longNumber.getNumberOfOccurrences(), longNumber.getNumberOfOccurrences() * 100 / total);
                            System.out.println("%");
                        }
                    }
                }
                break;
            default:
                if (configuration.sortingType == SortingType.NATURAL) {
                    List<String> listOfWords = new ArrayList<>();

                    if (!inputFileName.equals("")) {
                        try (Scanner fileScanner = new Scanner(inputFile)) {
                            while (fileScanner.hasNext()) {
                                listOfWords.add(fileScanner.next());
                            }
                        } catch (IOException ignored) {
                        }
                    } else {
                        while (scanner.hasNext()) {
                            listOfWords.add(scanner.next());
                        }
                    }

                    listOfWords.sort(new LineNaturalComparator());

                    if (!outputFileName.equals("")){
                        try (PrintWriter printWriter = new PrintWriter(outputFile)){
                            printWriter.println("Total words: " + listOfWords.size());
                            printWriter.println("Sorted data:");
                            for (String word :
                                    listOfWords) {
                                printWriter.println(word);
                            }
                        } catch (IOException ignored) {
                        }
                    }
                } else {
                    Map<String, Integer> mapOfWords = new HashMap<>();
                    total = 0;

                    if (!inputFileName.equals("")) {
                        try (Scanner fileScanner = new Scanner(inputFile)) {
                            while (fileScanner.hasNext()) {
                                String word = fileScanner.next();
                                total++;
                                if (mapOfWords.containsKey(word)) {
                                    int num = mapOfWords.get(word);
                                    mapOfWords.put(word, num + 1);
                                } else {
                                    mapOfWords.put(word, 1);
                                }
                            }
                        } catch (IOException ignored) {
                        }
                    } else {
                        while (scanner.hasNext()) {
                            String word = scanner.next();
                            total++;
                            if (mapOfWords.containsKey(word)) {
                                int num = mapOfWords.get(word);
                                mapOfWords.put(word, num + 1);
                            } else {
                                mapOfWords.put(word, 1);
                            }
                        }
                    }


                    List<Line> listOfWords = new ArrayList<>();

                    for (var entry :
                            mapOfWords.entrySet()) {
                        Line word = new Line(entry.getValue(), entry.getKey());
                        listOfWords.add(word);
                    }

                    listOfWords.sort(new LineByCountComparator());

                    if (!outputFileName.equals("")){
                        try (PrintWriter printWriter = new PrintWriter(outputFile)){
                            printWriter.println("Total words: " + total + ".");
                            for (Line line :
                                    listOfWords) {
                                printWriter.printf("%s: %d time(s), %d", line.getLine(), line.getNumberOfOccurrences(), line.getNumberOfOccurrences() * 100 / total);
                                printWriter.println("%");
                            }
                        } catch (IOException ignored) {
                        }
                    } else {
                        System.out.println("Total words: " + total + ".");
                        for (Line line :
                                listOfWords) {
                            System.out.printf("%s: %d time(s), %d", line.getLine(), line.getNumberOfOccurrences(), line.getNumberOfOccurrences() * 100 / total);
                            System.out.println("%");
                        }
                    }
                }
                break;
        }
    }
}
