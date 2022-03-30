package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Brutforce {

    private static void bruteForce(String message) {
        StringBuilder result = new StringBuilder(message.length());
        char temp;
        int key;
        String coin = Constant.SEARCH_MATCHES;
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
                System.out.println(Constant.DECIPHERED_TEXT + "\n" + str + "\n" + Constant.ENCRYPTION_KEY + (-1) * key + "\n" + Constant.DECRYPTION_KEY + key);
                break;
            } else {
                System.out.println(Constant.NO_MATCHES_FOUND);
            }
        }
    }

    public static void cryptanalysis() throws IOException {
        System.out.println(Constant.CRYPTANALYSIS_MODE);
        Path path = Path.of("encrypted.txt");
        bruteForce(Files.readString(path));
    }

}
