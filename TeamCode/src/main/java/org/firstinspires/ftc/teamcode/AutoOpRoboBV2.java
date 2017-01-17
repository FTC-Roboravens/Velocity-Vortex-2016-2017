/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
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

    @Override
    public void runOpMode() throws InterruptedException, NullPointerException {

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        telemetry.addData("Say", "Running Autonomous");    //
        telemetry.update();

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

        lis0 = (VuforiaTrackableDefaultListener)beacons.get(0).getListener();
        lis1 = (VuforiaTrackableDefaultListener)beacons.get(1).getListener();
        lis2 = (VuforiaTrackableDefaultListener)beacons.get(2).getListener();
        lis3 = (VuforiaTrackableDefaultListener)beacons.get(3).getListener();

        robot.light.enableLed(true);


        waitForStart();
        // Wait for the game to start (driver presses PLAY)

        // run until the end of the match (driver presses STOP)
        robot.FRMotor.setPower(-.5);
        robot.BLMotor.setPower(-.5);
        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        left(0.15);
        trackVuforia();
        back(0.25);
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            telemetry.addData("Error", "InterruptedException Error!");
            telemetry.update();
        }
        left(.15);
        trackLine();
    }

    private void trackLine() throws InterruptedException{
        while(opModeIsActive()){
            if(robot.light.getLightDetected()>.1){
                stopping();
                detectColor();
            }
            idle();
            robot.waitForTick(40);
        }
    }

    private void detectColor(){
        telemetry.addData("Red  ", robot.color.red());
        telemetry.addData("Blue ", robot.color.blue());
        telemetry.update();
        if (robot.color.red() > robot.color.blue()) {
            robot.colorPushR.setPower(1);
            try{
                Thread.sleep(750);
            }catch(InterruptedException e){
                telemetry.addData("Error", "InterruptedException Error!");
                telemetry.update();
            }
            robot.colorPushR.setPower(-1);
            try{
                Thread.sleep(750);
            }catch(InterruptedException e){
                telemetry.addData("Error", "InterruptedException Error!");
                telemetry.update();
            }
            robot.colorPushR.setPower(0);
        } else if (robot.color.red() < robot.color.blue()) {
            robot.colorPushL.setPower(1);
            try{
                Thread.sleep(750);
            }catch(InterruptedException e){
                telemetry.addData("Error", "InterruptedException Error!");
                telemetry.update();
            }
            robot.colorPushL.setPower(-1);
            try{
                Thread.sleep(750);
            }catch(InterruptedException e){
                telemetry.addData("Error", "InterruptedException Error!");
                telemetry.update();
            }
            robot.colorPushL.setPower(0);
        }
    }

    private void trackVuforia() throws InterruptedException {
        while (opModeIsActive()) {
            if (lis0.isVisible()) {
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

            if (lis1.isVisible()) {
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

            if (lis2.isVisible()) {
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

            if (lis3.isVisible()) {
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

