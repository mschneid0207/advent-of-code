package de.mschneid.aoc4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AdventOfCode4 {

    public List<String> getValidPasswords(int startPw, int endPw) {
        List<String> passwords = new ArrayList<>();
        for (int i = startPw; i < endPw; i++) {
            passwords.add(String.valueOf(i));
        }
        return passwords.stream()
                .filter(isNumberSixDigitLong())
                .filter(isNumberIncreasing())
                .filter(existDouble())
                .collect(Collectors.toList());
    }

    Predicate<String> isNumberSixDigitLong() {
        return x -> x.length() == 6;
    }

    Predicate<String> isNumberIncreasing() {
        return (String x) -> {
            for (int i = 0; i < x.length() - 1; i++) {
                if ((int) x.charAt(i + 1) < (int) x.charAt(i)) {
                    return false;
                }
            }
            return true;
        };
    }

    Predicate<String> existPair() {
        return (String x) -> {
            for (int i = 0; i < x.length() - 1; i++) {
                if ((int) x.charAt(i + 1) == (int) x.charAt(i)) {
                    return true;
                }
            }
            return false;
        };
    }

    Predicate<String> existDouble() {
        return (String pw) -> {
            for (int i = 0; i < pw.length() - 1; i++) {
                if (isCurrentDigitAndFollowingDigitAPair(pw, i)
                        && isPreviousDigitEqual(pw, pw.charAt(i), i)
                        && isFollowingDigitEqual(pw, pw.charAt(i), i)) {
                    return true;
                }
            }
            return false;
        };
    }

    private boolean isCurrentDigitAndFollowingDigitAPair(String password, int position) {
        if ((int) password.charAt(position) == password.charAt(position + 1)) {
            return true;
        }
        return false;
    }

    private boolean isPreviousDigitEqual(String password, int digit, int position) {
        if (position == 0 || password.charAt(position - 1) != digit) {
            return true;
        }
        return false;
    }

    private boolean isFollowingDigitEqual(String password, int digit, int position) {
        if (position >= password.length() - 2 || password.charAt(position + 2) != digit) {
            return true;
        }
        return false;
    }


}
