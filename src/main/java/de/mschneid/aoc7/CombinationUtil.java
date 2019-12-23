package de.mschneid.aoc7;

import java.util.ArrayList;
import java.util.List;

public class CombinationUtil {

    public static void printAllRecursive(int n, int[] elements, List<int[]> combinations) {

        if(n == 1) {
            combinations.add(elements.clone());
        } else {
            for(int i = 0; i < n-1; i++) {
                printAllRecursive(n - 1, elements, combinations);
                if(n % 2 == 0) {
                    swap(elements, i, n-1);
                } else {
                    swap(elements, 0, n-1);
                }
            }
            printAllRecursive(n - 1, elements, combinations);
        }
    }


    private static void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }


    private static void printArray(int[] input) {
        System.out.print('\n');
        for(int i = 0; i < input.length; i++) {
            System.out.print(input[i]);
        }
    }


    public static List<int[]> getCombinations(List<Integer> firstPhaseList) {
        List<int[]> combinations = new ArrayList<>();
        for (int i = 0; i < firstPhaseList.size(); i++) {
            List<Integer> secondPhaseList = new ArrayList<>(firstPhaseList);
            Integer firstPhase = firstPhaseList.get(i);
            secondPhaseList.remove(firstPhase);
            for (int j = 0; j < secondPhaseList.size(); j++) {
                List<Integer> thirdPhaseList = new ArrayList<>(secondPhaseList);
                Integer secondPhase = secondPhaseList.get(j);
                thirdPhaseList.remove(secondPhase);
                for (int k = 0; k < thirdPhaseList.size(); k++) {
                    List<Integer> fourthPhaseList = new ArrayList<>(thirdPhaseList);
                    Integer thirdPhase = thirdPhaseList.get(k);
                    fourthPhaseList.remove(thirdPhase);
                    for (int l = 0; l < fourthPhaseList.size(); l++) {
                        List<Integer> fifthPhaseList = new ArrayList<>(fourthPhaseList);
                        Integer fourthPhase = fourthPhaseList.get(l);
                        fifthPhaseList.remove(fourthPhase);
                        for (int m = 0; m < fifthPhaseList.size(); m++) {
                            Integer fifthPhase = fifthPhaseList.get(m);
                            int[] combination = {firstPhase, secondPhase, thirdPhase, fourthPhase, fifthPhase};
                            combinations.add(combination);
                        }
                    }
                }
            }
        }
        return combinations;
    }
}
