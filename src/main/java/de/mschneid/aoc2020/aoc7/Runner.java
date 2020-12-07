package de.mschneid.aoc2020.aoc7;

import de.mschneid.util.FileReader;

import java.util.*;

public class Runner {

    public static void main(String[] args) {
        List<String> puzzleLines = FileReader.readLines("aoc7-2020-data.txt");

        //puzzleLines.forEach(x -> System.out.println(x));

        /*puzzleLines.stream()
                .map(x -> x.split("contain")).toArray()
                .collect(y -> Collectors.toMap(y[0], y[1]))*/

        Map<String, String> bagsMap = new HashMap<>();
        for (String line : puzzleLines) {
            String[] bags = line.split("contain");
            String key = bags[0].split("bags")[0];
            bagsMap.put(key, bags[1]);
        }

        String bagColorToFind = "shiny gold";
        Set<String> colorsToFind = new HashSet<>();
        colorsToFind.add(bagColorToFind);
        int sum = 0;
        while (colorsToFind.size() > 0) {
            Set<String> newColors = new HashSet<>();
            for (String color : colorsToFind) {
                Set<String> bagsWhichContainsColor = findBagsWhichContainsColor(color, bagsMap);
                newColors.addAll(bagsWhichContainsColor);
            }
            colorsToFind.clear();
            colorsToFind.addAll(newColors);
            System.out.println("New colors to find: " + colorsToFind.size() + "; " +  colorsToFind);
            sum = sum + colorsToFind.size();
        }
        System.out.println(sum);

    }

    static Set<String> findBagsWhichContainsColor(String bagColor, Map<String, String> bagsMap) {
        Set<String> colors = new HashSet<>();
        for (Map.Entry<String, String> entry : bagsMap.entrySet()) {
            if (entry.getValue().contains(bagColor)) {
                colors.add(entry.getKey());
            }
        }
        return colors;
    }


}
