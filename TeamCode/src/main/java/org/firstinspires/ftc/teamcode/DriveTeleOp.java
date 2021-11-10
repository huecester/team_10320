package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class DriveTeleOp extends LinearOpMode {
	private DcMotor frontLeft;
	private DcMotor middleLeft;
	private DcMotor backLeft;
	private DcMotor frontRight;
	private DcMotor middleRight;
	private DcMotor backRight;

	private final DcMotor[] front = { frontRight, frontLeft };
	private final DcMotor[] right = { frontRight, middleRight, backRight };
	private final DcMotor[] left = { frontLeft, middleLeft, backLeft };
	private final DcMotor[] all = { frontRight, middleRight, backRight, frontLeft, middleLeft, backLeft };

	@Override
	public void runOpMode() {
		telemetry.addData("Status", "Initialized");
		telemetry.update();

		waitForStart();

		telemetry.addData("Status", "Running");
		telemetry.update();

		double forwardPower;
		double turnPower;
		final double deadzone = 0.1;
		while (opModeIsActive()) {
			// Get input
			forwardPower = -this.gamepad1.left_stick_y;
			turnPower = this.gamepad1.right_stick_x;

			// Movement
			if (forwardPower < -deadzone || forwardPower > deadzone) {
				// Prioritize forward over turning
				for (DcMotor motor : all) {
					motor.setPower(forwardPower);
				}

				telemetry.addData("Movement", "Forward");
			} else if (turnPower < -deadzone || turnPower > deadzone) {
				// Turn if not going forward
				if (turnPower < 0) {
					// Left
					for (DcMotor motor : left) {
						motor.setPower(0);
					}
					for (DcMotor motor : right) {
						motor.setPower(turnPower);
					}
				} else {
					// Right
					for (DcMotor motor : left) {
						motor.setPower(turnPower);
					}
					for (DcMotor motor : right) {
						motor.setPower(0);
					}
				}

				telemetry.addData("Movement", "Turning");
			} else {
				// Idle if not going forward or turning
				for (DcMotor motor : all) {
					motor.setPower(0);
				}

				telemetry.addData("Movement", "Idle");
			}

			// Update telemetry
			telemetry.update();
		}
	}
}
