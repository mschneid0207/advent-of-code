package de.mschneid.aoc2019.aoc7;

import de.mschneid.aoc2019.aoc5.OperationMode;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.parseInt;

public class AdventOfCode7 {

    private int firstParam = 0;
    private int secondParam = 0;
    private int inputParamPosition = 0;

    public void resetInputParamPosition() {
        inputParamPosition = 0;
    }

    public void setInputParamPositionToSecondParam() {
        inputParamPosition = 1;
    }

    public void executeProgram(int[] codes, AtomicInteger position, int[] inputParams, AtomicInteger output, AtomicInteger finishState) {
        boolean outputChanged = false;
        int opCode = codes[position.get()];
        if (opCode == 99) {
            finishState.set(99);
            return;
        }
        // init operations
        OperationMode opMode = determineOpCode(opCode);
        int[] modes = determineParameterMode(opCode);
        determineParams(codes, position.get(), opMode, modes);

        switch (opMode) {
            case ADD:
                add(codes, position.get(), firstParam, secondParam);
                position.set(position.get() + 4);
                break;
            case MULTIPLY:
                multiply(codes, position.get(), firstParam, secondParam);
                position.set(position.get() + 4);
                break;
            case INPUT:
                input(codes, position.get(), inputParams[inputParamPosition]);
                position.set(position.get() + 2);
                inputParamPosition++;
                break;
            case OUTPUT:
                output(codes, position.get(), modes, output);
                position.set(position.get() + 2);
                outputChanged = true;
                break;
            case JUMP_IF_TRUE:
                position.set(calcPositionForJumpIsTrue(codes, position.get(), firstParam, secondParam));
                break;
            case JUMP_IF_FALSE:
                position.set(calcPositionForJumpIsFalse(codes, position.get(), firstParam, secondParam));
                break;
            case LESS_THAN:
                boolean isLessThan = isFirstParamLessThanSecondParam(firstParam, secondParam);
                int thirdPos = codes[position.get() + 3];
                codes[thirdPos] = isLessThan ? 1 : 0;
                position.set(position.get() + 4);
                break;
            case EQUALS:
                boolean isEquals = isFirstParamEqualsSecondParam(firstParam, secondParam);
                int thirdPos2 = codes[position.get() + 3];
                codes[thirdPos2] = isEquals ? 1 : 0;
                position.set(position.get() + 4);
                break;
            default:
                return;
        }
        if (outputChanged) {
            return;
        }
        executeProgram(codes, position, inputParams, output, finishState);
    }



    void add(int[] codes, int position, int firstParam, int secondParam) {
        int thirdPos = codes[position + 3];
        codes[thirdPos] = firstParam + secondParam;
    }

    void multiply(int[] codes, int position, int firstParam, int secondParam) {
        int thirdPos = codes[position + 3];
        codes[thirdPos] = firstParam * secondParam;
    }

     void input(int[] codes, int position, int inputParam) {
        int firstPos = codes[position + 1];
        codes[firstPos] = inputParam;
    }

     void output(int[] codes, int position, int[] modes, AtomicInteger output) {
        int firstParam = modes[0] == 0 ? codes[codes[position + 1]] : codes[position + 1];
        System.out.println("OUTPUT: " + firstParam);
        output.set(firstParam);
    }

    int calcPositionForJumpIsTrue(int[] codes, int position, int firstParam, int secondParam) {
        return firstParam == 0 ? position + 3 : secondParam;
    }

    int calcPositionForJumpIsFalse(int[] codes, int position, int firstParam, int secondParam) {
        return firstParam == 0 ? secondParam : position + 3;
    }

    boolean isFirstParamLessThanSecondParam(int firstParam, int secondParam) {
        return firstParam < secondParam;
    }

    boolean isFirstParamEqualsSecondParam(int firstParam, int secondParam) {
        return firstParam == secondParam;
    }

    private OperationMode determineOpCode(int opCode) {
        return OperationMode.fromString(String.valueOf(opCode % 10));
    }

    private int[] determineParameterMode(int opCode) {
        String oc = String.valueOf(opCode);
        String modeFirstParam = oc.length() > 2 ? oc.substring(oc.length() - 3, oc.length() - 2) : "0";
        String modeSecondParam = oc.length() > 3 ? oc.substring(oc.length() - 4, oc.length() - 3) : "0";
        String modeThirdParam = "0";
        int[] modes = {parseInt(modeFirstParam), parseInt(modeSecondParam), parseInt(modeThirdParam)};
        return modes;
    }

    private void determineParams(int[] codes, int position, OperationMode opMode, int[] modes) {
        firstParam = modes[0] == 0 ? codes[codes[position + 1]] : codes[position + 1];
        if (!OperationMode.INPUT.equals(opMode) && !OperationMode.OUTPUT.equals(opMode)) {
            secondParam = modes[1] == 0 ? codes[codes[position + 2]] : codes[position + 2];
        }
    }

}
