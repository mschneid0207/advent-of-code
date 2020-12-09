package de.mschneid.aoc2019.aoc5;

import de.mschneid.util.FileReader;

import java.util.Arrays;

public class Runner {

    public static void main(String[] args) {
        AdventOfCode5 aoc = new AdventOfCode5();
        String line = FileReader.readLine("aoc5-data.txt");
        int[] codes = resetCodes(line);
            aoc.executeProgram(codes, 0, 5);
        //System.out.println(codes[0]);


    }

    private static int[] resetCodes(String lineOfCodes) {
        return Arrays.stream(lineOfCodes.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
