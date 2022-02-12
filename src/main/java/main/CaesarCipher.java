package main;

import java.util.ArrayList;
import java.util.List;


class CaesarCipher {

    public static List<Character> getAlphabet(String translatedText) throws CaesarException {
        if (translatedText.isEmpty()) throw new CaesarException("Методу выдающему алфавит передали пустое поле");
        if (isNumeric(translatedText))
            throw new CaesarException("Переданный текст не содержит символов русского и английского алфавита.\n Ключ подобного рода не подходит.");
        if (isWrittenOnEng(translatedText)) return getEngLetters();
        if (isWrittenOnRus(translatedText)) return getRusLetters();

        throw new CaesarException("Warning! unidentified Alphabet:" + translatedText);
    }

    private static boolean isWrittenOnRus(String translatedText) {
        String upperCasedText = translatedText.toUpperCase();
        ArrayList<Character> rusLetters = getRusLetters();
        for (int i = 0; i < upperCasedText.length(); i++) {
            char c = upperCasedText.charAt(i);
            if (rusLetters.contains(c)) {
                System.out.println("In string was used:Rus alphabet");
                return true;
            }
        }
        return false;
    }

    private static boolean isWrittenOnEng(String translatedText) {
        String upperCasedText = translatedText.toUpperCase();
        ArrayList<Character> engLetters = getEngLetters();
        for (int i = 0; i < upperCasedText.length(); i++) {
            char c = upperCasedText.charAt(i);
            if (engLetters.contains(c)) {
                System.out.println("In string was used:Eng alphabet");
                return true;
            }
        }
        return false;
    }

    //! Добавить проверки всем методам требующим
    private static boolean isNumeric(String translatedText) {
        try {
            Integer.parseInt(translatedText);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static String encrypt(String text, int numberToCaesar) throws CaesarException {
        StringBuilder result = new StringBuilder();
        List<Character> alphabet = getAlphabet(text);
        for (int i = 0; i < text.length(); i++) {
            result.append(getEncryptedSymbol(text.charAt(i), numberToCaesar, alphabet));
        }
        return result.toString();
    }

    static Character getEncryptedSymbol(char c, int numberToCaesar, List<Character> alphabet) throws CaesarException {
        int indexInAlphabet;
        if (notFoundInAlphabet(c)) {
            if (Character.getNumericValue(c) >= alphabet.size()) {
                indexInAlphabet = Character.getNumericValue(c) - alphabet.size();
            } else {
                indexInAlphabet = Character.getNumericValue(c);
            }
        } else {
            indexInAlphabet = takeIndexInAlphabet(c, alphabet);
        }
        int caesarIndex = indexInAlphabet + getAbsoluteCaesarNumber(numberToCaesar, alphabet);
        while (caesarIndex >= alphabet.size()) {
            caesarIndex -= alphabet.size();
        }
        return alphabet.get(caesarIndex);
    }

    static boolean notFoundInAlphabet(char c) {
        try {
            getAlphabet(String.valueOf(c));
            return false;
        } catch (CaesarException e) {
            return true;
        }
    }

    private static int takeIndexInAlphabet(char c, List<Character> alphabet) throws CaesarException {
        for (int i = 0; i < alphabet.size(); i++) {
            if (alphabet.get(i).equals(Character.toUpperCase(c))) {
                return i;
            }
        }
        throw new CaesarException("Not found symbol." + c);
    }


    private static int getAbsoluteCaesarNumber(int numberToCaesar, List<Character> alphabet) throws CaesarException {
        int positivityIndex;
        if (numberToCaesar < 0) {
            positivityIndex = alphabet.size() + numberToCaesar;
        } else if (numberToCaesar > 0) {
            positivityIndex = numberToCaesar;
        } else {
            throw new CaesarException("Обнаружен 0 переданом значении для смещения индекса");
        }
        if (positivityIndex > alphabet.size()) positivityIndex -= alphabet.size();
        return positivityIndex;
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