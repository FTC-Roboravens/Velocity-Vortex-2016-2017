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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcontroller.external.samples.HardwareK9bot;

//Hardware for the robot is defined by HardwareRobo1

//@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOpRobo1", group="Robo1")
@TeleOp(name="TeleOpRobo1", group="Robo1")
public class TeleOpRobo1 extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareRobo1   robot           = new HardwareRobo1();              // Use a K9'shardware
    @Override
    public void runOpMode() throws InterruptedException {

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            if(gamepad1.right_bumper){
                robot.colorPushR.setPower(1);
            }else if(gamepad1.right_trigger>0){
                robot.colorPushR.setPower(-1);
            }else{
                robot.colorPushR.setPower(0);
            }
            if(gamepad1.left_bumper){
                robot.colorPushL.setPower(1);
            }else if(gamepad1.left_trigger>0){
                robot.colorPushL.setPower(-1);
            }else{
                robot.colorPushL.setPower(0);
            }
            if(gamepad1.right_stick_x<0){
                robot.BRMotor.setPower(-.75);
                robot.FRMotor.setPower(-.75);
                robot.BLMotor.setPower(.75);
                robot.FLMotor.setPower(.75);
            }else if(gamepad1.right_stick_x>0) {
                robot.BRMotor.setPower(.75);
                robot.FRMotor.setPower(.75);
                robot.BLMotor.setPower(-.75);
                robot.FLMotor.setPower(-.75);
            }
            else if(gamepad1.dpad_up) {
                robot.FLMotor.setPower(-1);
                robot.FRMotor.setPower(-1);
                robot.BLMotor.setPower(-1);
                robot.BRMotor.setPower(-1);
            }
            else if(gamepad1.dpad_down){
                robot.FLMotor.setPower(1);
                robot.FRMotor.setPower(1);
                robot.BLMotor.setPower(1);
                robot.BRMotor.setPower(1);
            }
            else if(gamepad1.dpad_right){
                robot.FLMotor.setPower(-1);
                robot.FRMotor.setPower(1);
                robot.BLMotor.setPower(1);
                robot.BRMotor.setPower(-1);
            }
            else if(gamepad1.dpad_left){
                robot.FLMotor.setPower(1);
                robot.FRMotor.setPower(-1);
                robot.BLMotor.setPower(-1);
                robot.BRMotor.setPower(1);
            }
            else {
                robot.FLMotor.setPower(0);
                robot.FRMotor.setPower(0);
                robot.BLMotor.setPower(0);
                robot.BRMotor.setPower(0);
            }

            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            telemetry.addData("RGB", "Blue: " + robot.color.blue());
            telemetry.addData("RGB", "Red: " + robot.color.red());
            telemetry.addData("Light", "Light: " + robot.light.getLightDetected());
            telemetry.addData("Light", "Light: " + robot.light.getRawLightDetected());
            telemetry.update();
            robot.waitForTick(40);
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }
    }
}

