package org.usfirst.frc2832.Robot2017.commands.gearunload;

import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.RobotMap;
import org.usfirst.frc2832.Robot2017.subsystems.GearScore;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *Extends the pusher, duh
 */
public class ExtendPusher extends Command {

    public ExtendPusher() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearScore);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!Robot.isClimbing) GearScore.gearScorePusher.set(DoubleSolenoid.Value.kForward);
    	
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
