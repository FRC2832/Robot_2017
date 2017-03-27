package org.usfirst.frc2832.Robot2017.commands;

import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Climb extends Command {

	private boolean fixedSpeed = false;

    public Climb() {
        requires(Robot.climb);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.compressor.stop();
    	Robot.isClimbing = true;
    	if(Robot.lTrigger > 0.1) {
    		fixedSpeed = false;
    	} else {
    		fixedSpeed = true;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.lTrigger > 0.1) {
    		Robot.climb.setClimbMotorSpeed(1.0 * Robot.lTrigger);
    	} else {
    		Robot.climb.setClimbMotorSpeed(1.0);
    	}

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(!fixedSpeed) {
    		return Robot.lTrigger < 0.1;
    	} else {
    		return !Robot.oi.getXBoxController().getRawButton(8);
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.isClimbing = false;
    	RobotMap.compressor.start();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.isClimbing = false;
    	RobotMap.compressor.start();
    }
}
