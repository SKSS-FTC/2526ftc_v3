package org.firstinspires.ftc.teamcode.config.subsystem;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.config.path.BluePath;

public class BlueShooter {
    private DcMotor rotate,shooter;
    private Servo angleTuner;

    private BluePath bluePath;
    public BlueShooter(HardwareMap hardwareMap){
        bluePath = new BluePath();

        rotate = hardwareMap.get(DcMotor.class, "rotate");
        shooter = hardwareMap.get(DcMotor.class,"shooter");
        angleTuner = hardwareMap.get(Servo.class,"angleTuner");

        rotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        shooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        angleTuner.setDirection(Servo.Direction.FORWARD);
    }
    public void setShooterAngle(){
//        bluePath.follower.getPose().getX()
    }
}
