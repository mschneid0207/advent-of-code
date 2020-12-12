package de.mschneid.aoc2020.aoc11;

import de.mschneid.util.FileReader;

import java.util.List;

public class AoC11Runner {

    public static void main(String[] args) {
        List<String> puzzleLines = FileReader.readLines("aoc2020/aoc11-data-test.txt");

        int seatRows = puzzleLines.size();
        int seatColumns = puzzleLines.get(0).length();

        Character[][] seats = prepareSeats(puzzleLines, seatColumns);

        // Part1
        // make rounds
        boolean seatsChanged = true;
        while (seatsChanged) {
            // previousCounter
            int previousCounter = countOccupiedSeats(seats, seatRows, seatColumns);
            System.out.println("previousCounter: " + previousCounter);
            Character[][] seatsOccAfter = new Character[seatRows][seatColumns];
            makeRound(seats, seatRows, seatColumns, seatsOccAfter);
            // afterCounter
            int afterCounter = countOccupiedSeats(seatsOccAfter, seatRows, seatColumns);
            System.out.println("afterCounter: " + afterCounter);
            if (previousCounter == afterCounter) {
                seatsChanged = false;
                System.out.println("Result Part1: " + afterCounter);
            } else {
                seats = seatsOccAfter;
            }
        }

        // Part2
        seats = prepareSeats(puzzleLines, seatColumns);
        // make rounds
        seatsChanged = true;
        while (seatsChanged) {
            // previousCounter
            int previousCounter = countOccupiedSeats(seats, seatRows, seatColumns);
            System.out.println("previousCounter: " + previousCounter);
            Character[][] seatsOccAfter = new Character[seatRows][seatColumns];
            makeRoundPart2(seats, seatRows, seatColumns, seatsOccAfter);
            // afterCounter
            int afterCounter = countOccupiedSeats(seatsOccAfter, seatRows, seatColumns);
            System.out.println("afterCounter: " + afterCounter);
            if (previousCounter == afterCounter) {
                seatsChanged = false;
                System.out.println("Result Part2: " + afterCounter);
            } else {
                seats = seatsOccAfter;
            }
        }
    }

    private static Character[][] prepareSeats(List<String> puzzleLines, int seatColumns) {
        int seatRows = puzzleLines.size();
        Character[][] seats = new Character[seatRows][seatColumns];

        for (int i = 0; i < puzzleLines.size(); i++) {
            for (int j = 0; j < seatColumns; j++) {
                seats[i][j] = puzzleLines.get(i).charAt(j);
            }
        }
        return seats;
    }

    static int countOccupiedSeats(Character[][] seats, int seatRows, int seatColumns) {
        int counter = 0;
        for (int i = 0; i < seatRows; i++) {
            for (int j = 0; j < seatColumns; j++) {
                if (seats[i][j].equals('#')) {
                    counter++;
                }
            }
        }
        return counter;
    }

    static void makeRound(Character[][] seats, int seatRows, int seatColumns, Character[][] seatsOccAfter) {
        for (int i = 0; i < seatRows; i++) {
            for (int j = 0; j < seatColumns; j++) {
                if (seats[i][j].equals('.')) {
                    seatsOccAfter[i][j] = '.';
                    continue;
                }
                if (seats[i][j].equals('L') &&
                        checkOccupiedSeats(i, j, seats, seatRows, seatColumns) == 0) {
                    seatsOccAfter[i][j] = '#';
                } else {
                    seatsOccAfter[i][j] = 'L';
                }
                if (seats[i][j].equals('#')) {
                    int occupiedSeats = checkOccupiedSeats(i, j, seats, seatRows, seatColumns);
                    if (occupiedSeats >= 4) {
                        seatsOccAfter[i][j] = 'L';
                    } else {
                        seatsOccAfter[i][j] = '#';
                    }
                }
            }
        }
    }

    static int checkOccupiedSeats(int row, int column, Character[][] seats, int seatRows, int seatColumns) {
        int occupiedSeats = 0;
        // check right
        occupiedSeats += checkSeat(row, column + 1, seats, seatRows, seatColumns);
        // check right down
        occupiedSeats += checkSeat(row + 1, column + 1, seats, seatRows, seatColumns);
        // check down
        occupiedSeats += checkSeat(row + 1, column, seats, seatRows, seatColumns);
        // check left down
        occupiedSeats += checkSeat(row + 1, column - 1, seats, seatRows, seatColumns);
        // check left
        occupiedSeats += checkSeat(row, column - 1, seats, seatRows, seatColumns);
        // check left up
        occupiedSeats += checkSeat(row - 1, column - 1, seats, seatRows, seatColumns);
        // check up
        occupiedSeats += checkSeat(row - 1, column, seats, seatRows, seatColumns);
        // check right up
        occupiedSeats += checkSeat(row - 1, column + 1, seats, seatRows, seatColumns);
        return occupiedSeats;
    }

    static int checkSeat(int row, int column, Character[][] seats, int seatRows, int seatColumns) {
        int seatCounts = 0;
        if (row < 0 || row >= seatRows) {
            return 0;
        }
        if (column < 0 || column >= seatColumns) {
            return 0;
        }
        Character seat = seats[row][column];
        return seat.equals('#') ? 1 : 0;
    }

    static void makeRoundPart2(Character[][] seats, int seatRows, int seatColumns, Character[][] seatsOccAfter) {
        for (int i = 0; i < seatRows; i++) {
            for (int j = 0; j < seatColumns; j++) {
                if (seats[i][j].equals('.')) {
                    seatsOccAfter[i][j] = '.';
                    continue;
                }
                if (seats[i][j].equals('L') &&
                        checkOccupiedSeatsPart2(i, j, seats, seatRows, seatColumns) == 0) {
                    seatsOccAfter[i][j] = '#';
                } else {
                    seatsOccAfter[i][j] = 'L';
                }
                if (seats[i][j].equals('#')) {
                    int occupiedSeats = checkOccupiedSeatsPart2(i, j, seats, seatRows, seatColumns);
                    if (occupiedSeats >= 5) {
                        seatsOccAfter[i][j] = 'L';
                    } else {
                        seatsOccAfter[i][j] = '#';
                    }
                }
            }
        }
    }

    static int checkOccupiedSeatsPart2(int row, int column, Character[][] seats, int seatRows, int seatColumns) {
        int occupiedSeats = 0;
        // check right
        occupiedSeats += checkSeatPart2(row, column + 1, seats, seatRows, seatColumns, Direction.RIGHT);
        // check right down
        occupiedSeats += checkSeatPart2(row + 1, column + 1, seats, seatRows, seatColumns, Direction.RIGHT_DOWN);
        // check down
        occupiedSeats += checkSeatPart2(row + 1, column, seats, seatRows, seatColumns, Direction.DOWN);
        // check left down
        occupiedSeats += checkSeatPart2(row + 1, column - 1, seats, seatRows, seatColumns, Direction.LEFT_DOWN);
        // check left
        occupiedSeats += checkSeatPart2(row, column - 1, seats, seatRows, seatColumns, Direction.LEFT);
        // check left up
        occupiedSeats += checkSeatPart2(row - 1, column - 1, seats, seatRows, seatColumns, Direction.LEFT_UP);
        // check up
        occupiedSeats += checkSeatPart2(row - 1, column, seats, seatRows, seatColumns, Direction.UP);
        // check right up
        occupiedSeats += checkSeatPart2(row - 1, column + 1, seats, seatRows, seatColumns, Direction.RIGHT_UP);
        return occupiedSeats;
    }

    static int checkSeatPart2(int row, int column, Character[][] seats, int seatRows, int seatColumns, Direction direction) {
        int seatCounts = 0;
        if (row < 0 || row >= seatRows) {
            return 0;
        }
        if (column < 0 || column >= seatColumns) {
            return 0;
        }
        Character seat = seats[row][column];
        if (seat.equals('.')) {
            switch (direction) {
                case UP:
                    row--;
                    break;
                case RIGHT:
                    column++;
                    break;
                case DOWN:
                    row++;
                    break;
                case LEFT:
                    column--;
                    break;
                case RIGHT_UP:
                    row--;
                    column++;
                    break;
                case RIGHT_DOWN:
                    row++;
                    column++;
                    break;
                case LEFT_UP:
                    row--;
                    column--;
                    break;
                case LEFT_DOWN:
                    row++;
                    column--;
                    break;
            }
            seatCounts = seatCounts + checkSeatPart2(row, column, seats, seatRows, seatColumns, direction);
        }
        if (seatCounts == 0) {
            return seat.equals('#') ? 1 : 0;
        } else {
            return 1;
        }
    }

    public enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN,
        RIGHT_UP,
        RIGHT_DOWN,
        LEFT_UP,
        LEFT_DOWN
    }


}
