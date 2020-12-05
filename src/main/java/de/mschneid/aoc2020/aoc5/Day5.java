package de.mschneid.aoc2020.aoc5;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day5 {

     List<Integer> calculatePart1(List<String> binaries) {
         List<Integer> results = new ArrayList<>();
        for(String binary : binaries) {
            String rowBinary = binary.substring(0, 7);
            String seatBinary = binary.substring(7,10);
            //System.out.println(rowBinary + " " + seatBinary);

            Range range = new Range(0, 128);
            for (int i = 0; i < rowBinary.length(); i++) {
                range = getRow(range, rowBinary.charAt(i));
            }
            int row = range.getMin();
            //System.out.println("determined row: " + row);

            Range seatRange = new Range(0, 8);
            for (int i = 0; i < seatBinary.length(); i++) {
                seatRange = getSeat(seatRange, seatBinary.charAt(i));
                //System.out.println(seatRange.toString());
            }
            int seat = seatRange.getMax() -1;
            //System.out.println("determined seat: " + seat);
            System.out.println("row: " + row + "; seat " +seat);
            int result = (row * 8) + seat;
            System.out.println(result);
            results.add(result);
        }
         results.forEach(x -> System.out.println("unsorted " + x));
         Arrays.sort(results.toArray(), Collections.reverseOrder());
         Collections.sort(results);
         results.forEach(x -> System.out.println("sorted " + x));
         return results;


    }
//FBFBBFF
    public Range getRow (Range range, char ident) {
        int value = (range.getMax() - range.getMin()) / 2 ;
        switch (ident) {
            case 'F' :
                return new Range(range.getMin(), range.getMin() + value);
            case 'B' :
                return new Range(range.getMin() + value, range.getMax());
        }
        return null;
    }

    public Range getSeat (Range range, char ident) {
        int value = (range.getMax() - range.getMin()) / 2 ;
        switch (ident) {
            case 'L' :
                return new Range(range.getMin(), range.getMin() + value);
            case 'R' :
                return new Range(range.getMin() + value, range.getMax());
        }
        return null;
    }

    public int determineSeatId(List<Integer> ids) {
         int seatId = 0;
         for (int i = 0; i <ids.size(); i++) {
             if (ids.get(i + 1) - ids.get(i) == 2) {
                 seatId = ids.get(i + 1) - 1;
                 break;
             }
         }
         return seatId;
    }

    @Data
    @AllArgsConstructor
    public static class  Range {
        int min;
        int max;
    }
}
