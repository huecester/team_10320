package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.parts.Arm;
import org.firstinspires.ftc.teamcode.parts.DriveTrain;
import org.firstinspires.ftc.teamcode.parts.MecanumDriveTrain;
import org.firstinspires.ftc.teamcode.parts.Motor;
import org.firstinspires.ftc.teamcode.parts.Robot;

@TeleOp
public class DriveTeleOp extends LinearOpMode {
	// Physical parts
	// TODO add motors
	private DcMotor[] wheelDcMotors = new DcMotor[4];
	private DcMotor[] armDcMotors = new DcMotor[2];

	// Abstractions
	private Motor[] wheels = new Motor[6];
	private Motor[] armMotors = new Motor[2];

	private DriveTrain driveTrain;
	//private Arm arm;
	private Robot robot;

	@Override
	public void runOpMode() {
		// Init
		// Drive train
		for (int i = 0; i < 6; i++) {
			this.wheels[i] = new Motor(this.wheelDcMotors[i]);
		}
		this.driveTrain = new MecanumDriveTrain(this.wheels);

		// Arm
		//this.arm = new Arm(armMotors[0], armMotors[1]);

		// Robot
		this.robot = new Robot(this.driveTrain);//, this.arm);

		telemetry.addData("Status", "Initialized");
		telemetry.update();

		waitForStart();

		telemetry.addData("Status", "Running");
		telemetry.update();

		double forwardPower;
		double slidePower;
		double turnPower;
		final double deadzone = 0.1;
		while (opModeIsActive()) {
			// Get input
			forwardPower = -this.gamepad1.left_stick_y;
			slidePower = this.gamepad1.left_stick_x;
			turnPower = this.gamepad1.right_stick_x;

			// Movement
			if (forwardPower < -deadzone || forwardPower > deadzone) {
				// Forward
				this.robot.driveTrain.move(forwardPower);
				telemetry.addData("Movement", "Forward");
			} else if (slidePower < -deadzone || slidePower > deadzone) {
				// Slide
				this.robot.driveTrain.slide(slidePower);
				telemetry.addData("Movement", "Sliding");
			} else if (turnPower < -deadzone || turnPower > deadzone) {
				// turn
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
