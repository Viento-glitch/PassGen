package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static main.CesarCipher.encrypt;
import static main.CesarCipher.getAlphabet;

public class Owner {
    static ArrayList<String> site = new ArrayList<>();
    static ArrayList<String> logins = new ArrayList<>();
    static ArrayList<String> password = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

//        String key = requestKey();
        String key = "93ebefer";
        String lettersFromTheKey = takeLettersFrom(key);
        String numbersFromTheKey = takeNumbersFrom(key);

        int firstNumber = getNumber(numbersFromTheKey, 0);
        int lastNumber = getNumber(numbersFromTheKey, numbersFromTheKey.length() - 1);

        ArrayList<Character> alphabet = getAlphabet(lettersFromTheKey);


        int numberToMoveCesar = getNumberToMoveCesar(alphabet, numbersFromTheKey);
        String salt = getSalt(numbersFromTheKey, lettersFromTheKey);

//        String selectedSite = requestSite();
        String selectedSite = "odnoklassniki.com";


        String pass = makePassword(selectedSite, salt, numbersFromTheKey, firstNumber + lastNumber);
        System.out.println(pass);
        String testText = bufferedReader.readLine();
        if(pass.equals(testText)){
            System.out.println("пиздец");
        }
        bufferedReader.close();
    }

    private static String makePassword(String selectedSite, String salt, String numbersFromTheKey, int i) {
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
        return encrypt( result, result.length() / 3);
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


    private static String getSalt(String numberFromTheKey, String lettersFromTheKey) {
        String salt = "";
        salt = CesarCipher.execute(lettersFromTheKey, getNumberToMoveCesar(getAlphabet(lettersFromTheKey), numberFromTheKey));
        return salt;
    }

    private static String requestKey() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("===================================================\\");
        System.out.println("    Введите ключ для шифрования(запомните его)      \\");
        System.out.println("!   В СЛУЧАЕ УТРАТЫ ДАННЫЙ КЛЮЧ НЕ ВОССТАНОВИТЬ   !  }");
        System.out.println("====================================================/");
        return bufferedReader.readLine();
    }

    static String takeLettersFrom(String key) {
        String result = "";
        for (int i = 0; i < key.length(); i++) {
            if (!Character.isDigit(key.charAt(i))) {
                result += key.charAt(i);
            }
        }

        return result;
    }

    static String takeNumbersFrom(String key) {
        String result = "";
        for (int i = 0; i < key.length(); i++) {
            if (Character.isDigit(key.charAt(i))) {
                result += key.charAt(i);
            }
        }
        return result;
    }


    private static String requestSite() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("===================================================\\");
        System.out.println("Введите сайт(с символами после точки)              ||");
        System.out.println("===================================================/");
        String site = bufferedReader.readLine();
        return site;
    }

    private static int selectMission() throws IOException {
        int mission;
        System.out.println("|^|=============================================|^|");
        System.out.println("//             Выбирите действие                //");
        System.out.println("\\               1. Найти данные                \\\\");
        System.out.println("//              2. Добавить новые данные        //");
        System.out.println("\\\\              3. Заменить данные            \\");
        System.out.println("|_|=============================================|_|");
        System.out.println("   ---...4.Возврат к предыдущему действию...---");
        mission = readInt();
        return mission;
    }

    private static void joke() {
        System.out.println("===================================================");
        System.out.println("Молодой а уже память подводит?                   =)");
        System.out.println("===================================================");
    }

    private static int selectProfile() throws IOException {
        int numberOfName;
        System.out.println(" Управление производится посредством ввода цифры");
        System.out.println("|^|============================================|^|");
        System.out.println("//            Выберите имя пользователя         \\\\");
        System.out.println("\\\\                 1. Елена                   //");
        System.out.println("//                 2. Александр                 \\\\");
        System.out.println("\\\\                 3. Сергей                  //");
        System.out.println("|_|============================================|_|");
        numberOfName = readInt();
        return numberOfName;
    }

    static boolean finder(String site) {
        int result = findIt(site);
        if (result >= 0) {
            System.out.println("Сайт найден.");
            System.out.println("Логин: " + logins.get(result));
            System.out.println("Пароль: " + password.get(result));
            return true;
        } else {
            System.out.println("Сайт не найден.=(");
            return false;
        }
    }

    static int findIt(String findSite) {
        for (int i = 0; i < site.size(); i++) {
            if (site.get(i).equals(findSite)) return i;
        }
        return -1;
    }

    static int readInt() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(bufferedReader.readLine());

    }
}