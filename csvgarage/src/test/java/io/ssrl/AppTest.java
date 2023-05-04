package io.ssrl;

import io.ssrl.csvhandler.CSVUtils;
import io.ssrl.models.Car;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    private static ByteArrayOutputStream outContent;

    @BeforeAll
    static void setup() {
        System.setProperty("CSV_PATH", "JUnitTestGarage.csv");
        App.main(new String[] {"HardReset"});
        outContent = new ByteArrayOutputStream();
    }

    @BeforeEach
    void prepareOut() {
        outContent.reset();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void showInstructionsTest() {
        App.main(new String[0]);
        App.main(new String[] {""});
    }

    @Test
    void showHelpTest() {
        App.main(new String[] {"help"});
    }

    @Test
    void testGarageReset() {
        CSVUtils.printAllVehicles();
        String expected = "marca;modello;prezzo;targa;numero_ruote\n"
                + "fiat;panda;14000.0;pd000xy;4\n"
                + "ducati;monster;12000.0;dt000rr;2\n"
                + "iveco;aaa;100000.0;iv9999dt;4\n"
                + "ford;focus;35000.0;io000sr;4\n"
                + "honda;hornet;5000.0;ho987dg;2\n";
        String actual = outContent.toString().replace("\r", "");
        assertEquals(actual, expected);
    }

    @Test
    void testMotoInGarage() {
        ArrayList<String> allMotos = CSVUtils.listFromCSVByWheelNumber(2);
        String expected = "Moto nel file:";
        for (String singleRecord : allMotos) {
            expected += singleRecord.replaceAll(";", " ");
        }
        App.main(new String[] {"moto"});
        String actual = outContent.toString().replace("\r", "").replace("\n", "");
        assertEquals(actual, expected);
    }

    @Test
    void testMacchineInGarage() {
        ArrayList<String> allCars = CSVUtils.listFromCSVByWheelNumber(Car.WHEEL_NUMBER);
        String expected = "Macchine nel file:";
        for (String singleRecord : allCars) {
            expected += singleRecord.replaceAll(";", " ");
        }
        App.main(new String[] {"auto"});
        String actual = outContent.toString().replace("\r", "").replace("\n", "");
        assertEquals(actual, expected);
    }

    @Test
    void testPlateSearch() {
        App.main(new String[] {"iv9999dt"});
        String expected = "Record CSV e linea del file corrispondenti alla targa \"iv9999dt\" "
                + ":Trovato a riga: 4iveco;aaa;100000.0;iv9999dt;4";
        String actual = outContent.toString().replace("\r", "").replace("\n", "");
        assertEquals(actual, expected);
    }

    @Test
    void testPriceSearch() {
        App.main(new String[] {"14000.0$"});
        String expected = "fiat panda 14000.0 pd000xy 4";
        String actual = outContent.toString().replace("\r", "").replace("\n", "");
        assertEquals(actual, expected);
    }

    @Test
    void testStandard() {
        App.main(new String[] {"Standardtest.123"});
    }

    @Test
    void testMultipleInsertions() {
        App.main(new String[] {"carBrand;carModel;1233.0;te452ta;4", "motoBrand;motoModel;1233.0;te45st;2"});
        String expected = "carbrand carModel 1233.0 te452ta 4 ruote ora nel CSV."
                + "motobrand motoModel 1233.0 te45st 2 ruote ora nel CSV.";
        String actual = outContent.toString().replace("\r", "").replace("\n", "");
        assertEquals(actual, expected);
    }
}
