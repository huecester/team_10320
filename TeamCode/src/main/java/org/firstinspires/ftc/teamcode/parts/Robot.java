package org.firstinspires.ftc.teamcode.parts;

/** Class representing the robot. */
public class Robot {
	// Parts
	/** Robot drive train. */
	public final DriveTrain driveTrain;

	/** Robot arm. */
	public final Arm arm;

	// Constructor
	/**
	 * Robot constructor.
	 *
	 * @param driveTrain Drive train used in the robot.
	 */
	public Robot(DriveTrain driveTrain, Arm arm) {
		this.driveTrain = driveTrain;
		this.arm = arm;
	}
}
