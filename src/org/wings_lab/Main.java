package org.wings_lab;


import org.wings_lab.openCV.Camera;

public class Main {

    public static void main(String[] args) {
        /**
        //initial var
        Scanner scanner = new Scanner(System.in);
        String input;
        M2X m2x = new M2X();

        System.out.println("Script started");
        System.out.println("Enter s to start");

        //input = scanner.next();
         **/
        Camera c = new Camera();
        c.start();

    }
}
