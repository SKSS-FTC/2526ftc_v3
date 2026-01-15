package org.firstinspires.ftc.teamcode.config;

/**
 * @author Baron Henderson - 20077 The Indubitables
 * @version 2.0, 9/8/2024
 */


import com.qualcomm.robotcore.hardware.HardwareMap;

/** Everything that we want to store globally, for example positions of servos, motors, etc. goes in here. **/
public class RobotConstants {
    private static HardwareMap hardwareMap;
    public static double boardAngle = 45;
    public static double[] redShootingTarget = {132, 138};
    public static double[] redAprilTag = {129, 127.8};
    public static double[] blueShootingTarget = {12, 138};
    public static double[] blueAprilTag = {15, 127.8};
    public static double cameraDistance = 2;// in inch
    public static double shooterDistance = 2;// in inch


    /** Variables are positions for the claw servos. **/
}
