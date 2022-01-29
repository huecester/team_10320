package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LinearSlide {
	// References
	private final Gamepad gamepad;
	private final Telemetry.Line telemetry;
	private final DcMotor[] slide;
	private final DcMotor scoop;
	// Fields
	private LinearSlideDirection linearSlideDirection;
	private ScoopDirection scoopDirection;

	// Singleton
	private static LinearSlide instance;
	public static LinearSlide getInstance(Gamepad gamepad, Telemetry.Line telemetry, DcMotor[] slide, DcMotor scoop) {
		if (instance == null) {
			instance = new LinearSlide(gamepad, telemetry, slide, scoop);
		}
		return instance;
	}

	// Constructor
	private LinearSlide(Gamepad gamepad, Telemetry.Line telemetry, DcMotor[] slide, DcMotor scoop) {
		this.gamepad = gamepad;
		this.telemetry = telemetry;
		this.slide = slide;
		this.scoop = scoop;
	}

	// Methods
	public void tick() {
		parseController();

		slide();
		scoop();
	}

	// Operating
	private void slide() {
		if (linearSlideDirection == LinearSlideDirection.UP) {
			telemetry.addData("Slide", "Up");
			for (DcMotor motor : slide) {
				motor.setPower(Configuration.linearSlidePower);
			}
		} else if (linearSlideDirection == LinearSlideDirection.DOWN) {
			telemetry.addData("Slide", "Down");
			for (DcMotor motor : slide) {
				motor.setPower(-Configuration.linearSlidePower);
			}
		} else {
			telemetry.addData("Slide", "Stopped");
			for (DcMotor motor : slide) {
				motor.setPower(0);
			}
		}
	}

	private void scoop() {
		if (scoopDirection == ScoopDirection.FORWARD) {
			telemetry.addData("Scoop", "Forward");
			scoop.setPower(Configuration.scoopPower);
		} else if (scoopDirection == ScoopDirection.REVERSE) {
			telemetry.addData("Scoop", "Reverse");
			scoop.setPower(-Configuration.scoopPower);
		} else {
			telemetry.addData("Scoop", "Stopped");
			scoop.setPower(0);
		}
	}

	// Helpers
	private void parseController() {
		linearSlideDirection = parseLinearSlide();
		scoopDirection = parseScoop();
	}

	private LinearSlideDirection parseLinearSlide() {
		if (gamepad.right_trigger > Configuration.triggerThreshold) {
			return LinearSlideDirection.UP;
		} else if (gamepad.left_trigger > Configuration.triggerThreshold) {
			return LinearSlideDirection.DOWN;
		} else {
			return null;
		}
	}

	private ScoopDirection parseScoop() {
		if (gamepad.right_bumper) {
			return ScoopDirection.FORWARD;
		} else if (gamepad.left_bumper) {
			return ScoopDirection.REVERSE;
		} else {
			return null;
		}
	}
}
