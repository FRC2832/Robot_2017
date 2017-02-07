package org.usfirst.frc2832.Robot2017.commands;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class WaitUntilSpeed extends Command {
	
	public static int speed;
	public static CANTalon cantalon;

    public WaitUntilSpeed(CANTalon cantalon, int speed, Subsystem sub) {
    	this.speed = speed;
    	this.cantalon = cantalon;
    	requires(sub);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(cantalon.getSpeed() + ":" + speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return cantalon.getSpeed() > speed;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
