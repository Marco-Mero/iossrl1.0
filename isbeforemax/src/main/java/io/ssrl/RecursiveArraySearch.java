package io.ssrl;

public final class RecursiveArraySearch {
    private RecursiveArraySearch() {
    }

    private static int findFirstMaxIndex(int[] array) {
        int currentMaxIndex = 0;
        int max = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                currentMaxIndex = i;
            } else if (array[i] == max) {
                continue;
            }
        }
        return currentMaxIndex;
    }

    public static boolean isMaxBeforeItem(int[] array, int toFind) {
        switch (array.length) {
            case 0:
                System.out.println("Warn: Array nullo in ingresso.");
                return false;
            case 1:
                System.out.println("L'array contiene solo: " + toFind);
                return toFind == array[0];
            default:
                int maxIndex = findFirstMaxIndex(array);
                int max = array[findFirstMaxIndex(array)];
                return isMaxBeforeItem(array, toFind, max, maxIndex, 0, 0);
        }
    }

    private static boolean isMaxBeforeItem(
            int[] array, int toFind,
            int max, int maxIndex,
            int originalOccurrences, int originalIndex) {

        int occurrences = originalOccurrences;
        int currentIndex = originalIndex;

        if (currentIndex < array.length) {
            if (currentIndex > maxIndex && originalOccurrences == 0) {
                System.out.println("Valore non trovato prima del massimo.");
                return false;
            }
            if (array[currentIndex] == toFind) {
                occurrences++;
            }
            currentIndex++;
            return isMaxBeforeItem(array, toFind, max, maxIndex, occurrences, currentIndex);
        }

        System.out.println((occurrences != 0) ? "Il valore compare nell'array: " + occurrences + " volte." : "");

        return (occurrences == 0) ? false : true;

    }

}
