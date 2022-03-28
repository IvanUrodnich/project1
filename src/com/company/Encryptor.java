package com.company;

public class Encryptor {
    public static String getEncryption(String message, int key) {
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
}
