package de.mschneid.aoc2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AdventOfCode2Test {

    @Test
    public void test() {
        AdventOfCode2 aof2 = new AdventOfCode2();
        int[] codes = {1,0,0,0,99};
        int[] expectedCodes = {2,0,0,0,99};
        aof2.executeProgram(codes, 0);
        Assertions.assertArrayEquals(expectedCodes, codes);

        int[] codes2 = {2,3,0,3,99};
        int[] expectedCodes2 = {2,3,0,6,99};
        aof2.executeProgram(codes2, 0);
        Assertions.assertArrayEquals(expectedCodes2, codes2);

        int[] codes3 = {2,4,4,5,99,0};
        int[] expectedCodes3 = {2,4,4,5,99,9801};
        aof2.executeProgram(codes3, 0);
        Assertions.assertArrayEquals(expectedCodes3, codes3);
    }

}