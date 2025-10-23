package org.firstinspires.ftc.teamcode.OpMode.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.security.KeyStore;

@TeleOp(name = "aprilTagTest2",group = "TeleOP")
public class MOMO extends LinearOpMode {

    private DcMotor leftUp,rightUp,leftDown,rightDown;
    private double x,y,r;

    @Override
    public void runOpMode() {
        leftUp = hardwareMap.get(DcMotor.class,"leftUp");
        rightUp = hardwareMap.get(DcMotor.class,"rightUp");
        leftDown = hardwareMap.get(DcMotor.class,"leftDown");
        rightDown = hardwareMap.get(DcMotor.class,"rightDown");

        leftUp.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightUp.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDown.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDown.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftUp.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightUp.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftDown.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDown.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        telemetry.addData("Status", "Initialized");
        telemetry.update(); // Display the "Initialized" message
        rightDown.setDirection(DcMotorSimple.Direction.FORWARD);
        rightUp.setDirection(DcMotorSimple.Direction.FORWARD);
        leftDown.setDirection(DcMotorSimple.Direction.FORWARD);
        leftUp.setDirection(DcMotorSimple.Direction.FORWARD);

        waitForStart();

        while (opModeIsActive()) {

            double x = gamepad1.left_stick_x;
            double y = gamepad1.left_stick_y;
            double r = gamepad1.right_stick_x;
            // ... control motors

            telemetry.addData("Encoder Value", leftUp.getCurrentPosition());
            telemetry.addData("Encoder Value",rightUp.getCurrentPosition());
            telemetry.addData("Encoder Value", leftDown.getCurrentPosition());
            telemetry.addData("Encoder Value",rightDown.getCurrentPosition());

            leftUp.setPower(0);
            rightUp.setPower(0);
            leftDown.setPower(0);
            rightDown.setPower(0);

            leftUp.setPower(x+y-r);
            rightUp.setPower(-x+y+r);
            leftDown.setPower(-x+y-r);
            rightDown.setPower(x+y+r);

        }
    }
}