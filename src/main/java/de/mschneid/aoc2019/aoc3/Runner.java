package de.mschneid.aoc2019.aoc3;

import de.mschneid.util.FileReader;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Runner {

    public static void main(String[] args) {
        AdventOfCode3 aoc = new AdventOfCode3();
        List<String> lines = FileReader.readLines("aoc3-data.txt");
        String[] wire1Commands = lines.get(0).split(",");
        String[] wire2Commands = lines.get(1).split(",");
        List<Coordinate> wire1CoordinateList = aoc.prepareCoordinateList(Arrays.asList(wire1Commands));
        List<Coordinate> wire2CoordinateList = aoc.prepareCoordinateList(Arrays.asList(wire2Commands));
        Map<String, Coordinate> wire1CoordinateMap = aoc.prepareCoordinatesMap(wire1CoordinateList);
        Map<String, Coordinate> wire2CoordinateMap = aoc.prepareCoordinatesMap(wire2CoordinateList);
        List<Coordinate> intersectionCoordinates = aoc.getIntersectionCoordinates(wire1CoordinateMap, wire2CoordinateMap);
        int minDistanceByManhattanCalc = aoc.getMinDistanceByManhattanCalc(intersectionCoordinates);
        System.out.println(minDistanceByManhattanCalc);
        int minDistanceByClosestWay = aoc.getMinDistanceByClosestWay(intersectionCoordinates, wire1CoordinateList, wire2CoordinateList);
        System.out.println(minDistanceByClosestWay);

    }

}
