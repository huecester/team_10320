package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class ManualTeleOp extends LinearOpMode {
	// Configuration
	private final double stickThreshold = 0.1;

	// Drive train
	private DcMotor frontLeft;
	private DcMotor backLeft;
	private DcMotor frontRight;
	private DcMotor backRight;
	private DcMotor[] driveTrain;

	// Linear slide / scoop
	private DcMotor slideLeft;
	private DcMotor slideRight;
	private DcMotor scoop;
	private DcMotor[] slide;

	// Controllers
	private Controller controller;

	/*
	TODO:
	- fine tune button
	- braking
	- carousel? by driver
	 */

	@Override
	public void runOpMode() {
		// Hardware mapping
		mapHardware();

		// Configuration
		configureDriveTrain();

		// Initialize controllers
		controller = Controller.getInstance(gamepad1, telemetry, driveTrain, slide, scoop);

		telemetry.addData("Status", "Initialized");
		telemetry.update();

		waitForStart();

		telemetry.addData("Status", "Started");
		telemetry.update();

		while (opModeIsActive()) {
			controller.tick();

			//telemetry.addData("Wheels", "left (%.2f), right (%.2f)", 1, 1);
			telemetry.update();
		}
	}

	// Configuration/initialization
	private void mapHardware() {
		frontLeft = getMotor("frontLeft");
		backLeft = getMotor("backLeft");
		frontRight = getMotor("frontRight");
		backRight = getMotor("backRight");
		slideLeft = getMotor("slideLeft");
		slideRight = getMotor("slideRight");
		scoop = getMotor("scoop");

		driveTrain = new DcMotor[] { frontLeft, backLeft, frontRight, backRight };
		slide = new DcMotor[] { slideLeft, slideRight };
	}

	private void configureDriveTrain() {
		// Invert motors where needed
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
		slideRight.setDirection(DcMotorSimple.Direction.REVERSE);

		// Drive train to neutral on zero power
		for (DcMotor motor : driveTrain) {
			motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
			// motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		}

		// Slide and scoop stop on zero power
		for (DcMotor motor : slide) {
			motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		}
		scoop.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
	}

	// Helpers
	private DcMotor getMotor(String deviceName) {
		return hardwareMap.get(DcMotor.class, deviceName);
	}
}
