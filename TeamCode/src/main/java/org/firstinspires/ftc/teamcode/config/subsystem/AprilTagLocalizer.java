package org.firstinspires.ftc.teamcode.config.subsystem;


import static org.firstinspires.ftc.teamcode.config.RobotConstants.blueAprilTag;
import static org.firstinspires.ftc.teamcode.config.RobotConstants.boardAngle;
import static org.firstinspires.ftc.teamcode.config.RobotConstants.redAprilTag;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class AprilTagLocalizer {
    private Camera camera;
    private boolean localizer = false;
    private double fieldX = -1, fieldY = -1;


    public AprilTagLocalizer(HardwareMap hardwareMap) {
        camera = new Camera(hardwareMap);
    }

    public void setLocalizer(boolean state) {
        localizer = state;
        camera.setCamera(state);
    }

    public void update() {
        if (localizer && camera.getID() != 0) {
            if (camera.getID() == 20){
                // blue
                double aprilTagX = camera.getX();
                double aprilTagY = camera.getY();
                double xDistance = aprilTagY * Math.cos(boardAngle / 180 *Math.PI) + aprilTagX * Math.sin(boardAngle / 180 *Math.PI);
                double yDistance = aprilTagY * Math.sin(boardAngle / 180 *Math.PI) + aprilTagX * Math.cos(boardAngle / 180 *Math.PI);
                fieldX = blueAprilTag[0] + xDistance;
                fieldY = blueAprilTag[1] - yDistance;
            } else if (camera.getID() == 24) {
                // red
                double aprilTagX = camera.getX();
                double aprilTagY = camera.getY();
                double xDistance = aprilTagY * Math.cos(boardAngle / 180 *Math.PI) + aprilTagX * Math.sin(boardAngle / 180 *Math.PI);
                double yDistance = aprilTagY * Math.sin(boardAngle / 180 *Math.PI) + aprilTagX * Math.cos(boardAngle / 180 *Math.PI);
                fieldX = redAprilTag[0] - xDistance;
                fieldY = redAprilTag[1] - yDistance;
            }
        } else {
            fieldX = -1;
            fieldY = -1;
        }
    }
}