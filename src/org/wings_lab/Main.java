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
                try {
                    M2X.UpdateSpeed(speed);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                cam.start();
            } else if (cmd.equals("u")) {
                System.out.println("Enter the speed (mph)");
                speed = scanner.nextInt();
                try {
                    M2X.UpdateSpeed(speed);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                cam.stop();
            } else if (cmd.equals("x"))
                System.exit(0);
            else
                System.out.println("error");
            System.out.println("Enter u to update speed, x to exit");
        }
    }

}
