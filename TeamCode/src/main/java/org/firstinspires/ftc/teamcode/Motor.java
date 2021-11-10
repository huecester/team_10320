package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Class representing a motor on the robot.
 */
public class Motor {
	// Fields
	private final DcMotor motor;

	private boolean invertMovement = false;

	// Constructors
	/**
	 * Basic motor constructor.
	 *
	 * @param motor DcMotor.
	 */
	public Motor(DcMotor motor) {
		this.motor = motor;
	}

	/**
	 * Motor constructor with inversion parameters.
	 *
	 * @param motor          DcMotor.
	 * @param invertMovement Set to true to invert forward and backward.
	 */
	public Motor(DcMotor motor, boolean invertMovement, boolean invertTurning) {
		this.motor = motor;
		this.invertMovement = invertMovement;
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
