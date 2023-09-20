package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.motors.NeveRest20Gearmotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class TeleOpTest extends OpMode {
    private DcMotor leftFront; // declares motors
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;

    private float leftPower, rightPower, xValue, yValue;

    @Override
    /* setup stuff (ex: setting servos to the right angle) */
    public void init() {
        leftFront = hardwareMap.dcMotor.get("leftFront");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightBack = hardwareMap.dcMotor.get("rightBack");

        /*
        pulley = hardwareMap.servo.get("pulley");
        ducks = hardwareMap.crservo.get("ducks");
        grabber = hardwareMap.servo.get("grabber");
        */

        /*
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            -- makes the motor brake/lock in place
            -- good for motors for lifts/intakes

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            -- will not stop right away
            -- coast/glide to a stop
            -- default probably
        */

        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        // for display on phone
        telemetry.addLine("Initialized");
        telemetry.addLine("Recursion my Beloved");
        telemetry.addData("LeftFront Power:", leftFront.getPower());
        telemetry.update();

    }

    @Override
    /* run when the driver presses play */
    public void loop() {
        // tank drive: left stick = left motors, right stick = right motors
        /*
        leftFront.setPower(-gamepad1.left_stick_y);
        leftBack.setPower(-gamepad1.left_stick_y);
        rightFront.setPower(-gamepad1.right_stick_y);
        rightBack.setPower(-gamepad1.right_stick_y);
        */

        // arcade drive??? Hopefully functions...
        yValue = gamepad1.right_stick_y * -1;
        xValue = gamepad1.right_stick_x * -1;

        leftPower =  yValue - xValue;
        rightPower = yValue + xValue;

        leftFront.setPower(Range.clip(leftPower, -1.0, 1.0)/2);
        leftBack.setPower(Range.clip(leftPower, -1.0, 1.0)/2);
        rightFront.setPower(Range.clip(rightPower, -1.0, 1.0)/2);
        rightBack.setPower(Range.clip(rightPower, -1.0, 1.0)/2);

        // strafe
        if (gamepad1.right_trigger > 0) {
            leftFront.setPower(1);
            leftBack.setPower(-1);
            rightFront.setPower(-1);
            rightBack.setPower(1);
        }

        if (gamepad1.left_trigger > 0) {
            leftFront.setPower(-1);
            leftBack.setPower(1);
            rightFront.setPower(1);
            rightBack.setPower(-1);
        }
    }
}
