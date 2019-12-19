package de.mschneid.aoc1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdventOfCode1Test {

    @Test
    public void test_calculate_fuel_for_mass() {
        AdventOfCode1 aoc = new AdventOfCode1();
        int fuel = aoc.calculateFuelForMass(14);
        assertEquals(2, fuel);
        fuel = aoc.calculateFuelForMass(100756);
        assertEquals(33583, fuel);
    }

    @Test
    public void test_calculate_fuel_for_mass_and_fuel_for_fuel_mass() {
        AdventOfCode1 aoc = new AdventOfCode1();
        int fuel = aoc.calculateFuelForMassAndFuelForFuelMass(14);
        assertEquals(2, fuel);
        fuel = aoc.calculateFuelForMassAndFuelForFuelMass(1969);
        assertEquals(966, fuel);
        fuel = aoc.calculateFuelForMassAndFuelForFuelMass(100756);
        assertEquals(50346, fuel);
    }

}