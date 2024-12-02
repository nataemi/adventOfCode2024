package pl.ngalda.adventofcode2024.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day1Main {

    private static String CSV_FILE = "/Users/nataliagalda/IdeaProjects/adventOfCode2024/src/main/resources/day1input.csv";
    private static List<Integer> firstColumnLocations;
    private static List<Integer> secondColumnLocations;

    public static void main(String[] args) throws IOException {
        readColumns();
//        part1();
        part2();
    }

    private static void part2() {
        int similarityScore = 0;
        for(Integer locationId: firstColumnLocations){
            for(Integer locationId2: secondColumnLocations){
                if(locationId.equals(locationId2)) similarityScore += locationId;
            }
        }
        System.out.println(similarityScore);
    }

    private static void part1() throws IOException {
        List<Integer> firstColumnLocationsSorted = sortIntegerList(firstColumnLocations);
        List<Integer> secondColumnLocationsSorted = sortIntegerList(secondColumnLocations);
        Integer totalDistance = 0;
        for(int i = 0 ; i<firstColumnLocationsSorted.size(); i++){
            totalDistance += Math.abs(firstColumnLocationsSorted.get(i)-secondColumnLocationsSorted.get(i));
        }
        System.out.println(totalDistance);
    }

    private static void readColumns() throws IOException {
        firstColumnLocations = new ArrayList<>();
        secondColumnLocations = new ArrayList<>();
        List<List<String>> records = Files.readAllLines(Paths.get(CSV_FILE))
                .stream()
                .map(line -> Arrays.asList(line.split("   ")))
                .collect(Collectors.toList());
        divdeIntoColumn(firstColumnLocations, records, 0);
        divdeIntoColumn(secondColumnLocations, records, 1);
    }

    private static List<Integer> sortIntegerList(List<Integer> firstColumnLocations) {
        return firstColumnLocations.stream().sorted().collect(Collectors.toList());
    }

    private static void divdeIntoColumn(List<Integer> firstColumnLocations, List<List<String>> records, int i) {
        records.forEach(record -> firstColumnLocations.add(Integer.valueOf(record.get(i))));
    }
}
