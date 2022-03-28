package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        MessageProvider.printModeSelection();
        Scanner scanner = new Scanner(System.in);

        label:
        while (scanner.hasNext()) {
            String str = scanner.nextLine();

            switch (str) {
                case "1": {
                    Encryptor.encryption();
                    break label;
                }
                case "2": {
                    Encryptor.decoding();
                    break label;
                }
                case "3": {
                    Brutforce.cryptanalysis();
                    break label;
                }
                case "0":
                    System.out.println("Выход");
                    break label;
                default:
                    MessageProvider.printModeSelection();
                    break;
            }
        }
    }

}





