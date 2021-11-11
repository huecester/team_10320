package org.firstinspires.ftc.teamcode.parts;

/** Interface for a drive train, containing methods that manipulate the drive train.. */
public interface DriveTrain {
	/** Moves the drive train forward and backward. Positive speeds should move te drive train forward, and vice versa. */
	void move(double speed);

	/** Slides the drive train left and right. Positive speeds should slide the drive train right, and vice versa. */
	void slide(double speed);

	/** Turns the drive train. Positive speeds should turn the drive train right, and vice versa. */
	void turn(double speed);

	/** Stop the drive train. */
	void stop();
}
