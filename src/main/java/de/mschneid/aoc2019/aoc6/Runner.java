package de.mschneid.aoc2019.aoc6;

import de.mschneid.util.FileReader;

import java.util.List;

public class Runner {

    public static void main(String[] args) throws Exception {
        AdventOfCode6 aoc = new AdventOfCode6();
        List<String> lines = FileReader.readLines("aoc6-data.txt");
        System.out.println(aoc.countTotalNumberOfDirectAndIndirectOrbits(lines));
        System.out.println(aoc.getAmountOfMinOrbitalTransfers(lines, "YOU", "SAN"));
    }
}
