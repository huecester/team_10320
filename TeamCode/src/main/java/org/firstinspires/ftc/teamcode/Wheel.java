package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Abstract class for a wheel on the robot.
 */
public abstract class Wheel {
	// Fields
	private final DcMotor motor;

	private boolean invertForward = false;
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
	 * @param motor         Motor used to power the wheel.
	 * @param invertForward Set to true to invert forward and backward.
	 * @param invertTurning Set to true to invert turning direction.
	 */
	public Wheel(DcMotor motor, boolean invertForward, boolean invertTurning) {
		this.motor = motor;
		this.invertForward = invertForward;
		this.invertTurning = invertTurning;
	}

	// Methods
	/**
	 * Turn the wheel forward.
	 * @param speed Speed to set the motor to.
	 */
	public void forward(double speed) {
		this.motor.setPower((this.invertForward) ? -speed : speed);
	}

	/**
	 * Turn the wheel backward.
	 * @param speed Speed to set the motor to.
	 */
	public void backward(double speed) {
		this.motor.setPower((this.invertForward) ? speed : -speed);
	}

	/**
	 * Stop the wheel.
	 */
	public void stop() {
		this.motor.setPower(0);
	}
}
