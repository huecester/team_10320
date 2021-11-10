package org.firstinspires.ftc.teamcode;

/**
 * Hybrid mekanim-omni drive train.
 */
public class MekanimOmniDriveTrain implements DriveTrain {
	// Fields
	// Individual wheels
	private Wheel frontLeft;
	private Wheel middleLeft;
	private Wheel backLeft;
	private Wheel frontRight;
	private Wheel middleRight;
	private Wheel backRight;

	// Wheel sets
	private Wheel[] allWheels;
	private Wheel[] leftWheels;
	private Wheel[] rightWheels;

	// Constructor
	/**
	 * Constructor for hybrid mekanim-omni drive train.
	 *
	 * @param wheels          An array of 6 wheels, starting from the front left, going to the bottom left, and doing the same with the right side.
	 * @throws AssertionError Throws if wheels is not of length 6.
	 */
	public MekanimOmniDriveTrain(Wheel[] wheels) throws AssertionError {
		assert wheels.length == 6;

		this.frontLeft = wheels[0];
		this.middleLeft = wheels[1];
		this.backLeft = wheels[2];
		this.frontRight = wheels[3];
		this.middleRight = wheels[4];
		this.backRight = wheels[5];

		// Create wheel sets
		this.allWheels = new Wheel[] {
				this.frontLeft,
				this.middleLeft,
				this.backLeft,
				this.frontRight,
				this.middleRight,
				this.backRight,
		};

		this.leftWheels = new Wheel[] {
				this.frontLeft,
				this.middleLeft,
				this.backLeft,
		};

		this.rightWheels = new Wheel[] {
				this.frontRight,
				this.middleRight,
				this.backRight,
		};
	}

	// Methods
	/**
	 * Move the drive train forwards or backwards.
	 *
	 * @param speed Speed to set the wheels to. Positive values move the drive train forwards, while negative values move the drive train backwards.
	 */
	public void move(double speed) {
		for (Wheel wheel : this.allWheels) {
			wheel.move(speed);
		}
	}

	/**
	 * Turn the drive train.
	 *
	 * @param speed Speed to set the wheels to. Positive values turn the drive train right, while negative values turn the drive train left.
	 */
	public void turn(double speed) {
		for (Wheel wheel : this.rightWheels) {
			wheel.move(speed);
		}
		for (Wheel wheel : this.leftWheels) {
			wheel.move(-speed);
		}
	}

	/**
	 * Stop the drive train.
	 */
	public void stop() {
		for (Wheel wheel : this.allWheels) {
			wheel.stop();
		}
	}
}
