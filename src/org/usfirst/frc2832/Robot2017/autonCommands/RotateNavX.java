package org.usfirst.frc2832.Robot2017.autonCommands;

import org.usfirst.frc2832.Robot2017.DriveEncoders;
import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.subsystems.DriveTrain;
import org.usfirst.frc2832.Robot2017.subsystems.NavX;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateNavX extends Command {

	private double rot, initDeg;
	private int dispCount=0;
	
    public RotateNavX(double rot) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis)
    	this.rot = rot;
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//System.out.println("RotateNavX Start");
    	initDeg = NavX.getHeading();
    	dispCount=0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(rot > 0) {
    		DriveTrain.robotDrive.arcadeDrive(0.1, 0.6);
    		//Robot.driveTrain.setTankDriveCommand(0.5, -0.5);
    		//System.out.println(">");
    		//Robot.driveTrain.setTankDriveCommand(-0.2 * (rot - initDeg), 0.2 * (initDeg - rot));
    	} else {
    		DriveTrain.robotDrive.arcadeDrive(0.1, -0.6);
    		//Robot.driveTrain.setTankDriveCommand(-0.5, 0.5);
    		//System.out.println("<");
    		//Robot.driveTrain.setTankDriveCommand(-0.2 * (initDeg - rot), 0.2 * (initDeg - rot));
    	}
    	if (dispCount==10) { 
    		System.out.println(NavX.getHeading() + ":	" + initDeg + ":	" + rot);
    		dispCount=0;
    		
    	}
    	else dispCount++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (rot > 0) {
    		return NavX.getHeading() > normalize(initDeg + rot);
    	} else {
    		return NavX.getHeading() < normalize(initDeg + rot);
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	DriveEncoders.intializeEncoders();
    	//System.out.println("RotateNavX End");

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {

    }
    
    private double normalize(double input) {
		double normalizedValue = input;
		while (normalizedValue > 180)
			normalizedValue -= 360;
		while (normalizedValue < -180)
			normalizedValue += 360;

		return normalizedValue;
	}
}
