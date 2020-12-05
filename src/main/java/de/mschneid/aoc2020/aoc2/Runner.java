package de.mschneid.aoc2020.aoc2;

import de.mschneid.util.FileReader;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Runner {

    public static void main(String[] args) {
        List<String> passwords = FileReader.readLines("aoc2-2020-data.txt");
        Pattern p = Pattern.compile("-?\\d+");

        List<String[]> rules = passwords.stream()
                .map(x -> x.replace("-", " ").replace(":", ""))
                .map(x -> x.split(" "))
                .collect(Collectors.toList());
        part1(rules);
        part2(rules);

    }

    private static void part1(List<String[]> rules) {
        int counter = 0;
        for (String[] x : rules) {
            int minValue = Integer.parseInt(x[0]);
            int maxValue = Integer.parseInt(x[1]);
            char key = x[2].charAt(0);
            String password = x[3];

            long count = password.chars()
                    .filter(ch -> ch == key)
                    .count();
            if (count >= minValue && count <= maxValue) {
                counter++;
            }
        }
        System.out.println(counter);
    }

    private static void part2(List<String[]> rules) {
        int counter = 0;
        for (String[] x : rules) {
            int position1 = Integer.parseInt(x[0]);
            int position2 = Integer.parseInt(x[1]);
            char key = x[2].charAt(0);
            String password = x[3];

            char charAtPos1 = password.charAt(position1 - 1);
            char charAtPos2 = password.charAt(position2 - 1);

            long count = ("" + charAtPos1 + charAtPos2).chars().filter(ch -> ch == key).count();
            if (count == 1) {
                counter++;
            }
        }
        System.out.println(counter);
    }
}
