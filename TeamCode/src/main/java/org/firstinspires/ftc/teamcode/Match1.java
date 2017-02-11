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
@Autonomous(name="Match1", group = "Robo1")
//@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="AutoOpRoboB", group="Robo1")
public class Match1 extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareRobo1 robot = new HardwareRobo1();

    @Override
    public void runOpMode() throws InterruptedException, NullPointerException {

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        telemetry.addData("Say", "Running Autonomous");    //
        telemetry.update();


        waitForStart();
        // Wait for the game to start (driver presses PLAY)

        // run until the end of the match (driver presses STOP)
        while(opModeIsActive()) {
            if(opModeIsActive()){
                try{
                    Thread.sleep(10000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }else{
                break;
            }
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
                    Thread.sleep(900);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                robot.colorPushL.setPower(0);
            }else{
                break;
            }
            if(opModeIsActive()){
                left(.5);
                try{
                    Thread.sleep(750);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }else{
                break;
            }
            if (opModeIsActive()) {
                back(.5);
                try {
                    Thread.sleep(3500);
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

