package org.firstinspires.ftc.teamcode.OpMode.test;


import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.config.path.BluePath;

@Autonomous
public class coordinateTest1_1 extends LinearOpMode {
    public static Pose setPose = new Pose(0,30,0);
    private BluePath bluePath;
    @Override
    public void runOpMode() throws InterruptedException {
        bluePath = new BluePath();
        bluePath.init(hardwareMap);
        waitForStart();
        while(opModeIsActive()){
            if (gamepad1.dpad_up){
                bluePath.follower.setStartingPose(setPose);
            }
            telemetry.addData("pose",bluePath.follower.getPose());
            telemetry.update();
        }
    }
}
