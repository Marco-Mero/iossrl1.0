package io.ssrl;

public final class App {
    private App() {
    }

    public static void main(String[] args) {

        System.out.println("\n~~~ ~~~ ~~~ ~~~ ~~~ ~~~ ");
        System.out.println(
                "| ~ Test di ricerca ricorsiva minimo in un array");
        System.out.println(
                "| di lunghezza 20+ con valori fra 1 e 1000.\n");
        int[] intArray;

        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                intArray = ArrayUtils.stringToIntArray(args[0]);
                System.out.print("Array in input:");
                ArrayUtils.printArr(intArray);
                System.out.println("Minimo trovato: " + RecursionArrayMin.findMinimum(intArray));
            }
        }
    }
}
