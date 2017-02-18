package org.usfirst.frc2832.Robot2017.commands.ingest;

import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.subsystems.BallIntake;
import org.usfirst.frc2832.Robot2017.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IngestToggle extends Command {

    public IngestToggle() {
    	requires(Robot.ballIntake);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.isIngesting = !Robot.isIngesting;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
