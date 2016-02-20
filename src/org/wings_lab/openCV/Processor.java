package org.wings_lab.openCV;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 * The class work for the face & eyes detection.
 * Created by Wing on 21/2/2016.
 */
public class Processor {
    private CascadeClassifier face_cascade;

    public Processor() {
        face_cascade = new CascadeClassifier("vendor/opencv-3.1.0/data/haarcascades/haarcascade_frontalface_alt.xml");
        if(face_cascade.empty())
        {
            System.out.println("--(!)Erroring A\n");
        }
        else
        {
            System.out.println("Facesifier loooaaaaaded up");
        }
    }

    public Mat detect(Mat inputframe) {
        long startTime = System.nanoTime();
        Mat mRgba=new Mat();
        Mat mGrey=new Mat();
        MatOfRect faces = new MatOfRect();
        inputframe.copyTo(mRgba);
        inputframe.copyTo(mGrey);
        Imgproc.cvtColor( mRgba, mGrey, Imgproc.COLOR_BGR2GRAY);
        Imgproc.equalizeHist( mGrey, mGrey );
        face_cascade.detectMultiScale(mGrey, faces);
        long endTime = System.nanoTime();
        System.out.println(String.format("Detect: %.2f ms", (float)(endTime - startTime)/1000000));
        System.out.println(String.format("Detectedaces", faces.toArray().length));
        for(Rect rect:faces.toArray())
        {
            Point center= new Point(rect.x + rect.width*0.5, rect.y + rect.height*0.5 );
            Imgproc.ellipse( mRgba, center, new Size( rect.width*0.5, rect.height*0.5), 0, 0, 360, new Scalar( 255, 0, 255 ), 4, 8, 0 );
        }
        return mRgba;
    }
}
