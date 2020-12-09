package de.mschneid.aoc2020.aoc8;

import de.mschneid.util.FileReader;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class AoC8Runner {

    public static void main(String[] args) {
        List<String> puzzleLines = FileReader.readLines("aoc2020/aoc8-data.txt");

       /* Map<String, String> instructionsMap = puzzleLines.stream()
                .map(x -> x.split(" "))
                .collect(Collectors.toList(y -> new Instruction(y[0], y[1])));*/

        List<Instruction> instructions = new ArrayList<>();
        for (String line : puzzleLines) {
            String[] s = line.split(" ");
            instructions.add(new Instruction(s[0], s[1]));
        }

        // part1
        Result result = runProgram(instructions);
        System.out.println("Part1: " + result);

        //part2
        for (int i = 0; i < instructions.size(); i++) {
            Instruction originalInstr = instructions.get(i);
            inverseCommand(originalInstr);
            Result resultPart2 = runProgram(instructions);
            if (!resultPart2.infinite) {
                System.out.println(resultPart2);
                System.out.println("Part2: " + result);
                break;
            }
            inverseCommand(originalInstr);
        }


    }


    private static Result runProgram(List<Instruction> instructions) {
        int terminationIndex = instructions.size() - 1;
        int acc = 0;
        int index = 0;
        Set<Integer> indexDirectory = new HashSet<>();
        Result result = null;
        boolean runSignal = true;

        while (runSignal) {
            // if infinite then we interrupt
            if (indexDirectory.contains(index)) {
                runSignal = false;
                result = new Result(true, acc);
                break;
            }
            // if index is higher than the last element we interrupt (WE FIXED THE PROGRAM)
            if (index > terminationIndex) {
                runSignal = false;
                result = new Result(false, acc);
                break;
            }
            indexDirectory.add(index);
            Instruction instruction = instructions.get(index);
            switch (instruction.getCommand()) {
                case "nop":
                    index++;
                    break;
                case "acc":
                    acc += parseInt(instruction.getOperation());
                    index++;
                    break;
                case "jmp":
                    index += parseInt(instruction.getOperation());
                    break;
            }
        }

        return result;
    }


    static void inverseCommand(Instruction instr) {
        switch (instr.getCommand()) {
            case "nop":
                instr.setCommand("jmp");
                break;
            case "jmp":
                instr.setCommand("nop");
                break;
        }
    }


    @Data
    @AllArgsConstructor
    static class Instruction {
        private String command;
        private String operation;
    }

    @Data
    @AllArgsConstructor
    static class Result {
        private boolean infinite;
        private int value;
    }
}
