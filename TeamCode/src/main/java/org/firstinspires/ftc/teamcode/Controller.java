package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Controller {
	/*
	Driving:
		- if either analog stick is past deadzone, tank driving
			- priority to forwards/backwards
		- else if dpad is down, slide
			- priority to diagonals
		- else, zero drive train
	Slide/scoop:
		- slide: triggers, right up / left down
		- scoop: left stick, up forward / down backward
	 */

	// References
	private final Gamepad gamepad;
	private final Telemetry telemetry;
	private final DriveTrain driveTrain;
	private final LinearSlide linearSlide;
	// Fields
	private double drivePower;
	private double turnPower;
	private boolean slideUp;
	private boolean slideDown;
	private boolean slideLeft;
	private boolean slideRight;
	private boolean dpadPressed;

	private LinearSlideDirection linearSlideDirection;
	private double scoopPower;

	// Singleton
	private static Controller instance;
	public static Controller getInstance(Gamepad gamepad, Telemetry telemetry, DcMotor[] driveTrain, DcMotor[] slide, DcMotor scoop) {
		if (instance == null) {
			instance = new Controller(gamepad, telemetry, driveTrain, slide, scoop);
		}
		return instance;
	}

	// Constructor
	private Controller(Gamepad gamepad, Telemetry telemetry, DcMotor[] driveTrain, DcMotor[] slide, DcMotor scoop) {
		this.gamepad = gamepad;
		this.telemetry = telemetry;
		this.driveTrain = DriveTrain.getInstance(gamepad, telemetry.addLine("Drive Train"), driveTrain);
		this.linearSlide = LinearSlide.getInstance(gamepad, telemetry.addLine("Linear Slide"), slide, scoop);
	}

	// Methods
	public void tick() {
		driveTrain.tick();
		linearSlide.tick();
	}
}
