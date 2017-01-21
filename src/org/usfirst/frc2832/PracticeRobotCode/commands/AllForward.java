package org.usfirst.frc2832.PracticeRobotCode.commands;

import org.usfirst.frc2832.PracticeRobotCode.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AllForward extends Command {

    public AllForward() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	/*
    	WheelMotors.lFrontDrive.enable();
    	WheelMotors.rFrontDrive.enable();
    	WheelMotors.lRearDrive.enable();
    	WheelMotors.rRearDrive.enable();
    	
    	WheelMotors.lFrontDrive.changeControlMode(TalonControlMode.Speed);
    	WheelMotors.rFrontDrive.changeControlMode(TalonControlMode.Speed);
    	WheelMotors.lRearDrive.changeControlMode(TalonControlMode.Speed);
    	WheelMotors.rRearDrive.changeControlMode(TalonControlMode.Speed);
    	
    	WheelMotors.lFrontDrive.set(0);
    	WheelMotors.rFrontDrive.set(0);
    	WheelMotors.lRearDrive.set(0);
    	WheelMotors.rRearDrive.set(0);
    	*/
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*
    	if(Robot.oi.bButton.get()){
    	WheelMotors.lFrontDrive.set(0.5);
    	WheelMotors.rFrontDrive.set(0.5);
    	WheelMotors.lRearDrive.set(0.5);
    	WheelMotors.rRearDrive.set(0.5);
    	}else{
    		WheelMotors.lFrontDrive.set(0);
        	WheelMotors.rFrontDrive.set(0);
        	WheelMotors.lRearDrive.set(0);
        	WheelMotors.rRearDrive.set(0);
    		
    	}*/
    	Robot.driveTrain.robotDrive.tankDrive(0.5, 0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return !Robot.oi.bButton.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
