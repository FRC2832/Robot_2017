package org.usfirst.frc2832.Robot2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *Waits a set amount of milliseconds, not centiseconds. 
 *To be used in command groups. Second parameter should be the respective subsystem.
 */
public class WaitUntilTime extends Command {

    private long length;

	public WaitUntilTime(long length, Subsystem sub) {
    	this.length = length;
    	requires(sub);
    }

    private static long initialTime;
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	initialTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return System.currentTimeMillis() > initialTime + length;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
