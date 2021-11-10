package org.firstinspires.ftc.teamcode;

/**
 * Interface representing a drive train.
 */
public interface DriveTrain {
	void move(double speed);
	void turn(double speed);
	void stop();
}
