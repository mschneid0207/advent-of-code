package de.mschneid.aoc2019.aoc6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdventOfCode6Test {

    private AdventOfCode6 sut;
    private List<String> orbitData = Arrays.asList(
            "COM)B",
            "B)C",
            "C)D",
            "D)E",
            "E)F",
            "B)G",
            "G)H",
            "D)I",
            "E)J",
            "J)K",
            "K)L");
    private List<String> orbitData2 = Arrays.asList(
            "COM)B",
            "B)C",
            "C)D",
            "D)E",
            "E)F",
            "B)G",
            "G)H",
            "D)I",
            "E)J",
            "J)K",
            "K)L",
            "K)YOU",
            "I)SAN");

    @BeforeEach
    public void setUp() {
        sut = new AdventOfCode6();
    }

    @Test
    public void test_count_total_number_of_directs_and_indirect_orbits() {
        int sum = sut.countTotalNumberOfDirectAndIndirectOrbits(orbitData);
        assertEquals(42, sum);
    }

    @Test
    public void test_amount_of_min_orbital_transfers() throws Exception {
        int minOrbitalTransfers = sut.getAmountOfMinOrbitalTransfers(orbitData2, "YOU", "SAN");
        assertEquals(4, minOrbitalTransfers);
    }


}