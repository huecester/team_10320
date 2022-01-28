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
	private double scoopPower;

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
			telemetry.addData("Linear Slide Status", "Up");
			for (DcMotor motor : slide) {
				motor.setPower(Configuration.linearSlidePower);
			}
		} else if (linearSlideDirection == LinearSlideDirection.DOWN) {
			telemetry.addData("Linear Slide Status", "Down");
			for (DcMotor motor : slide) {
				motor.setPower(-Configuration.linearSlidePower);
			}
		} else {
			telemetry.addData("Linear Slide Status", "Stopped");
			for (DcMotor motor : slide) {
				motor.setPower(0);
			}
		}
	}

	private void scoop() {
		telemetry.addData("Scoop Power", scoopPower);
		scoop.setPower(scoopPower);
	}

	// Helpers
	private void parseController() {
		linearSlideDirection = parseLinearSlide();
		scoopPower = -gamepad.left_stick_y;
	}

	private LinearSlideDirection parseLinearSlide() {
		if (gamepad.right_bumper || gamepad.right_trigger > Configuration.triggerThreshold) {
			return LinearSlideDirection.UP;
		} else if (gamepad.left_bumper || gamepad.left_trigger > Configuration.triggerThreshold) {
			return LinearSlideDirection.DOWN;
		} else {
			return null;
		}
	}
}
