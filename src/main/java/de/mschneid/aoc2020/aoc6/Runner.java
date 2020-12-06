package de.mschneid.aoc2020.aoc6;

import de.mschneid.util.FileReader;

import java.util.*;

public class Runner {

    public static void main(String[] args) {
        List<String> puzzleLines = FileReader.readLines("aoc6-2020-data.txt");

        puzzleLines.forEach(x -> System.out.println(x));

        String group = "";
        List<String> groups = new ArrayList<>();
        //part1(puzzleLines, group, groups);
        part2(puzzleLines);

    }

    private static void part1(List<String> puzzleLines, String group, List<String> groups) {
        // collect groups
        for (int i = 0; i < puzzleLines.size(); i++) {
            String line = puzzleLines.get(i);
            if (line.isEmpty()) {
                groups.add(group);
                group = "";
            } else if (i == puzzleLines.size() - 1) {
                group = group + line;
                groups.add(group);
            } else {
                group = group + line;
            }
        }
        groups.forEach(x -> System.out.println(x));

        // count yes answer and sum together

        int sumYes = 0;

        for (String value : groups) {
            Set<Character> answers = new HashSet<Character>();
            for (int i = 0; i < value.length(); i++) {
                Character answer = value.charAt(i);
                answers.add(answer);
            }
            sumYes = sumYes + answers.size();
        }

        System.out.println(sumYes);
    }

    private static void part2(List<String> puzzleLines) {
        String group = "";
        List<String> groups = new ArrayList<>();
        // collect groups
        for (int i = 0; i < puzzleLines.size(); i++) {
            String line = puzzleLines.get(i);
            if (line.isEmpty()) {
                groups.add(group);
                group = "";
            } else if (i == puzzleLines.size() - 1) {
                if (group.isEmpty()) {
                    group = line;
                } else {
                    group = group + " " + line;
                }
                groups.add(group);
            } else if (group.isEmpty()) {
                group = line;
            } else {
                group = group + " " + line;
            }
        }
        groups.forEach(x -> System.out.println(x));

        // count yes answer and sum together

        int sumYes = 0;

        for (String value : groups) {
            String[] answersOfPerons = value.split(" ");
            System.out.println(Arrays.toString(answersOfPerons) + " " + answersOfPerons.length);

            HashMap<Character, Integer> answerMap = new HashMap<>();
            for (String x : answersOfPerons) {
                for (int j = 0; j < x.length(); j++) {
                    Character answer = x.charAt(j);
                    if (answerMap.containsKey(answer)) {
                        Integer sum = answerMap.get(answer);
                        Integer newSum = sum + 1;
                        answerMap.put(answer, newSum);
                    } else {
                        answerMap.put(answer, 1);
                    }
                }
            }

            for (Map.Entry<Character, Integer> entry : answerMap.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
                if (entry.getValue() == answersOfPerons.length) {
                    sumYes++;
                }
            }

        }

        System.out.println(sumYes);

       /* int sumYes = 0;

        for (String value : groups) {
            Set<Character> answers = new HashSet<Character>();
            for (int i = 0; i < value.length(); i++) {
                Character answer = value.charAt(i);
                answers.add(answer);
            }
            sumYes = sumYes + answers.size();
        }

        System.out.println(sumYes);*/
    }


}
