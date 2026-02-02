package org.firstinspires.ftc.teamcode.config.subsystem;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class RedShooter {
    private DcMotor rotate,shooter;
    private Servo angleTuner;
    private AprilTagLocalizer aprilTagLocalizer;
    private Follower follower;
    public RedShooter(HardwareMap hardwareMap, AprilTagLocalizer inputAprilTagLocalizer, Follower inputFollower){
        aprilTagLocalizer = inputAprilTagLocalizer;
        follower = inputFollower;
        rotate = hardwareMap.get(DcMotor.class, "rotate");
        shooter = hardwareMap.get(DcMotor.class,"shooter");
        angleTuner = hardwareMap.get(Servo.class,"angleTuner");

        rotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        shooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        angleTuner.setDirection(Servo.Direction.FORWARD);
    }
    public void setShooterAngle(){

    }
}
