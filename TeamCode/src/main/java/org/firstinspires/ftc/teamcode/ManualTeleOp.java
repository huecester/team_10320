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

		leftSlide = hardwareMap.get(DcMotor.class, "leftSlide");
		rightSlide = hardwareMap.get(DcMotor.class, "rightSlide");
		leftScoop = hardwareMap.get(Servo.class, "leftScoop");
		rightScoop = hardwareMap.get(Servo.class, "rightScoop");

		// TODO maybe change
		/*leftDrive.setDirection(DcMotor.Direction.FORWARD);
		rightDrive.setDirection(DcMotor.Direction.REVERSE);*/

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


			// slide
			if (gamepad1.dpad_up) {
				leftSlide.setPower(1.0);
				rightSlide.setPower(1.0);
			} else if (gamepad1.dpad_down) {
				leftSlide.setPower(-1.0);
				rightSlide.setPower(-1.0);
			} else {
				leftSlide.setPower(0.0);
				rightSlide.setPower(0.0);
			}

			// scoop
			if (gamepad1.right_bumper) {
				leftScoop.setPosition(0.5);
				rightScoop.setPosition(0.5);
			} else {
				leftScoop.setPosition(0.0);
				rightScoop.setPosition(0.0);
			}

			telemetry.update();
		}
	}
}
