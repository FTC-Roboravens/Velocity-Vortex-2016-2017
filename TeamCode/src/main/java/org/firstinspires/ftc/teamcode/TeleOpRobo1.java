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

            //button-pressing
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

            //dynamic-speed turning tank-drive style
            if(gamepad1.right_stick_x<0){
                robot.BRMotor.setPower(-Math.abs(Math.pow(gamepad1.right_stick_x/1.27, 3)));
                robot.FRMotor.setPower(-Math.abs(Math.pow(gamepad1.right_stick_x/1.27, 3)));
                robot.BLMotor.setPower(Math.abs(Math.pow(gamepad1.right_stick_x/1.27, 3)));
                robot.FLMotor.setPower(Math.abs(Math.pow(gamepad1.right_stick_x/1.27, 3)));
            }else if(gamepad1.right_stick_x>0){
                robot.BRMotor.setPower(Math.pow(gamepad1.right_stick_x/1.27, 3));
                robot.FRMotor.setPower(Math.pow(gamepad1.right_stick_x/1.27, 3));
                robot.BLMotor.setPower(-Math.pow(gamepad1.right_stick_x/1.27, 3));
                robot.FLMotor.setPower(-Math.pow(gamepad1.right_stick_x/1.27, 3));
            }

            //strafing at max
            else if(gamepad1.dpad_up) {
                robot.FLMotor.setPower(-1);
                robot.FRMotor.setPower(-1);
                robot.BLMotor.setPower(-1);
                robot.BRMotor.setPower(-1);
            }else if(gamepad1.dpad_down){
                robot.FLMotor.setPower(1);
                robot.FRMotor.setPower(1);
                robot.BLMotor.setPower(1);
                robot.BRMotor.setPower(1);
            }else if(gamepad1.dpad_right){
                robot.FLMotor.setPower(-1);
                robot.FRMotor.setPower(1);
                robot.BLMotor.setPower(1);
                robot.BRMotor.setPower(-1);
            }else if(gamepad1.dpad_left){
                robot.FLMotor.setPower(1);
                robot.FRMotor.setPower(-1);
                robot.BLMotor.setPower(-1);
                robot.BRMotor.setPower(1);
            }

            //dynamic-speed strafing
            else if(Math.abs(gamepad1.left_stick_x)>Math.abs(gamepad1.left_stick_y)){
                robot.FLMotor.setPower(-(gamepad1.left_stick_x/1.5)*Math.pow(gamepad1.left_stick_x/1.5,2));
                robot.FRMotor.setPower((gamepad1.left_stick_x/1.5)*Math.pow(gamepad1.left_stick_x/1.5,2));
                robot.BLMotor.setPower((gamepad1.left_stick_x/1.5)*Math.pow(gamepad1.left_stick_x/1.5,2));
                robot.BRMotor.setPower(-(gamepad1.left_stick_x/1.5)*Math.pow(gamepad1.left_stick_x/1.5,2));
            }else if(Math.abs(gamepad1.left_stick_x)<Math.abs(gamepad1.left_stick_y)){
                robot.FLMotor.setPower((gamepad1.left_stick_y/1.5)*Math.pow(gamepad1.left_stick_y/1.5,2));
                robot.FRMotor.setPower((gamepad1.left_stick_y/1.5)*Math.pow(gamepad1.left_stick_y/1.5,2));
                robot.BLMotor.setPower((gamepad1.left_stick_y/1.5)*Math.pow(gamepad1.left_stick_y/1.5,2));
                robot.BRMotor.setPower((gamepad1.left_stick_y/1.5)*Math.pow(gamepad1.left_stick_y/1.5,2));
            }

            //robot not moving
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

