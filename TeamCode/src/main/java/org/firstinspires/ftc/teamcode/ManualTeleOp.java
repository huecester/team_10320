package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class ManualTeleOp extends LinearOpMode {
	private DcMotor frontLeft;
	private DcMotor backLeft;
	private DcMotor frontRight;
	private DcMotor backRight;

	private DcMotor leftSlide;
	private DcMotor rightSlide;
	private Servo leftScoop;
	private Servo rightScoop;

	@Override
	public void runOpMode() {
		telemetry.addData("Status", "Initialized");
		telemetry.update();

		frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
		backLeft = hardwareMap.get(DcMotor.class, "backLeft");
		frontRight = hardwareMap.get(DcMotor.class, "frontRight");
		backRight = hardwareMap.get(DcMotor.class, "backRight");

		waitForStart();

		while (opModeIsActive()) {
			// driving
			double drive = -gamepad1.left_stick_y;
			double turn = gamepad1.right_stick_x;
			double leftPower = Range.clip(drive + turn, -1.0, 1.0);
			double rightPower = Range.clip(drive - turn, -1.0, 1.0);

			frontLeft.setPower(leftPower);
			backLeft.setPower(leftPower);
			frontRight.setPower(rightPower);
			backRight.setPower(rightPower);

			telemetry.addData("Wheels", "left (%.2f), right (%.2f)", leftPower, rightPower);
			telemetry.update();
		}
	}
}
