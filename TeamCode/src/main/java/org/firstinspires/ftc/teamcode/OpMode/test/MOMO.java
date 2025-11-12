package org.firstinspires.ftc.teamcode.OpMode.test;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@TeleOp(name = "MOMO",group = "TeleOP")
public class MOMO extends LinearOpMode {

    private DcMotor leftUp, rightUp, leftDown, rightDown;
    private double x, y, r;

    private IMU imu;
    private double ERROR;
    private YawPitchRollAngles orientation;

    @Override
    public void runOpMode() {
        imu = hardwareMap.get(IMU.class, "imu");
        leftUp = hardwareMap.get(DcMotor.class, "leftU.p");
        rightUp = hardwareMap.get(DcMotor.class, "rightUp");
        leftDown = hardwareMap.get(DcMotor.class, "leftDown");
        rightDown = hardwareMap.get(DcMotor.class, "rightDown");

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
        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD)));
        imu.resetYaw();

        while (opModeIsActive()) {

            double x = gamepad1.left_stick_x;
            double y = gamepad1.left_stick_y;
            double r = gamepad1.right_stick_x;

            telemetry.addData("Encoder Value", leftUp.getCurrentPosition());
            telemetry.addData("Encoder Value", rightUp.getCurrentPosition());
            telemetry.addData("Encoder Value", leftDown.getCurrentPosition());
            telemetry.addData("Encoder Value", rightDown.getCurrentPosition());

            leftUp.setPower(0.4 * (-x + y - r));
            rightUp.setPower(0.4 * (-x - y - r));
            leftDown.setPower(0.4 * (-x - y + r));
            rightDown.setPower(0.4 * (x - y - r));
            orientation = imu.getRobotYawPitchRollAngles();
            telemetry.addData("key", orientation.getYaw(AngleUnit.DEGREES));
            telemetry.update();
        }
    }
}