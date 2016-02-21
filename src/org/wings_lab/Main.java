package org.wings_lab;


import org.wings_lab.openCV.Camera;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //initial var
        Scanner scanner = new Scanner(System.in);
        String cmd;
        int speed;
        Camera cam = new Camera();

        System.out.println("Script started");
        System.out.println("Enter s to start");

        while (true) {
            cmd = scanner.next();
            if (cmd.equals("s")) {
                System.out.println("Enter the speed (mph)");
                speed = scanner.nextInt();
                if (speed > 0) {
                    try {
                        M2X.UpdateSpeed(speed);
                        cam.start();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    System.exit(0);
                }
            } else
                System.out.println("error");
        }
    }
}
