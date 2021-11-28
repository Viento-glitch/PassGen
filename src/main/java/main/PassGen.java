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

    public static String executeEncryption(String key, String selectedSite) throws Exception {
        String lettersFromTheKey = getLettersFrom(key);
        String numbersFromTheKey = getNumbersFrom(key);

        int firstNumber = getNumber(numbersFromTheKey, 0);
        int lastNumber = getNumber(numbersFromTheKey, numbersFromTheKey.length() - 1);

        ArrayList<Character> alphabet = getAlphabet(lettersFromTheKey);


        int numberToMoveCesar = getNumberToMoveCesar(alphabet, numbersFromTheKey);
        String salt = getSalt(numbersFromTheKey, lettersFromTheKey);


        String pass = makePassword(selectedSite, salt, numbersFromTheKey, firstNumber + lastNumber);

        String AES_Password = AES.execute(key, selectedSite);
        return AES_Password;
    }

    private static String makePassword(String selectedSite, String salt, String numbersFromTheKey, int i) throws Exception {
        String result = "";
        if (checkSite(selectedSite)) {
            int countSalt = 0;

            for (int j = 0; j < selectedSite.length(); j++) {
                if (countSalt == 0) {
                    result += numbersFromTheKey.charAt(0);
                }
                countSalt++;

                if (j % i == 2) {
                    result += salt;
                }

                if (i == j) {
                    result += salt;
                }
                result += selectedSite.charAt(j);

                if (countSalt < 2) {
                    while (salt.length() < i) {
                        salt += i;
                    }
                }
            }

        }
        return encrypt(result, result.length() / 3);
    }

    static boolean checkSite(String selectedSite) {
        if (selectedSite.contains(".")) {
            if (selectedSite.charAt(selectedSite.length() - 1) != '.') {
                return true;
            }
        }
        return false;
    }

    static int getNumber(String numberFromTheKey, int i) {
        return Integer.parseInt(String.valueOf(numberFromTheKey.charAt(i)));
    }


    static int getNumberToMoveCesar(ArrayList<Character> abc, String numbersFromTheKey) {
        int firstNumber = 0;
        int lastNumber = 0;

        int p = 0;
        while (true) {

            for (int i = 0; i < numbersFromTheKey.length() - 1; i++) {
                if (firstNumber == 0 || firstNumber == 1) {
                    firstNumber = getNumber(numbersFromTheKey, i);
                    firstNumber += p;
                } else {
                    break;
                }
            }
            for (int i = numbersFromTheKey.length() - 1; i > 0; i--) {
                if (lastNumber == 0 || lastNumber == 1) {
                    lastNumber = getNumber(numbersFromTheKey, i);
                    lastNumber += p;
                } else {
                    break;
                }
            }

            int result = firstNumber * lastNumber;
            if (String.valueOf(result).length() < 2) {
                result *= result;
            }

            while (true) {
                if (result > abc.size()) {
                    result = result / 2;
                } else if (result < abc.size()) {
                    break;
                } else {
                    result -= (abc.size() % result);
                }
            }
            if (result > 0) {
                return result;
            }
            p++;
        }
    }


    private static String getSalt(String numberFromTheKey, String lettersFromTheKey) throws Exception {
        String salt = "";
        salt = CesarCipher.execute(lettersFromTheKey, getNumberToMoveCesar(getAlphabet(lettersFromTheKey), numberFromTheKey));
        return salt;
    }

    static String getLettersFrom(String key) {
        try {
            if (key.equals("") || key.equals(null)) {
                throw new Exception("Метод getLettersFrom получил пустой параметр");
            }
            String result = "";
            for (int i = 0; i < key.length(); i++) {
                if (!Character.isDigit(key.charAt(i))) {
                    result += key.charAt(i);
                }
            }

            return result;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }


    static String getNumbersFrom(String key) {
        try {
            if (key.equals("") || key.equals(null)) {
                throw new Exception("Метод getNumbersFrom получил пустой параметр");
            }

            String result = "";
            for (int i = 0; i < key.length(); i++) {
                if (Character.isDigit(key.charAt(i))) {
                    result += key.charAt(i);
                }
            }

            if(result.equals("")){
                throw new Exception("В подаваемом значении не обнаружено цифр");
            }

            return result;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }}