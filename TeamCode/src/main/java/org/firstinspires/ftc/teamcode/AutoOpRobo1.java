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

//Hardware for the robot is defined by HardwareRobo1

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="AutoOpRobo1", group="Robo1")
public class AutoOpRobo1 extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareRobo1   robot           = new HardwareRobo1();              // Use a K9's hardware
    int WHITE_LINE_LIGHT_THRESHOLD = 80;
    int BLUE_LIGHT_THRESHOLD = 80;
    boolean turning_left = true;
    @Override
    public void runOpMode() throws InterruptedException {
        double left = -.5;
        double right = .5;
        double servo1Pos = 0;
        double servo2Pos = 0;
        double servoIncrement = .01;

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Running Autonomous");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            //Line Following: Not Tested

            if(robot.cSensorLine.alpha()>WHITE_LINE_LIGHT_THRESHOLD){
                if(turning_left){
                    turning_left = false;
                    robot.rightMotor.setPower(right);
                    robot.leftMotor.setPower(0);
                }else{
                    turning_left = true;
                    robot.leftMotor.setPower(left);
                    robot.rightMotor.setPower(0);
                }
            }

            //Color Sensing: Not Tested
            /*
            if(robot.cSensorButton.blue()>BLUE_LIGHT_THRESHOLD){
                if(servo1Pos<1)
                    servo1Pos+=servoIncrement;
                if(servo2Pos>0)
                    servo2Pos-=servoIncrement;
            }else{
                if(servo1Pos>0)
                    servo1Pos-=servoIncrement;
                if(servo2Pos<1)
                    servo2Pos+=servoIncrement;
            }

            robot.servo1.setPosition(servo1Pos);
            robot.servo2.setPosition(servo2Pos);
            */
            // Send telemetry message to signify robot running;
            //telemetry.addData("left",  "%.2f", left);
            //telemetry.addData("right", "%.2f", right);
            telemetry.addData("Color Light", robot.cSensorLine.alpha());
            //telemetry.addData("Color Blue", robot.cSensorButton.blue());
            telemetry.update();

            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }
    }


}
