package de.mschneid.aoc2020.aoc10;

import de.mschneid.util.FileReader;

import java.util.*;
import java.util.stream.Collectors;

public class AoC10Runner {

    public static void main(String[] args) {
        List<String> puzzleLines = FileReader.readLines("aoc2020/aoc10-data-test.txt");

        List<Integer> adapters = puzzleLines.stream()
                .map(x -> Integer.parseInt(x))
                .collect(Collectors.toList());

        Integer maxAdapter = Collections.max(adapters);
        adapters.add(maxAdapter + 3);
        Collections.sort(adapters);

        adapters.forEach(x -> System.out.println(x));

        int inputAdapter = 0;
        int adapter1Difference = 0;
        int adapter2Difference = 0;
        int adapter3Difference = 0;
        List<Integer> adaptersToCheck = new ArrayList<>();
        adaptersToCheck.add(inputAdapter);

        boolean isValid = true;

        while (true) {
            for (Integer adaptToCheck : adaptersToCheck) {
                Map<Integer, Integer> adaptersMap = determineJolts(adaptToCheck, adapters);
                System.out.println("test");
                if (adaptersMap.containsKey(1)) {
                    adapter1Difference += adaptersMap.get(1);
                }
                if (adaptersMap.containsKey(2)) {
                    adapter1Difference += adaptersMap.get(2);
                }
                if (adaptersMap.containsKey(3)) {
                    adapter1Difference += adaptersMap.get(3);
                }

                List<Integer> newAdapters = new ArrayList<>(adaptersMap.keySet());
                Collections.sort(newAdapters);

            }
        }
    }

    static void determineAdapters(Map<Integer, Integer> adaptersMap, List<Integer> adaptersToCheck, List<Integer> allAdapters) {
        for (Integer toCheck : adaptersToCheck) {
            Map<Integer, Integer> retMap = determineAdapter(toCheck, allAdapters);
            if (retMap.containsKey(1)) {
                int a = adaptersMap.get(1);
                adaptersMap.put(1, a + retMap.get(1));
            }
            if (retMap.containsKey(2)) {
                int a = adaptersMap.get(2);
                adaptersMap.put(2, a + retMap.get(2));
            }
            if (retMap.containsKey(3)) {
                int a = adaptersMap.get(3);
                adaptersMap.put(3, a + retMap.get(3));
            }
        }
    }

    static Map<Integer, Integer> determineAdapter(int inputAdapter, List<Integer> adapters) {
        Map<Integer, Integer> foundAdapters = new HashMap<>();
        int adapter1Diff = inputAdapter + 1;
        if (adapters.contains(adapter1Diff)) {
            foundAdapters.put(1, adapter1Diff);
        }
        int adapter2Diff = inputAdapter + 2;
        if (adapters.contains(adapter2Diff)) {
            foundAdapters.put(2, adapter2Diff);
        }
        int adapter3Diff = inputAdapter + 3;
        if (adapters.contains(adapter3Diff)) {
            foundAdapters.put(3, adapter3Diff);
        }
        return foundAdapters;
    }

}
