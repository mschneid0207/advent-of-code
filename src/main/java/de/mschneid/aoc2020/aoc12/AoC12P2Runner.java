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

public class AoC12P2Runner {

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
                    moveShip(course, instr.value);
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
        System.out.println("Part2: " + (abs(course.shipX) + abs(course.shipY)));
    }

    static void moveDegrees(Course course, Instruction instruction) {
        int turn = instruction.getValue() / 90;
        int turnWithDirection = instruction.getCommand().equals("R") ? turn : turn * -1;

        Direction dirX = course.wpX >= 0 ? Direction.EAST : Direction.WEST;
        Direction dirY = course.wpY >= 0 ? Direction.NORTH : Direction.SOUTH;

        Direction turnX = getNewDirection(dirX, turnWithDirection);
        Direction turnY = getNewDirection(dirY, turnWithDirection);
        int wpX = 0;
        int wpY = 0;
        switch (turnX) {
            case NORTH:
                wpY = abs(course.getWpX());
                break;
            case SOUTH:
                wpY = abs(course.getWpX()) * -1;
                break;
            case EAST:
                wpX = abs(course.getWpX());
                break;
            case WEST:
                wpX = abs(course.getWpX()) * -1;
                break;
        }
        switch (turnY) {
            case NORTH:
                wpY = abs(course.getWpY());
                break;
            case SOUTH:
                wpY = abs(course.getWpY()) * -1;
                break;
            case EAST:
                wpX = abs(course.getWpY());
                break;
            case WEST:
                wpX = abs(course.getWpY()) * -1;
                break;
        }
        course.setWpY(wpY);
        course.setWpX(wpX);

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


    static void moveShip(Course course, int value) {
        course.setShipX(course.getShipX() + course.getWpX() * value);
        course.setShipY(course.getShipY() + course.getWpY() * value);
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
        int wpX = 10;
        int wpY = 1;
        int shipX = 0;
        int shipY = 0;
    }

    public enum Direction {
        EAST,
        SOUTH,
        WEST,
        NORTH
    }


}
