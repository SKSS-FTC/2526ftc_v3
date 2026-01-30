package org.firstinspires.ftc.teamcode.pedroPathing; // make sure this aligns with class location

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import  com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "AutoPathing")
public class AutoPathing extends OpMode {
    private Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;
    private int pathState;
    private final Pose StartPose = new Pose(122.6490939044481, 123.36079077429983, 217);
    private final Pose ShootPose = new Pose(87.06425041186161, 101.06095551894562, 31);
    private final Pose PickUp1 = new Pose();
    private final Pose PickUp2 = new Pose();
    private final Pose PickUp3 = new Pose();
    private Path scorePreload;
    private PathChain grab1, score1, grab2, score2, grab3, score3;
    boolean PathGrabShoot1 = true;
    boolean PathGrabShoot2 = true;
    boolean PathGrabShoot3 = true;
    private enum pState {none,grab1, score1, grab2, score2, grab3, score3, finish};
    private pState currentPState = pState.none;

    public void buildPaths() {
        scorePreload = new Path(new BezierLine(StartPose, ShootPose));
        scorePreload.setLinearHeadingInterpolation(StartPose.getHeading(), ShootPose.getHeading());

        grab1 = follower.pathBuilder()
                .addPath(new BezierLine(ShootPose, PickUp1))
                .setLinearHeadingInterpolation(ShootPose.getHeading(), PickUp1.getHeading())
                .build();

        score1 = follower.pathBuilder()
                .addPath(new BezierLine(PickUp1, ShootPose))
                .setLinearHeadingInterpolation(PickUp1.getHeading(), ShootPose.getHeading())
                .build();

        grab2 = follower.pathBuilder()
                .addPath(new BezierLine(ShootPose, PickUp2))
                .setLinearHeadingInterpolation(ShootPose.getHeading(), PickUp2.getHeading())
                .build();

        score2 = follower.pathBuilder()
                .addPath(new BezierLine(PickUp2, ShootPose))
                .setLinearHeadingInterpolation(PickUp2.getHeading(), ShootPose.getHeading())
                .build();

        grab3 = follower.pathBuilder()
                .addPath(new BezierLine(ShootPose, PickUp3))
                .setLinearHeadingInterpolation(ShootPose.getHeading(), PickUp3.getHeading())
                .build();

        score3 = follower.pathBuilder()
                .addPath(new BezierLine(PickUp3, ShootPose))
                .setLinearHeadingInterpolation(PickUp3.getHeading(), ShootPose.getHeading())
                .build();
    }
    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }

    private void determinePath(int currentPath){
        if (currentPath == 0){
            if (PathGrabShoot1){
                currentPState = pState.grab1;
            }
        }
        if (currentPath <= 1){
            //finish path 1
            if (PathGrabShoot2){
                currentPState = pState.grab2;
            }
        }
        if (currentPath <= 2){
            if (PathGrabShoot3){
                currentPState = pState.grab3;
            }
        }else{
            currentPState = pState.finish;
        }
    }

    @Override
    public void loop() {
        //method 1
        if (PathGrabShoot1){
            follower.followPath(grab1);
            while(follower.isBusy()){
                follower.update();
            }
            follower.followPath(score1);
            while(follower.isBusy()){
                follower.update();
            }
        }

        if (PathGrabShoot2){
            follower.followPath(grab2);
            while(follower.isBusy()){
                follower.update();
            }
            follower.followPath(score2);
            while(follower.isBusy()){
                follower.update();
            }
        }

        if (PathGrabShoot3){
            follower.followPath(grab3);
            while(follower.isBusy()){
                follower.update();
            }
            follower.followPath(score3);
            while(follower.isBusy()){
                follower.update();
            }
        }

        //method 2 using fsm
        //using this method no need use while loop, so no need to put follower.update() in all loop
        // it also can run problem outside the fsm, while method cannot, it can only run program inside while loop
        switch (currentPState){
            case none:
                determinePath(0);

            case grab1:
                follower.followPath(grab1);
                if(!follower.isBusy()){
                    currentPState = pState.score1;
                }

            case score1:
                follower.followPath(score1);
                if(!follower.isBusy()){
                    determinePath(1);
                }

            case grab2:
                follower.followPath(grab2);
                if(!follower.isBusy()){
                    currentPState = pState.score2;
                }

            case score2:
                follower.followPath(score2);
                if(!follower.isBusy()){
                    determinePath(2);
                }

            case grab3:
                follower.followPath(grab3);
                if(!follower.isBusy()){
                    currentPState = pState.score3;
                }

            case score3:
                follower.followPath(score3);
                if(!follower.isBusy()){
                    determinePath(3);
                }

            case finish:
                if (gamepad1.triangle){
                    currentPState = pState.none;
                    //restart the path again
                }
        }
        follower.update();


        telemetry.addData("path state", pathState);
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