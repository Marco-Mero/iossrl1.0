package io.ssrl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.ssrl.csvhandler.CSVUtils;

class AppTest {
    private static ByteArrayOutputStream outContent;
    
    @BeforeAll
    static void setup(){
        App.main(new String[] {"Reset--hard"});
        outContent = new ByteArrayOutputStream();
    }

    @BeforeEach
    void prepareOut() {
        outContent.reset();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testGarageReset() {
        CSVUtils.printAllVehicles();
        String expected = CSVUtils.DEFAULTS_CSV;
        String actual = outContent.toString().replace("\r", "");
        assertEquals(actual,expected);
    }
}
