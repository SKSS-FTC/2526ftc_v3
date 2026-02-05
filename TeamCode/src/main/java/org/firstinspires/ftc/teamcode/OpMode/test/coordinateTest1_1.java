package org.firstinspires.ftc.teamcode.OpMode.test;


import static org.firstinspires.ftc.teamcode.config.RobotConstants.currentPose;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.config.path.BluePath;

@Autonomous(preselectTeleOp = "coordinateTest1_2")
public class coordinateTest1_1 extends LinearOpMode {
    public static Pose setPose = new Pose(0,30,Math.toRadians(0));
    public BluePath bluePath;
    @Override
    public void runOpMode() throws InterruptedException {
        bluePath = new BluePath(hardwareMap);
        bluePath.setFarStartPose();
        waitForStart();
        while(opModeIsActive()){
            bluePath.update();
            if (gamepad1.dpad_up){
                bluePath.follower.setPose(setPose);
            }
            telemetry.addData("x",bluePath.getX());
            telemetry.addData("y",bluePath.getY());
            telemetry.addData("heading",bluePath.getHeading());
            telemetry.addData("pose",currentPose);
            telemetry.update();
        }
    }
}
