package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by howardzhang on 11/10/16.
 */
@TeleOp(name="VuforiaTest", group = "Empty")
public class DaRealVuforiaTest extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException {
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        params.vuforiaLicenseKey = "AbnwLPb/////AAAAGbZt0tXfv0+Xm9x4mKReybCGiDabToKD8Cj8lhIlHaNr56qx0TWoO+j3DvgPpRaXAZTgspbiBybsoRGhwCdO3Yt/6aA4USE9StUPcePbyL04IiUMNprqc9PzR7GG6vS6YQvnLYOjvrZTAQtO87krd1tJDYsYCY3coFwp3fsP7DudnCqoLk3D2po/QD56f9CenPq5J+dw4t3cOc+o05yQR4LCH9AWr+iG+1MaFUWhkHjkvfn1WmCCqW8kjNKtEJIXucAsA2z0PLUXDYsxJxm7WIQYc+HZGnElG/0isWaL0048nt7mMvLy7igRo2eGvVtt7lWdajrRKuZLrnJSWd/fjs7wVZ0jqv2NPIqwlp97k0qT";
        params.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;

        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(params);
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 4);
        VuforiaTrackables beacons = vuforia.loadTrackablesFromAsset("FTC_2016-17");
        beacons.get(0).setName("Wheels");
        beacons.get(1).setName("Tools");
        beacons.get(2).setName("Lego");
        beacons.get(3).setName("Gears");

        waitForStart();

        beacons.activate();

        VuforiaTrackableDefaultListener lis0 = (VuforiaTrackableDefaultListener)beacons.get(0).getListener();
        VuforiaTrackableDefaultListener lis1 = (VuforiaTrackableDefaultListener)beacons.get(1).getListener();
        VuforiaTrackableDefaultListener lis2 = (VuforiaTrackableDefaultListener)beacons.get(2).getListener();
        VuforiaTrackableDefaultListener lis3 = (VuforiaTrackableDefaultListener)beacons.get(3).getListener();

        while(opModeIsActive()){


            telemetry.update();
        }
    }
}
