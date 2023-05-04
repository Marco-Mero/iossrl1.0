package io.ssrl.csvhandler;

import io.ssrl.models.Vehicle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public final class CSVUtils {
    private static final String DEFAULTS_CSV = "marca;modello;prezzo;targa;numero_ruote\n"
            + "fiat;panda;14000.0;pd000xy;4\n"
            + "ducati;monster;12000.0;dt000rr;2\n"
            + "iveco;aaa;100000.0;iv9999dt;4\n"
            + "ford;focus;35000.0;io000sr;4\n"
            + "honda;hornet;5000.0;ho987dg;2\n";

    private static final String CSV_PATH = System.getProperty("CSV_PATH", "CSVGarage.csv");

    private CSVUtils() {
    }

    public static void printAllVehicles() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CSV_PATH))) {
            String currentVehicle;
            while ((currentVehicle = bufferedReader.readLine()) != null) {
                System.out.println(currentVehicle);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }

    public static ArrayList<String> listFromCSVByWheelNumber(int wheelNumber) {

        ArrayList<String> vehicleList = new ArrayList<String>();
        String currentVehicle;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CSV_PATH))) {

            while ((currentVehicle = bufferedReader.readLine()) != null) {
                if (currentVehicle.charAt(currentVehicle.length() - 1) == (char) ('0' + wheelNumber)) {
                    vehicleList.add(currentVehicle);
                }
            }

            if (currentVehicle == null || vehicleList.size() == 0) {
                vehicleList.add("Nessun veicolo a " + wheelNumber + " ruote nel garage.");
            }

            bufferedReader.close();

        } catch (IOException e) {
            System.err.println("IOException:" + e.getMessage());
        }
        return vehicleList;
    }

    public static String findVehicleCSVRecord(String plate) {
        int lineNumber = 0;
        String msg = "Veicolo targato " + plate + " non trovato.";
        String currentVehicle = msg;

        if (plate == null || plate.trim().equals("")) {
            throw new IllegalArgumentException("Plate must not be null.");
        }

        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(CSV_PATH))) {
            do {
                currentVehicle = bufferedReader.readLine();
                lineNumber++;
            } while (currentVehicle != null && !currentVehicle.contains(";" + plate + ";"));
            if (currentVehicle == null) {
                lineNumber = 0;
                currentVehicle = msg;
            }

        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        System.out.println((lineNumber == 0) ? "" : "Trovato a riga: " + lineNumber);
        return currentVehicle;
    }

    public static ArrayList<String> listFromCSVByPrice(String inputtedPrice) {
        if (inputtedPrice == null) {
            throw new IllegalArgumentException("Price must not be null.");
        }
        String priceString = inputtedPrice.replaceAll("[^\\d\\.]", "");
        ArrayList<String> vehicleList = new ArrayList<String>();
        String msg = "Non sono presenti veicoli a costo " + priceString + "$";
        String currentVehicle = msg;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CSV_PATH))) {
            while ((currentVehicle = bufferedReader.readLine()) != null) {
                if (currentVehicle.contains(";" + priceString + ";")) {
                    vehicleList.add(currentVehicle);
                }
            }
            if (currentVehicle == null && vehicleList.size() == 0) {
                vehicleList.add(msg);
            }
        } catch (IOException e) {
            System.err.println("IOException:" + e.getMessage());
        }

        return vehicleList;
    }

    public static boolean isInCSV(String plate) {
        String currentVehicle;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CSV_PATH))) {
            do {
                currentVehicle = bufferedReader.readLine();
            } while (currentVehicle != null && !currentVehicle.contains(plate));
            if (currentVehicle != null) {
                return true;
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return false;
    }

    public static void addVehicleToCSV(Vehicle vehicle) {
        String duplicateMsg = "Veicolo duplicato non aggiunto.";
        try (FileWriter fileWriter = new FileWriter(CSV_PATH, true)) {
            if (!isInCSV(vehicle.getPlate())) {
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(vehicle.toCSVRecord() + "\n");
                bufferedWriter.close();
                System.out.println(vehicle.toCSVRecord().replaceAll(";", " ") + " ruote ora nel CSV.");
            } else {
                System.out.println(duplicateMsg);
            }
        } catch (IOException e) {
            System.out.println("Error writing CSV file: " + e.getMessage());
        }
    }

    public static void addVehicleToCSV(String vehicleString) {
        try {
            addVehicleToCSV(Vehicle.getVehicleFromCSVRecord(vehicleString));
        } catch (IllegalArgumentException e) {
            System.out.println("Veicolo non ben formattato non aggiunto " + vehicleString);
        }
    }

    public static void addVehiclesToCSV(ArrayList<Vehicle> newVehicles) {
        for (int i = 0; i < newVehicles.size(); i++) {
            addVehicleToCSV(newVehicles.get(i));
        }
    }

    public static void hardResetToExampleFileProvided() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(CSV_PATH))) {
            bufferedWriter.write(DEFAULTS_CSV);
        } catch (IOException e) {
            System.out.println("reset failed.");
        }
    }

}
