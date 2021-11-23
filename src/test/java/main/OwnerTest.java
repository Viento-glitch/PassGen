package main;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class OwnerTest {

    @Test
    public void testTakeLettersFrom() {
        Assert.assertEquals("Test", Owner.takeLettersFrom("12Test3251234"));
    }

    @Test
    public void testTakeNumbersFrom() {
        Assert.assertEquals("123251234", Owner.takeNumbersFrom("12Test3251234"));
    }

    @Test
    public void testGetNumberToMoveCesar() {
        ArrayList<Character> alphabet = CesarCipher.getEngLetters();
        String numbersFromTheKey = "1000";
        Assert.assertEquals(4, Owner.getNumberToMoveCesar(alphabet, numbersFromTheKey));
    }

    @Test
    public void testCheckSite(){
        Assert.assertTrue(Owner.checkSite("site.ru"));
    }

}