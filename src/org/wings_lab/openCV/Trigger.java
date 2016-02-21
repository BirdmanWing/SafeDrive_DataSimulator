package org.wings_lab.openCV;

import org.wings_lab.M2X;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * To count the seconds
 * Created by Wing on 21/2/2016.
 */
public class Trigger {
    boolean onOff = false;
    Timer timer = new Timer();
    Thread t;

    public void update(boolean trigger) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                if (!trigger) {
                    timer.cancel();
                    try {
                        M2X.UpdateEyesDetection(0);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    onOff = false;
                }
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            M2X.UpdateEyesDetection(1);
                            onOff = true;
                            System.out.println("Trigger!");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }, 2000);
            }
        };
        try {
            t.stop();
        } catch (Exception ex) {}
        t = new Thread(r);
        t.start();
    }
}
