package org.firstinspires.ftc.teamcode.OpMode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.config.path.RedPath;

@Autonomous(name = "AutoMove")
public class AutoMove extends LinearOpMode {

    public DcMotor leftUp = null;
    public DcMotor rightUp = null;
    public DcMotor leftDown = null;
    public DcMotor rightDown = null;

    public ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        leftUp = hardwareMap.get(DcMotor.class, "leftUp");
        rightUp = hardwareMap.get(DcMotor.class, "rightUp");
        leftDown = hardwareMap.get(DcMotor.class, "leftDown");
        rightDown = hardwareMap.get(DcMotor.class, "rightDown");

        leftUp.setDirection(DcMotor.Direction.REVERSE);
        rightUp.setDirection(DcMotor.Direction.FORWARD);
        leftDown.setDirection(DcMotor.Direction.FORWARD);
        rightDown.setDirection(DcMotorSimple.Direction.FORWARD);

        telemetry.addData("","");
        telemetry.update();

        waitForStart();
        runtime.reset();

        if (opModeIsActive()) {

            leftUp.setPower(0.5);
            rightUp.setPower(0.5);
            leftDown.setPower(0.5);
            rightDown.setPower(0.5);

            sleep(7000);

            leftUp.setPower(0);
            rightUp.setPower(0);
            leftDown.setPower(0);
            rightDown.setPower(0);

            telemetry.addData("", "");
            telemetry.update();
        }
    }
}