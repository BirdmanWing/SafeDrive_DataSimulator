package org.wings_lab.openCV;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;

/**
 * Stream the camera.
 * Created by Wing on 21/2/2016.
 */
public class Camera {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("OpenCV loaded");
    }

    //initial variables
    String window_name = "Camera Streaming";
    JFrame frame = new JFrame(window_name);
    Processor processor = new Processor();
    Panel panel = new Panel();
    VideoCapture capture;
    Mat cam_image;

    public Camera() {

        //JFrame setup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setContentPane(panel);
    }

    public void start() {
        //show the panel
        frame.setVisible(true);

        //Read the video stream
        cam_image = new Mat();
        capture = new VideoCapture(0);
        if (capture.isOpened()) {
            while (true) {
                capture.read(cam_image);
                if (!cam_image.empty()) {
                    //resize the window to fit the image
                    frame.setSize(cam_image.width(), cam_image.height());

                    //apply classifier to capture the image
                    cam_image = processor.detect(cam_image);

                    //display the image
                    panel.MatToBufferedImage(cam_image);
                    panel.repaint();
                } else {
                    System.out.println(" --(!) No captured frame -- Break!");
                    break;
                }
            }
        }
    }
}
