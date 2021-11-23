package main;

import java.io.IOException;
import java.io.ObjectInputFilter;
import java.util.ArrayList;
import java.util.Locale;


class CesarCipher {

    public static String execute(String translatedText, int selectedIndex) {
        ArrayList<Character> alphabet = getAlphabet(translatedText);
        return encrypt(translatedText, selectedIndex);
    }

    public static ArrayList<Character> getAlphabet(String translatedText) {
        if (!isNumeric(translatedText)) {

            ArrayList<Character> rusLetters = getRusLetters();
            ArrayList<Character> engLetters = getEngLetters();

            String upperCasedText = translatedText.toUpperCase();
            ArrayList<Character> alphabet = null;


            for (int i = 0; i < upperCasedText.length(); i++) {
                char c = upperCasedText.charAt(i);
                if (engLetters.contains(c)) {
                    alphabet = engLetters;
                    break;
                }
                if (rusLetters.contains(c)) {
                    alphabet = rusLetters;
                    break;
                }
            }

            if (alphabet == null) {
                throw new RuntimeException("Алфавит не обнаружен.");
            } else {
                return alphabet;
            }

        } else {
            throw new RuntimeException("Ваш ключ не содержит букв английского и русского алфавита.\n " +
                    "Ключ подобного рода не подходит.");
        }
    }


    private static boolean isNumeric(String translatedText) {
        try {
            Integer.parseInt(translatedText);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static String encrypt(String text, int p) {
        ArrayList<Character> alphabet = getAlphabet(text);
        text = text.toUpperCase(Locale.ROOT);

        ArrayList<Integer> numbersOfCodedCharacters = new ArrayList<>();//список цифр равный зашифрованным символам
        for (int i = 0; i < text.length(); i++) {
            final char currentCharacterBeingChecked = text.charAt(i);
            if (alphabet.contains(currentCharacterBeingChecked)) {
                for (int j = 0; j < alphabet.size(); j++) {
                    if (currentCharacterBeingChecked == ' ') {
                        numbersOfCodedCharacters.add(-1);
                        break;
                    }
                    if (currentCharacterBeingChecked == alphabet.get(j)) {
                        numbersOfCodedCharacters.add(j);
                        break;
                    }
                    if (j + 1 == alphabet.size()) {
                        System.out.println("Символ не был обнаружен в алфавите! искомый символ =" + currentCharacterBeingChecked);
                    }
                }
            }
        }


        ArrayList<String> resultArray = new ArrayList<>();
        for (int j = 0; j < alphabet.size(); j++) {
            String resultText = "";
            for (Integer numbersOfCodedCharacter : numbersOfCodedCharacters) {
                if (numbersOfCodedCharacter != -1) {
                    int countIndexOfABC = numbersOfCodedCharacter + j;
                    if (countIndexOfABC >= alphabet.size()) countIndexOfABC = countIndexOfABC - alphabet.size();
                    resultText += alphabet.get(countIndexOfABC);
                } else {
                    resultText += " ";
                }
            }
            resultArray.add(resultText);
        }
        int positivityIndex = 0;
        if (p < 0) {
            positivityIndex = alphabet.size() + p;
        } else if (p != 0) {
            positivityIndex = p;
        } else {
            throw new RuntimeException("Обнаружен 0 переданом значении для смещения индекса");

        }
        return resultArray.get(positivityIndex);
    }

    static ArrayList<Character> getEngLetters() {
        ArrayList<Character> engAbc = new ArrayList<>();
        engAbc.add('A');
        engAbc.add('B');
        engAbc.add('C');
        engAbc.add('D');
        engAbc.add('E');
        engAbc.add('F');
        engAbc.add('G');
        engAbc.add('H');
        engAbc.add('I');
        engAbc.add('J');
        engAbc.add('K');
        engAbc.add('L');
        engAbc.add('M');
        engAbc.add('N');
        engAbc.add('O');
        engAbc.add('P');
        engAbc.add('Q');
        engAbc.add('R');
        engAbc.add('S');
        engAbc.add('T');
        engAbc.add('U');
        engAbc.add('V');
        engAbc.add('W');
        engAbc.add('X');
        engAbc.add('Y');
        engAbc.add('Z');
        return engAbc;
    }


    static ArrayList<Character> getRusLetters() {
        ArrayList<Character> abc = new ArrayList<>();

        abc.add('А');
        abc.add('Б');
        abc.add('В');
        abc.add('Г');
        abc.add('Д');
        abc.add('Е');
        abc.add('Ё');
        abc.add('Ж');
        abc.add('З');
        abc.add('И');
        abc.add('Й');
        abc.add('К');
        abc.add('Л');
        abc.add('М');
        abc.add('Н');
        abc.add('О');
        abc.add('П');
        abc.add('Р');
        abc.add('С');
        abc.add('Т');
        abc.add('У');
        abc.add('Ф');
        abc.add('Х');
        abc.add('Ц');
        abc.add('Ч');
        abc.add('Ш');
        abc.add('Щ');
        abc.add('Ъ');
        abc.add('Ы');
        abc.add('Ь');
        abc.add('Э');
        abc.add('Ю');
        abc.add('Я');
        return abc;
    }
}