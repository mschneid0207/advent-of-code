package de.mschneid.aoc2;

public class AdventOfCode2 {

    public void executeProgram(int[] codes, int position) {
        int opcode = codes[position];
        if (opcode == 99) {
            return;
        } else if (opcode == 1) {
            int firstPos = codes[position + 1];
            int secondPos = codes[position + 2];
            int thirdPos = codes[position + 3];
            codes[thirdPos] = codes[firstPos] + codes[secondPos];
            executeProgram(codes, position + 4);
        } else if (opcode == 2) {
            int firstPos = codes[position + 1];
            int secondPos = codes[position + 2];
            int thirdPos = codes[position + 3];
            codes[thirdPos] = codes[firstPos] * codes[secondPos];
            executeProgram(codes, position + 4);
        } else {
            System.out.println("error at the program");
        }
    }
}
