package de.mschneid.aoc7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdventOfCode7Test {

    private AdventOfCode7 sut;

    @BeforeEach
    public void setUp() {
        sut = new AdventOfCode7();
    }

    @Test
    public void executeProgram() {
        int[] codes = {3, 15, 3, 16, 1002, 16, 10, 16, 1, 16, 15, 15, 4, 15, 99, 0, 0};
        int[] params = {0, 4321};
        sut.executeProgram(codes, new AtomicInteger(0), params, null, null);
    }

    @Test
    public void executeProgram54321() {
        AtomicInteger output = new AtomicInteger(0);
        int[] phaseSettings = {0,1,2,3,4};
        for (int i = 0; i < phaseSettings.length; i++) {
            // reset codes
            int[] codes = resetCodes();
            int[] params = {phaseSettings[i], output.get()};
            sut.resetInputParamPosition();
            sut.executeProgram(codes, new AtomicInteger(0), params, output, output);
        }
        assertEquals(54321, output.get());
    }

    @Test
    public void executeProgramWithPhases56789() {
        AtomicInteger output = new AtomicInteger(0);
        AtomicInteger finishState = new AtomicInteger(0);
        int[] phaseSettings = {9,8,7,6,5};
        List<int[]> codeList = Arrays.asList(resetCodes2(), resetCodes2(), resetCodes2(), resetCodes2(), resetCodes2());
        List<AtomicInteger> pointers = Arrays.asList(new AtomicInteger(0), new AtomicInteger(0), new AtomicInteger(0), new AtomicInteger(0), new AtomicInteger(0));
        boolean loopMode = false;
        for (int i = 0; i < phaseSettings.length; i++) {
            int[] codes = codeList.get(i);
            AtomicInteger pointer = pointers.get(i);
            int[] params = {phaseSettings[i], output.get()};
            if (loopMode) {
                sut.setInputParamPositionToSecondParam();
            } else {
                sut.resetInputParamPosition();
            }
            sut.executeProgram(codes, pointer, params, output, finishState);

            if (finishState.get() == 99) {
                System.out.println("FINISH: " + i);
                break;
            }

            if (i == phaseSettings.length -1 ) {
                i = -1;
                loopMode = true;
            }
        }
        Assertions.assertEquals(139629729, output.get());
    }

    @Test
    public void executeProgramWithPhases97856() {
        AtomicInteger output = new AtomicInteger(0);
        AtomicInteger finishState = new AtomicInteger(0);
        int[] phaseSettings = {9,7,8,5,6};
        List<int[]> codeList = Arrays.asList(resetCodes3(), resetCodes3(), resetCodes3(), resetCodes3(), resetCodes3());
        List<AtomicInteger> pointers = Arrays.asList(new AtomicInteger(0), new AtomicInteger(0), new AtomicInteger(0), new AtomicInteger(0), new AtomicInteger(0));
        boolean loopMode = false;
        for (int i = 0; i < phaseSettings.length; i++) {
            int[] codes = codeList.get(i);
            AtomicInteger pointer = pointers.get(i);
            int[] params = {phaseSettings[i], output.get()};
            if (loopMode) {
                sut.setInputParamPositionToSecondParam();
            } else {
                sut.resetInputParamPosition();
            }
            sut.executeProgram(codes, pointer, params, output, finishState);

            if (finishState.get() == 99) {
                System.out.println("FINISH " + i);
                break;
            }

            if (i == phaseSettings.length -1 ) {
                i = -1;
                loopMode = true;
            }
        }
        Assertions.assertEquals(18216, output.get());
    }


    private int[] resetCodes() {
        int[] resetCodes = {3,23,3,24,1002,24,10,24,1002,23,-1,23,
                101,5,23,23,1,24,23,23,4,23,99,0,0};
        return resetCodes;
    }

    private int[] resetCodes2() {
        int[] resetCodes = {3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,
                27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5};
        return resetCodes;
    }

    private int[] resetCodes3() {
        int[] resetCodes = {3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,
                -5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,
                53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10};
        return resetCodes;
    }

}