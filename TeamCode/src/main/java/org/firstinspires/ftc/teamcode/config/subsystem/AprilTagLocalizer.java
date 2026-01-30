package org.firstinspires.ftc.teamcode.config.subsystem;


import static org.firstinspires.ftc.teamcode.config.RobotConstants.blueAprilTag;
import static org.firstinspires.ftc.teamcode.config.RobotConstants.boardAngle;
import static org.firstinspires.ftc.teamcode.config.RobotConstants.cameraDistance;
import static org.firstinspires.ftc.teamcode.config.RobotConstants.redAprilTag;
import static org.firstinspires.ftc.teamcode.config.RobotConstants.shooterDistance;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class AprilTagLocalizer {
    private Camera camera;
    private boolean localizer = false,aprilTagFound = false;
    private double fieldX = -1, fieldY = -1,fieldHeading,xDistance,yDistance;


    public AprilTagLocalizer(HardwareMap hardwareMap) {
        camera = new Camera(hardwareMap);
    }

    public void setLocalizer(boolean state) {
        localizer = state;
        camera.setCamera(state);
    }

    public boolean aprilTagFound(){return  aprilTagFound;}

    public double getFieldX(){return fieldX;}

    public double getFieldY(){return fieldY;}
    public double getFieldHeading(){return fieldHeading;}


    private double getFieldHeading(double relativeShooterHeading){
        if (camera.getID() == 20){
            //blue
            return boardAngle + relativeShooterHeading;
        } else{
            //red
            return -boardAngle + relativeShooterHeading;
        }
    }
    private void getXYDistance(double aprilTagX,double aprilTagY){
        xDistance = aprilTagY * Math.cos(boardAngle / 180 *Math.PI) + aprilTagX * Math.sin(boardAngle / 180 *Math.PI);
        yDistance = aprilTagY * Math.sin(boardAngle / 180 *Math.PI) + aprilTagX * Math.cos(boardAngle / 180 *Math.PI);
    }

    private void getShootCoordinate(double x, double y,double relativeShooterHeading){
        fieldHeading = getFieldHeading(relativeShooterHeading);
        double shooterX = x + cameraDistance * Math.cos(fieldHeading / 180 * Math.PI);
        double shooterY = y + cameraDistance * Math.sin(fieldHeading / 180 * Math.PI);
        fieldX = shooterX + shooterDistance * Math.cos(fieldHeading / 180 * Math.PI);
        fieldY = shooterY + shooterDistance * Math.sin(fieldHeading / 180 * Math.PI);
    }

    public void update(double relativeShooterHeading) {
        if (localizer && camera.getID() != 0) {
            if (camera.getID() == 20){
                // blue
                aprilTagFound = true;
                getXYDistance(camera.getX(),camera.getY());
                double cameraX = blueAprilTag[0] + xDistance;
                double cameraY = blueAprilTag[1] - yDistance;
                getShootCoordinate(cameraX,cameraY,relativeShooterHeading);
            } else if (camera.getID() == 24) {
                // red
                aprilTagFound = true;
                getXYDistance(camera.getX(),camera.getY());
                double cameraX = redAprilTag[0] - xDistance;
                double cameraY = redAprilTag[1] - yDistance;
                getShootCoordinate(cameraX,cameraY,relativeShooterHeading);
            }
        } else {
            aprilTagFound = false;
            fieldX = -1;
            fieldY = -1;
            fieldHeading = -1;
        }
    }
}