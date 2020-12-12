package de.mschneid.aoc2020.aoc10;

import de.mschneid.util.FileReader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AoC10Runner {

    public static void main(String[] args) {
        List<String> puzzleLines = FileReader.readLines("aoc2020/aoc10-data-test.txt");

        List<Integer> adapters = puzzleLines.stream()
                .map(x -> Integer.parseInt(x))
                .collect(Collectors.toList());

        Integer maxAdapter = Collections.max(adapters);
        adapters.add(0);
        adapters.add(maxAdapter + 3);
        Collections.sort(adapters);

        adapters.forEach(x -> System.out.println(x));

        Day10 day10 = new Day10();
        long arrangements = day10.getArrangements(adapters);
        System.out.println(arrangements);

        int inputAdapter = 0;
        List<Integer> adaptersToCheck = new ArrayList<>();
        adaptersToCheck.add(inputAdapter);
        Result result = new Result();
        // Part1
        for (Integer adapter : adapters) {
            solvePart1(adapter, adapters, result);
        }
        System.out.println(result);
        System.out.println(result.getAdapterDifference1() * result.getAdapterDifference3());

        // Part2
        result = new Result();
        Integer[] defaultCombination = {0};
        List<Integer[]> combinations = new ArrayList<>();
        combinations.add(defaultCombination);
        result.setCombinations(combinations);

        for (int i = 0; i < adapters.size() - 1; i++) {
            Integer adapter = adapters.get(i);
            solvePart2(adapter, adapters, result);
        }
        System.out.println(result.getCombinations().size());
    }


    static void solvePart1(int inputAdapter, List<Integer> adapters, Result result) {
        result.getCheckedAdapters().add(inputAdapter);
        int adapterDiff1 = inputAdapter + 1;
        if (adapters.contains(adapterDiff1)) {
            int value = result.getAdapterDifference1() + 1;
            result.setAdapterDifference1(value);
            System.out.println("add 1 for " + inputAdapter);
            return;
        }
        int adapterDiff2 = inputAdapter + 2;
        if (adapters.contains(adapterDiff2)) {
            int value = result.getAdapterDifference2() + 1;
            result.setAdapterDifference2(value);
            return;
        }
        int adapterDiff3 = inputAdapter + 3;
        if (adapters.contains(adapterDiff3)) {
            int value = result.getAdapterDifference3() + 1;
            result.setAdapterDifference3(value);
            System.out.println("add 3 for " + inputAdapter);
            return;
        }

    }

    static void solvePart2(int inputAdapter, List<Integer> adapters, Result result) {
        List<Integer> possibilities = new ArrayList<>();
        int adapterDiff1 = inputAdapter + 1;
        if (adapters.contains(adapterDiff1)) {
            int value = result.getAdapterDifference1() + 1;
            result.setAdapterDifference1(value);
            //System.out.println("add 1 for " + inputAdapter);
            possibilities.add(adapterDiff1);
        }
        int adapterDiff2 = inputAdapter + 2;
        if (adapters.contains(adapterDiff2)) {
            int value = result.getAdapterDifference2() + 1;
            result.setAdapterDifference2(value);
            possibilities.add(adapterDiff2);
        }
        int adapterDiff3 = inputAdapter + 3;
        if (adapters.contains(adapterDiff3)) {
            int value = result.getAdapterDifference3() + 1;
            result.setAdapterDifference3(value);
            //System.out.println("add 3 for " + inputAdapter);
            possibilities.add(adapterDiff3);
        }

        List<String> stringCombinations = new ArrayList<>();
        List<Integer[]> newCombinations = new ArrayList<>();
        for (Integer[] combination : result.getCombinations()) {
            for (Integer pos : possibilities) {
                if (combination[combination.length - 1] < pos) {
                    Integer[] newComb = ArrayUtils.insert(combination.length, combination, pos);
                    //System.out.println("Add new one: " + Arrays.toString(newComb));
                    newCombinations.add(newComb);
                    stringCombinations.add(Arrays.toString(newComb));
                } else {
                    if (!stringCombinations.contains(Arrays.toString(combination))) {
                        //System.out.println("Add old one: " + Arrays.toString(combination));
                        newCombinations.add(combination);
                        stringCombinations.add(Arrays.toString(combination));
                    }
                }
            }
        }
        result.setCombinations(newCombinations);
        System.out.println("------- " + inputAdapter + " ---------");
        //newCombinations.forEach(x -> System.out.println(Arrays.toString(x)));
        //System.out.println("inputAdapter: " + inputAdapter + " combinations: " + newCombinations);
        int a = adapterDiff1;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Result {
        private int adapterDifference1 = 0;
        private int adapterDifference2 = 0;
        private int adapterDifference3 = 0;
        private List<Integer> checkedAdapters = new ArrayList<>();
        private List<Integer[]> combinations = new ArrayList<>();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Path {
        private int adapter;
        private List<Integer> combination = new ArrayList<>();
    }

}
