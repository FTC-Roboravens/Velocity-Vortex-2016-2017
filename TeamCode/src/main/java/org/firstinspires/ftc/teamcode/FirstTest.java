package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Tristan Collis on 03-Nov-16.
 * You got hacked
 */
@TeleOp(name="TeleOp", group="Robo1")
public class FirstTest extends OpMode {

    //Motor Controllers

    HardwareFirstTest   robot   = new HardwareFirstTest();


    private DcMotorController dc_drive_controller;

    @Override public void init() {
        robot.init(hardwareMap);
    }

    @Override public void init_loop() {
    }

    public void loop() {
        robot.frontleftMotor.setPower(-power_fl());
        robot.frontrightMotor.setPower(-power_fr());
        robot.backleftMotor.setPower(-power_bl());
        robot.backrightMotor.setPower(-power_br());
    }



    public double power_fl() {
        double power = gamepad1.left_stick_x + gamepad1.left_stick_y + gamepad1.right_stick_x;
        if (power > 1) {
            power = 1;
        }
        return power;
    }

    public double power_br() {
        double power = gamepad1.left_stick_x + gamepad1.left_stick_y - gamepad1.right_stick_x;
        if (power > 1) {
            power = 1;
        }
        return power;
    }

    public double power_fr() {
        double power = gamepad1.left_stick_x - gamepad1.left_stick_y - gamepad1.right_stick_x;
        if (power > 1) {
            power = 1;
        }
        return power;
    }

    public double power_bl() {
        double power = gamepad1.left_stick_x - gamepad1.left_stick_y + gamepad1.right_stick_x;
        if (power > 1) {
            power = 1;
        }
        return power;
    }

    public double power_arm() {
        if (gamepad1.dpad_up) {
            return 1;
        }
        return 0;
    }




}
