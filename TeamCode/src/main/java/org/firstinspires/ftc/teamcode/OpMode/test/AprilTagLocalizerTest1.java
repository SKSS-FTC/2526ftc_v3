package org.firstinspires.ftc.teamcode.OpMode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.config.subsystem.AprilTagLocalizer;

@TeleOp(name = "AprilTagLocalizerTest1")
public class AprilTagLocalizerTest1 extends LinearOpMode  {
    private AprilTagLocalizer aprilTagLocalizer;

    public void runOpMode(){
        aprilTagLocalizer = new AprilTagLocalizer(hardwareMap);
        waitForStart();
        while(opModeIsActive()){
            if (gamepad1.dpad_up){
                aprilTagLocalizer.setLocalizer(true);
            }else if (gamepad1.dpad_down){
                aprilTagLocalizer.setLocalizer(false);
            }
            telemetry.addData("aprilTag found",aprilTagLocalizer.aprilTagFound());
            telemetry.addData("fieldX",aprilTagLocalizer.getFieldX());
            telemetry.addData("fieldY",aprilTagLocalizer.getFieldY());
            telemetry.addData("field heading",aprilTagLocalizer.getFieldHeading());
            telemetry.update();
        }
    }
}
