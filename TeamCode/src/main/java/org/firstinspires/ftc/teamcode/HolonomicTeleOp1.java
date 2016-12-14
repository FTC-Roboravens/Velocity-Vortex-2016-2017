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
import org.firstinspires.ftc.robotcore.external.Telemetry;

//Hardware for the robot is defined by HardwareRobo1

//@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOpRobo1", group="Robo1")
@TeleOp(name="HolonomicTeleOp1", group="Robo1")
public class HolonomicTeleOp1 extends LinearOpMode {

    /* Declare OpMode members. */
    HolonomicHardware1   robot           = new HolonomicHardware1();              // Use a K9'shardware
    @Override
    public void runOpMode() throws InterruptedException {
        double left;
        double right;

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
                if(Math.abs(gamepad1.left_stick_y)>(Math.abs(Math.tan(67.5)*gamepad1.left_stick_x))){
                    robot.leftUpMotor.setPower(gamepad1.left_stick_y);
                    robot.rightUpMotor.setPower(gamepad1.left_stick_y);
                    robot.leftDownMotor.setPower(gamepad1.left_stick_y);
                    robot.rightDownMotor.setPower(gamepad1.left_stick_y);
                }
                else if((Math.abs(gamepad1.left_stick_y))>(Math.abs(Math.tan(22.5)*gamepad1.left_stick_x))&&
                        (Math.abs(gamepad1.left_stick_y))<(Math.abs(Math.tan(62.5)*gamepad1.left_stick_x))){
                    robot.leftUpMotor.setPower((gamepad1.left_stick_y*gamepad1.left_stick_x)/2);
                    robot.rightUpMotor.setPower(0);
                    robot.leftDownMotor.setPower(0);
                    robot.rightDownMotor.setPower((gamepad1.left_stick_y*gamepad1.left_stick_x)/2);
                }
                else if(Math.abs(gamepad1.left_stick_y)<(Math.abs(Math.tan(22.5) * gamepad1.left_stick_x))){
                    robot.leftUpMotor.setPower(gamepad1.left_stick_y);
                    robot.rightUpMotor.setPower(-gamepad1.left_stick_y);
                    robot.leftDownMotor.setPower(-gamepad1.left_stick_y);
                    robot.rightDownMotor.setPower(gamepad1.left_stick_y);
                }
             else if((Math.abs(gamepad1.left_stick_y))<(Math.abs(Math.tan(157.5) * gamepad1.left_stick_x))&&
                     (Math.abs(gamepad1.left_stick_y))>(Math.abs(Math.tan(112.5) * gamepad1.left_stick_x))){
                    robot.leftUpMotor.setPower(0);
                    robot.rightUpMotor.setPower(-gamepad1.left_stick_x*(gamepad1.left_stick_y/2));
                    robot.leftDownMotor.setPower(-gamepad1.left_stick_x*(gamepad1.left_stick_y/2));
                    robot.rightDownMotor.setPower(0);
                }
            else if(gamepad1.right_bumper){
                    robot.leftUpMotor.setPower(gamepad1.left_stick_y);
                    robot.rightUpMotor.setPower(gamepad1.left_stick_y);
                    robot.leftDownMotor.setPower(-gamepad1.left_stick_y);
                    robot.rightDownMotor.setPower(-gamepad1.left_stick_y);
                }
            else if(gamepad1.left_bumper){
                    robot.leftUpMotor.setPower(-gamepad1.left_stick_y);
                    robot.rightUpMotor.setPower(-gamepad1.left_stick_y);
                    robot.leftDownMotor.setPower(gamepad1.left_stick_y);
                    robot.rightDownMotor.setPower(gamepad1.left_stick_y);
                }
            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }
    }
}

