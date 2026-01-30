package org.firstinspires.ftc.teamcode.config.path;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class RedPath {
    private final Pose StartPose = new Pose(122.6490939044481, 123.36079077429983, 217);
    private final Pose ShootPose = new Pose(87.06425041186161, 101.06095551894562, 31);
    private final Pose PickUp1 = new Pose();
    private final Pose PickUp2 = new Pose();
    private final Pose PickUp3 = new Pose();
    public PathChain scorePreload,grab1, score1, grab2, score2, grab3, score3;
    public Follower follower;

    public void init(HardwareMap hardwareMap){
        follower = Constants.createFollower(hardwareMap);
        buildPaths();
        follower.setStartingPose(StartPose);
    }

    private void buildPaths(){
        scorePreload = follower.pathBuilder()
                .addPath(new BezierLine(StartPose, ShootPose))
                .setLinearHeadingInterpolation(StartPose.getHeading(), ShootPose.getHeading())
                .build();

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

    public void runPath(PathChain pathChain){
        follower.followPath(pathChain);
    }

}
