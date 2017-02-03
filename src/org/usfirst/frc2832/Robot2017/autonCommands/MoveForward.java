package org.usfirst.frc2832.Robot2017.autonCommands;

import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.RobotMap;
import org.usfirst.frc2832.Robot2017.DriveEncoders;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class MoveForward extends Command {
	double initEncoderVal;
	double dist = 1000; 
    public MoveForward() {
    	requires(Robot.driveTrain);
    
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    public MoveForward(double distance)
    {
    	required(Robot.driveTrain);
    	dist = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//initEncoderVal = RobotMap.driveTrainRightFront.getEncPosition();
    	
    	initEncoderVal = (RobotMap.driveTrainLeftFront.getEncPosition()+RobotMap.driveTrainRightFront.getEncPosition())/2;
    			
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.robotDrive.arcadeDrive(0.5, 0);
    	//SmartDashboard.putDouble("Change in encoder values", ((RobotMap.driveTrainLeftFront.getEncPosition()+RobotMap.driveTrainRightFront.getEncPosition())/2 - initEncoderVal));
    	
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
      //return (RobotMap.driveTrainLeftFront.getEncPosition() - initEncoderVal > Math.abs(dist));
    	System.out.println("**********************"+DriveEncoders.getAbsoluteValue());
    	return (Math.abs((RobotMap.driveTrainLeftFront.getEncPosition()+RobotMap.driveTrainRightFront.getEncPosition())/2 - initEncoderVal)) > Math.abs(dist);
    }

    
    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.robotDrive.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
