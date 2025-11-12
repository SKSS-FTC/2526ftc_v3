package org.firstinspires.ftc.teamcode.OpMode.test;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.opencv.core.Mat;

@TeleOp(name = "MOMO",group = "TeleOP")
public class absoluteDriveTest1 extends LinearOpMode {

    private DcMotor leftUp, rightUp, leftDown, rightDown;
    private double relativeTargetAngle,joystickMagnitude,outputX,outputY,outputR,currentHeading;

    private IMU imu;
    private double ERROR;
    private YawPitchRollAngles orientation;

    @Override
    public void runOpMode() {
        imu = hardwareMap.get(IMU.class, "imu");
        leftUp = hardwareMap.get(DcMotor.class, "leftUp");
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
        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD)));
        imu.resetYaw();

        while (opModeIsActive()) {
            if (gamepad1.dpad_up){
                imu.resetYaw();
            }
            orientation = imu.getRobotYawPitchRollAngles();
            currentHeading = orientation.getYaw(AngleUnit.DEGREES);
            relativeTargetAngle = currentHeading - getStickAngle(gamepad1.left_stick_x, gamepad1.left_stick_y);
            joystickMagnitude = Math.sqrt(Math.pow(gamepad1.left_stick_x,2) + Math.pow(gamepad1.left_stick_y,2)) ;

            outputX = joystickMagnitude * Math.sin(relativeTargetAngle);
            outputY = joystickMagnitude * Math.cos(relativeTargetAngle);
            outputR = gamepad1.right_stick_x;
            leftUp.setPower(0.4 * (-outputX + outputY - outputR));
            rightUp.setPower(0.4 * (-outputX - outputY - outputR));
            leftDown.setPower(0.4 * (outputX + outputY - outputR));
            rightDown.setPower(0.4 * (outputX - outputY - outputR));
            telemetry.addData("output x", outputX);
            telemetry.addData("output y", outputY);
            telemetry.addData("output r", outputR);
            telemetry.addData("stick angle", getStickAngle(gamepad1.left_stick_x, gamepad1.left_stick_y));
            telemetry.addData("yaw", orientation.getYaw(AngleUnit.DEGREES));
            telemetry.update();
        }
    }



    private double getStickAngle(double x, double y){
        if (y == 0 ){
            if (x == 0){
                return 0;
            }else{
                return angelCalculation(x,y,0);
            }
        }else if (x == 0){
            return angelCalculation(x,y,90);
        }
        else {
            return angelCalculation(x,y,Math.atan(Math.abs(gamepad1.left_stick_y/gamepad1.left_stick_x)) / Math.PI *180);
        }
    }

    private double angelCalculation(double x,double y,double inputAngle){
        if (x >0){
            if (y >0){
                //quadrant 1
                return 90 + inputAngle;
            }else{
                //quadrant 4
                return 90 - inputAngle;
            }
        }else{
            if (y >0){
                //quadrant 2
                return 270 - inputAngle;
            }else{
                //quadrant 3
                if (inputAngle == 90){
                    return 0;
                }else {
                    return 270 + inputAngle;
                }
            }
        }
    }
}