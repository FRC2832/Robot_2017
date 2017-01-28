package org.usfirst.frc2832.Robot2017.autonCommands;

import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

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

    // Called just before this Command runs the first time
    protected void initialize() {
    	initEncoderVal = RobotMap.driveTrainRightFront.getEncPosition();
    			
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.robotDrive.arcadeDrive(0.5, 0.5);
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
      return (RobotMap.driveTrainLeftFront.getEncPosition() - initEncoderVal > Math.abs(dist));
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
