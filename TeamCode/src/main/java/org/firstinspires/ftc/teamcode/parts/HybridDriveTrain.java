package org.firstinspires.ftc.teamcode.parts;

/** Hybrid mecanum-omni drive train. */
public class HybridDriveTrain implements DriveTrain {
	// Fields
	// Individual wheels
	private Motor frontLeft;
	private Motor middleLeft;
	private Motor backLeft;
	private Motor frontRight;
	private Motor middleRight;
	private Motor backRight;

	// Motor sets
	private Motor[] allWheels;
	private Motor[] leftWheels;
	private Motor[] rightWheels;

	// Constructor
	/**
	 * Hybrid drive train constructor.
	 *
	 * @param wheels          An array of 6 wheels, starting from the front left, going to the bottom left, and doing the same with the right side.
	 * @throws AssertionError Throws if wheels is not of length 6.
	 */
	public HybridDriveTrain(Motor[] wheels) throws AssertionError {
		assert wheels.length == 6;

		this.frontLeft = wheels[0];
		this.middleLeft = wheels[1];
		this.backLeft = wheels[2];
		this.frontRight = wheels[3];
		this.middleRight = wheels[4];
		this.backRight = wheels[5];

		// Create wheel sets
		this.allWheels = new Motor[] {
			this.frontLeft,
			this.middleLeft,
			this.backLeft,
			this.frontRight,
			this.middleRight,
			this.backRight,
		};

		this.leftWheels = new Motor[] {
			this.frontLeft,
			this.middleLeft,
			this.backLeft,
		};

		this.rightWheels = new Motor[] {
			this.frontRight,
			this.middleRight,
			this.backRight,
		};
	}

	// Methods
	/**
	 * Move the drive train forwards or backwards.
	 *
	 * @param speed Speed to set the wheels to. Positive values move the drive train forwards, and vice versa.
	 */
	public void move(double speed) {
		for (Motor wheel : this.allWheels) {
			wheel.move(speed);
		}
	}

	/**
	 * Slide the drive train left or right.
	 *
	 * @param speed Speed to set the wheels to. Positive values slide the drive train right, and vice versa.
	 */
	public void slide(double speed) {
		frontLeft.move(speed);
		backRight.move(speed);

		frontRight.move(-speed);
		backLeft.move(-speed);
	}

	/**
	 * Turn the drive train.
	 *
	 * @param speed Speed to set the wheels to. Positive values turn the drive train right, and vice versa.
	 */
	public void turn(double speed) {
		for (Motor wheel : this.rightWheels) {
			wheel.move(speed);
		}
		for (Motor wheel : this.leftWheels) {
			wheel.move(-speed);
		}
	}

	/** Stop the drive train. */
	public void stop() {
		for (Motor wheel : this.allWheels) {
			wheel.stop();
		}
	}
}
