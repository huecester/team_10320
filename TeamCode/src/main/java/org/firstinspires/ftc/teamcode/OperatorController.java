package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class OperatorController {
	/*
	C2 - Slide/scoop operator:
		- slide: triggers, right up / left down
		- scoop: left stick, up forward / down backward
	*/

	// Fields
	private Gamepad gamepad;
	private DcMotor[] slide;
	private DcMotor scoop;

	// Singleton parts
	private static OperatorController instance;
	public static OperatorController getInstance(Gamepad gamepad, DcMotor[] slide, DcMotor scoop) {
		if (instance == null) {
			instance = new OperatorController(gamepad, slide, scoop);
		}
		return instance;
	}

	// Constructor
	private OperatorController(Gamepad gamepad, DcMotor[] slide, DcMotor scoop) {
		this.gamepad = gamepad;
		this.slide = slide;
		this.scoop = scoop;
	}

	// Methods
	public void tick() {}
}
