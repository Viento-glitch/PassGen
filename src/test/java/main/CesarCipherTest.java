package main;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class CesarCipherTest {
    @Test
    public void testEncryptEng() {
        String text = "abc";
        Assert.assertEquals("BCD", CesarCipher.encrypt(text, 1));
    }

    @Test(expected = Exception.class)
    public void testEncryptEngNull() {
        String text = "abc";
        Assert.assertEquals("BCD", CesarCipher.encrypt(text, 0));
    }

    @Test

    public void testEncryptEngNegative() {
        String text = "bcd";
        Assert.assertEquals("ABC", CesarCipher.encrypt( text, -1));
    }

    @Test
    public void testEncryptRus() {
        String text = "абв";
        Assert.assertEquals("БВГ", CesarCipher.encrypt( text, 1));

    }

    @Test(expected = Exception.class)
    public void testEncryptRusNull() {
        String text = "абв";
        Assert.assertEquals("БВГ", CesarCipher.encrypt( text, 0));
    }

    @Test
    public void testEncryptRusNegative() {
        String text = "а";
        Assert.assertEquals("Ю", CesarCipher.encrypt( text, -2));
    }

}
