package com.company;

public class Brutforce {
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
