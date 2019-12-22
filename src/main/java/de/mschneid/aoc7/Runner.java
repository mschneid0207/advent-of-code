package de.mschneid.aoc7;

import de.mschneid.util.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Runner {

    public static void main(String[] args) throws Exception {
        final AtomicInteger maxThrusterSignal = new AtomicInteger(0);

        AdventOfCode7 aoc = new AdventOfCode7();
        String line = FileReader.readLine("aoc7-data.txt");


        List<int[]> combinations = getCombinations2();


        combinations.forEach(phaseSettings -> {
            AtomicInteger output = new AtomicInteger(0);
            for (int i = 0; i < phaseSettings.length; i++) {
                // reset codes
                int[] codes = resetCodes(line);
                int[] params = {phaseSettings[i], output.get()};
                aoc.resetProgram();
                aoc.executeProgram(codes, 0, params, output);
            }
            System.out.println("current max thruster signal: " + maxThrusterSignal.get());
            System.out.println("output: " + output.get());
            maxThrusterSignal.set(Math.max(output.get(), maxThrusterSignal.get()));
            System.out.println("new max thruster signal: " + maxThrusterSignal.get());

        });
        //aoc.executeProgram(codes, 0, null  );
        System.out.println(maxThrusterSignal.get());

    }

    private static List<int[]> getCombinations() {
        List<int[]> combinations = new ArrayList<>();
        List<Integer> values = Arrays.asList(0, 1, 2, 3, 4);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    for (int l = 0; l < 5; l++) {
                        for (int m = 0; m < 5; m++) {
                            int[] combination = {i, j, k, l, m};
                            combinations.add(combination);
                        }
                    }
                }
            }
        }
        return combinations;
    }

    private static List<int[]> getCombinations2() {
        List<int[]> combinations = new ArrayList<>();
        List<Integer> firstPhaseList = Arrays.asList(0, 1, 2, 3, 4);
        for (int i = 0; i < firstPhaseList.size(); i++) {
            List<Integer> secondPhaseList = new ArrayList<>(firstPhaseList);
            Integer firstPhase = firstPhaseList.get(i);
            secondPhaseList.remove(firstPhase);
            for (int j = 0; j < secondPhaseList.size(); j++) {
                List<Integer> thirdPhaseList = new ArrayList<>(secondPhaseList);
                Integer secondPhase = secondPhaseList.get(j);
                thirdPhaseList.remove(secondPhase);
                for (int k = 0; k < thirdPhaseList.size(); k++) {
                    List<Integer> fourthPhaseList = new ArrayList<>(thirdPhaseList);
                    Integer thirdPhase = thirdPhaseList.get(k);
                    fourthPhaseList.remove(thirdPhase);
                    for (int l = 0; l < fourthPhaseList.size(); l++) {
                        List<Integer> fifthPhaseList = new ArrayList<>(fourthPhaseList);
                        Integer fourthPhase = fourthPhaseList.get(l);
                        fifthPhaseList.remove(fourthPhase);
                        for (int m = 0; m < fifthPhaseList.size(); m++) {
                            Integer fifthPhase = fifthPhaseList.get(m);
                            int[] combination = {firstPhase, secondPhase, thirdPhase, fourthPhase, fifthPhase};
                            combinations.add(combination);
                        }
                    }
                }
            }
        }

        return combinations;
    }

    private static int[] resetCodes(String lineOfCodes) {
        return Arrays.stream(lineOfCodes.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
