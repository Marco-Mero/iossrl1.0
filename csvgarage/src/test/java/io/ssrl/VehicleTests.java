package io.ssrl;

import io.ssrl.models.Car;
import io.ssrl.models.Moto;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class VehicleTests {
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
    public void testFailures() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Car("Fiat", "Punto", 0, "atest71");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Moto("Ducati", "Monster", 0, "test71");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Car.getVehicleFromCSVRecord("Fiat;Punto;12.0;atest71;5");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Moto.getVehicleFromCSVRecord("Ducati;Monster;12.0;test71;3");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Car.getVehicleFromCSVRecord("Ducati;Monster;12.0;atest71;4;error");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Moto("Ducati", "Monster", 2, "qest23");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Car("Fiat", "Punto", 2, "itest23");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Moto("Ducati", "Monster", 2, "123423");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Moto("Ducati", "Monster", 2, "abst2");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Car("Fiat", "Punto", 2, "1234223");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Car("Fiat", "Punto", 2, "abast2");
        });
    }
}
