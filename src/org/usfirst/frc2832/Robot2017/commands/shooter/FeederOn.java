package org.usfirst.frc2832.Robot2017.commands.shooter;

import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FeederOn extends Command {

    public FeederOn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!Robot.isClimbing)
    		Shooter.shooterFeeder.set(20);
    	
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
