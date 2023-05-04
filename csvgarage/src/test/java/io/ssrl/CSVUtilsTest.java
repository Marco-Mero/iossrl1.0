package io.ssrl;

import io.ssrl.csvhandler.CSVUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CSVUtilsTest {
    private static ByteArrayOutputStream outContent;

    @BeforeAll
    static void setup() {
        String csvPath = "JUnitTestGarage.csv";
        System.setProperty("CSV_PATH", csvPath);
        File csvFile = new File(csvPath);
        csvFile.delete();
        try {
            csvFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Errore nella creazione del file " + csvPath + ": " + e.getMessage());
        }
        CSVUtils.addVehicleToCSV("fiat;panda;14000.0;pd000xy;4");
        outContent = new ByteArrayOutputStream();
    }

    @BeforeEach
    void prepareOut() {
        outContent.reset();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testSearchNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            CSVUtils.findVehicleCSVRecord(null);
        });
    }

    @Test
    void testSearchNullPrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            CSVUtils.listFromCSVByPrice(null);
        });
    }

    @Test
    void testNotFoundValid() {
        assertFalse(CSVUtils.isInCSV("pd010xy"));
    }

    @Test
    void testFoundPlate() {
        assertTrue(CSVUtils.isInCSV("pd000xy"));
    }
}
