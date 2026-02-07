package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.R;

@TeleOp
public class forward extends LinearOpMode {
    private DcMotor left,right;
    @Override
    public void runOpMode() throws InterruptedException {
        left = hardwareMap.get(DcMotor.class,"shoot");
        right = hardwareMap.get(DcMotor.class,"intake");
        left.setDirection(DcMotorSimple.Direction.REVERSE);
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();
        while(opModeIsActive()){
            double leftPosition = left.getCurrentPosition();
            double rightPosition = right.getCurrentPosition();
            double tti =39.3700787/ ((leftPosition + rightPosition)/2);
            telemetry.addData("left",leftPosition);
            telemetry.addData("right",rightPosition);
            telemetry.addData("tick to inch ",39.3700787/ ((leftPosition + rightPosition)/2));
            telemetry.update();
        }
    }
}
//0.03
