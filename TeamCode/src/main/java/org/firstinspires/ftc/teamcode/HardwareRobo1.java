package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.internal.testcode.TestColorSensors;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a K9 robot.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left motor"
 * Motor channel:  Right drive motor:        "right motor"
 * Servo channel:  Servo to raise/lower arm: "arm"
 * Servo channel:  Servo to open/close claw: "claw"
 *
 * Note: the configuration of the servos is such that:
 *   As the arm servo approaches 0, the arm position moves up (away from the floor).
 *   As the claw servo approaches 0, the claw opens up (drops the game element).
 */
@TeleOp(name="Hardware", group="Robot")
public class HardwareRobo1
{
    /* Public OpMode members. */
    public DcMotor leftUpMotor  = null;
    public DcMotor rightUpMotor = null;
    public DcMotor leftDownMotor = null;
    public DcMotor rightDownMotor = null;
    public DcMotor intake = null;
    /* Local OpMode members. */
    HardwareMap hwMap  = null;
    private ElapsedTime period = new ElapsedTime();
    private double ServoInitialPos = 0;

    /* Constructor */
    public HardwareRobo1() {
    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // save reference to HW Map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftUpMotor   = hwMap.dcMotor.get("leftUpMotor");
        rightUpMotor  = hwMap.dcMotor.get("rightUpMotor");
        leftDownMotor = hwMap.dcMotor.get("leftDownMotor");
        rightDownMotor = hwMap.dcMotor.get("rightDownMotor");
        intake = hwMap.dcMotor.get("intake");
        leftUpMotor.setDirection(DcMotor.Direction.REVERSE);
        leftDownMotor.setDirection(DcMotor.Direction.REVERSE);
        // Set all motors to zero power
        leftUpMotor.setPower(0);
        rightUpMotor.setPower(0);
        leftDownMotor.setPower(0);
        rightDownMotor.setPower(0);
        //intake.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftUpMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightUpMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDownMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDownMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     * @throws InterruptedException
     */
    public void waitForTick(long periodMs)  throws InterruptedException {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}
