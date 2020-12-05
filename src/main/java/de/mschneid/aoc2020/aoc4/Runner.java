package de.mschneid.aoc2020.aoc4;

import de.mschneid.util.FileReader;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;

public class Runner {

    public static void main(String[] args) {
        List<String> lines = FileReader.readLines("aoc4-2020-data.txt");

        lines.forEach(x -> System.out.println(x));
        System.out.println(lines.size());

        String passport = "";
        List<String> passportLines = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.isEmpty()) {
                passportLines.add(passport);
                passport = "";
            } else if (i == lines.size() - 1) {
                passport = passport + " " + line;
                passportLines.add(passport);
            } else if (passport.isEmpty()) {
                passport = line;
            } else {
                passport = passport + " " + line;
            }
        }


        passportLines.forEach(x -> System.out.println(x));

        List<String[]> passportValues = new ArrayList();

        for (String passportLine : passportLines) {
            String[] values = passportLine.split(" ");
            passportValues.add(values);
        }

        passportValues.forEach(x -> System.out.println(x.length));

        int counterPasswordValid = checkPasswordValidPart2(passportValues);

        System.out.println("Valid: " + counterPasswordValid);

    }

    private static int checkPasswordValidPart1(List<String[]> passportValues) {
        int counterPasswordValid = 0;

        for (String[] value : passportValues) {
            if (value.length == 8) {
                counterPasswordValid++;
            } else if (value.length == 7) {
                Optional<String> cid = asList(value).stream()
                        .filter(x -> x.contains("cid"))
                        .findFirst();
                if (!cid.isPresent()) {
                    counterPasswordValid++;
                }
            }
        }
        return counterPasswordValid;
    }

    private static int checkPasswordValidPart2(List<String[]> passportValues) {
        int counterPasswordValid = 0;

        for (String[] value : passportValues) {
            if (value.length < 7) {
                continue;
            } else if (value.length == 7) {
                Optional<String> cid = asList(value).stream()
                        .filter(x -> x.contains("cid"))
                        .findFirst();
                if (cid.isPresent()) {
                    continue;
                }
            }
            if (checkPasswordValid(value)) {
                counterPasswordValid++;
            }
        }
        return counterPasswordValid;
    }

    private static boolean checkPasswordValid(String[] passportValues) {
        //System.out.println(asList(passportValues).toString());
        for (String value : passportValues) {
            String[] splits = value.split(":");
            //System.out.println(splits[0] + " " + splits[1]);
            if (splits[0].equals("byr") && !checkYear(splits[1], 1920, 2002)) {
                return false;
            }
            if (splits[0].equals("iyr") && !checkYear(splits[1], 2010, 2020)) {
                return false;
            }
            if (splits[0].equals("eyr") && !checkYear(splits[1], 2020, 2030)) {
                return false;
            }
            if (splits[0].equals("hgt") && !checkHeight(splits[1])) {
                return false;
            }
            if (splits[0].equals("ecl") && !checkEyeColor(splits[1])) {
                return false;
            }
            if (splits[0].equals("pid") && !checkPid(splits[1])) {
                return false;
            }
            if (splits[0].equals("hcl") && !checkHairColor(splits[1])) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkYear(String year, int from, int to) {
        if (year.length() == 4 && parseInt(year) >= from && parseInt(year) <= to) {
            return true;
        }
        return false;
    }

    private static boolean checkHeight(String value) {
        if (value.endsWith("cm")) {
            int height = parseInt(value.substring(0, value.indexOf("c")));
            System.out.println(height);
            if (height >= 150 && height <= 193) {
                return true;
            }
        }
        if (value.endsWith("in")) {
            int height = parseInt(value.substring(0, value.indexOf("i")));
            System.out.println(height);
            if (height >= 59 && height <= 76) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkEyeColor(String value) {
        List<String> eyeColors = asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
        if (eyeColors.contains(value)) {
            return true;
        }
        return false;
    }

    private static boolean checkPid(String value) {
        if (StringUtils.isNumeric(value) && value.length() == 9) {
            return true;
        }
        return false;
    }

    private static boolean checkHairColor(String value) {
        if (value.matches("#[0-9a-f]{6}")) {
            return true;
        }
        return false;
    }


    /*
    byr (Birth Year) - four digits; at least 1920 and at most 2002.
iyr (Issue Year) - four digits; at least 2010 and at most 2020.
eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
hgt (Height) - a number followed by either cm or in:
If cm, the number must be at least 150 and at most 193.
If in, the number must be at least 59 and at most 76.
hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
pid (Passport ID) - a nine-digit number, including leading zeroes.
cid (Country ID) - ignored, missing or not.
     */
}
