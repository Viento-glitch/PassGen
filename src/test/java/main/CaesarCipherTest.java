package main;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;


public class CaesarCipherTest {
    @Test
    public void testEncryptEng() throws Exception {
        String text = "abc";
        Assert.assertEquals("BCD", CaesarCipher.encrypt(text, 1));
    }

    @Test(expected = Exception.class)
    public void testEncryptEngNull() throws Exception {
        CaesarCipher.encrypt("BCD", 0);
    }

    @Test

    public void testEncryptEngNegative() throws Exception {
        String text = "bcd";
        Assert.assertEquals("ABC", CaesarCipher.encrypt(text, -1));
    }

    @Test
    public void testEncryptRus() throws Exception {
        String text = "абв";
        Assert.assertEquals("БВГ", CaesarCipher.encrypt(text, 1));

    }

    @Test(expected = Exception.class)
    public void testEncryptRusNull() throws Exception {
        CaesarCipher.encrypt("абв", 0);
    }

    @Test
    public void testEncryptRusNegative() throws Exception {
        String text = "а";
        Assert.assertEquals("Ю", CaesarCipher.encrypt(text, -2));
    }

    @Test
    public void testGetEngAlphabet() throws Exception {
        Character text = 'A';
        List<Character> testArray = CaesarCipher.getAlphabet("a");
        Assert.assertTrue(testArray.contains(text));
    }

    @Test
    public void testGetRusAlphabet() throws Exception {
        Character text = 'Ф';
        List<Character> testArray = CaesarCipher.getAlphabet("ф");
        Assert.assertTrue(testArray.contains(text));
    }

    @Test(expected = CaesarException.class)
    public void testGetCaesarException() throws Exception {
        CaesarCipher.getAlphabet(".");
    }

    @Test
    public void testCountSpecSymbolInPassword() throws Exception {
        Random random = new Random();
        String key = "abasd" + random.nextInt();
        String site = "site" + random.nextInt() + ".com";
        Assert.assertTrue(PassGen.countSpecSymbol(PassGen.executeEncrypting(site, key)) > 2);
    }

    @Test
    public void testConstantOutputWithEqualInput() throws Exception {
        String key = "abasd123132";
        String site = "site.com";
        Assert.assertEquals("ZLRwOcaOzBHfF+UkrZ+5XvImhL6P4=",PassGen.executeEncrypting(site, key));
    }

}
