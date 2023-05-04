package io.ssrl;

import io.ssrl.csvhandler.CSVUtils;
import io.ssrl.models.Car;
import io.ssrl.models.Moto;
import io.ssrl.models.Vehicle;
import java.util.ArrayList;

public final class App {
    static final int TEST_PRICE = 1233;

    static final String INSTRUCTIONS = "~ Test funzioni:\n"
            + "| Aggiunta veicoli via input utente in CSVGarage.csv\n"
            + "| Ricerca e stampa di liste divise per tipo di veicolo.\n"
            + "| Ricerca veicolo via targa con stampa in console.\n"
            + "| Ricerca veicolo via prezzo con stampa in console.\n"
            + "\n ~ Contenuto del file CSV:";

    static final String HELP = "~~~ ~~~ ~~~ Args accettati:\n"
            + "~~~ Singola Stringa:\n"
            + "- Prezzo esatto da cercare con singola posizione decimale seguito da $ (e.g. 0.0$).\n"
            + "- La targa esatta del veicolo cercato.\n"
            + "- 'moto' visualizza i ciclomotori nel csv\n"
            + "- 'auto' visualizza le macchine nel csv.\n"
            + "- 'HardReset' ricrea il file CSV d'esempio.\n"
            + "~~~ Stringhe Multiple:\n"
            + "- Lista separata da spazi delle stringhe CSV corrispondenti ai veicoli da aggiungere al CSV";

    private App() {
    }

    private static void printArrayList(ArrayList<String> recordList) {
        for (String singleRecord : recordList) {
            System.out.println(singleRecord.replaceAll(";", " "));
        }
    }

    private static void printVehicleList(ArrayList<Vehicle> vehicleList) {
        for (Vehicle singleVehicle : vehicleList) {
            System.out.println(singleVehicle.toCSVRecord().replaceAll(";", " "));
        }
    }

    private static void selectProgram(String choice) {
        switch (choice) {
            case "moto":
                System.out.println("\nMoto nel file:");
                printArrayList(CSVUtils.listFromCSVByWheelNumber(Moto.WHEEL_NUMBER));
                break;
            case "auto":
                System.out.println("\nMacchine nel file:");
                printArrayList(CSVUtils.listFromCSVByWheelNumber(Car.WHEEL_NUMBER));
                break;
            case "help":
                System.out.println(HELP);
                break;
            case "Standardtest.123":
                Vehicle fiesta = new Car("ford", "fiesta", TEST_PRICE, "pl473id");
                Vehicle harley = new Moto("harley", "moto", TEST_PRICE, "pl47id");
                standardTest(fiesta, harley, "io000sr", "1233.0");
                break;
            case "HardReset":
                CSVUtils.hardResetToExampleFileProvided();
                break;
            default:
                if (choice == null || choice.isEmpty()) {
                    System.out.println(INSTRUCTIONS);
                    CSVUtils.printAllVehicles();
                    System.out.println("Scrivi help per visualizzare una lista degli input accettati.");
                    break;
                }
                if (choice.charAt(choice.length() - 1) == '$') {
                    String price = choice.replaceAll("\\$", "");
                    printArrayList(CSVUtils.listFromCSVByPrice(price.toString()));
                } else {
                    System.out.println("\nRecord CSV e linea del file corrispondenti alla targa \"" + choice + "\" :");
                    System.out.println(CSVUtils.findVehicleCSVRecord(choice));
                }
        }
    }

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("INSTRUCTIONS");
            CSVUtils.printAllVehicles();
        }

        if (args.length == 1) {
            selectProgram(args[0]);
        }

        if (args.length > 1) {
            for (int i = 0; i < args.length; i++) {
                CSVUtils.addVehicleToCSV(args[i]);
            }
        }

        System.out.print("\n");
    }

    private static void standardTest(Vehicle vehicle1, Vehicle vehicle2, String plateToFind, String priceToFind) {
        System.out.println("~ Test standard ~");
        ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>() {
            {
                add(vehicle1);
                add(vehicle2);
            }
        };
        System.out.println("\n ~ Aggiungo i veicoli :");
        printVehicleList(vehicleList);

        System.out.println("al file .csv:");
        CSVUtils.addVehiclesToCSV(vehicleList);

        System.out.println("\nCerco il veicolo con targa: " + plateToFind);
        System.out.println(CSVUtils.findVehicleCSVRecord(plateToFind).replace(";", " "));

        System.out.println("\nCerco tutti i veicoli con prezzo: " + priceToFind);
        printArrayList(CSVUtils.listFromCSVByPrice(priceToFind));
    }

}
