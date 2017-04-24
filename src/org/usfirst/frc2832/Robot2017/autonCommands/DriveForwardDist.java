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
	int dispCount = 0;

    public DriveForwardDist(double diameter, double dist, double timeOut) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	DriveEncoders.intializeEncoders();
    	this.diameter = diameter;
    	this.dist = dist;
    	this.timeOut = timeOut;
    	initLeft = DriveEncoders.getRawLeftValue();
    	initRight = DriveEncoders.getRawRightValue();
    	dispCount = 0;
    	
    	//curDist = (left + right) / 2 / 1440 * Math.PI * diameter;
    	/*if(curDist > dist){ 
    		dist +=curDist;
    	}*/
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveEncoders.intializeEncoders();
    	
    	startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Math.abs(DriveEncoders.getRawEncDifference()) < 51 ){
    		Robot.driveTrain.setTankDriveCommand(.6, .6);
    	}
    	else if (DriveEncoders.getRawEncDifference() > 50){
    		//Robot.driveTrain.setTankDriveCommand(.7, .5);
    		Robot.driveTrain.setTankDriveCommand(.3, .6); //* (currRightEnc / currLeftEnc));
    	}
    	else if (DriveEncoders.getRawEncDifference() < -50)
    	{
    		Robot.driveTrain.setTankDriveCommand(.6, .3);
    	}
    	left = Math.abs(DriveEncoders.getRawLeftValue()) - initLeft;
    	right = Math.abs(DriveEncoders.getRawRightValue()) - initRight;
    	
    	curDist = (left + right) / 2 / 1440 * Math.PI * diameter;
    	if (dispCount == 10) {
    			dispCount=0;
    	}
    	else
    			dispCount++;
    				
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(curDist > dist)
    		System.out.println(curDist + "------ " + dist + "------" + initLeft + ":" + initRight);
        return curDist > dist || Timer.getFPGATimestamp() - startTime > timeOut;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//RobotMap.driveTrainRightFront.setPosition(0);
    	//RobotMap.driveTrainLeftFront.setPosition(0);
    	Robot.driveTrain.setTankDriveCommand(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {

    }
}
