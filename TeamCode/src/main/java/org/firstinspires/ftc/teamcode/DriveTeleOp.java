package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class DriveTeleOp extends LinearOpMode {
	// Physical parts
	// TODO add motors
	private DcMotor[] motors = new DcMotor[6];

	// Abstractions
	private Wheel[] wheels = new Wheel[6];
	private DriveTrain driveTrain;
	private Robot robot;

	@Override
	public void runOpMode() {
		// Init
		for (int i = 0; i < 6; i++) {
			this.wheels[i] = new Wheel(this.motors[i]);
		}
		this.driveTrain = new MekanimOmniDriveTrain(this.wheels);
		this.robot = new Robot(this.driveTrain);

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
				this.robot.driveTrain.move(forwardPower);
				telemetry.addData("Movement", "Forward");
			} else if (turnPower < -deadzone || turnPower > deadzone) {
				// Turn if not going forward
				this.robot.driveTrain.turn(turnPower);
				telemetry.addData("Movement", "Turning");
			} else {
				// Idle if not going forward or turning
				this.robot.driveTrain.stop();
				telemetry.addData("Movement", "Idle");
			}

			// Update telemetry
			telemetry.update();
		}
	}
}
