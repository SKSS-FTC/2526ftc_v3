package org.firstinspires.ftc.teamcode.OpMode.test;


import static org.firstinspires.ftc.teamcode.config.RobotConstants.currentPose;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.config.path.BluePath;

@TeleOp
public class coordinateTest1_2 extends LinearOpMode {
    private BluePath bluePath;
    @Override
    public void runOpMode() throws InterruptedException {
        bluePath = new BluePath(hardwareMap);
        bluePath.setTeleopStartPose();
        waitForStart();
        while(opModeIsActive()){
            bluePath.update();
            telemetry.addData("x",bluePath.getX());
            telemetry.addData("y",bluePath.getY());
            telemetry.addData("heading",bluePath.getHeading());
            telemetry.addData("pose",currentPose);
            telemetry.update();
        }
    }
}
