package io.ssrl;

@SuppressWarnings({ "checkstyle:MissingJavadocType", "checkstyle:MissingJavadocMethod" })
public final class App {
    private App() {
    }

    public static void main(String[] args) {
        int[] intArray1;
        int[] intArray2;
        System.out.println(
                " ~ Unione di due array, senza duplicati.");

        if (args.length == 0) {
            System.out.println(
                    "String[] args deve contenere stringhe valide.");
        } else {
            System.out.println("\n~$ Primo array:");
            intArray1 = ArrayUtils.stringToIntArray(args[0]);
            ArrayUtils.printArr(intArray1);

            if (args.length == 1) {
                System.out.println("\n\n~~~ Unione con [ ]:");
                intArray2 = new int[0];
                ArrayUtils.printArr(intArray1);
            } else {
                System.out.println("\n~$ Secondo array:");
                intArray2 = ArrayUtils.stringToIntArray(args[1]);
                ArrayUtils.printArr(intArray2);
            }
            System.out.println("\n\n~~~ Unione:");
            ArrayUtils.printArr(JoinUniqueArrays.joinArrays(intArray1, intArray2));
        }
    }
}
