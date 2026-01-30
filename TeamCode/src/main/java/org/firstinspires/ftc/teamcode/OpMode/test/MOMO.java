package org.firstinspires.ftc.teamcode.OpMode.test;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "MOMO",group = "TeleOP")
public class MOMO extends LinearOpMode {
    private DcMotor leftUp, rightUp, leftDown, rightDown, Intake, Shoot;
    private double x, y, r, L, R;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

//        leftUp = hardwareMap.get(DcMotor.class, "leftUp");
//        rightUp = hardwareMap.get(DcMotor.class, "rightUp");
//        leftDown = hardwareMap.get(DcMotor.class, "leftDown");
//        rightDown = hardwareMap.get(DcMotor.class, "rightDown");
        Intake = hardwareMap.get(DcMotor.class, "Intake");
        Shoot = hardwareMap.get(DcMotor.class, "Shoot");

        waitForStart();

//        leftUp.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightUp.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        leftDown.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightDown.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Shoot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftUp.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightUp.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftDown.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDown.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Shoot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        leftUp.setDirection(DcMotor.Direction.REVERSE);
//        rightUp.setDirection(DcMotor.Direction.FORWARD);
//        leftDown.setDirection(DcMotor.Direction.REVERSE);
//        rightDown.setDirection(DcMotor.Direction.FORWARD);


        while (opModeIsActive()) {
//            double x = gamepad1.left_stick_x;
//            double y = gamepad1.left_stick_y;
//            double r = gamepad1.right_stick_x;
            double L = gamepad1.left_trigger;
            double R = gamepad1.right_trigger;

//           leftUp.setPower( x + y + r ) ;
//           rightUp.setPower( x - y + r ) ;
//           leftDown.setPower( x + y - r ) ;
//           rightDown.setPower( x - y - r ) ;
           Intake.setPower(L);
           Shoot.setPower(R);

        telemetry.update();
        }
    }
}