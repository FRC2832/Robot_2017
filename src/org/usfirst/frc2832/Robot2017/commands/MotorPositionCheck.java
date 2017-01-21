package org.usfirst.frc2832.Robot2017.commands;

import org.usfirst.frc2832.Robot2017.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MotorPositionCheck extends Command {
	public double startTime;
    public MotorPositionCheck() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double runTime = Timer.getFPGATimestamp() - startTime;
    	
    	/*
    	if(runTime < 2) {
    		WheelMotors.lFrontDrive.set(0.5);
    		WheelMotors.rFrontDrive.set(0.0);
    		WheelMotors.lRearDrive.set(0.0);
    		WheelMotors.rRearDrive.set(0.0);
    	} else if(runTime < 4) {
    		WheelMotors.lFrontDrive.set(0.0);
    		WheelMotors.rFrontDrive.set(0.5);
    		WheelMotors.lRearDrive.set(0.0);
    		WheelMotors.rRearDrive.set(0.0);
    	} else if(runTime < 6) {
    		WheelMotors.lFrontDrive.set(0.0);
    		WheelMotors.rFrontDrive.set(0.0);
    		WheelMotors.lRearDrive.set(0.5);
    		WheelMotors.rRearDrive.set(0.0);
    	} else if(runTime < 8) {
    		WheelMotors.lFrontDrive.set(0.0);
    		WheelMotors.rFrontDrive.set(0.0);
    		WheelMotors.lRearDrive.set(0.0);
    		WheelMotors.rRearDrive.set(0.5);
    	} else {
    		WheelMotors.lFrontDrive.set(0.0);
    		WheelMotors.rFrontDrive.set(0.0);
    		WheelMotors.lRearDrive.set(0.0);
    		WheelMotors.rRearDrive.set(0.0);
    	} 
    	*/
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double runTime = Timer.getFPGATimestamp() - startTime;
    	if(runTime < 8) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
