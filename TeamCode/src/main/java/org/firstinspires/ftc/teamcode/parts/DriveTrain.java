package org.firstinspires.ftc.teamcode.parts;

/**
 * Interface representing a drive train.
 */
public interface DriveTrain {
	void move(double speed);
	void slide(double speed);
	void turn(double speed);
	void stop();
}
