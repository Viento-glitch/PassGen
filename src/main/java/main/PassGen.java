package main;

import main.VsualInterface.Visual;

import java.util.List;

import static main.CaesarCipher.encrypt;
import static main.CaesarCipher.getAlphabet;

public class PassGen {
    public static void main(String[] args) {
        Visual frame = new Visual();
        frame.setVisible(true);
    }

    public static String executeEncrypting(String selectedSite, String key) throws GenException {
        try {
            String pass = getPassReadyToAES(selectedSite, key);
            String result = AES.execute(key, pass);
            return upgradeEncryptedPass(pass, result);
        } catch (GenException e) {
            throw new GenException("Не удалось выполнить шифрование", e);
        }
    }

    private static String upgradeEncryptedPass(String pass, String result) {
        while (true) {
            int specSymbolCounter = countSpecSymbol(result);
            if (checkSpecSymbol(result) && result.length() <= 30 && specSymbolCounter > 2) {
                return result;
            } else {
                result = AES.execute(result, pass);
                if (result.length() > 30) {
                    result = result.substring(result.length() - 30);
                }
            }
        }
    }

    static int countSpecSymbol(String result) {
        int number = 0;
        for (int i = 0; i < result.length(); i++) {
            if (isSpecSymbol(result.charAt(i))) {
                number++;
            }
        }
        return number;
    }

    private static boolean isSpecSymbol(char symbol) {
        return CaesarCipher.notFoundInAlphabet(symbol) && !Character.isDigit(symbol);
    }

    private static boolean checkSpecSymbol(String text) {
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if (CaesarCipher.notFoundInAlphabet(symbol) && !Character.isDigit(symbol)) {
                return true;
            }
        }
        return false;
    }

    private static String getPassReadyToAES(String selectedSite, String key) throws GenException {
        String numbersFromTheKey = getNumbersFrom(key);
        String result;
        try {
            if (checkSite(selectedSite)) {
                result = generatePassWithSalt(selectedSite, numbersFromTheKey);

            } else {
                throw new InputException("Ввёдённое значение не является сайтом");
            }
            return encrypt(result, result.length() / 3);
        } catch (CaesarException | InputException e) {
            throw new GenException("Неудалось создать пароль", e);
        }
    }


    private static String generatePassWithSalt(String key, String site) throws GenException {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < site.length() / 3; i++) {
            result.append(getSalt(key + site + result));
        }
        return result.toString();
    }

    static boolean checkSite(String selectedSite) {
        if (selectedSite.contains(".")) {
            return selectedSite.charAt(selectedSite.length() - 1) != '.';
        }
        return false;
    }

    static int getNumber(String numberFromTheKey, int i) {
        return Integer.parseInt(String.valueOf(numberFromTheKey.charAt(i)));
    }

    static int getNumberToMoveCesar(List<Character> alphabet, String numbersFromTheKey) {
        int firstNumber = 0;
        int lastNumber = 0;

        int counter = 0;
        while (true) {
            firstNumber = getGeneratedFirstNumber(numbersFromTheKey, firstNumber, counter);
            lastNumber = getGeneratedLastNumber(numbersFromTheKey, lastNumber, counter);
            int result = firstNumber * lastNumber;
            if (result == 0) {
                result = Integer.parseInt(numbersFromTheKey) * numbersFromTheKey.length();
            }
            if (String.valueOf(result).length() < 2) {
                result *= result;
            }
            while (result > alphabet.size()) {
                result = result / 2;
                if (result < alphabet.size()) {
                    break;
                } else {
                    result -= (alphabet.size() % result);
                }
            }
            if (result > 0) {
                return result;
            }
            counter++;
        }
    }

    private static int getGeneratedFirstNumber(String numbersFromTheKey, int firstNumber, int counter) {
        for (int i = 0; i < numbersFromTheKey.length() - 1; i++) {
            if (firstNumber == 0 || firstNumber == 1) {
                firstNumber = getMagicNumber(numbersFromTheKey, counter, i);
            } else {
                break;
            }
        }
        return firstNumber;
    }

    private static int getGeneratedLastNumber(String numbersFromTheKey, int lastNumber, int counter) {
        for (int i = numbersFromTheKey.length() - 1; i > 0; i--) {
            if (lastNumber == 0 || lastNumber == 1) {
                lastNumber = getMagicNumber(numbersFromTheKey, counter, i);
            } else {
                break;
            }
        }
        return lastNumber;
    }

    private static int getMagicNumber(String numbersFromTheKey, int counter, int i) {
        int number;
        number = getNumber(numbersFromTheKey, i);
        number += counter;
        return number;
    }

    private static String getSalt(String key) throws GenException {
        String lettersFromTheKey = getLettersFrom(key);
        String numberFromTheKey = getNumbersFrom(key);
        try {
            return CaesarCipher.encrypt(lettersFromTheKey, getNumberToMoveCesar(getAlphabet(lettersFromTheKey), numberFromTheKey));
        } catch (CaesarException e) {
            throw new GenException("Неудалось создоть соль", e);
        }
    }

    static String getLettersFrom(String key) throws GenException {
        try {
            if (key.equals("")) {
                throw new InputException("Метод getLettersFrom получил пустой параметр");
            }
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < key.length(); i++) {
                if (!Character.isDigit(key.charAt(i))) {
                    result.append(key.charAt(i));
                }
            }

            if (result.toString().equals("")) {
                throw new InputException("В подаваемом значении не обнаружено букв.");
            }

            return result.toString();
        } catch (Exception e) {
            throw new GenException("Не удалось получить набор букв из текста :" + key, e);
        }
    }

    static String getNumbersFrom(String key) throws GenException {
        try {
            if (key.equals("")) {
                throw new InputException("Метод getNumbersFrom получил пустой параметр");
            }

            StringBuilder result = new StringBuilder();
            for (int i = 0; i < key.length(); i++) {
                if (Character.isDigit(key.charAt(i))) {
                    result.append(key.charAt(i));
                }
            }

            if (result.toString().equals("")) {
                throw new InputException("В подаваемом значении не обнаружено цифр");
            }

            return result.toString();
        } catch (Exception e) {
            throw new GenException("0", e);
        }
    }
}