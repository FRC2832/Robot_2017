package org.usfirst.frc2832.Robot2017.commands;

import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc2832.Robot2017.subsystems.DriveTrain;
import org.usfirst.frc2832.Robot2017.subsystems.NavX;

/**
 *
 */
public class Rotate extends Command {
	public static double angle = 0;

    public Rotate() {
    	requires(Robot.navX);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	NavX.turnController.enable();
    	NavX.turnController.setSetpoint(90);
    	//DriveTrain.robotDrive.mecanumDrive_Cartesian(0, 0, NavX.rotateToAngleRate, NavX.ahrs.getAngle());
    	//DriveTrain.robotDrive.arcadeDrive(NavX.rotateToAngleRate, 0, NavX.ahrs.getAngle(), 0);
    	//The above code is for rotating with NavX.  Haven't figured out how to redo mecanum code for arcadedrive yet

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
