package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class EasyBabyTeleOp extends LinearOpMode {
	private DcMotor leftDrive = null;
	private DcMotor rightDrive = null;

	@Override
	public void runOpMode() {
		telemetry.addData("Status", "Initialized");
		telemetry.update();

		leftDrive  = hardwareMap.get(DcMotor.class, "frontLeft");
		rightDrive = hardwareMap.get(DcMotor.class, "frontRight");

		// TODO maybe change
		/*leftDrive.setDirection(DcMotor.Direction.FORWARD);
		rightDrive.setDirection(DcMotor.Direction.REVERSE);*/

		waitForStart();

		while (opModeIsActive()) {
			double leftPower;
			double rightPower;

			double drive = -gamepad1.left_stick_y;
			double turn  =  gamepad1.right_stick_x;
			leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
			rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

			leftDrive.setPower(leftPower);
			rightDrive.setPower(rightPower);

			telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
			telemetry.update();
		}
	}
}
