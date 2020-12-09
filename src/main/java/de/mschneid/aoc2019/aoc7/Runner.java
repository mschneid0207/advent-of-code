package de.mschneid.aoc2019.aoc7;

import de.mschneid.util.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;

public class Runner {

    public static void main(String[] args) throws Exception {
        AdventOfCode7 aoc = new AdventOfCode7();
        String line = FileReader.readLine("aoc7-data.txt");
        System.out.println(format("Max thruster signal: %s", determineMaxThrusterSignal(line, aoc)));
        System.out.println(format("Max thruster signal with loop: %s", determineMaxThrusterSignalWithLoop(line, aoc)));
    }

    private static AtomicInteger determineMaxThrusterSignal(String line, AdventOfCode7 aoc) {
        AtomicInteger maxThrusterSignal = new AtomicInteger(0);
        List<int[]> combinations = new ArrayList<>();
        int[] phases = {0,1,2,3,4};
        CombinationUtil.printAllRecursive(5, phases, combinations);

        combinations.forEach(phaseSettings -> {
            AtomicInteger output = new AtomicInteger(0);
            for (int i = 0; i < phaseSettings.length; i++) {
                // reset codes
                int[] codes = resetCodes(line);
                int[] params = {phaseSettings[i], output.get()};
                aoc.resetInputParamPosition();
                aoc.executeProgram(codes, new AtomicInteger(0), params, output, null);
            }
            maxThrusterSignal.set(Math.max(output.get(), maxThrusterSignal.get()));

        });
        return maxThrusterSignal;
    }

    private static AtomicInteger determineMaxThrusterSignalWithLoop(String line, AdventOfCode7 aoc) {
        AtomicInteger maxThrusterSignal = new AtomicInteger(0);
        List<int[]> combinations = new ArrayList<>();
        int[] phases = {5,6,7,8,9};
        CombinationUtil.printAllRecursive(5, phases, combinations);

        combinations.forEach(phaseSettings -> {
            AtomicInteger output = new AtomicInteger(0);
            AtomicInteger finishState = new AtomicInteger(0);
            boolean loopMode = false;
            List<int[]> codeList = Arrays.asList(resetCodes(line), resetCodes(line), resetCodes(line), resetCodes(line), resetCodes(line));
            List<AtomicInteger> positions = Arrays.asList(new AtomicInteger(0), new AtomicInteger(0), new AtomicInteger(0), new AtomicInteger(0), new AtomicInteger(0));

            for (int i = 0; i < phaseSettings.length; i++) {
                int[] codes = codeList.get(i);
                AtomicInteger position = positions.get(i);
                int[] params = {phaseSettings[i], output.get()};
                if (loopMode) {
                    aoc.setInputParamPositionToSecondParam();
                } else {
                    aoc.resetInputParamPosition();
                }
                aoc.executeProgram(codes, position, params, output, finishState);

                if (finishState.get() == 99) {
                    // we leave the for loop, the last output is our signal
                    break;
                }

                if (i == phaseSettings.length -1 ) {
                    // loop
                    i = -1;
                    loopMode = true;
                }
            }
            // set the greater of the two values
            maxThrusterSignal.set(Math.max(output.get(), maxThrusterSignal.get()));

        });
        return maxThrusterSignal;
    }

    private static int[] resetCodes(String lineOfCodes) {
        return Arrays.stream(lineOfCodes.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
