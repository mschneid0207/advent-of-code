package de.mschneid.aoc2019.aoc8;

import de.mschneid.util.FileReader;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class Runner {

    public static void main(String[] args) throws Exception {
        AdventOfCode8 aoc = new AdventOfCode8();
        String line = FileReader.readLine("aoc8-data.txt");
        int lengthOfLayer = 25 * 6;
        Map<Integer, Integer[]> layerMap = splitLineInLayers(line, lengthOfLayer);
        int layerWithFewestZeros = findLayerWithFewestZeros(layerMap);
        System.out.println("layer with fewest zeros: " + layerWithFewestZeros);
        int code = calculateCode(layerMap.get(layerWithFewestZeros));
        System.out.println("Code: " + code);
        String decodeImage = decodeImage(layerMap, lengthOfLayer);

        paintImage(decodeImage);
    }

    private static Map<Integer, Integer[]> splitLineInLayers(String line, int lengthOfLayer) {
        Map<Integer, Integer[]> layerMap = new HashMap<>();
        int amountOfLayers = line.length() / lengthOfLayer;
        int pixelCounter = 0;
        System.out.println("amount of layers " + amountOfLayers + "; length of layer: " + lengthOfLayer);
        for (int i = 1; i <= amountOfLayers; i++) {
            Integer[] pixelArray = new Integer[lengthOfLayer];
            for (int j = 0; j < lengthOfLayer; j++) {
                pixelArray[j] = parseInt(Character.toString(line.charAt(pixelCounter)));
                pixelCounter++;
            }
            layerMap.put(i, pixelArray);
        }
        return layerMap;
    }

    private static int findLayerWithFewestZeros(Map<Integer, Integer[]> layerMap) {
        Pair<Integer, Integer> layerWithZeroAmount = new Pair<>(0, 0);

        for (Map.Entry<Integer, Integer[]> entry : layerMap.entrySet()) {
            int zeroCounter = 0;
            for (int i = 0; i < entry.getValue().length; i++) {
                if (entry.getValue()[i] == 0) {
                    zeroCounter++;
                }
            }
            // compare counted zeros with determined fewest zeros
            if (layerWithZeroAmount.getValue() == 0 || zeroCounter < layerWithZeroAmount.getValue()) {
                layerWithZeroAmount = new Pair<>(entry.getKey(), zeroCounter);
            }
        }
        return layerWithZeroAmount.getKey();
    }

    private static int calculateCode(Integer[] layer) {
        int numberOneDigits = 0;
        int numberTwoDigits = 0;
        for (int i = 0; i < layer.length; i++) {
            if (layer[i] == 1) {
                numberOneDigits++;
            } else if (layer[i] == 2) {
                numberTwoDigits++;
            }
        }
        return numberOneDigits * numberTwoDigits;
    }

    private static String decodeImage(Map<Integer, Integer[]> layerMap, int lengthOfLayer) {
        StringBuilder decodeImage = new StringBuilder();
        for (int i = 0; i < lengthOfLayer; i++) {
            for (Map.Entry<Integer, Integer[]> entry : layerMap.entrySet()) {
                int key = entry.getKey();
                Integer[] value = entry.getValue();
                if (entry.getValue()[i] == 2) {
                    // if pixel is transparent then we search in the next layer for a white or black pixel
                    continue;
                } else {
                    decodeImage.append(entry.getValue()[i]);
                    break;
                }

            }
        }
        System.out.println("decoded pixel puzzle: " + decodeImage.toString());
        return decodeImage.toString();
    }

    private static void paintImage(String code) {
        StringBuilder formattedCode = new StringBuilder();
        for (int i = 1; i <= code.length(); i++) {
            if (Character.toString(code.charAt(i - 1)).equals("0")) {
                formattedCode.append(" ");
            } else {
                formattedCode.append("*");
            }
            if (i % 25 == 0) {
                formattedCode.append("\n");
            }
        }
        System.out.println(formattedCode.toString());
    }
}
