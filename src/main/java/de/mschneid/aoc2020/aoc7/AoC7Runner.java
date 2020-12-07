package de.mschneid.aoc2020.aoc7;

import de.mschneid.util.FileReader;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class AoC7Runner {

    public static void main(String[] args) {
        List<String> puzzleLines = FileReader.readLines("aoc7-2020-data.txt");

        Map<String, String[]> roughBagsMap = puzzleLines.stream()
                .map(puzzleLine -> puzzleLine.split("bags contain"))
                .collect(Collectors.toMap(x -> x[0].trim(), x -> x[1].split(",")));

        Map<String, List<Bag>> bagsMap = roughBagsMap.entrySet().stream()
                .collect(Collectors.toMap(x -> x.getKey(), x -> {
                    List<Bag> bagInfos = new ArrayList<>();
                    Arrays.stream(x.getValue()).forEach(y -> {
                        String innerBag = y.replace("bags", "")
                                .replace("bag", "")
                                .replace(".", "")
                                .trim();
                        int index = innerBag.indexOf(" ");
                        String quantity = innerBag.substring(0, index);
                        String bagName = innerBag.substring(index + 1, innerBag.length());
                        System.out.println("BagName: " + bagName + "; Quantity: " + quantity);
                        if (!quantity.equals("no")) {
                            bagInfos.add(new Bag(bagName, parseInt(quantity)));
                        }
                    });
                    return bagInfos;
                }));

        String bagToFind = "shiny gold";
        // part1
        Set bagSet = new HashSet();
        findBagsWhichContainsColor(bagToFind, bagsMap, bagSet);
        System.out.println("Part1" + bagSet.size());

        // part2
        AtomicInteger sum = new AtomicInteger(0);
        countBagsWhichContainsColor(bagToFind, bagsMap, sum, 1);
        System.out.println("Part2: " + sum);


    }

    static void findBagsWhichContainsColor(String bagColor, Map<String, List<Bag>> bagsMap, Set<String> bagSet) {
        bagsMap.forEach((key, value) -> value.stream()
                .filter(x -> (bagColor.equals(x.getName())))
                .findAny()
                .ifPresent(y -> {
                    bagSet.add(key);
                    findBagsWhichContainsColor(key, bagsMap, bagSet);
                }));
    }

    static void countBagsWhichContainsColor(String bagColor, Map<String, List<Bag>> bagsMap, AtomicInteger sum, int factor) {
        bagsMap.forEach((key, value) -> {
                    if (bagColor.equals(key)) {
                        int sumForBag = value.stream()
                                .mapToInt(Bag::getQty)
                                .sum();
                        System.out.println("Bag: " + bagColor + " Sum: " + factor * sumForBag);
                        sum.addAndGet(factor * sumForBag);
                        value.forEach(bag -> {
                            countBagsWhichContainsColor(bag.getName(), bagsMap, sum, factor * bag.getQty());
                        });
                    }
                }
        );
    }

    @Data
    @AllArgsConstructor
    static class Bag {
        private String name;
        private int qty;
    }
}
