package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class turn extends LinearOpMode {
    private DcMotor left,right,strafe;
    @Override
    public void runOpMode() throws InterruptedException {
        left = hardwareMap.get(DcMotor.class,"shoot");
        right = hardwareMap.get(DcMotor.class,"intake");
        strafe = hardwareMap.get(DcMotor.class,"rotate");
        left.setDirection(DcMotorSimple.Direction.REVERSE);
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        strafe.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        strafe.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();
        while(opModeIsActive()){
            double leftPosition = left.getCurrentPosition();
            double rightPosition = right.getCurrentPosition();
            double strafePosition = strafe.getCurrentPosition();
            double tti = 2 * Math.PI/ ((leftPosition + rightPosition + strafePosition)/3);
            telemetry.addData("left",leftPosition);
            telemetry.addData("right",rightPosition);
            telemetry.addData("tick to inch ",2 * Math.PI/((leftPosition + rightPosition + strafePosition)/3));
            telemetry.update();
        }
    }
}
//0.03
