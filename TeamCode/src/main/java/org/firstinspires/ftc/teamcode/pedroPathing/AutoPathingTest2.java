package org.firstinspires.ftc.teamcode.pedroPathing; // make sure this aligns with class location

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.config.path.BluePath;

@Autonomous(name = "AutoPathingTest2")
public class AutoPathingTest2 extends OpMode {
    boolean PathGrabShoot1 = true;
    boolean PathGrabShoot2 = true;
    boolean PathGrabShoot3 = true;
    private BluePath bluePath;
    private enum pState {none,scorePreload,Get_Ball1, Shoot_Ball1, Get_Ball2, Shoot_Ball2, Get_Ball3, Shoot_Ball3,endPath, finish};
    private pState currentPState = pState.none;

    boolean OpmodeTimer = false;
    private Timer opmodeTimer;

    private void determinePath(int currentPath){
        if(opmodeTimer.getElapsedTime()>=25000){
            //25000ms = 25s
            currentPState = pState.endPath;
        }
        if (currentPath == 0){
            if (PathGrabShoot1){
                currentPState = pState.Get_Ball1;
            }
        }
        if (currentPath <= 1){
            //finish path 1
            if (PathGrabShoot2){
                currentPState = pState.Get_Ball2;
            }
        }
        if (currentPath <= 2){
            if (PathGrabShoot3){
                currentPState = pState.Get_Ball3;
            }
        }else{
            currentPState = pState.finish;
        }
    }

    @Override
    public void loop() {
        if(!OpmodeTimer){
            opmodeTimer.resetTimer();
            OpmodeTimer = true;
        }
        switch (currentPState){
            case none:
                currentPState = pState.scorePreload;

            case scorePreload:
                bluePath.follower.followPath(bluePath.scorePreload);
                if(!bluePath.follower.isBusy()){
                    determinePath(0);
                }

            case Get_Ball1:
                bluePath.follower.followPath(bluePath.Get_Ball1);
                if(!bluePath.follower.isBusy()){
                    currentPState = pState.Shoot_Ball1;
                }

            case Shoot_Ball1:
                bluePath.follower.followPath(bluePath.Shoot_Ball1);
                if(!bluePath.follower.isBusy()){
                    determinePath(1);
                }

            case Get_Ball2:
                bluePath.follower.followPath(bluePath.Get_Ball2);
                if(!bluePath.follower.isBusy()){
                    currentPState = pState.Shoot_Ball2;
                }

            case Shoot_Ball2:
                bluePath.follower.followPath(bluePath.Shoot_Ball2);
                if(!bluePath.follower.isBusy()){
                    determinePath(2);
                }

            case Get_Ball3:
                bluePath.follower.followPath(bluePath.Get_Ball3);
                if(!bluePath.follower.isBusy()){
                    currentPState = pState.Shoot_Ball3;
                }

            case Shoot_Ball3:
                bluePath.follower.followPath(bluePath.Shoot_Ball3);
                if(!bluePath.follower.isBusy()){
                    determinePath(3);
                }

            case endPath:
                bluePath.follower.followPath(bluePath.EndPath);
                if(!bluePath.follower.isBusy()){
                    currentPState = pState.finish;
                }

            case finish:
                if (gamepad1.triangle){
                    currentPState = pState.none;
                    //restart the path again
                }
        }
        bluePath.follower.update();


        telemetry.addData("path state", currentPState);
        telemetry.addData("x", bluePath.follower.getPose().getX());
        telemetry.addData("y", bluePath.follower.getPose().getY());
        telemetry.addData("heading", bluePath.follower.getPose().getHeading());
        telemetry.update();
    }
    @Override
    public void init() {
        bluePath = new BluePath(hardwareMap);
        opmodeTimer = new Timer();
        opmodeTimer.resetTimer();
    }
}