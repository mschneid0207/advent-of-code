package de.mschneid.aoc2019.aoc1;

import de.mschneid.util.FileReader;

import java.util.List;

import static java.lang.String.format;

public class Runner {

    public static void main(String[] args) {
        AdventOfCode1 aoc = new AdventOfCode1();
        List<String> lines = FileReader.readLines("aoc1-data.txt");
        // Task 1
        int fuelSum = lines.stream()
                .mapToInt(x -> aoc.calculateFuelForMass(Integer.parseInt(x)))
                .sum();
        System.out.println(format("Fuel Sum: %s", fuelSum));

        // Task 2
        int fuelSumComplete = lines.stream()
                .mapToInt(x -> aoc.calculateFuelForMassAndFuelForFuelMass(Integer.parseInt(x)))
                .sum();
        System.out.println(format("Fuel Sum Complete: %s", fuelSumComplete));
    }
}
