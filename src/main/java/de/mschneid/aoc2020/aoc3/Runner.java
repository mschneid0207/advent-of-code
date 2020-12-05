package de.mschneid.aoc2020.aoc3;

import de.mschneid.util.FileReader;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Runner {

    public static void main (String[] args) {
        List<String> lines = FileReader.readLines("aoc3-2020-data.txt");

        int[][] slopes = new int[][]{{1, 1}, {3, 1}, {5, 1}, {7, 1}, {1,2}};

        BigDecimal result = Arrays.stream(slopes)
                .map(x -> BigDecimal.valueOf(getTrees(x[0], x[1], lines)))
                .reduce(BigDecimal.ONE, BigDecimal::multiply);

        System.out.println(result);
    }

    private static int getTrees(int right, int down, List<String> puzzle) {
        int counter = 0;
        int index = 0;
        int offset = 0;

        for (int i = 0; i < puzzle.size(); i = i + down) {
            String line = puzzle.get(i);
            index = (offset * right) % line.length();
            if (line.charAt(index) == '#') {
                counter++;
            }
            offset++;
        }
        return counter;
    }
}
