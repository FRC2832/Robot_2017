package org.usfirst.frc2832.Robot2017.autonCommands;

import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.DriveEncoders;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveForward extends Command {
	private double distance= 2;//to cross green line distnace in meters is approximately 2.41097 so set it to 2.5 meters
	private double initEncoderVal =0;
	private double startTime;
	

     public DriveForward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	}
    
    
     public DriveForward(double distance) {
        // Use requires() here to declare subsystem dependencies
        
    	this();
    	setDistance(distance);
    	
    	}
     
     

    public double getDistance() {
		return distance;
	}


	public void setDistance(double straightDistance) {
		this.distance = straightDistance;
	}


	public double getInitEncoderVal() {
		return initEncoderVal;
	}


	public void setInitEncoderVal(double initEncoderVal) {
		this.initEncoderVal = initEncoderVal;
	}


	// Called just before this Command runs the first time
    protected void initialize() {
    	startTime = Timer.getFPGATimestamp();
    	setInitEncoderVal(DriveEncoders.getAbsoluteValue());
    	//SmartDashboard.putString("Auton Debugging", "DriveForwardInit");
    	//System.out.println("DriveFowardInit");
    	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
    	Robot.driveTrain.setArcadeDriveCommand(0.5, 0.0);
    	//SmartDashboard.putNumber("distanceAuton", DriveEncoders.getAbsoluteValue());
    	//SmartDashboard.putNumber("auton stop", (getDistance()) * (DriveEncoders.getAbsoluteValue() - getInitEncoderVal());
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.signum(getDistance()) * (DriveEncoders.getAbsoluteValue() - getInitEncoderVal()) > Math.abs(getDistance()) || (Timer.getFPGATimestamp() - startTime) > 5;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.setArcadeDriveCommand(0.0, 0.0);
    	//SmartDashboard.putString("Auton Debugging", "DriveForwardEnd");
    	//System.out.println("DriveFowardEnd" + DriveEncoders.getAbsoluteValue());

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
