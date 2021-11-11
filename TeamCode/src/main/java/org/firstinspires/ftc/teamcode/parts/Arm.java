package org.firstinspires.ftc.teamcode.parts;

/** Class representing an arm on the robot. */
public class Arm {
	// Left motor as if looking from the back, same with right
	private final Motor left;
	private final Motor right;

	// Motor set
	private final Motor[] all;

	/**
	 * Arm constructor.
	 *
	 * @param left  Left motor as if looking from the back of the robot.
	 * @param right Right motor as if looking from the back of the robot.
	 */
	public Arm(Motor left, Motor right) {
		this.left = left;
		this.right = right;

		this.all = new Motor[] {
			this.left,
			this.right,
		};
	}

	/**
	 * Turn the arm forward (outward/extending) or backward (inward/retracting).
	 *
	 * @param speed Speed to set the motors to. Positive values send the arm forward, and vice versa.
	 */
	public void turn(Double speed) {
		for (Motor motor : all) {
			motor.move(speed);
		}
	}
}
