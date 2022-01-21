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
			// parameters
			final double threshold = 0.05;

			// `turning/forward
			double drive = -gamepad1.left_stick_y;
			double turn = gamepad1.right_stick_x;
			double leftPower = Range.clip(drive + turn, -1.0, 1.0);
			double rightPower = Range.clip(drive - turn, -1.0, 1.0);

			// sliding
			boolean leftSlide = gamepad1.dpad_left;
			boolean rightSlide = gamepad1.dpad_right;

			if (Math.abs(drive) >= threshold || Math.abs(turn) >= threshold) {
				frontLeft.setPower(leftPower);
				backLeft.setPower(leftPower);
				frontRight.setPower(rightPower);
				backRight.setPower(rightPower);
			} else if (leftSlide) {
				frontLeft.setPower(1);
				backLeft.setPower(-1);
				frontRight.setPower(-1);
				backRight.setPower(1);
			} else if (rightSlide) {
				frontLeft.setPower(-1);
				backLeft.setPower(1);
				frontRight.setPower(1);
				backRight.setPower(-1);
			} else {
				frontLeft.setPower(0);
				backLeft.setPower(0);
				frontRight.setPower(0);
				backRight.setPower(0);
			}


			telemetry.addData("Wheels", "left (%.2f), right (%.2f)", leftPower, rightPower);
			telemetry.update();
		}
	}
}
