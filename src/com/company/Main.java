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
        label:
        while (scanner.hasNext()) {
            String str = scanner.nextLine();

            switch (str) {
                case "1": {
                    System.out.println("Режим Шифрования\nВведите ключ:");
                    Path path = Path.of("source");
                    String original = Files.readString(path);
                    String encrypted = Encryptor.getEncryption(original, keyEntry());
                    System.out.println("\nИсходный текст: \n\n" + original + "\n\nЗашифрованный текст: \n\n" + encrypted);
                    while (true) {
                        if (Files.exists(Path.of("encrypted.txt"))) {
                            Files.delete(Path.of("encrypted.txt"));
                            continue;
                        } else {
                            Files.createFile(Path.of("encrypted.txt"));
                            Files.write(Path.of("encrypted.txt"), Collections.singleton(encrypted));
                        }
                        break;
                    }
                    break label;

                }
                case "2": {

                    System.out.println("Режим Расшифровки\nВведите ключ:");
                    Path path = Path.of("encrypted.txt");
                    String encrypted = Files.readString(path);
                    String decrypted = Encryptor.getEncryption(encrypted, -1 * keyEntry());
                    System.out.println("\nЗашифрованный текст: \n\n" + encrypted + "\nРасшифрованный текст: \n\n" + decrypted);
                    break label;

                }
                case "3": {
                    System.out.println("Режим Криптоанализа");
                    Path path = Path.of("encrypted.txt");
                    Brutforce.bruteForce(Files.readString(path));
                    break label;

                }
                case "0":
                    System.out.println("Выход");
                    break label;
                default:
                    printModeSelection();
                    break;
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

}





