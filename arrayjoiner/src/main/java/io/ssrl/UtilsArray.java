package io.ssrl;

import java.util.Random;

@SuppressWarnings({ "checkstyle:MissingJavadocType", "checkstyle:MissingJavadocMethod" })
public final class UtilsArray {
    private UtilsArray() {
    }

    public static int[] stringToIntArray(String originalArrayString) {
        if (originalArrayString == null || originalArrayString.length() == 0) {
            return new int[0];
        }
        String arrayString = originalArrayString.trim()
                .replaceAll("[^\\d\\-\\s]", "")
                .trim()
                .replaceAll("\\- ", "-")
                .replaceAll("\\s+", " ");
        if (arrayString == null || arrayString.length() == 0) {
            return new int[0];
        }
        String[] splitString = arrayString.split(" ");
        int[] intArray = new int[splitString.length];
        for (int i = 0; i < splitString.length; i++) {
            if (!splitString[i].isEmpty()) {
                intArray[i] = Integer.parseInt(splitString[i]);
            }
        }
        return intArray;
    }

    public static void printArr(int[] intArray) {
        if (intArray.length < 1) {
            System.out.print("[ ]");
        } else {
            System.out.print("[");
            for (int i = 0; i < intArray.length - 1; i++) {
                System.out.print(intArray[i] + " ");
            }
            System.out.print(intArray[intArray.length - 1] + "]");
        }
    }

    public static int[] generateIntArray(int maxValue, int length) {
        return generateIntArray(0, Math.max(maxValue, 1), length, 0);
    }

    public static int[] generateIntArray(int inputtedMin, int inputtedMax, int minLength, int lengthDifference) {
        Random rand = new Random();
        int maxValue = Math.max(Math.max(inputtedMin, inputtedMax), 1);
        int minValue = Math.max(Math.min(inputtedMax, inputtedMin), 0);

        int diff = maxValue - minValue;
        diff = Math.max(diff, 1);

        int lengthDelta = (lengthDifference > 0) ? rand.nextInt(lengthDifference) : 0;
        int[] intArr = new int[minLength + lengthDelta];
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = rand.nextInt(diff) + minValue;
        }

        return intArr;
    }
}
