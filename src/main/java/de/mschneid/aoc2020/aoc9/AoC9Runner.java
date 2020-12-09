package de.mschneid.aoc2020.aoc9;

import de.mschneid.util.FileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AoC9Runner {

    public static void main(String[] args) {
        List<String> puzzleLines = FileReader.readLines("aoc9-2020-data.txt");

        int limit = 5;
        int index = 0;

        List<Long> numberList = new ArrayList<>();
        for (String line : puzzleLines) {
            numberList.add(Long.parseLong(line));
        }

        boolean isValid = true;
        while (isValid) {
            long numberToCheck = numberList.get(index + limit);
            System.out.println("numberToCheck: " + numberToCheck);
            List<Long> previousNumberListCombinations = getPreviousNumberListCombinations(limit, index, numberList);
            previousNumberListCombinations.forEach(x -> System.out.println(x));
            if (previousNumberListCombinations.contains(numberToCheck)) {
                index++;
                System.out.println(numberToCheck + " was correct;");
            } else {
                isValid = false;
                System.out.println(numberToCheck + " was incorrect;");
            }
        }

        // part2
        index = 0;
        long error = 133015568;
        boolean isValidPart2 = false;
        while (!isValidPart2) {
            isValidPart2 = getCorrectCombination(index, numberList, error);
            index++;
        }

    }

    private static boolean getCorrectCombination(int index, List<Long> numberList, long error) {
        List<Long> numbers = new ArrayList<>();
        long sum = 0;
        boolean isValid = false;
        for (int i = index; i < numberList.size(); i++) {
            long num = numberList.get(i);
            numbers.add(num);
            sum = sum + num;
            if (sum == error) {
                isValid = true;
                break;
            }
            if (sum > error) {
                System.out.println("Break sum is: " + sum);
                break;
            }
        }
        if (isValid) {
            long min = Collections.min(numbers);
            long max = Collections.max(numbers);
            System.out.println(min + max);
        }
        return isValid;
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
