package de.mschneid.aoc5;

import java.util.Arrays;

public enum OperationMode {

    ADD("1"),
    MULTIPLY("2"),
    INPUT("3"),
    OUTPUT("4"),
    JUMP_IF_TRUE("5"),
    JUMP_IF_FALSE("6"),
    LESS_THAN("7"),
    EQUALS("8"),
    EXIT("99");

    private String opCode;

    OperationMode(String opCode) {
        this.opCode = opCode;
    }

    public static OperationMode fromString(String s) throws IllegalArgumentException {
        return Arrays.stream(OperationMode.values())
                .filter(v -> v.opCode.equals(s))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unknown value: " + s));
    }

}
