package de.mschneid.aoc2019.aoc3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdventOfCode3 {

    public List<Coordinate> prepareCoordinateList(List<String> coordinates) {
        List<Coordinate> coordinateList = new ArrayList<>();
        Coordinate currentCoordinate = Coordinate.builder().x(0).y(0).build();
        coordinates.forEach(coord -> {
            String direction = coord.substring(0, 1);
            int length = Integer.parseInt(coord.substring(1, coord.length()));
            for (int i = 0; i < length; i++) {

                switch (direction) {
                    case "R":
                        currentCoordinate.moveRight();
                        break;
                    case "L":
                        currentCoordinate.moveLeft();
                        break;
                    case "U":
                        currentCoordinate.moveUp();
                        break;
                    case "D":
                        currentCoordinate.moveDown();
                        break;
                    default:
                        System.out.println("ERROR: unknown direction");
                }
                coordinateList.add(new Coordinate(currentCoordinate.getX(), currentCoordinate.getY()));
            }
        });
        return coordinateList;
    }

    public List<Coordinate> getIntersectionCoordinates(Map<String, Coordinate> wire1Map, Map<String, Coordinate> wire2Map) {
        return wire1Map.entrySet().stream()
                .filter(e -> wire2Map.containsKey(e.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

    }

    public int getMinDistanceByManhattanCalc(List<Coordinate> intersectionPoints) {
        Integer minDistance = null;
        for (Coordinate point : intersectionPoints) {
            int distance = Math.abs(point.getX()) + Math.abs(point.getY());
            if (minDistance == null || distance < minDistance) {
                minDistance = distance;
            }
        }
        return minDistance;
    }

    public int getMinDistanceByClosestWay(List<Coordinate> intersectionPoins, List<Coordinate> wire1List, List<Coordinate> wire2List) {
        int minDistance = 0;
        List<String> wire1CoordinateKeyList = wire1List.stream().map(Coordinate::getCoordinateKey).collect(Collectors.toList());
        List<String> wire2CoordinateKeyList = wire2List.stream().map(Coordinate::getCoordinateKey).collect(Collectors.toList());
        for (Coordinate point : intersectionPoins) {
            int stepsFromCentralPointForWire1 = wire1CoordinateKeyList.indexOf(point.getCoordinateKey()) + 1;
            int stepsFromCentralPointForWire2 = wire2CoordinateKeyList.indexOf(point.getCoordinateKey()) + 1;
            int sumSteps = stepsFromCentralPointForWire1 + stepsFromCentralPointForWire2;
            if (minDistance == 0 || sumSteps < minDistance) {
                minDistance = sumSteps;
            }
        }
        return minDistance;
    }

    public Map<String, Coordinate> prepareCoordinatesMap(List<Coordinate> coordinateList) {
        return coordinateList.stream()
                .distinct()
                .collect(Collectors.toMap(Coordinate::getCoordinateKey, x -> x));
    }

}
