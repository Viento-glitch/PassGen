package main;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class PassGenTest {

    @Test
    public void testTakeLettersFrom() {
        Assert.assertEquals("Test", PassGen.getLettersFrom("12Test3251234"));
    }

    @Test
    public void testTakeNumbersFrom() {
        Assert.assertEquals("123251234", PassGen.getNumbersFrom("12Test3251234"));
    }

    @Test(expected = Exception.class)
    public void testTakeNumbersFromNullValues() {
        Assert.assertEquals(null, PassGen.getNumbersFrom(""));
    }

    @Test
    public void testGetNumberToMoveCesar() {
        ArrayList<Character> alphabet = CesarCipher.getEngLetters();
        String numbersFromTheKey = "1000";
        Assert.assertEquals(4, PassGen.getNumberToMoveCesar(alphabet, numbersFromTheKey));
    }

    @Test
    public void testCheckSite() {
        Assert.assertTrue(PassGen.checkSite("site.ru"));
    }

}