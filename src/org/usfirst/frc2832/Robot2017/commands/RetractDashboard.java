package org.usfirst.frc2832.Robot2017.commands;



import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RetractDashboard extends Command {

    public RetractDashboard() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	    // SmartDashboard Buttons
	    SmartDashboard.getData("Autonomous Command");
	    SmartDashboard.getData("RelayOn");
	    SmartDashboard.getData("AllForward");
	    SmartDashboard.getData("MotorPositionCheck");
	    SmartDashboard.getData("Shooter sequence on");
	    SmartDashboard.getData("Shooter sequence off") ;
	    SmartDashboard.getData("Agitator on");
	    SmartDashboard.getData("Agitator off");
	    SmartDashboard.getData("Feeder on");
	    SmartDashboard.getData("Feeder off");
	    SmartDashboard.getData("Shooter on");
	    SmartDashboard.getData("Shooter off");
	    SmartDashboard.getData("Expell gear");
	    SmartDashboard.getData("Open door");
	    SmartDashboard.getData("Close door");
	    SmartDashboard.getData("Extend pusher");
	    SmartDashboard.getData("Retract pusher");

	}
//   SmartDashboard.getData("Climb", new Climb());
    

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
