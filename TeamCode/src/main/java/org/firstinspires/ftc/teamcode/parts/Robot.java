package org.firstinspires.ftc.teamcode.parts;

/** Class representing the robot. */
public class Robot {
	// Parts
	/** Robot drive train. */
	public final DriveTrain driveTrain;

	/** Robot arm. */
	public Arm arm = null;

	// Constructors

	/**
	 * Basic robot constructor.
	 *
	 * @param driveTrain Drive train uesd in the robot.
	 */
	public Robot(DriveTrain driveTrain) {
		this.driveTrain = driveTrain;
	}

	/**
	 * Robot constructor with arm.
	 *
	 * @param driveTrain Drive train used in the robot.
	 * @param arm        Arm used in the robot.
	 */
	public Robot(DriveTrain driveTrain, Arm arm) {
		this.driveTrain = driveTrain;
		this.arm = arm;
	}
}
