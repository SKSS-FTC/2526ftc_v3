package org.firstinspires.ftc.teamcode.config.path;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.pedroPathing.AutoPathing;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class BluePath {
    private final Pose StartPose = new Pose(22.138385502471177, 124.06919275123559, 140);
//    this is blue up starting point

//    private final Pose StartPose = new Pose(36.37232289950577,9.01153212520594,90);
//    this is blue down starting point
    private final Pose ShootPose = new Pose(59.24382207578253, 97.67874794069192, 140);
    private final Pose EndPose = new Pose(56.121911037891294,59.62108731466227,180);
    private final Pose PickUp1_start = new Pose(43.24052718286654,83.84184514003294,180);//From Up to Down
    private final Pose PickUp1_final = new Pose(16.051070840197696,83.60461285008238,180);//From Up to Down
    private final Pose PickUp2_start = new Pose(41.70345963756179,59.495881383855036,180);//From Up to Down
    private final Pose PickUp2_final = new Pose(18.05766062602966,59.258649093904445,180);//From Up to Down
    private final Pose PickUp3_start = new Pose(39.62932454695221,35.98023064250413,180);//From Up to Down
    private final Pose PickUp3_final = new Pose(14.828665568369027,35.742998352553556,180);//From Up to Down
//    private final Pose PickUp1_start = new Pose(39.62932454695221,35.98023064250413,180);//From Down to Up
//    private final Pose PickUp1_final = new Pose(14.828665568369027,35.742998352553556,180);//From Down to Up
//    private final Pose PickUp2_start = new Pose(41.70345963756179,59.495881383855036,180);//From Down to Up
//    private final Pose PickUp2_final = new Pose(18.05766062602966,59.258649093904445,180);//From Down to UP
//    private final Pose PickUp3_start = new Pose(43.24052718286654,83.84184514003294,180);//From Down to Up
//    private final Pose PickUp3_final = new Pose(16.051070840197696,83.60461285008238,180);//From Down to Up
    public PathChain scorePreload, ready1, grab1, score1, ready2, grab2, score2, ready3, grab3, score3, EndPath;
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
        ready1 = follower.pathBuilder()
                .addPath(new BezierLine(ShootPose, PickUp1_start))
                .setLinearHeadingInterpolation(ShootPose.getHeading(), ShootPose.getHeading())
                .build();
        grab1 = follower.pathBuilder()
                .addPath(new BezierLine(PickUp1_start, PickUp1_final))
                .setLinearHeadingInterpolation(ShootPose.getHeading(), PickUp1_final.getHeading())
                .build();

        score1 = follower.pathBuilder()
                .addPath(new BezierLine(PickUp1_final, ShootPose))
                .setLinearHeadingInterpolation(PickUp1_final.getHeading(), ShootPose.getHeading())
                .build();
        ready2 = follower.pathBuilder()
                .addPath(new BezierCurve(ShootPose,PickUp2_start,PickUp2_final))
//                .addPath(new BezierLine(ShootPose, PickUp2_start))
                .setLinearHeadingInterpolation(ShootPose.getHeading(), PickUp2_start.getHeading())
                .build();
        grab2 = follower.pathBuilder()
                .addPath(new BezierLine(PickUp2_start, PickUp2_final))
                .setLinearHeadingInterpolation(PickUp2_start.getHeading(), ShootPose.getHeading())
                .build();

        score2 = follower.pathBuilder()
                .addPath(new BezierLine(PickUp2_final, ShootPose))
                .setLinearHeadingInterpolation(PickUp2_final.getHeading(), ShootPose.getHeading())
                .build();

        ready3 = follower.pathBuilder()
                .addPath(new BezierLine(ShootPose, PickUp3_start))
                .setLinearHeadingInterpolation(ShootPose.getHeading(), PickUp3_start.getHeading())
                .build();
        grab3 = follower.pathBuilder()
                .addPath(new BezierLine(PickUp3_start, PickUp3_final))
                .setLinearHeadingInterpolation(PickUp3_start.getHeading(), PickUp3_final.getHeading())
                .build();
        score3 = follower.pathBuilder()
                .addPath(new BezierLine(PickUp3_final, ShootPose))
                .setLinearHeadingInterpolation(PickUp3_final.getHeading(), ShootPose.getHeading())
                .build();

        EndPath = follower.pathBuilder()
                .addPath(new BezierLine(ShootPose, EndPose))
                .setLinearHeadingInterpolation(ShootPose.getHeading(), EndPose.getHeading())
                .build();
    }

    public void runPath(PathChain pathChain){
        follower.followPath(pathChain);
    }

}
