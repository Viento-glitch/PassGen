package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Owner {
    static ArrayList<String> site = new ArrayList<>();
    static ArrayList<String> logins = new ArrayList<>();
    static ArrayList<String> password = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            int selectedOwner;
            int mission;
            selectedOwner = selectProfile();
            if (selectedOwner == 3) {
                joke();
            }
            mission = selectMission();
            if (mission == 2) {
                site(bufferedReader);
            }
            break;
        }

        bufferedReader.close();
    }

    private static void site(BufferedReader bufferedReader) throws IOException {
        System.out.println("===================================================\\");
        System.out.println("Введите сайт(с символами после точки)              ||");
        System.out.println("===================================================/");
        String site = bufferedReader.readLine();
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