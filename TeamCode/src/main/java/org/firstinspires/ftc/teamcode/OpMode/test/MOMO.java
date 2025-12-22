package org.firstinspires.ftc.teamcode.OpMode.test;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "MOMO",group = "TeleOP")
public class MOMO extends LinearOpMode {

    private DcMotor leftUp, rightUp, leftDown, rightDown;
    private ElapsedTime timer = new ElapsedTime();
    private double x, y, r;
    private boolean playerDrive = true;

    @Override
    public void runOpMode() {
        leftUp = hardwareMap.get(DcMotor.class, "leftUp");
        rightUp = hardwareMap.get(DcMotor.class, "rightUp");
        leftDown = hardwareMap.get(DcMotor.class, "leftDown");
        rightDown = hardwareMap.get(DcMotor.class, "rightDown");


        waitForStart();

        if (isStopRequested()) {
            return;
        }
        moveForwardTime(2);
        StrafeTime(2);
    }

    public void moveForwardTime(double time) {
        timer.reset();
        while (timer.time() < time) {
            leftUp.setPower(1);
            rightUp.setPower(1);
            leftDown.setPower(1);
            rightDown.setPower(1);
        }
    }

    public void StrafeTime(double time) {
        timer.reset();
        while (timer.time() < time) {
            leftUp.setPower(1);
            rightUp.setPower(-1);
            leftDown.setPower(1);
            rightDown.setPower(-1);
        }
    }
}
