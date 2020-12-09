package de.mschneid.aoc2020.aoc1;

import de.mschneid.aoc2019.aoc1.AdventOfCode1;
import de.mschneid.util.FileReader;

import java.util.List;

public class Runner {

    public static void main (String[] args) {
        System.out.println("Start");
        de.mschneid.aoc2019.aoc1.AdventOfCode1 aoc = new AdventOfCode1();
        List<String> lines = FileReader.readLines("aoc2020/aoc1-data.txt");
        lines.forEach(x -> System.out.println(x));

        for (int i = 0; i < lines.size(); i++) {
            int a = Integer.parseInt(lines.get(i));
            for (int j = i + 1; j < lines.size(); j++) {
                int b = Integer.parseInt(lines.get(j));
                if (a + b == 2020) {
                    int c = a * b;
                    System.out.println("Hey finished:" + c);
                }
            }
        }

        for (int i = 0; i < lines.size(); i++) {
            int a = Integer.parseInt(lines.get(i));
            for (int j = i + 1; j < lines.size(); j++) {
                int b = Integer.parseInt(lines.get(j));
                for (int k = j + 1; k < lines.size(); k++) {
                    int c = Integer.parseInt(lines.get(k));
                    if (a + b + c == 2020) {
                        int result = a * b * c;
                        System.out.println("Hey finished:" + result);
                    }
                }

            }
        }
    }
}
