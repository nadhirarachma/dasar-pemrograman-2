package assignments.assignment1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ExtractNPMTest {
    
    @Test
    public void testValidateCorrectOne() {
        assertEquals(true, ExtractNPM.validate(20022808200017L));
    }

    @Test
    public void testValidateCorrectTwo() {
        assertEquals(true, ExtractNPM.validate(19011403200038L));
    }

    @Test
    public void testValidateCorrectThree() {
        assertEquals(true, ExtractNPM.validate(18010903200033L));
    }

    @Test
    public void testValidateFalseOne() {
        assertEquals(false, ExtractNPM.validate(20021907200237L));
    }

    @Test
    public void testValidateFalseTwo() {
        assertEquals(false, ExtractNPM.validate(190214032000380L));
    }

    @Test
    public void testValidateFalseThree() {
        assertEquals(false, ExtractNPM.validate(20041907200626L));
    }
    
    @Test
    public void testExtractCorrectOne() {
        String expected = "Tahun masuk: 2020\n";
        expected += "Jurusan: Sistem Informasi\n";
        expected += "Tanggal Lahir: 28-08-2000";
        assertEquals(expected, ExtractNPM.extract(20022808200017L));
    }

    @Test
    public void testExtractCorrectTwo() {
        String expected = "Tahun masuk: 2019\n";
        expected += "Jurusan: Ilmu Komputer\n";
        expected += "Tanggal Lahir: 14-03-2000";
        assertEquals(expected, ExtractNPM.extract(19011403200038L));
    }

    @Test
    public void testExtractCorrectThree() {
        String expected = "Tahun masuk: 2018\n";
        expected += "Jurusan: Ilmu Komputer\n";
        expected += "Tanggal Lahir: 09-03-2000";
        assertEquals(expected, ExtractNPM.extract(18010903200033L));
    }
}