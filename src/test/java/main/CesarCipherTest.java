package main;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class CesarCipherTest {
    @Test
    public void testEncryptEng() throws Exception {
        String text = "abc";
        Assert.assertEquals("BCD", CesarCipher.encrypt(text, 1));
    }

    @Test(expected = Exception.class)
    public void testEncryptEngNull() throws Exception {
        String text = "abc";
        Assert.assertEquals("BCD", CesarCipher.encrypt(text, 0));
    }

    @Test

    public void testEncryptEngNegative() throws Exception {
        String text = "bcd";
        Assert.assertEquals("ABC", CesarCipher.encrypt(text, -1));
    }

    @Test
    public void testEncryptRus() throws Exception {
        String text = "абв";
        Assert.assertEquals("БВГ", CesarCipher.encrypt(text, 1));

    }

    @Test(expected = Exception.class)
    public void testEncryptRusNull() throws Exception {
        String text = "абв";
        Assert.assertEquals("БВГ", CesarCipher.encrypt(text, 0));
    }

    @Test
    public void testEncryptRusNegative() throws Exception {
        String text = "а";
        Assert.assertEquals("Ю", CesarCipher.encrypt(text, -2));
    }

    @Test
    public void testGetEngAlphabet() throws Exception {
        Character text = 'A';
        ArrayList testArray = CesarCipher.getAlphabet("a");
        Assert.assertTrue(testArray.contains(text));
    }

    @Test
    public void testGetRusAlphabet() throws Exception {
        Character text = 'Ф';
        ArrayList testArray = CesarCipher.getAlphabet("ф");
        Assert.assertTrue(testArray.contains(text));
    }
}
