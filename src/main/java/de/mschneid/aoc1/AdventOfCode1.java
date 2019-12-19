package de.mschneid.aoc1;

public class AdventOfCode1 {

    public int calculateFuelForMass(int mass) {
        // divide integer is always returning the rounded down integer
        return Math.round(mass / 3) - 2;
    }

    public int calculateFuelForMassAndFuelForFuelMass(int mass) {
        int fuel = Math.round(mass / 3) - 2;
        System.out.println("mass: [" + mass + "] requires fuel: [" + fuel + "]");
        int fuelForMassOfFuel = fuel;
            if (fuel > 0) {
            fuelForMassOfFuel = fuelForMassOfFuel + calculateFuelForMassAndFuelForFuelMass(fuel);
            return fuelForMassOfFuel;
        }
        return 0;
    }


}
