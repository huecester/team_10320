package org.firstinspires.ftc.teamcode;

/**
 * Class used to represent the robot.
 */
public class Robot {
	// Individual wheels
	private Wheel frontLeft;
	private Wheel middleLeft;
	private Wheel backLeft;
	private Wheel frontRight;
	private Wheel middleRight;
	private Wheel backRight;

	// Wheel sets
	private final Wheel[] allWheels = { frontLeft, middleLeft, backLeft, frontRight, middleRight, backRight };

	// Constructor
	/**
	 * Robot constructor.
	 *
	 * @param wheels          An array of 6 wheels, starting from the front left, going to the bottom left, and doing the same with the right side.
	 * @throws AssertionError Throws if wheels is not of length 6.
	 */
	public Robot(Wheel[] wheels) throws AssertionError {
		assert wheels.length == 6;

		this.frontLeft = wheels[0];
		this.middleLeft = wheels[1];
		this.backLeft = wheels[2];
		this.frontRight = wheels[3];
		this.middleRight = wheels[4];
		this.backRight = wheels[5];
	}

	// Methods
	// Movement
	/**
	 * Move the robot forward.
	 *
	 * @param speed Speed to set the motors at.
	 */
	public void forward(double speed) {
		for (Wheel wheel : allWheels) {
			wheel.forward(speed);
		}
	}

	/**
	 * Move the robot backward.
	 *
	 * @param speed Speed to set the motors at.
	 */
	public void backward(double speed) {
		for (Wheel wheel : allWheels) {
			wheel.backward(speed);
		}
	}

	/**
	 * Stop the robot.
	 */
	public void stop() {
		for (Wheel wheel: allWheels) {
			wheel.stop();
		}
	}
}
