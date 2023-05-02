package io.ssrl;

public final class RecursionArrayMin {
    private RecursionArrayMin() {
    }

    public static int findMinimum(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Input Array must not be null or empty.");
        }
        if (array.length == 1) {
            return array[0];
        }
        return findMinimum(array, array.length - 1, array[array.length - 1]);
    }

    private static int findMinimum(int[] inputArray, int lastIndex, int currentMin) {
        if (lastIndex < 0) {
            return currentMin;
        }
        if (currentMin == 1) {
            return currentMin;
        }
        int newMin = (inputArray[lastIndex] < currentMin) ? inputArray[lastIndex] : currentMin;
        int newIndex = lastIndex - 1;
        return findMinimum(inputArray, newIndex, newMin);
    }

}
