package org.usfirst.frc2832.Robot2017.autonCommands;

import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.subsystems.NavX;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateNavX extends Command {

	private double rot, initDeg;
	
    public RotateNavX(double rot) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis)
    	this.rot = rot;
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initDeg = NavX.getHeading();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(rot > 0) {
    		Robot.driveTrain.setTankDriveCommand(0.2, -0.2);
    		//Robot.driveTrain.setTankDriveCommand(-0.2 * (rot - initDeg), 0.2 * (initDeg - rot));
    	} else {
    		Robot.driveTrain.setTankDriveCommand(-0.2, 0.2);
    		//Robot.driveTrain.setTankDriveCommand(-0.2 * (initDeg - rot), 0.2 * (initDeg - rot));
    	}
    	System.out.println(NavX.getHeading() + ":" + initDeg);
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
