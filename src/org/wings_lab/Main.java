package org.wings_lab;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //initial var
        Scanner scanner = new Scanner(System.in);
        String input;
        M2X m2x = new M2X();

        //initial runnable
        Runnable speed = new Runnable() {
            public void run() {

            }
        };

        System.out.println("Script started");
        System.out.println("Enter s to start");

        input = scanner.next();
    }
}
