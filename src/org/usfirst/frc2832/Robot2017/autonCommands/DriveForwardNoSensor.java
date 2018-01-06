package org.usfirst.frc2832.Robot2017.autonCommands;

import org.usfirst.frc2832.Robot2017.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwardNoSensor extends Command {
	private double startTime;
	private double timeout = 2.7;
	
    public DriveForwardNoSensor() {
    	requires(Robot.driveTrain);
    	timeout = 2.7;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.setTankDriveCommand(0.6, 0.56);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Timer.getFPGATimestamp() - startTime > timeout) {
			return true;
    	}
    	else {
    		return false;
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
