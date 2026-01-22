package org.firstinspires.ftc.teamcode.pedroPathing.;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.pedropathing.util.Timer;

@Autonomous
public class AutoPathing extends OpMode {
    private Follower follower;
    private Timer pathTimer, opModeTimer;

    public enum PathState {
        DRIVE_STARTPOS_SHOOT_POS, SHOOT_PRELOAD
        SHOOT_PRELOAD

    }
    PathState pathState;

    private final Pose startpose = new Pose(72,20,90);
    private final Pose shootpose = new Pose(72,120,90);

    private PathChain driverStartPosShootPos;

    public void buildPaths() {
        driverStartPosShootPos = follower.pathBuilder()
                .addPath(new BezierLine(startpose, shootpose))
                .setLinearHeadingInterpolation(startpose.getHeading(), shootpose.getHeading())
                .build();
    }

    public void StatePathUpdate() {
        switch (pathState) {
            case DRIVE_STARTPOS_SHOOT_POS:
                follower.followPath(driverStartPosShootPos, true);
                pathState = PathState.SHOOT_PRELOAD;
                break;
            case SHOOT_PRELOAD:
                if (!follower.isBusy()) {
                    telemetry.addLine("Done Path 1 ");

                }
                break;
            default:
                telemetry.addLine("No State Commanded");
                break;
        }
    }

    public void set


    @Override
    public void init() {

    }
    @Override
    public void loop() {

    }
}