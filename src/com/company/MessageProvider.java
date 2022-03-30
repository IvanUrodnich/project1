package com.company;

import java.util.Scanner;
import static com.company.Constant.KEY_ENTRY;

public class MessageProvider {

    public static int keyEntry() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else if (scanner.hasNextLine()) {
                System.out.println(KEY_ENTRY);
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
