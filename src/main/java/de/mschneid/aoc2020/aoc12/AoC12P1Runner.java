package de.mschneid.aoc2020.aoc12;

import de.mschneid.util.FileReader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;

public class AoC12P1Runner {

    static List<Direction> directions = Arrays.asList(Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.NORTH);

    public static void main(String[] args) {
        List<String> puzzleLines = FileReader.readLines("aoc2020/aoc12-data.txt");

        List<Instruction> instructions = new ArrayList<>();
        for (String line : puzzleLines) {
            String command = line.substring(0, 1);
            int value = parseInt(line.substring(1));
            instructions.add(new Instruction(command, value));
        }

        Course course = new Course();

        for (Instruction instr : instructions) {
            switch (instr.command) {
                case "F":
                    move(course, instr.value);
                    break;
                case "N":
                    course.wpY += instr.value;
                    break;
                case "S":
                    course.wpY -= instr.value;
                    break;
                case "E":
                    course.wpX += instr.value;
                    break;
                case "W":
                    course.wpX -= instr.value;
                    break;
                default:
                    moveDegrees(course, instr);
            }
        }
        System.out.println("Part1: " + (abs(course.wpX) + abs(course.wpY)));
    }

    static void moveDegrees(Course course, Instruction instruction) {
        int turn = instruction.getValue() / 90;
        int turnWithDirection = instruction.getCommand().equals("R") ? turn : turn * -1;
        course.setDirection(getNewDirection(course.direction, turnWithDirection));
    }

    private static Direction getNewDirection(Direction old, int turn) {
        int index = directions.indexOf(old);
        int newIndex;
        if ((index + turn) < 0) {
            newIndex = 4 - abs(index + turn);
        } else {
            newIndex = (index + turn) % 4;
        }
        return directions.get(newIndex);
    }


    static void move(Course course, int value) {
        switch (course.direction) {
            case EAST:
                course.wpX += value;
                break;
            case WEST:
                course.wpX -= value;
                break;
            case NORTH:
                course.wpY += value;
                break;
            case SOUTH:
                course.wpY -= value;
                break;
        }
    }

    @Data
    @AllArgsConstructor
    static class Instruction {
        private String command;
        private int value;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Course {
        int wpX = 0;
        int wpY = 0;
        Direction direction = Direction.EAST;
    }

    public enum Direction {
        EAST,
        SOUTH,
        WEST,
        NORTH
    }


}
