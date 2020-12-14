package de.mschneid.aoc2020.aoc14;

import de.mschneid.util.FileReader;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.toBinaryString;
import static java.lang.Long.parseLong;

public class AoC14Runner {

    static List<Integer> ignoreList = new ArrayList<>();

    public static void main(String[] args) {
        List<String> puzzleLines = FileReader.readLines("aoc2020/aoc14-data.txt");

        List<Instruction> instructions = new ArrayList<>();
        for (String line : puzzleLines) {
            String[] s = line.split("=");
            instructions.add(new Instruction(s[0].trim(), s[1].trim()));
        }

        String mask = "";
         Map<String, Long> binMap = new HashMap<>();

        for (Instruction instr: instructions) {
            if (instr.getOp().startsWith("mem")) {
                String address = instr.getOp().substring(
                        instr.getOp().indexOf("[")+1,instr.getOp().indexOf("]"));
                //System.out.println("address " + address);
                String result = toBinaryString(parseInt(instr.getValue()));
                System.out.println(result);

                result = preFillZeros(mask, result);

                System.out.println("new result: " + result);
                String newBinary = "";
                for (int i = mask.length(); i > 0; i--) {
                   String maskValue = mask.substring(i - 1, i);
                   String resultValue = result.substring(i - 1, i);
                   if (maskValue.equals("X")) {
                       newBinary = resultValue + newBinary;
                   } else {
                       newBinary = maskValue + newBinary;
                   }
                }
                System.out.println("newBin: " + newBinary);
                System.out.println("newBin: " + parseLong(newBinary, 2));
                binMap.put(address, parseLong(newBinary, 2));

            }
            if (instr.getOp().startsWith("mask")) {
                mask = instr.getValue();
            }


            // 000000000000000000000000000001001001
            /*String binaryString="000000000000000000000000000001001001";
            int decimal=Integer.parseInt(binaryString,2);
            System.out.println(decimal);*/
        }

        long sum = 0;
        for(Map.Entry<String, Long> entry: binMap.entrySet()) {
            sum += entry.getValue();
        }
        System.out.println(sum);

    }

    static String preFillZeros(String mask, String value) {
        String newValue = value;
        while (newValue.length() != mask.length()) {
            newValue = "0" + newValue;
        }
        return newValue;
    }


    @Data
    @AllArgsConstructor
    static class Instruction {
        private String op;
        private String value;
    }

}