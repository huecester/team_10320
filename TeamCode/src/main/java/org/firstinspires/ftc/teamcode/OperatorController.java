package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class OperatorController {
	/*
	C2 - Slide/scoop operator:
		- slide: triggers, right up / left down
		- scoop: left stick, up forward / down backward
	*/

	// Config
	public static double deadzone = 0.1;

	// Fields
	private final Gamepad gamepad;
	private final Telemetry telemetry;
	private final DcMotor[] slide;
	private final DcMotor scoop;

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
	public void tick() {}
}
