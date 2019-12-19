package de.mschneid.aoc3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AdventOfCode3Test {



    @Test
    public void test_min_distance_by_manhattan_calc() {
        AdventOfCode3 sut = new AdventOfCode3();
        List<Coordinate> wire1CoordinateList = sut.prepareCoordinateList(Arrays.asList("R8","U5","L5","D3"));
        List<Coordinate> wire2CoordinateList  = sut.prepareCoordinateList(Arrays.asList("U7","R6","D4","L4"));

        Map<String, Coordinate> wire1CoordinateMap = sut.prepareCoordinatesMap(wire1CoordinateList);
        Map<String, Coordinate> wire2CoordinateMap = sut.prepareCoordinatesMap(wire2CoordinateList);

        List<Coordinate> intersectionCoordinates = sut.getIntersectionCoordinates(wire1CoordinateMap, wire2CoordinateMap);
        int minDistanceByManhattanCalc = sut.getMinDistanceByManhattanCalc(intersectionCoordinates);
        assertEquals(6, minDistanceByManhattanCalc);
    }

    @Test
    public void test_min_distance_by_closest_way() {
        AdventOfCode3 sut = new AdventOfCode3();
        List<Coordinate> wire1CoordinateList = sut.prepareCoordinateList(Arrays.asList("R75","D30","R83","U83","L12","D49","R71","U7","L72"));
        List<Coordinate> wire2CoordinateList  = sut.prepareCoordinateList(Arrays.asList("U62","R66","U55","R34","D71","R55","D58","R83"));

        Map<String, Coordinate> wire1CoordinateMap = sut.prepareCoordinatesMap(wire1CoordinateList);
        Map<String, Coordinate> wire2CoordinateMap = sut.prepareCoordinatesMap(wire2CoordinateList);

        List<Coordinate> intersectionCoordinates = sut.getIntersectionCoordinates(wire1CoordinateMap, wire2CoordinateMap);
        int minDistanceByClosestWay = sut.getMinDistanceByClosestWay(intersectionCoordinates, wire1CoordinateList, wire2CoordinateList);
        assertEquals(610, minDistanceByClosestWay);
    }

    @Test
    public void test_min_distance_by_manahatten_calc() {
        AdventOfCode3 sut = new AdventOfCode3();
        Coordinate c1 = new Coordinate(-2, 2);
        Coordinate c2 = new Coordinate(-2, 3);
        Coordinate c3 = new Coordinate(2, 4);

        int minDistance = sut.getMinDistanceByManhattanCalc(Arrays.asList(c1, c2, c3));
        Assertions.assertEquals(4, minDistance);
    }

}