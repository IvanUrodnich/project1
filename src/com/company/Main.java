package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        printModeSelection();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String str = scanner.nextLine();

            if (str.equals("1")) {
                System.out.println("Режим Шифрования\nВведите ключ:");
                Path path = Path.of("C:\\Users\\uiv\\IdeaProjects\\project1\\source");
                String original = Files.readString(path);
                String encrypted = getEncryption(original, keyEntry());
                System.out.println("\nИсходный текст: \n\n" + original + "\n\nЗашифрованный текст: \n\n" + encrypted);
                while (true) {
                    if (Files.exists(Path.of("C:\\Users\\uiv\\IdeaProjects\\project1\\encrypted.txt"))) {
                        Files.delete(Path.of("C:\\Users\\uiv\\IdeaProjects\\project1\\encrypted.txt"));
                        continue;
                    } else {
                        Files.createFile(Path.of("C:\\Users\\uiv\\IdeaProjects\\project1\\encrypted.txt"));
                        Files.write(Path.of("C:\\Users\\uiv\\IdeaProjects\\project1\\encrypted.txt"), Collections.singleton(encrypted));
                    }
                    break;
                }
                break;

            } else if (str.equals("2")) {

                System.out.println("Режим Расшифровки\nВведите ключ:");
                Path path = Path.of("C:\\Users\\uiv\\IdeaProjects\\project1\\encrypted.txt");
                String encrypted = Files.readString(path);
                String decrypted = getEncryption(encrypted, -1 * keyEntry());
                System.out.println("\nЗашифрованный текст: \n\n" + encrypted + "\nРасшифрованный текст: \n\n" + decrypted);
                break;


            } else if (str.equals("3")) {
                System.out.println("Режим Криптоанализа");
                Path path = Path.of("C:\\Users\\uiv\\IdeaProjects\\project1\\encrypted.txt");
                bruteForce(Files.readString(path));
                break;

            } else if (str.equals("0")) {
                System.out.println("Выход");
                break;
            } else {
                printModeSelection();
            }
        }

    }

    public static int keyEntry() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else if (scanner.hasNextLine()) {
                System.out.println("Введите ключ:");
            }
        }
    }

    public static void printModeSelection() {
        System.out.println("Выберите режим работы: ");
        System.out.println("1 - Режим Шифрования");
        System.out.println("2 - Режим Расшифровки");
        System.out.println("3 - Режим Криптоанализа");
        System.out.println("0 - Выход");
    }

    private static String getEncryption(String message, int key) {
        StringBuilder result = new StringBuilder(message.length());
        char temp;
        for (int i = 0; i < message.length(); i++) {
            temp = message.charAt(i);
            if (Character.isLetter(message.charAt(i))) {
                temp += key % 63;
                if (temp > 'я')
                    temp = (char) (temp % 'я' + 'А');
                else if (temp <= 'А')
                    temp += 63;
            }
            result.append(temp);
        }
        return result.toString();
    }

    public static void bruteForce(String message) {
        StringBuilder result = new StringBuilder(message.length());
        char temp;
        int key;
        String coin = "даже";
        for (int j = -63; j < 63; j++) {
            key = j;
            for (int i = 0; i < message.length(); i++) {
                temp = message.charAt(i);
                if (Character.isLetter(message.charAt(i))) {
                    temp += key % 63;
                    if (temp > 'я')
                        temp = (char) (temp % 'я' + 'А');
                    else if (temp <= 'А')
                        temp += 63;
                }
                result.append(temp);
            }
            String str = String.valueOf(result);
            if (str.contains(coin)) {
                System.out.println("Расшифрованная строка:\n" + str + "\nКлюч шифрования: " + (-1) * key + "\nКлюч расшифровки: " + key);
                break;
            } else {
                System.out.println("Совпадения не найдены");
            }
        }
    }

}





