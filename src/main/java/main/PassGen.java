package main;

import main.VsualInterface.Visual;

import java.util.ArrayList;

import static main.CesarCipher.encrypt;
import static main.CesarCipher.getAlphabet;

public class PassGen {
    public static void main(String[] args) {
        Visual frame = new Visual();
        frame.setVisible(true);
    }

    public static String executeEncrypting(String key, String selectedSite) throws GenException {
        try {
            String numbersFromTheKey = getNumbersFrom(key);

            int firstNumber = getNumber(numbersFromTheKey, 0);
            int lastNumber = getNumber(numbersFromTheKey, numbersFromTheKey.length() - 1);

            String pass = getPassReadyToAES(selectedSite, numbersFromTheKey, key, firstNumber + lastNumber);
            return AES.execute(key, pass);
        } catch (GenException e) {
            throw new GenException("Не удалось выполнить шифрование", e);
        }
    }

    private static String getPassReadyToAES(String selectedSite, String numbersFromTheKey, String key, int i) throws GenException {
        String lettersFromTheKey = getLettersFrom(key);
        String salt = getSalt(numbersFromTheKey, lettersFromTheKey);
        StringBuilder result = new StringBuilder();
        try {
            if (checkSite(selectedSite)) {
                generatePassWithSalt(selectedSite, salt, numbersFromTheKey, i, result);
            } else {
                throw new InputException("Ввёдённое значение не является сайтом");
            }
            return encrypt(result.toString(), result.length() / 3);
        } catch (CesarException | InputException e) {
            throw new GenException("Неудалось создать пароль", e);
        }
    }

    private static void generatePassWithSalt(String selectedSite, String salt, String numbersFromTheKey, int number, StringBuilder result) {
        int countSalt = 0;
        for (int j = 0; j < selectedSite.length(); j++) {
            if (countSalt == 0) {
                result.append(numbersFromTheKey.charAt(0));
            }
            countSalt++;
            if (j % number == 2) {
                result.append(salt);
            }
            if (number == j) {
                result.append(salt);
            }
            result.append(selectedSite.charAt(j));
            if (countSalt < 2) {
                while (salt.length() < number) {
                    salt += number;
                }
            }
        }
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

    static int getNumberToMoveCesar(ArrayList<Character> alphabet, String numbersFromTheKey) {
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

    private static String getSalt(String numberFromTheKey, String lettersFromTheKey) throws GenException {
        try {
            return CesarCipher.execute(lettersFromTheKey, getNumberToMoveCesar(getAlphabet(lettersFromTheKey), numberFromTheKey));
        } catch (CesarException e) {
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