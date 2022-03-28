package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

public class Encryptor {

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

    public static void encryption() throws IOException {
        System.out.println("Режим Шифрования\nВведите ключ:");
        Path path = Path.of("source");
        String original = Files.readString(path);
        String encrypted = getEncryption(original, MessageProvider.keyEntry());
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
    }

    public static void decoding() throws IOException {
        System.out.println("Режим Расшифровки\nВведите ключ:");
        Path path = Path.of("encrypted.txt");
        String encrypted = Files.readString(path);
        String decrypted = getEncryption(encrypted, -1 * MessageProvider.keyEntry());
        System.out.println("\nЗашифрованный текст: \n\n" + encrypted + "\nРасшифрованный текст: \n\n" + decrypted);
    }

}
