package org.usfirst.frc2832.Robot2017.autonCommands;

import org.usfirst.frc2832.Robot2017.DriveEncoders;
import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwardDist extends Command {

	private double diameter, initLeft, initRight, left, right, dist, curDist, startTime, timeOut;
	
    public DriveForwardDist(double diameter, double dist, double timeOut) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	this.diameter = diameter;
    	this.dist = dist;
    	this.timeOut = timeOut;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveEncoders.intializeEncoders();
    	RobotMap.driveTrainRightFront.setPosition(0);
    	RobotMap.driveTrainLeftFront.setPosition(0);
    	initLeft = RobotMap.driveTrainLeftFront.getEncPosition();
    	initRight = RobotMap.driveTrainRightFront.getEncPosition();
    	startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println(DriveEncoders.getRawEncDifference());
    	if (Math.abs(DriveEncoders.getRawEncDifference()) < 51 ){
    		Robot.driveTrain.setTankDriveCommand(.5, .5);
    	}
    	else if (DriveEncoders.getRawEncDifference() > 50){
    		//Robot.driveTrain.setTankDriveCommand(.7, .5);
    		Robot.driveTrain.setTankDriveCommand(.25, .5); //* (currRightEnc / currLeftEnc));
    	}
    	else if (DriveEncoders.getRawEncDifference() < -50)
    	{
    		Robot.driveTrain.setTankDriveCommand(.5, .25);
    	}
    	left = Math.abs(RobotMap.driveTrainLeftFront.getEncPosition()) - initLeft;
    	right = Math.abs(RobotMap.driveTrainRightFront.getEncPosition()) - initRight;
    	curDist = (left + right) / 2 / 1440 * Math.PI * diameter;
    	System.out.println();
    	System.out.println(curDist + ":" + dist);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return curDist > dist || Timer.getFPGATimestamp() - startTime > timeOut;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.driveTrainRightFront.setPosition(0);
    	RobotMap.driveTrainLeftFront.setPosition(0);
    	Robot.driveTrain.setTankDriveCommand(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
