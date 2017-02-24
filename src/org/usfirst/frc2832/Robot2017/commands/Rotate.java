package org.usfirst.frc2832.Robot2017.commands;

import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.subsystems.NavX;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Rotate extends Command {
	private double targetHeading;
	private double currentHeading;

	// Clockwise or counterclockwise rotation
	private int direction = 1;

	// Rotate faster if far away from target heading
	private double fastRotateSpeed = 0.8;

	// Rotate slower when approaching target heading
	private double slowRotateSpeed = 0.7;

	// Threshold (degrees) at which to switch from fast to slow
	private double speedThreshold = 15.0;

	private int maxCycles = 120;

	// "Close enough"
	private double arrivalThreshold = 2.0;

	private int done = 10;

	public Rotate(double angle) {
		requires(Robot.driveTrain);
		this.targetHeading = normalize(angle);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrain.setTankDriveCommand(0, 0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		currentHeading = normalize(NavX.getHeading());
		double error = normalize(targetHeading - currentHeading);

		// which direction are we off by?
		direction = (error > 0.0 ? -1 : 1);

		if (Math.abs(error) <= arrivalThreshold) {
			// close enough! We're done
			done--;
			Robot.driveTrain.setTankDriveCommand(0, 0);
		} else if (Math.abs(error) > speedThreshold) {
			// turn fast
			Robot.driveTrain.setTankDriveCommand(direction * fastRotateSpeed, -direction * fastRotateSpeed);
		} else {
			// turn slow
			Robot.driveTrain.setTankDriveCommand(direction * slowRotateSpeed, -direction * slowRotateSpeed);
		}
		maxCycles--;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (done <= 0 || (maxCycles <= 0))
			return true;
		else
			return false;
	}

	// Called once after isFinished returns true
	protected void end() {

		Robot.driveTrain.setTankDriveCommand(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

	private double normalize(double input) {
		double normalizedValue = input;
		while (normalizedValue > 180)
			normalizedValue -= 360;
		while (normalizedValue < -180)
			normalizedValue += 360;

		return normalizedValue;
	}

}
