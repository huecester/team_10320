package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriverController {
	/*
	C1 - Driver:
		- if either analog stick is past deadzone, tank driving
			- priority to forwards/backwards
		- else if dpad is down, slide
			- priority to diagonals
		- else, zero drive train
	 */

	// Configuration
	public static double deadzone = 0.1;
	public static double slidePower = 1;

	// References
	private final Gamepad gamepad;
	private final Telemetry telemetry;
	private final DcMotor[] driveTrain;
	// Fields
	private double drivePower;
	private double turnPower;
	private boolean slideUp;
	private boolean slideDown;
	private boolean slideLeft;
	private boolean slideRight;
	private boolean dpadPressed;

	// Singleton parts
	private static DriverController instance;
	public static DriverController getInstance(Gamepad gamepad, Telemetry telemetry, DcMotor[] driveTrain) {
		if (instance == null) {
			instance = new DriverController(gamepad, telemetry, driveTrain);
		}
		return instance;
	}

	// Constructor
	private DriverController(Gamepad gamepad, Telemetry telemetry, DcMotor[] driveTrain) {
		this.gamepad = gamepad;
		this.telemetry = telemetry;
		this.driveTrain = driveTrain;
	}

	// Methods
	public void tick() {
		parseController();

		if (Math.abs(drivePower) >= deadzone || Math.abs(turnPower) >= deadzone) {
			telemetry.addData("Driver Status", "Driving");
			tankDrive();
		} else if (dpadPressed) {
			telemetry.addData("Driver Status", "Sliding");
			slide();
		} else {
			telemetry.addData("Driver Status", "Stopped");
			zero();
		}
	}

	// Driving
	private void tankDrive() {
		if (Math.abs(drivePower) >= deadzone) {
			for (DcMotor motor : driveTrain) {
				motor.setPower(drivePower);
			}
		} else {
			// Left motors
			driveTrain[0].setPower(turnPower);
			driveTrain[1].setPower(turnPower);

			// Right motors
			driveTrain[2].setPower(-turnPower);
			driveTrain[3].setPower(-turnPower);
		}
	}

	private void slide() {
		SlideDirection direction = parseDirection();
		if (direction == null) return;

		switch (direction) {
			case UP_RIGHT:
				driveTrain[0].setPower(slidePower);
				driveTrain[1].setPower(0);
				driveTrain[2].setPower(0);
				driveTrain[3].setPower(slidePower);
				break;
			case DOWN_RIGHT:
				driveTrain[0].setPower(0);
				driveTrain[1].setPower(-slidePower);
				driveTrain[2].setPower(-slidePower);
				driveTrain[3].setPower(0);
				break;
			case UP_LEFT:
				driveTrain[0].setPower(0);
				driveTrain[1].setPower(slidePower);
				driveTrain[2].setPower(slidePower);
				driveTrain[3].setPower(0);
				break;
			case DOWN_LEFT:
				driveTrain[0].setPower(-slidePower);
				driveTrain[1].setPower(0);
				driveTrain[2].setPower(0);
				driveTrain[3].setPower(-slidePower);
				break;
			case UP:
				for (DcMotor motor : driveTrain) {
					motor.setPower(slidePower);
				}
				break;
			case RIGHT:
				driveTrain[0].setPower(slidePower);
				driveTrain[1].setPower(-slidePower);
				driveTrain[2].setPower(-slidePower);
				driveTrain[3].setPower(slidePower);
				break;
			case DOWN:
				for (DcMotor motor : driveTrain) {
					motor.setPower(-slidePower);
				}
				break;
			case LEFT:
				driveTrain[0].setPower(-slidePower);
				driveTrain[1].setPower(slidePower);
				driveTrain[2].setPower(slidePower);
				driveTrain[3].setPower(-slidePower);
				break;
		}
	}

	private void zero() {
		for (DcMotor motor : driveTrain) {
			motor.setPower(0);
		}
	}

	// Helpers
	private void parseController() {
		drivePower = -gamepad.left_stick_y;
		turnPower = gamepad.right_stick_x;
		slideUp = gamepad.dpad_up;
		slideDown = gamepad.dpad_down;
		slideLeft = gamepad.dpad_left;
		slideRight = gamepad.right_bumper;
		dpadPressed = slideUp || slideDown || slideLeft || slideRight;
	}

	private SlideDirection parseDirection() {
		if (slideUp && slideRight) {
			return SlideDirection.UP_RIGHT;
		} else if (slideDown && slideRight) {
			return SlideDirection.DOWN_RIGHT;
		} else if (slideUp && slideLeft) {
			return SlideDirection.UP_LEFT;
		} else if (slideDown && slideLeft) {
			return SlideDirection.DOWN_LEFT;
		} else if (slideUp) {
			return SlideDirection.UP;
		} else if (slideRight) {
			return SlideDirection.RIGHT;
		} else if (slideDown) {
			return SlideDirection.DOWN;
		} else if (slideLeft) {
			return SlideDirection.LEFT;
		} else {
			return null;
		}
	}
}
