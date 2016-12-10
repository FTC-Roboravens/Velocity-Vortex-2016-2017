package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Tristan Collis on 10-Nov-16.
 */
public class HardwareFirstTest
{
    /* Public OpMode members. */
    public DcMotor  frontleftMotor      = null;
    public DcMotor  frontrightMotor     = null;
    public DcMotor  backleftMotor       = null;
    public DcMotor  backrightMotor      = null;

//    public final static double ARM_HOME = 0.2;
//    public final static double CLAW_HOME = 0.2;
//    public final static double ARM_MIN_RANGE  = 0.20;
//    public final static double ARM_MAX_RANGE  = 0.90;
//    public final static double CLAW_MIN_RANGE  = 0.20;
//    public final static double CLAW_MAX_RANGE  = 0.7;

    /* Local OpMode members. */
    HardwareMap hwMap  = null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareFirstTest() {
    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // save reference to HW Map
        hwMap = ahwMap;

        // Define and Initialize Motors
        frontleftMotor   = hwMap.dcMotor.get("front-left_drive");
        frontrightMotor  = hwMap.dcMotor.get("front-right_drive");
        backleftMotor   = hwMap.dcMotor.get("back-left_drive");
        backrightMotor  = hwMap.dcMotor.get("back-right_drive");
        frontleftMotor.setDirection(DcMotor.Direction.REVERSE);
        backleftMotor.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power
        frontleftMotor.setPower(0);
        frontrightMotor.setPower(0);
        backleftMotor.setPower(0);
        backrightMotor.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        frontleftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontrightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backrightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     */
    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}
