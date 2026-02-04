package org.firstinspires.ftc.teamcode.config.path;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class RedPath {
    private final Pose StartPose = new Pose(122, 124.06919275123559, Math.toRadians(125));
//    this is red up starting point

    //    private final Pose StartPose = new Pose(36.37232289950577,9.01153212520594,90);
//    this is red down starting point
    private final Pose ShootPose = new Pose(85, 97.67874794069192, Math.toRadians(35));
    private final Pose EndPose = new Pose(91.29654036243821,59.820428336079075,Math.toRadians(0));
    private final Pose PickUp1_start = new Pose(103.26029654036246,83.44481054365735,Math.toRadians(0));//From Up to Down
    private final Pose PickUp1_final = new Pose(128,83.44481054365735,Math.toRadians(0));//From Up to Down
    private final Pose PickUp2_start = new Pose(102.19110378912686,59.22075782537066,Math.toRadians(0));//From Up to Down
    private final Pose PickUp2_final = new Pose(129,59.22075782537066,Math.toRadians(0));//From Up to Down
    private final Pose PickUp3_start = new Pose(102.56013179571664,35.52553542009881,Math.toRadians(0));//From Up to Down
    private final Pose PickUp3_final = new Pose(129,35.52553542009881, Math.toRadians(0));//From Up to Down
//    private final Pose PickUp1_start = new Pose(102.56013179571664,35.52553542009881,0);//From Down to Up
//    private final Pose PickUp1_final = new Pose(129,35.52553542009881,0);//From Down to Up
//    private final Pose PickUp2_start = new Pose(102.19110378912686,59.22075782537066,0);//From Down to Up
//    private final Pose PickUp2_final = new Pose(129,59.22075782537066,0);//From Down to UP
//    private final Pose PickUp3_start = new Pose(103.26029654036246,83.44481054365735,0);//From Down to Up
//    private final Pose PickUp3_final = new Pose(128,83.44481054365735,0);//From Down to Up
    public PathChain scorePreload, Get_Ball1, Shoot_Ball1, Get_Ball2, Shoot_Ball2, Get_Ball3, Shoot_Ball3, EndPath;
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
                .setLinearHeadingInterpolation(ShootPose.getHeading(), PickUp3_final.getHeading())
                .build();
        Shoot_Ball3 = follower.pathBuilder()
                .addPath(new BezierLine(PickUp3_final, ShootPose))
                .setLinearHeadingInterpolation(PickUp3_final.getHeading(), ShootPose.getHeading())
                .build();

        EndPath = follower.pathBuilder()
                .addPath(new BezierLine(ShootPose, EndPose))
                .setLinearHeadingInterpolation(ShootPose.getHeading(), EndPose.getHeading())
                .build();
    }
}
