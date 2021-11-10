package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class OmniWheel extends Wheel {
	/**
	 * Basic wheel constructor.
	 *
	 * @param motor Motor used to power the wheel.
	 */
	public OmniWheel(DcMotor motor) {
		super(motor);
	}

	/**
	 * Wheel constructor with inversion parameters.
	 *
	 * @param motor         Motor used to power the wheel.
	 * @param invertForward Set to true to invert forward and backward.
	 * @param invertTurning Set to true to invert turning direction.
	 */
	public OmniWheel(DcMotor motor, boolean invertForward, boolean invertTurning) {
		super(motor, invertForward, invertTurning);
	}
}
