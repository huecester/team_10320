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
	private DcMotor[] driveTrain = { frontLeft, backLeft, frontRight, backRight };

	// Linear slide / scoop
	private DcMotor slideLeft;
	private DcMotor slideRight;
	private DcMotor scoop;
	private DcMotor[] slide = { slideLeft, slideRight };

	// Controllers
	private DriverController driverController;
	private OperatorController operatorController;

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
		driverController = DriverController.getInstance(gamepad1, driveTrain);
		operatorController = OperatorController.getInstance(gamepad2, slide, scoop);

		telemetry.addData("Status", "Initialized");
		telemetry.update();

		waitForStart();

		while (opModeIsActive()) {
			driverController.tick();
			operatorController.tick();

			//telemetry.addData("Wheels", "left (%.2f), right (%.2f)", 1, 1);
			telemetry.update();
		}
	}

	// Configuration/initialization
	private void mapHardware() {
		frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
		backLeft = hardwareMap.get(DcMotor.class, "backLeft");
		frontRight = hardwareMap.get(DcMotor.class, "frontRight");
		backRight = hardwareMap.get(DcMotor.class, "backRight");
		slideLeft = hardwareMap.get(DcMotor.class, "slideLeft");
		slideRight = hardwareMap.get(DcMotor.class, "slideRight");
		scoop = hardwareMap.get(DcMotor.class, "scoop");
	}

	private void configureDriveTrain() {
		// Invert motors where needed
		frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

		// Drive train to neutral on zero power
		for (DcMotor motor : driveTrain) {
			motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
			// motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		}
	}
}
