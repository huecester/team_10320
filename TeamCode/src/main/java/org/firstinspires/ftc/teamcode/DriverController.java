package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class DriverController {
	/*
	C1 - Driver:
		- if either analog stick is past deadzone, tank driving
		- else if dpad is down, slide
		- else, zero drive train
	 */

	// Configuration
	public static double deadzone = 0.1;

	// References
	private Gamepad gamepad;
	private DcMotor[] driveTrain;
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
	public static DriverController getInstance(Gamepad gamepad, DcMotor[] driveTrain) {
		if (instance == null) {
			instance = new DriverController(gamepad, driveTrain);
		}
		return instance;
	}

	// Constructor
	private DriverController(Gamepad gamepad, DcMotor[] driveTrain) {
		this.gamepad = gamepad;
		this.driveTrain = driveTrain;
	}

	// Methods
	public void tick() {
		parseController();

		if (Math.abs(drivePower) >= deadzone || Math.abs(turnPower) >= deadzone) {
			tankDrive();
		} else if (dpadPressed) {
			slide();
		} else {
			zero();
		}
	}

	// Driving
	private void tankDrive() {}
	private void slide() {}
	private void zero() {}

	// Controller parsing
	private void parseController() {
		drivePower = -gamepad.left_stick_y;
		turnPower = gamepad.right_stick_x;
		slideUp = gamepad.dpad_up;
		slideDown = gamepad.dpad_down;
		slideLeft = gamepad.dpad_left;
		slideRight = gamepad.right_bumper;
		dpadPressed = slideUp || slideDown || slideLeft || slideRight;
	}
}
