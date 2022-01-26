package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.prefs.BackingStoreException;

public class OperatorController {
	/*
	C2 - Slide/scoop operator:
		- slide: triggers, right up / left down
		- scoop: left stick, up forward / down backward
	*/

	// Configuration
	public static double deadzone = 0.1;
	public static double slidePower = 1;
	public static double scoopPower = 0.5;

	// References
	private final Gamepad gamepad;
	private final Telemetry telemetry;
	private final DcMotor[] slide;
	private final DcMotor scoop;
	// Fields
	private LinearSlideDirection linearSlideDirection;
	private ScoopDirection scoopDirection;

	// Singleton parts
	private static OperatorController instance;
	public static OperatorController getInstance(Gamepad gamepad, Telemetry telemetry, DcMotor[] slide, DcMotor scoop) {
		if (instance == null) {
			instance = new OperatorController(gamepad, telemetry, slide, scoop);
		}
		return instance;
	}

	// Constructor
	private OperatorController(Gamepad gamepad, Telemetry telemetry, DcMotor[] slide, DcMotor scoop) {
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
				motor.setPower(slidePower);
			}
		} else if (linearSlideDirection == LinearSlideDirection.DOWN) {
			telemetry.addData("Linear Slide Status", "Down");
			for (DcMotor motor : slide) {
				motor.setPower(-slidePower);
			}
		} else {
			telemetry.addData("Linear Slide Status", "Stopped");
			for (DcMotor motor : slide) {
				motor.setPower(0);
			}
		}
	}

	private void scoop() {
		if (scoopDirection == ScoopDirection.FORWARD) {
			telemetry.addData("Scoop Status", "Forward");
			scoop.setPower(scoopPower);
		} else if (scoopDirection == ScoopDirection.REVERSE) {
			telemetry.addData("Scoop Status", "Reverse");
			scoop.setPower(-scoopPower);
		} else {
			telemetry.addData("Scoop Status", "Stopped");
			scoop.setPower(0);
		}
	}

	// Helpers
	private void parseController() {}
}
