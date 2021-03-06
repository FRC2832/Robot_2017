package org.usfirst.frc2832.Robot2017.commands;

import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DefaultClimb extends Command {

    public DefaultClimb() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.climb);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.compressor.start();
    	Robot.isClimbing = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.climb.setClimbMotorSpeed(0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
