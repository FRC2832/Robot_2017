package org.usfirst.frc2832.Robot2017.commands.auton;

import org.usfirst.frc2832.Robot2017.DriveEncoders;
import org.usfirst.frc2832.Robot2017.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnLeft extends Command {
	public double initEnc;
	public double finalEnc;

    public TurnLeft() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    public TurnLeft(double dist)
    {
    	finalEnc = -dist;
    	//System.out.println("Constructor " + initEnc + ", " + DriveEncoders.getRightValue() + ", " + finalEnc);
    	
    	//SmartDashboard.putString("Turn Left Command", "Constructor"); //TODO: remove this,for testing only
    }

    
    // Called just before this Command runs the first time
    protected void initialize() {
    	initEnc = DriveEncoders.getRightValue();
    	//SmartDashboard.putString("Turn Left Command", "Initialize"); //TODO: remove this,for testing only
    	//System.out.println("Initialize: "+ initEnc + ", " + DriveEncoders.getRightValue() + ", " + finalEnc);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.setTankDriveCommand(-.5, .5);
    	//SmartDashboard.putString("Turn Left Command", "Execute"); //TODO: remove this,for testing only
    	//System.out.println("Execute " + initEnc + ", " + DriveEncoders.getRightValue() + ", " + finalEnc);
    	//System.out.println("Execute: " + finalEnc + " < " + (DriveEncoders.getRightValue() - initEnc) + " is " + (finalEnc < (DriveEncoders.getRightValue() - initEnc)));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (finalEnc > (DriveEncoders.getRightValue() - initEnc));
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	//System.out.println("End" + initEnc + ", " + DriveEncoders.getRightValue() + ", " + finalEnc);
    	Robot.driveTrain.setTankDriveCommand(0, 0);
    	//SmartDashboard.putString("Turn Left Command", "End"); //TODO: remove this,for testing only
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
