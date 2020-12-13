package de.mschneid.aoc2020.aoc13;

import de.mschneid.util.FileReader;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class AoC13Runner {

    public static void main(String[] args) {
        List<String> puzzleLines = FileReader.readLines("aoc2020/aoc13-data.txt");

        //solvePart1(puzzleLines);

        //solvePart2Faster("17,x,13,19");
        //solvePart2("67,7,59,61");
        //solvePart2Faster("67,x,7,59,61");
        solvePart2Faster("13,x,x,41,x,x,x,x,x,x,x,x,x,569,x,29,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,19,x,x,x,23,x,x,x,x,x,x,x,937,x,x,x,x,x,37,x,x,x,x,x,x,x,x,x,x,17");
        //Day13 day13 = new Day13();
        //((day13.solve(puzzleLines);

    }

    static int getMax(String[] busArrivalsAsString) {
       return Arrays.stream(busArrivalsAsString)
                .filter(x -> !x.equals("x"))
                .map(Integer::parseInt)
                .max(Integer::compareTo)
                .get();


    }

    static void solvePart2Faster(String busLine) {
        String[] busArrivalsAsString = busLine.split(",");
        int max = getMax(busArrivalsAsString);
        int indexMax = 0;

        List<Bus> busList = new ArrayList<>();
        for (int i = 0; i < busArrivalsAsString.length; i++) {
            String intervall = busArrivalsAsString[i];
            if (intervall.equals("x"))
                continue;
            else {
                busList.add(new Bus(parseInt(intervall), i));
                if (parseInt(intervall) == max) {
                    indexMax = i;
                }
            }
        }


        long outCounter = max - indexMax;
        //int outCounter = busList.get(0).intervall;
        //int outCounter = 3417;
        boolean run = true;
        while (run) {
            System.out.println("outcounter: " + outCounter);
            run = false;
            long inCounter = outCounter;
            for (Bus bus : busList) {
                if ((inCounter + bus.index) % bus.intervall != 0) {
                    outCounter += max;
                    run = true;
                    break;
                }
            }
        }
        System.out.println(outCounter);
    }

    static void solvePart2(String busLine) {
        String[] busArrivalsAsString = busLine.split(",");

        List<Bus> busList = new ArrayList<>();
        for (int i = 0; i < busArrivalsAsString.length; i++) {
            String intervall = busArrivalsAsString[i];
            if (intervall.equals("x"))
                continue;
            else
                busList.add(new Bus(parseInt(intervall), i));
        }

        int outCounter = busList.get(0).intervall;
        //int outCounter = 3417;
        boolean run = true;
        while (run) {
            System.out.println("outcounter: " + outCounter);
            run = false;
            int inCounter = outCounter;
            for (Bus bus : busList) {
                if ((inCounter + bus.index) % bus.intervall != 0) {
                    outCounter += busList.get(0).intervall;
                    run = true;
                    break;
                }
            }
        }
        System.out.println(outCounter);
    }

    private static void solvePart1(List<String> puzzleLines) {
        int myArrival = parseInt(puzzleLines.get(0));

        String[] busArrivalsAsString = puzzleLines.get(1).split(",");

        List<Integer> busArrivals = Arrays.stream(busArrivalsAsString)
                .filter(x -> !x.equals("x"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Map<Integer, Integer> waitTimeMap = new HashMap<>();
        for (Integer bus : busArrivals) {
            int factor = (myArrival / bus) + 1;
            int waitTime = (factor * bus) - myArrival;
            System.out.println("Bus: " + bus + " waiting time: " + waitTime);
            waitTimeMap.put(bus, waitTime);
        }

        Map.Entry<Integer, Integer> minEntry = waitTimeMap.entrySet().stream()
                .min((Map.Entry.comparingByValue(Integer::compareTo)))
                .get();

        System.out.println("Result: " + (minEntry.getKey() * minEntry.getValue()));
    }

    @Data
    @AllArgsConstructor
    static class Bus {
        private int intervall;
        private int index;
    }
}