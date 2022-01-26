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

	// Fields
	private Gamepad gamepad;
	private DcMotor[] driveTrain;

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
	public void tick() {}
}
