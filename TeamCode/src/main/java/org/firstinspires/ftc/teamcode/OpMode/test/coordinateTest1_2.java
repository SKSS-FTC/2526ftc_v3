package org.firstinspires.ftc.teamcode.OpMode.test;


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
        bluePath = new BluePath();
        bluePath.init(hardwareMap);
        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("pose",bluePath.follower.getPose());
            telemetry.update();
        }
    }
}
