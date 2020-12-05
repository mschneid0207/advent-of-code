package de.mschneid.aoc2020.aoc5;

import de.mschneid.util.FileReader;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;

public class Runner {

    public static void main(String[] args) {
        List<String> binaries = FileReader.readLines("aoc5-2020-data-test.txt");

        Day5 day5 = new Day5();
        // part 1
        List<Integer> ids = day5.calculatePart1(binaries);
        int maxId = ids.stream().mapToInt(x -> x).max().getAsInt();
        System.out.println(maxId);
        // part2
        int seatId = day5.determineSeatId(ids);
        System.out.println("My seat id: " + seatId);
    }


}
