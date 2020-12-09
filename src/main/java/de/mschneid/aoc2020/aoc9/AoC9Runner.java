package de.mschneid.aoc2020.aoc9;

import de.mschneid.util.FileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;

public class AoC9Runner {

    public static void main(String[] args) {
        List<String> puzzleLines = FileReader.readLines("aoc2020/aoc9-data.txt");

        List<Long> numberList = puzzleLines.stream()
                .map(x -> parseLong(x))
                .collect(Collectors.toList());

        int limit = 25;
        int index = 0;

        // part1
        long resultPart1 = solvePart1(limit, index, numberList);
        System.out.println("Part1: " + resultPart1);

        // part2
        index = 0;
        boolean isValid = false;
        while (!isValid) {
            isValid = getCorrectCombination(index, numberList, resultPart1);
            index++;
        }

    }

    private static long solvePart1(int limit, int index, List<Long> numberList) {
        boolean isValid = true;
        while (isValid) {
            long numberToCheck = numberList.get(index + limit);
            List<Long> previousNumberListCombinations = getPreviousNumberListCombinations(limit, index, numberList);
            if (previousNumberListCombinations.contains(numberToCheck)) {
                index++;
                //System.out.println(numberToCheck + " was correct;");
            } else {
                System.out.println(numberToCheck + " was incorrect;");
                return numberToCheck;
            }
        }
        return 0;
    }

    private static boolean getCorrectCombination(int index, List<Long> numberList, long incorretNumber) {
        List<Long> numbers = new ArrayList<>();
        long sum = 0;
        boolean wasFound = false;
        for (int i = index; i < numberList.size(); i++) {
            long num = numberList.get(i);
            numbers.add(num);
            sum += num;
            if (sum == incorretNumber) {
                wasFound = true;
                break;
            }
            if (sum > incorretNumber) {
                break;
            }
        }

        if (wasFound) {
            long min = Collections.min(numbers);
            long max = Collections.max(numbers);
            System.out.println("Part2: " + (min + max));
        }
        return wasFound;
    }


    private static List<Long> getPreviousNumberListCombinations(int limit, int index, List<Long> numberList) {
        List<Long> previousNumbers = new ArrayList<>();
        for (int i = index; i < index + limit; i++) {
            for (int j = i + 1; j < index + limit; j++) {
                previousNumbers.add(Long.sum(numberList.get(i), numberList.get(j)));
            }
        }
        return previousNumbers;
    }


}
