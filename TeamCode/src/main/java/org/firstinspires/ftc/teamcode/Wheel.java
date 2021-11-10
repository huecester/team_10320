package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Class representing a wheel on the drive train.
 */
public class Wheel {
	// Fields
	private final DcMotor motor;

	private boolean invertMovement = false;
	private boolean invertTurning = false;

	// Constructors
	/**
	 * Basic wheel constructor.
	 *
	 * @param motor Motor used to power the wheel.
	 */
	public Wheel(DcMotor motor) {
		this.motor = motor;
	}

	/**
	 * Wheel constructor with inversion parameters.
	 *
	 * @param motor          Motor used to power the wheel.
	 * @param invertMovement Set to true to invert forward and backward.
	 * @param invertTurning  Set to true to invert turning direction.
	 */
	public Wheel(DcMotor motor, boolean invertMovement, boolean invertTurning) {
		this.motor = motor;
		this.invertMovement = invertMovement;
		this.invertTurning = invertTurning;
	}

	// Methods
	/**
	 * Move the wheel forwards or backwards.
	 *
	 * @param speed Speed to set the motor to. Depending on the state of invertForward, the wheel will turn forwards or backwards based on the sign of the value.
	 */
	public void move(double speed) {
		this.motor.setPower((!this.invertMovement) ? speed : -speed);
	}

	/**
	 * Stop the wheel.
	 */
	public void stop() {
		this.motor.setPower(0);
	}
}
