package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class strafe extends LinearOpMode {
    private DcMotor strafe;
    @Override
    public void runOpMode() throws InterruptedException {
        strafe = hardwareMap.get(DcMotor.class,"rotate");
//        left.setDirection(DcMotorSimple.Direction.REVERSE);
        strafe.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        strafe.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();
        while(opModeIsActive()){
            double strafePosition = strafe.getCurrentPosition();
            double tti = 39.3700787/ strafe.getCurrentPosition();
            telemetry.addData("strafe",strafePosition);
            telemetry.addData("tick to inch ",39.3700787/ strafe.getCurrentPosition());
            telemetry.update();
        }
    }
}
//0.03
