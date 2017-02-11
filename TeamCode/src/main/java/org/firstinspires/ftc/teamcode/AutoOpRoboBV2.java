package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

//Hardware for the robot is defined by HardwareRobo1
@Autonomous(name="AutoRoboB2", group = "Robo1")
//@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="AutoOpRoboB", group="Robo1")
public class AutoOpRoboBV2 extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareRobo1 robot = new HardwareRobo1();
    VectorF trans = null;
    public VuforiaTrackableDefaultListener lis0;
    public VuforiaTrackableDefaultListener lis1;
    public VuforiaTrackableDefaultListener lis2;
    public VuforiaTrackableDefaultListener lis3;
    public boolean tracked1 = false;
    public boolean tracked2 = false;
    public boolean tracked3 = false;
    public boolean tracked4 = false;

    @Override
    public void runOpMode() throws InterruptedException, NullPointerException {

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        telemetry.addData("Say", "Running Autonomous");    //
        telemetry.update();

        //initialize Vuforia
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
        beacons.activate();

        //initialize the listeners that find the visual cues
        lis0 = (VuforiaTrackableDefaultListener) beacons.get(0).getListener();
        lis1 = (VuforiaTrackableDefaultListener) beacons.get(1).getListener();
        lis2 = (VuforiaTrackableDefaultListener) beacons.get(2).getListener();
        lis3 = (VuforiaTrackableDefaultListener) beacons.get(3).getListener();


        waitForStart();
        // Wait for the game to start (driver presses PLAY)

        // run until the end of the match (driver presses STOP)
        while(opModeIsActive()) {
            //run all of the initial movements
            //(shoot the ball, line up with the wall, drive parallel to corner vortex)
            if (opModeIsActive()) {
                robot.colorPushL.setPower(1);
                try {
                    Thread.sleep(750);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                break;
            }
            if (opModeIsActive()) {
                robot.colorPushL.setPower(-1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                robot.colorPushL.setPower(0);
            }else{
                break;
            }
            if (opModeIsActive()) {
                back(.5);
                try {
                    Thread.sleep(350);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                break;
            }
            if(opModeIsActive()) {
                stopping();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                break;
            }
            if(opModeIsActive()) {
                robot.BRMotor.setPower(-.5);
                robot.FRMotor.setPower(-.5);
                robot.BLMotor.setPower(.5);
                robot.FLMotor.setPower(.5);
                try {
                    Thread.sleep(850);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                break;
            }
            if(opModeIsActive()) {
                stopping();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                break;
            }
            if(opModeIsActive()) {
                right(.5);
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                break;
            }
            if(opModeIsActive()) {
                stopping();
                robot.FRMotor.setPower(-.5);
                robot.BLMotor.setPower(-.5);
                try {
                    Thread.sleep(3500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                break;
            }
            if(opModeIsActive()) {
                left(0.15);
            }else{
                break;
            }
            //track beacons
            trackVuforia();
            if(opModeIsActive()) {
                back(0.25);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    telemetry.addData("Error", "InterruptedException Error!");
                    telemetry.update();
                }
            }else{
                break;
            }
            if(opModeIsActive()) {
                left(0.3);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                break;
            }
            if(opModeIsActive()) {
                left(0.15);
            }else{
                break;
            }
            //track beacons
            trackVuforia();
            //hit the cap ball off and park
            if(opModeIsActive()) {
                back(.75);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                break;
            }
            if(opModeIsActive()) {
                stopping();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                break;
            }
            if(opModeIsActive()) {
                robot.FRMotor.setPower(.75);
                robot.BLMotor.setPower(.75);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                break;
            }
            stopping();
            break;
        }
    }

    private void detectColor(){
        //detects color and presses the correct respective button
        telemetry.addData("Red  ", robot.color.red());
        telemetry.addData("Blue ", robot.color.blue());
        telemetry.update();
        if (robot.color.red() > robot.color.blue()) {
            robot.colorPushR.setPower(1);
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                telemetry.addData("Error", "InterruptedException Error!");
                telemetry.update();
            }
            robot.colorPushR.setPower(-1);
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                telemetry.addData("Error", "InterruptedException Error!");
                telemetry.update();
            }
            robot.colorPushR.setPower(0);
        } else if (robot.color.red() < robot.color.blue()) {
            robot.colorPushL.setPower(1);
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                telemetry.addData("Error", "InterruptedException Error!");
                telemetry.update();
            }
            robot.colorPushL.setPower(-1);
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                telemetry.addData("Error", "InterruptedException Error!");
                telemetry.update();
            }
            robot.colorPushL.setPower(0);
        }
    }

    private void trackVuforia() throws InterruptedException {
        //track the beacons
        while (opModeIsActive()) {
            if (lis0.isVisible() && !tracked1) {
                while (opModeIsActive()){
                    trans = lis0.getPose().getTranslation();
                    if (trans.get(0) > 10 || trans.get(0) < -10) {
                        if (trans.get(0) < 0) {
                            left(.05);
                        } else {
                            right(.05);
                        }
                    } else {
                        if (trans.get(2) < -125) {
                            forward(.15);
                        }else{
                            tracked1 = true;
                            trans = null;
                            break;
                        }
                    }
                    telemetry.addData("Location", "x: " + trans.get(0) + "\ty: " + trans.get(2));
                    telemetry.update();
                    robot.waitForTick(40);
                    idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
                }
                stopping();
                detectColor();
                break;
            }

            if (lis1.isVisible() && !tracked2) {
                while (opModeIsActive()) {
                    trans = lis1.getPose().getTranslation();
                    if (trans.get(0) > 10 || trans.get(0) < -10) {
                        if (trans.get(0) < 0) {
                            left(.05);
                        } else {
                            right(.05);
                        }
                    } else {
                        if (trans.get(2) < -125) {
                            forward(.15);
                        } else {
                            tracked2 = true;
                            trans = null;
                            break;
                        }
                    }
                    telemetry.addData("Location", "x: " + trans.get(0) + "\ty: " + trans.get(2));
                    telemetry.update();
                    robot.waitForTick(40);
                    idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
                }
                stopping();
                detectColor();
                break;
            }

            if (lis2.isVisible() && !tracked3) {
                while (opModeIsActive()) {
                    trans = lis2.getPose().getTranslation();
                    if (trans.get(0) > 10 || trans.get(0) < -10) {
                        if (trans.get(0) < 0) {
                            left(.05);
                        } else {
                            right(.05);
                        }
                    } else {
                        if (trans.get(2) < -125) {
                            forward(.15);
                        } else {
                            tracked3 = true;
                            trans = null;
                            break;
                        }
                    }
                    telemetry.addData("Location", "x: " + trans.get(0) + "\ty: " + trans.get(2));
                    telemetry.update();
                    robot.waitForTick(40);
                    idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
                }
                stopping();
                detectColor();
                break;
            }

            if (lis3.isVisible() && !tracked4) {
                while (opModeIsActive()) {
                    trans = lis3.getPose().getTranslation();
                    if (trans.get(0) > 10 || trans.get(0) < -10) {
                        if (trans.get(0) < 0) {
                            left(.05);
                        } else {
                            right(.05);
                        }
                    } else {
                        if (trans.get(2) < -125) {
                            forward(.15);
                        } else {
                            tracked4 = true;
                            trans = null;
                            break;
                        }
                    }
                    telemetry.addData("Location", "x: " + trans.get(0) + "\ty: " + trans.get(2));
                    telemetry.update();
                    robot.waitForTick(40);
                    idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
                }
                stopping();
                detectColor();
                break;
            }
            robot.waitForTick(40);
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }
    }
    //movement commands for simplicity
    private void right(double i){
        robot.FLMotor.setPower(-i);
        robot.FRMotor.setPower(i);
        robot.BLMotor.setPower(i);
        robot.BRMotor.setPower(-i);
    }

    private void left(double i){
        robot.FLMotor.setPower(i);
        robot.FRMotor.setPower(-i);
        robot.BLMotor.setPower(-i);
        robot.BRMotor.setPower(i);
    }

    private void forward(double i){
        robot.FLMotor.setPower(-i);
        robot.FRMotor.setPower(-i);
        robot.BLMotor.setPower(-i);
        robot.BRMotor.setPower(-i);
    }

    private void back(double i){
        robot.FLMotor.setPower(i);
        robot.FRMotor.setPower(i);
        robot.BLMotor.setPower(i);
        robot.BRMotor.setPower(i);
    }

    private void stopping(){
        forward(0);
    }
}

