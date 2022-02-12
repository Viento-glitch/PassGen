package main;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class PassGenTest {

    @Test
    public void testTakeLettersFrom() {
        try {
            Assert.assertEquals("Test", PassGen.getLettersFrom("12Test3251234"));
        } catch (GenException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTakeNumbersFrom() {
        try {
            Assert.assertEquals("123251234", PassGen.getNumbersFrom("12Test3251234"));
        } catch (GenException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = Exception.class)
    public void testTakeNumbersFromNullValues() {
        try {
            Assert.assertEquals(null, PassGen.getNumbersFrom(""));
        } catch (GenException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetNumberToMoveCesar() {
        ArrayList<Character> alphabet = CaesarCipher.getEngLetters();
        String numbersFromTheKey = "1000";
        Assert.assertEquals(4, PassGen.getNumberToMoveCesar(alphabet, numbersFromTheKey));
    }

    @Test
    public void testCheckSite() {
        Assert.assertTrue(PassGen.checkSite("site.ru"));
    }

}