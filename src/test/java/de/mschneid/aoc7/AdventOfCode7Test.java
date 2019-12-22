package de.mschneid.aoc7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        sut.executeProgram(codes, 0, params, null);
    }

    @Test
    public void executeProgram54321() {
        AtomicInteger output = new AtomicInteger(0);
        int[] phaseSettings = {0,1,2,3,4};
        for (int i = 0; i < phaseSettings.length; i++) {
            // reset codes
            int[] codes = resetCodes();
            int[] params = {phaseSettings[i], output.get()};
            sut.resetProgram();
            sut.executeProgram(codes, 0, params, output);
        }
        assertEquals(54321, output.get());
    }

    @Test
    public void executeProgramWithPhases56789() {
        int[] codes = {3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,
                27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5};
        AtomicInteger output = new AtomicInteger(0);
        int[] phaseSettings = {9,8,7,6,5};

        for (int i = 0; i < phaseSettings.length; i++) {
            // reset codes
            int[] params = {phaseSettings[i], output.get()};
            sut.resetProgram();
            sut.executeProgram(codes, 0, params, output);
        }
    }

    private int[] resetCodes() {
        int[] resetCodes = {3,23,3,24,1002,24,10,24,1002,23,-1,23,
                101,5,23,23,1,24,23,23,4,23,99,0,0};
        return resetCodes;
    }

}