package de.mschneid.aoc2020.aoc12;

import de.mschneid.util.FileReader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class AoC12Runner {

    public static void main(String[] args) {
        List<String> puzzleLines = FileReader.readLines("aoc2020/aoc12-data.txt");

        List<Instruction> instructions = new ArrayList<>();
        for (String line : puzzleLines) {
            String command = line.substring(0, 1);
            int value = parseInt(line.substring(1));
            instructions.add(new Instruction(command, value));
        }
        //instructions.forEach(x -> System.out.println(x));

        Course course = new Course();

        for (Instruction instr : instructions) {
            switch (instr.command) {
                case "F":
                    move(course, instr.getValue(), true);
                    break;
                case "N":
                    if (course.northSouthDirection == null) {
                        course.setNorthSouthDirection(Direction.NORTH);
                        course.northSouthPos += instr.getValue();
                        break;
                    }
                    switch (course.northSouthDirection) {
                        case NORTH:
                            course.northSouthPos += instr.getValue();
                            break;
                        case SOUTH:
                            course.northSouthPos -= instr.getValue();
                            break;
                    }
                    break;
                case "S":
                    if (course.northSouthDirection == null) {
                        course.setNorthSouthDirection(Direction.SOUTH);
                        break;
                    }
                    switch (course.northSouthDirection) {
                        case SOUTH:
                            course.northSouthPos += instr.getValue();
                            break;
                        case NORTH:
                            course.northSouthPos -= instr.getValue();
                            break;
                    }
                    break;
                case "E":
                    switch (course.eastWestDirection) {
                        case EAST:
                            course.eastWestPos += instr.getValue();
                            break;
                        case WEST:
                            course.eastWestPos -= instr.getValue();
                            break;
                    }
                    break;
                case "W":
                    switch (course.eastWestDirection) {
                        case WEST:
                            course.eastWestPos += instr.getValue();
                            break;
                        case EAST:
                            course.eastWestPos -= instr.getValue();
                            break;
                    }
                    break;
                default:
                    moveDegrees(course, instr);
            }
            System.out.println("Instruction: " + instr + " ; course: " + course);
        }
        System.out.println(course.eastWestPos + course.northSouthPos);
    }

    static void moveDegrees(Course course, Instruction instruction) {
        int turn = instruction.getValue() / 90;

        switch (course.mainDirection) {
            case EAST:
                if (turn == 1) {
                    course.setMainDirection(instruction.getCommand().equals("L") ?
                            Direction.NORTH :
                            Direction.SOUTH);
                    if (!course.getNorthSouthDirection().equals(course.getMainDirection())) {
                        course.northSouthPos = -course.northSouthPos;
                        course.setNorthSouthDirection(course.getMainDirection());
                    }
                }
                if (turn == 2) {
                    course.setMainDirection(Direction.WEST);
                    course.eastWestPos = -course.eastWestPos;
                    course.setEastWestDirection(Direction.WEST);
                }
                if (turn == 3) {
                    course.setMainDirection(instruction.getCommand().equals("L") ?
                            Direction.SOUTH :
                            Direction.NORTH);
                    if (!course.getNorthSouthDirection().equals(course.getMainDirection())) {
                        course.northSouthPos = -course.northSouthPos;
                        course.setNorthSouthDirection(course.getMainDirection());
                    }
                }
                break;
            case NORTH:
                if (turn == 1) {
                    course.setMainDirection(instruction.getCommand().equals("L") ?
                            Direction.WEST :
                            Direction.EAST);
                    if (!course.getEastWestDirection().equals(course.getMainDirection())) {
                        course.eastWestPos = -course.eastWestPos;
                        course.setEastWestDirection(course.getMainDirection());
                    }
                }
                if (turn == 2) {
                    course.setMainDirection(Direction.SOUTH);
                    course.setNorthSouthDirection(Direction.SOUTH);
                    course.northSouthPos = -course.northSouthPos;
                }
                if (turn == 3) {
                    course.setMainDirection(instruction.getCommand().equals("L") ?
                            Direction.EAST :
                            Direction.WEST);
                    if (!course.getEastWestDirection().equals(course.getMainDirection())) {
                        course.eastWestPos = -course.eastWestPos;
                        course.setEastWestDirection(course.getMainDirection());
                    }
                }

                break;
            case WEST:
                if (turn == 1) {
                    course.setMainDirection(instruction.getCommand().equals("L") ?
                            Direction.SOUTH :
                            Direction.NORTH);
                    if (!course.getNorthSouthDirection().equals(course.getMainDirection())) {
                        course.northSouthPos = -course.northSouthPos;
                        course.setNorthSouthDirection(course.getMainDirection());
                    }
                }
                if (turn == 2) {
                    course.setMainDirection(Direction.EAST);
                    course.setEastWestDirection(Direction.EAST);
                    course.eastWestPos = -course.eastWestPos;
                }
                if (turn == 3) {
                    course.setMainDirection(instruction.getCommand().equals("L") ?
                            Direction.NORTH :
                            Direction.SOUTH);
                    if (!course.getNorthSouthDirection().equals(course.getMainDirection())) {
                        course.northSouthPos = -course.northSouthPos;
                        course.setNorthSouthDirection(course.getMainDirection());
                    }
                }

                break;
            case SOUTH:
                if (turn == 1) {
                    course.setMainDirection(instruction.getCommand().equals("L") ?
                            Direction.EAST :
                            Direction.WEST);
                    if (!course.getEastWestDirection().equals(course.getMainDirection())) {
                        course.eastWestPos = -course.eastWestPos;
                        course.setEastWestDirection(course.getMainDirection());
                    }
                }
                if (turn == 2) {
                    course.setMainDirection(Direction.NORTH);
                    course.setNorthSouthDirection(Direction.NORTH);
                    course.northSouthPos = -course.northSouthPos;
                }
                if (turn == 3) {
                    course.setMainDirection(instruction.getCommand().equals("L") ?
                            Direction.WEST :
                            Direction.EAST);
                    if (!course.getEastWestDirection().equals(course.getMainDirection())) {
                        course.eastWestPos = -course.eastWestPos;
                        course.setEastWestDirection(course.getMainDirection());
                    }
                }
                break;
        }

    }


    static void move(Course course, int value, boolean forward) {
        if (forward) {
            switch (course.mainDirection) {
                case EAST:
                    course.eastWestPos += value;
                    break;
                case WEST:
                    course.eastWestPos += value;
                    break;
                case NORTH:
                    course.northSouthPos += value;
                    break;
                case SOUTH:
                    course.northSouthPos += value;
                    break;
            }
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
        int eastWestPos = 0;
        int northSouthPos = 0;
        Direction mainDirection = Direction.EAST;
        Direction northSouthDirection = Direction.SOUTH;
        Direction eastWestDirection = Direction.EAST;
    }

    public enum Direction {
        EAST,
        SOUTH,
        WEST,
        NORTH
    }


}
