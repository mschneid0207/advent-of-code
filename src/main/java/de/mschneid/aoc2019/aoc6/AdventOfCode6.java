package de.mschneid.aoc2019.aoc6;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

public class AdventOfCode6 {

    public int countTotalNumberOfDirectAndIndirectOrbits(List<String> orbitData) {
        // 1. prepare orbit map key = orbit, value = all direct orbits
        Map<String, String> orbitMap = prepareOrbitMap(orbitData);

        //2. iterate over map and run recursive through all direct orbits
        return countStepsForAllOrbits(orbitMap);
    }

    public int getAmountOfMinOrbitalTransfers(List<String> orbitData, String source, String dest) throws Exception {
        Map<String, String> orbitMap = prepareOrbitMap(orbitData);
        List<String> sourceOrbitList = new ArrayList<>();
        List<String> destOrbitList = new ArrayList<>();

        enrichOrbitList(orbitMap, source, sourceOrbitList);
        enrichOrbitList(orbitMap, dest, destOrbitList);

        String firstCommonOrbit = sourceOrbitList.stream()
                .filter(x -> destOrbitList.contains(x))
                .findFirst()
                .orElseThrow(() -> new Exception("no common orbit was found"));

        int sourceIndex = sourceOrbitList.indexOf(firstCommonOrbit);
        int destIndex = destOrbitList.indexOf(firstCommonOrbit);

        return sourceIndex + destIndex;
    }

   public Map<String, String> prepareOrbitMap(List<String> orbitData) {
        List<Orbit> orbitInfos = orbitData.stream()
                .map(x -> x.split("\\)"))
                .map(x -> new Orbit(x[0], x[1]))
                .collect(Collectors.toList());
        Map<String, String> orbitMap = new HashMap<>();
        orbitInfos.forEach(x -> orbitMap.put(x.getName(), x.getDirectOrbit()));
        return orbitMap;
    }

    public int countStepsForAllOrbits(Map<String, String> orbitMap) {
        int sum = 0;
        for (Map.Entry<String, String> entry : orbitMap.entrySet()) {
            sum = sum + countStepsForOneOrbit(orbitMap, entry.getValue());
        }
        return sum;
    }

    public int countStepsForOneOrbit(Map<String, String> orbitMap, String directOrbit) {
        int sum = 0;
            if (orbitMap.containsKey(directOrbit)) {
                sum = sum + countStepsForOneOrbit(orbitMap, orbitMap.get(directOrbit));
            }
        sum = sum + 1;
        return sum;
    }

    public void enrichOrbitList(Map<String, String> orbitMap, String orbit, List<String> orbits) {

        if (orbitMap.containsKey(orbit)) {
            String directOrbit = orbitMap.get(orbit);
            orbits.add(directOrbit);
            enrichOrbitList(orbitMap, directOrbit, orbits);
        }

    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class Orbit {
        private String directOrbit;
        private String name;
    }
}
