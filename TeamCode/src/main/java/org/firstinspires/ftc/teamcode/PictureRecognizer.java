package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Vuforia - Image Recognition
 * Created by Jeff Sprenger - Feb 3, 2018
 * Based on the video tutorial: FTC Tutorials - Vuforia Basics
 * https://www.youtube.com/watch?v=gbcdveLP-Ns
 *
 * Image Targets
 *     A:
 *     B:
 *     C:
 */

@TeleOp(name = "ImageRecognition", group = "Vision")
public class PictureRecognizer extends LinearOpMode {

    VuforiaLocalizer vuforiaLocalizer;
    VuforiaLocalizer.Parameters parameters;
    VuforiaTrackables visionTargets;

    //array would be better
    VuforiaTrackable target01;
    VuforiaTrackable target02;
    VuforiaTrackable target03;
    VuforiaTrackableDefaultListener listener01;
    VuforiaTrackableDefaultListener listener02;
    VuforiaTrackableDefaultListener listener03;

    //matrices
    OpenGLMatrix glMat;

    static final int IMAGE_A = 0;
    static final int IMAGE_B = 1;
    static final int IMAGE_C = 2;
    static final int IMAGE_NONE = 3;

    //this key belongs to Essex Robotics
    public static final String VUFORIA_KEY = "ARDXCjL/////AAAAGXuBMxMI5EhrvrvaZoqpzmpfBmB1WDZJn56wtltNERZooZAfHDBUmdq10DYuq/f7VYSyV7pEtBGzGANIJJgM+ci+Kc/GrLyuKzoHPdV6VJAozfHadE2vFpBl5HnYUotKhCTC6ocsnEFZ9M1WaFh2KKSXXLOnQiPRWgYTq4o+KcUaY5Ki9BtcbnjSodBmcmW4lS/Qz6qfgdlHA/Dhm/XtLgtW7OhUwxyPg0i3ZKsQ8FyWNGkFZxd7yvo3p+AtzHb86o6hueWdP1mn1jZlcrp5IQILYBc4h11bngmmtUQ8EMsvaTLIDPivzSNjbrnlJ5WZNa2di5mr8tdTgByxmM3cadzS0U9VKG5KB6TCq2pcJvE9";

    //simply checks for recognition of image A,B,C
    public void runOpMode() throws InterruptedException {

        setupVuforia();
        waitForStart();
        visionTargets.activate();

        while(opModeIsActive()) {
            // get position of robot from trackables
        }
    }

    private void setupVuforia() {
        //remove R.id.cameraMonitorViewId) to turn off display - saves batter power for robot controller phone
        parameters = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforiaLocalizer = ClassFactory.createVuforiaLocalizer(parameters);

        visionTargets = vuforiaLocalizer.loadTrackablesFromAsset("RoverRuckus");

        target01 = visionTargets.get(IMAGE_A);
        target01.setName("Image A");
        listener01 = (VuforiaTrackableDefaultListener) target01.getListener();

        target02 = visionTargets.get(IMAGE_B);
        target02.setName("Image B");
        listener02 = (VuforiaTrackableDefaultListener) target02.getListener();

        target03 = visionTargets.get(IMAGE_C);
        target03.setName("Image C");
        listener03 = (VuforiaTrackableDefaultListener) target03.getListener();
    }
}