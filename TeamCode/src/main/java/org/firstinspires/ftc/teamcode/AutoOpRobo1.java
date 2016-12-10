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

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Hardware;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

//Hardware for the robot is defined by HardwareRobo1
@Autonomous(name="AutoRobo1", group = "Robo1")
//@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="AutoOpRobo1", group="Robo1")
public class AutoOpRobo1 extends LinearOpMode {

    /* Declare OpMode members. */
    String teamColor = "blue";
    HardwareRobo1 robot = new HardwareRobo1();
    int isTracking = 0;
    VectorF trans = null;
    public VuforiaTrackableDefaultListener lis0;
    public VuforiaTrackableDefaultListener lis1;
    public VuforiaTrackableDefaultListener lis2;
    public VuforiaTrackableDefaultListener lis3;

    static final double INCREMENT   =  0.5;     // amount to slew servo each CYCLE_MS cycle
    static final double MAX_POS     =  1.0;     // Maximum rotational position
    static final double MIN_POS     =  0.0;     // Minimum rotational position
    double  position = (MAX_POS - MIN_POS) / 2; // Start at halfway position
    //color sensor stuff

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

        waitForStart();

        beacons.activate();

        lis0 = (VuforiaTrackableDefaultListener)beacons.get(0).getListener();
        lis1 = (VuforiaTrackableDefaultListener)beacons.get(1).getListener();
        lis2 = (VuforiaTrackableDefaultListener)beacons.get(2).getListener();
        lis3 = (VuforiaTrackableDefaultListener)beacons.get(3).getListener();


        // Wait for the game to start (driver presses PLAY)

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            robot.colorPush.setPosition(position);
            if(isTracking==0) {
                back(0.25);
                telemetry.update();
                track();
            }
            else if (isTracking==2){
               /* left(0.15);
                sleep(3000);
                back(0.25);
                track();*/
            }
            robot.waitForTick(40);
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }
    }
    private void detectColor(){
        telemetry.addData("Red  ", robot.sensorRGB.red());
        telemetry.addData("Green", robot.sensorRGB.green());
        telemetry.addData("Blue ", robot.sensorRGB.blue());
        telemetry.update();
         if (teamColor.equalsIgnoreCase("blue")){
            if (robot.sensorRGB.red() > robot.sensorRGB.blue()) {
                position += INCREMENT;
            } else if (robot.sensorRGB.red() < robot.sensorRGB.blue()) {
                position -= INCREMENT;
            }
             robot.colorPush.setPosition(position);
        }
        else if(teamColor.equalsIgnoreCase("red")){
             if (robot.sensorRGB.red() < robot.sensorRGB.blue()) {
                 position -= INCREMENT;
             } else if (robot.sensorRGB.red() < robot.sensorRGB.blue()) {
                 position += INCREMENT;
             }
             robot.colorPush.setPosition(position);
         }
    }
       private void track(){
        if(lis0.isVisible()){
            isTracking = 1;
            while(isTracking==1) {
                trans = lis0.getPose().getTranslation();
                if (trans.get(0) > 100 || trans.get(0) < -100) {
                    if (trans.get(0) < 0) {
                        back(0.25);
                    } else {
                        forward(0.25);
                    }

                } else {
                    if (trans.get(2) < -300) {
                        right(0.15);
                    } else {
                        detectColor();
                        telemetry.addData("Status", "Tracking Done.");
                        isTracking = 2;
                    }
                }
                telemetry.addData("Location", "x: " + trans.get(0) + "\ty: " + trans.get(2));
                telemetry.update();
            }
        }

        if(lis1.isVisible()){
            isTracking = 1;
            while(isTracking==1) {
                trans = lis1.getPose().getTranslation();
                if (trans.get(0) > 100 || trans.get(0) < -100) {
                    if (trans.get(0) < 0) {
                        back(0.25);
                    } else {
                        forward(0.25);
                    }
                } else {
                    if (trans.get(2) < -300) {
                        right(0.15);
                    } else {
                        telemetry.addData("Status", "Tracking Done.");
                        isTracking = 2;
                    }
                }
                telemetry.addData("Location", "x: " + trans.get(0) + "\ty: " + trans.get(2));
                telemetry.update();
            }
        }

        if(lis2.isVisible()){
            isTracking = 1;
            while(isTracking==1) {
                trans = lis2.getPose().getTranslation();
                if (trans.get(0) > 100 || trans.get(0) < -100) {
                    if (trans.get(0) < 0) {
                        back(0.25);
                    } else {
                        forward(0.25);
                    }
                } else {
                    if (trans.get(2) < -300) {
                        right(0.15);
                    } else {
                        telemetry.addData("Status", "Tracking Done.");
                        isTracking = 2;
                    }
                }
                telemetry.addData("Location", "x: " + trans.get(0) + "\ty: " + trans.get(2));
                telemetry.update();
            }
        }

        if(lis3.isVisible()){
            isTracking = 1;
            while(isTracking==1) {
                trans = lis3.getPose().getTranslation();
                if (trans.get(0) > 100 || trans.get(0) < -100) {
                    if (trans.get(0) < 0) {
                        back(0.25);
                    } else {
                        forward(0.25);
                    }
                } else {
                    if (trans.get(2) < -300) {
                        right(0.15);
                    } else {
                        telemetry.addData("Status", "Tracking Done.");
                        isTracking = 2;
                    }
                }
                telemetry.addData("Location", "x: " + trans.get(0) + "\ty: " + trans.get(2));
                telemetry.update();
            }
        }
    }
    private void right(double i){
        robot.rightDownMotor.setPower(i);
        robot.leftDownMotor.setPower(-i);
        robot.rightUpMotor.setPower(-i);
        robot.leftUpMotor.setPower(i);
        telemetry.addData("Status", "Status: Moving Right");
    }
    private void left(double i){
        right(-i);
        telemetry.addData("Status", "Status: Moving Left");
    }
    private void forward(double i){
        robot.rightDownMotor.setPower(i);
        robot.leftDownMotor.setPower(i);
        robot.rightUpMotor.setPower(i);
        robot.leftUpMotor.setPower(i);
        telemetry.addData("Status", "Status: Moving Forward");
    }
    private void back(double i){
        forward(-i);
        telemetry.addData("Status", "Status: Moving Backward");
    }


}
