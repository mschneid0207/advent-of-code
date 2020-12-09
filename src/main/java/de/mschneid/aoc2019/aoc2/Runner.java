package de.mschneid.aoc2019.aoc2;

import de.mschneid.util.FileReader;

import java.util.Arrays;

import static java.lang.String.format;

public class Runner {

    public static void main(String[] args) {
        AdventOfCode2 aoc = new AdventOfCode2();
        String line = FileReader.readLine("aoc2-data.txt");
        int[] codes = resetCodes(line);
        codes[1] = 12;
        codes[2] = 2;
        aoc.executeProgram(codes, 0);
        System.out.println(codes[0]);

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                codes = resetCodes(line);
                codes[1] = i;
                codes[2] = j;
                aoc.executeProgram(codes, 0);
                if (codes[0] == 19690720) {
                    System.out.println("noun: " + i + " verb: " + j);
                    return;
                }
            }
        }

    }

    private static int[] resetCodes(String lineOfCodes) {
        return Arrays.stream(lineOfCodes.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
