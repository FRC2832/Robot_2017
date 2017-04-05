package org.usfirst.frc2832.Robot2017.autonCommands;

import org.usfirst.frc2832.Robot2017.DriveEncoders;
import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/*
 *  New class, started from DriveForwardDist, with ramp up and ramp down
 *  Created with Pat from Lightning
 *  4/4/2017  Created.
 */
public class DriveDistance extends Command {

	private double diameter, initLeft, initRight, left, right, dist, curDist, startTime, timeOut;

    public DriveDistance(double diameter, double dist, double timeOut) {
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
    	//System.out.println("CURENT DISTANCE----------------->" + curDist);
    	/*if(curDist > dist){ 
    		dist +=curDist;
    	}*/
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//System.out.println("DriveDistance Init: " + dist);
    	DriveEncoders.intializeEncoders();
    	
    	startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double power = 0.5;
    	double elapsed = Timer.getFPGATimestamp() - startTime;
    	final double rampTime = 1.0;  // seconds
    	
    	final double rampDownDist = 500; // mm
    	final double minPower = 0.3;
    	
    	left = Math.abs(DriveEncoders.getRawLeftValue()) - initLeft;
    	right = Math.abs(DriveEncoders.getRawRightValue()) - initRight;
    	
    	curDist = (left + right) / 2 / 1440 * Math.PI * diameter;
    	
		// Adjust power incrementally to avoid abruptness.
	
    	if ((curDist + rampDownDist) > dist) {
    		power *= (dist - curDist) / rampDownDist;
    	}
    	
		// shouldn't this be an else if?
    	if (elapsed < rampTime) {
    		power = power * (elapsed / rampTime);
    	}
    	
    	if (Math.abs(power) < minPower) {
    		power = minPower;
    	}
    	
		// TODO: these threshholds are different than in DriveForward
    	if (Math.abs(DriveEncoders.getRawEncDifference()) < 51 ){
    		Robot.driveTrain.setTankDriveCommand(power, power);
    	}
    	else if (DriveEncoders.getRawEncDifference() > 50){
    		//Robot.driveTrain.setTankDriveCommand(.7, .5);
    		Robot.driveTrain.setTankDriveCommand(power / 2, power); //* (currRightEnc / currLeftEnc));
    	}
    	else if (DriveEncoders.getRawEncDifference() < -50)
    	{
    		Robot.driveTrain.setTankDriveCommand(power, power / 2);
    	}
    	if (dispCount == 10) {
    			System.out.println(curDist + "------ " + dist + "------" + initLeft + ":" + initRight);
    			dispCount=0;
    	}
    	else
    			dispCount++;
    				
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(curDist > dist)
    		System.out.println(curDist + "------ " + dist + "------" + initLeft + ":" + initRight);
        return curDist >= dist || Timer.getFPGATimestamp() - startTime > timeOut;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//RobotMap.driveTrainRightFront.setPosition(0);
    	//RobotMap.driveTrainLeftFront.setPosition(0);
    	Robot.driveTrain.setTankDriveCommand(0, 0);
    	//System.out.println("DriveDistance End");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//System.out.println("DriveDistance Interrupt");

    }
}
