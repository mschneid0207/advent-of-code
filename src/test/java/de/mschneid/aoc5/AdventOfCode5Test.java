package de.mschneid.aoc5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdventOfCode5Test {

    private AdventOfCode5 sut;

    @BeforeEach
    public void setUp() {
        sut = new AdventOfCode5();
    }

    @Test
    public void test_add() {
        int[] codes = {1002,4,3,4,33};
        int[] expectedCodes = {1002,4,3,4,37};

        int[] modes = {0,0,0};
        sut.add(codes, 0, 33, 4);
        assertArrayEquals(expectedCodes, codes);
    }

    @Test
    public void test_add_with_immediate_mode() {
        int[] codes = {1002,4,3,4,33};
        int[] expectedCodes = {1002,4,3,4,7};

        int[] modes = {1,1,0};
        sut.add(codes, 0, 4, 3);
        assertArrayEquals(expectedCodes, codes);
    }

    @Test
    public void executeProgram() {
        int[] codes = {3,9,8,9,10,9,4,9,99,-1,8};
        int[] expectedCodes = {3,9,8,9,10,9,4,9,99,1,8};
        sut.executeProgram(codes, 0, 8);
        assertArrayEquals(expectedCodes, codes);

    }

    @Test
    public void executeProgram2() {
        int[] codes = {3,9,7,9,10,9,4,9,99,-1,8};
        int[] expectedCodes = {3,9,7,9,10,9,4,9,99,1,8};
        sut.executeProgram(codes, 0, 7);
        assertArrayEquals(expectedCodes, codes);

    }

    @Test
    public void executeProgram3() {
        int[] codes = {3,3,1105,-1,9,1101,0,0,12,4,12,99,1};
        sut.executeProgram(codes, 0, 0);
    }

    @Test
    public void executeProgram4() {
        int[] codes = {3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
                1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
                999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99};
        sut.executeProgram(codes, 0, 9);
    }

}