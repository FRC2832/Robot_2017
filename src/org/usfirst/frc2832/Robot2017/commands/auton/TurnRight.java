package org.usfirst.frc2832.Robot2017.commands.auton;

import org.usfirst.frc2832.Robot2017.DriveEncoders;
import org.usfirst.frc2832.Robot2017.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnRight extends Command {
	public double initEnc;
	public double finalEnc;

    public TurnRight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    public TurnRight(double dist)
    {
    	finalEnc = dist;
    	//System.out.println("Constructor " + initEnc + ", " + DriveEncoders.getLeftValue() + ", " + finalEnc);
    	
    	//SmartDashboard.putString("Turn Right Command", "Constructor"); //TODO: remove this,for testing only
    }

    
    // Called just before this Command runs the first time
    protected void initialize() {
    	initEnc = DriveEncoders.getLeftValue();
    	//SmartDashboard.putString("Turn Right Command", "Initialize"); //TODO: remove this,for testing only
    	//System.out.println("Initialize: "+ initEnc + ", " + DriveEncoders.getLeftValue() + ", " + finalEnc);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.setTankDriveCommand(.75, -.75);
    	//SmartDashboard.putString("Turn Right Command", "Execute"); //TODO: remove this,for testing only
    	//System.out.println("Execute " + initEnc + ", " + DriveEncoders.getLeftValue() + ", " + finalEnc);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (finalEnc < (DriveEncoders.getLeftValue() - initEnc));
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	//System.out.println("End" + initEnc + ", " + DriveEncoders.getLeftValue() + ", " + finalEnc);
    	Robot.driveTrain.setTankDriveCommand(0, 0);
    	//SmartDashboard.putString("Turn Right Command", "End"); //TODO: remove this,for testing only
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
