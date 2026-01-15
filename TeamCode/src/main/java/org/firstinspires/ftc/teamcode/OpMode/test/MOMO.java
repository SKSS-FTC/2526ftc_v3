package org.firstinspires.ftc.teamcode.OpMode.test;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "MOMO",group = "TeleOP")
public class MOMO extends LinearOpMode {
    private DcMotor leftUp, rightUp, leftDown, rightDown = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftUp = hardwareMap.get(DcMotor.class, "leftUp");
        rightUp = hardwareMap.get(DcMotor.class, "rightUp");
        leftDown = hardwareMap.get(DcMotor.class, "leftDown");
        rightDown = hardwareMap.get(DcMotor.class, "rightDown");


        waitForStart();

        leftUp.setDirection(DcMotor.Direction.REVERSE);
        rightUp.setDirection(DcMotor.Direction.FORWARD);
        leftDown.setDirection(DcMotor.Direction.REVERSE);
        rightDown.setDirection(DcMotor.Direction.FORWARD);


        waitForStart();

        leftUp.setPower(0.5);
        leftDown.setPower(0.5);
        rightUp.setPower(0.5);
        rightDown.setPower(0.5);
        sleep(3000);

        leftUp.setPower(-0.5);
        leftDown.setPower(0.5);
        rightUp.setPower(0.5);
        rightDown.setPower(-0.5);
        sleep(3000);

        leftUp.setPower(0);
        leftDown.setPower(0);
        rightUp.setPower(0);
        rightDown.setPower(0);

        telemetry.addData("Status", "Run Complete");
        telemetry.update();
        }
    }
