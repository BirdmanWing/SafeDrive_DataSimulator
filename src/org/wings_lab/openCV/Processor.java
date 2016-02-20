package org.wings_lab.openCV;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 * The class work for the face & eyes detection.
 * Created by Wing on 21/2/2016.
 */
public class Processor {
    private CascadeClassifier face_cascade, eyes_cascade;
    private Trigger t;

    public Processor() {
        face_cascade = new CascadeClassifier("vendor/opencv-3.1.0/data/haarcascades/haarcascade_frontalface_alt.xml");
        eyes_cascade = new CascadeClassifier("vendor/opencv-3.1.0/data/haarcascades/haarcascade_eye.xml");

        if (face_cascade.empty() | eyes_cascade.empty()) {
            System.out.println("--(!)Erroring A\n");
        } else {
            System.out.println("Classifier loooaaaaaded up");
        }

        t = new Trigger();
    }

    public Mat detect(Mat inputframe) {
        long startTime = System.nanoTime();
        Mat mRgba = new Mat();
        Mat mGrey = new Mat();
        MatOfRect faces = new MatOfRect();
        inputframe.copyTo(mRgba);
        inputframe.copyTo(mGrey);
        Imgproc.cvtColor(mRgba, mGrey, Imgproc.COLOR_BGR2GRAY);
        Imgproc.equalizeHist(mGrey, mGrey);
        face_cascade.detectMultiScale(mGrey, faces);
        long endTime = System.nanoTime();

        //detection log
        //System.out.println(String.format("Detect: %.2f ms", (float) (endTime - startTime) / 1000000));
        //System.out.println(String.format("Detectedaces", faces.toArray().length));

        //detection

        for (Rect rect : faces.toArray()) {
            Imgproc.rectangle(mRgba, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(255, 0, 255), 4, 8, 0);

            Rect crop = new Rect(rect.x, rect.y, rect.width, (int)(rect.height/1.5));
            Mat faceROI = mGrey.submat(crop);
            MatOfRect eyes = new MatOfRect();
            eyes_cascade.detectMultiScale(faceROI, eyes);
            for (Rect rect_eye : eyes.toArray()) {
                Point centre = new Point(rect.x + rect_eye.x + rect_eye.width*0.5, rect.y + rect_eye.y + rect_eye.height*0.5);
                Imgproc.ellipse(mRgba, centre, new Size(rect_eye.width*0.5, rect_eye.height*0.5), 0, 0, 360, new Scalar(0, 255, 0), 4, 8, 0);
                t.update(false);
            }
        }

        return mRgba;
    }
}
