package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class SlideLeft extends LinearOpMode {
	@Override
	public void runOpMode() {
		final double power = 0.5;

		DcMotor frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
		DcMotor backLeft = hardwareMap.get(DcMotor.class, "backLeft");
		DcMotor frontRight = hardwareMap.get(DcMotor.class, "frontRight");
		DcMotor backRight = hardwareMap.get(DcMotor.class, "backRight");

		waitForStart();

		if (opModeIsActive()) {
			frontLeft.setPower(-power);
			backLeft.setPower(power);
			frontRight.setPower(power);
			backRight.setPower(-power);

			sleep(10000);

			frontLeft.setPower(0);
			backLeft.setPower(0);
			frontRight.setPower(0);
			backRight.setPower(0);
		}
	}
}
