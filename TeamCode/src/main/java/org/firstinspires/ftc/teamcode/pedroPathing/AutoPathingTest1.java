package org.firstinspires.ftc.teamcode.pedroPathing; // make sure this aligns with class location

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import  com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "AutoPathingTest1")
public class AutoPathingTest1 extends OpMode {
    private Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;
    private int pathState;
    private final Pose StartPose = new Pose();
    private final Pose ShootPose = new Pose();
    private final Pose EndPose = new Pose();
    private final Pose PickUp1_start = new Pose();
    private final Pose PickUp1_final = new Pose();
    private final Pose PickUp2_start = new Pose();
    private final Pose PickUp2_final = new Pose();
    private final Pose PickUp3_start = new Pose();
    private final Pose PickUp3_final = new Pose();
    private PathChain scorePreload,EndPath,Get_Ball1, Shoot_Ball1, Get_Ball2, Shoot_Ball2, Get_Ball3, Shoot_Ball3;
    boolean PathGrabShoot1 = true;
    boolean PathGrabShoot2 = true;
    boolean PathGrabShoot3 = true;
    boolean OpmodeTimer = false;
    private enum pState {none,scorePreload,Get_Ball1, Shoot_Ball1, Get_Ball2, Shoot_Ball2, Get_Ball3, Shoot_Ball3,endPath, finish};
    private pState currentPState = pState.none;

    public void buildPaths() {
        scorePreload = follower.pathBuilder()
                .addPath(new Path(new BezierLine(StartPose, ShootPose)))
                .setLinearHeadingInterpolation(StartPose.getHeading(), ShootPose.getHeading())
                .build();
        EndPath = follower.pathBuilder()
                .addPath(new Path(new BezierLine(ShootPose, EndPose)))
                .setLinearHeadingInterpolation(ShootPose.getHeading(), EndPose.getHeading())
                .build();

        Get_Ball1 = follower.pathBuilder()
                .addPath(new BezierCurve(ShootPose, PickUp1_start,PickUp1_final))
                .setLinearHeadingInterpolation(ShootPose.getHeading(), PickUp1_final.getHeading())
                .build();
        Shoot_Ball1 = follower.pathBuilder()
                .addPath(new BezierLine(PickUp1_final, ShootPose))
                .setLinearHeadingInterpolation(PickUp1_final.getHeading(), ShootPose.getHeading())
                .build();

        Get_Ball2 = follower.pathBuilder()
                .addPath(new BezierCurve(ShootPose, PickUp2_start, PickUp2_final))
                .setLinearHeadingInterpolation(ShootPose.getHeading(), PickUp2_final.getHeading())
                .build();
        Shoot_Ball2 = follower.pathBuilder()
                .addPath(new BezierLine(PickUp2_final, ShootPose))
                .setLinearHeadingInterpolation(PickUp2_final.getHeading(), ShootPose.getHeading())
                .build();

        Get_Ball3 = follower.pathBuilder()
                .addPath(new BezierCurve(ShootPose, PickUp3_start, PickUp3_final))
                .setLinearHeadingInterpolation(ShootPose.getHeading(), PickUp3_start.getHeading())
                .build();
        Shoot_Ball3 = follower.pathBuilder()
                .addPath(new BezierLine(PickUp3_final, ShootPose))
                .setLinearHeadingInterpolation(PickUp3_final.getHeading(), ShootPose.getHeading())
                .build();
    }
    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }

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
                follower.followPath(scorePreload);
                if(!follower.isBusy()){
                    determinePath(0);
                }

            case Get_Ball1:
                follower.followPath(Get_Ball1);
                if(!follower.isBusy()){
                    currentPState = pState.Shoot_Ball1;
                }

            case Shoot_Ball1:
                follower.followPath(Shoot_Ball1);
                if(!follower.isBusy()){
                    determinePath(1);
                }

            case Get_Ball2:
                follower.followPath(Get_Ball2);
                if(!follower.isBusy()){
                    currentPState = pState.Shoot_Ball2;
                }

            case Shoot_Ball2:
                follower.followPath(Shoot_Ball2);
                if(!follower.isBusy()){
                    determinePath(2);
                }

            case Get_Ball3:
                follower.followPath(Get_Ball3);
                if(!follower.isBusy()){
                    currentPState = pState.Shoot_Ball3;
                }

            case Shoot_Ball3:
                follower.followPath(Shoot_Ball3);
                if(!follower.isBusy()){
                    determinePath(3);
                }

            case endPath:
                follower.followPath(EndPath);
                if(!follower.isBusy()){
                    currentPState = pState.finish;
                }

            case finish:
                if (gamepad1.triangle){
                    currentPState = pState.none;
                    //restart the path again
                }
        }
        follower.update();


        telemetry.addData("path state", currentPState);
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.update();
    }
    @Override
    public void init() {
        pathTimer = new Timer();
        opmodeTimer = new Timer();
        opmodeTimer.resetTimer();

        follower = Constants.createFollower(hardwareMap);
        buildPaths();
        follower.setStartingPose(StartPose);
    }
}