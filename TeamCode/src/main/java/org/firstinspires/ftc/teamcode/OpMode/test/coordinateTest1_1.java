package org.firstinspires.ftc.teamcode.OpMode.test;


import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.config.path.BluePath;

@Autonomous
public class coordinateTest1_1 extends LinearOpMode {
    public static Pose setPose = new Pose(0,30,Math.toRadians(0));
    public BluePath bluePath;
    @Override
    public void runOpMode() throws InterruptedException {
        bluePath = new BluePath(hardwareMap);
        bluePath.setStartPose();
        waitForStart();
        while(opModeIsActive()){
            if (gamepad1.dpad_up){
                bluePath.follower.setPose(setPose);
            }
            telemetry.addData("x",bluePath.getX());
            telemetry.addData("y",bluePath.getY());
            telemetry.addData("heading",bluePath.getHeading());
            telemetry.update();
        }
    }
}
