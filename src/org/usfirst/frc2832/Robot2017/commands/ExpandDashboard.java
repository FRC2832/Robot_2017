package org.usfirst.frc2832.Robot2017.commands;

import org.usfirst.frc2832.Robot2017.commands.gearunload.CloseDoors;
import org.usfirst.frc2832.Robot2017.commands.gearunload.ExpelGear;
import org.usfirst.frc2832.Robot2017.commands.gearunload.ExtendPusher;
import org.usfirst.frc2832.Robot2017.commands.gearunload.OpenDoors;
import org.usfirst.frc2832.Robot2017.commands.gearunload.RetractPusher;
import org.usfirst.frc2832.Robot2017.commands.shooter.AgitatorOff;
import org.usfirst.frc2832.Robot2017.commands.shooter.AgitatorOn;
import org.usfirst.frc2832.Robot2017.commands.shooter.FeederOff;
import org.usfirst.frc2832.Robot2017.commands.shooter.FeederOn;
import org.usfirst.frc2832.Robot2017.commands.shooter.ShooterOff;
import org.usfirst.frc2832.Robot2017.commands.shooter.ShooterOn;
import org.usfirst.frc2832.Robot2017.commands.shooter.ShooterSequenceOff;
import org.usfirst.frc2832.Robot2017.commands.shooter.ShooterSequenceOn;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ExpandDashboard extends Command {

    public ExpandDashboard() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	    // SmartDashboard Buttons
    	    SmartDashboard.putData("Autonomous Command", new DriveForward(4.0));
    	    SmartDashboard.putData("RelayOn", new RelayOn());
    	    SmartDashboard.putData("AllForward", new AllForward());
    	    SmartDashboard.putData("MotorPositionCheck", new MotorPositionCheck());
    	    SmartDashboard.putData("Shooter sequence on", new ShooterSequenceOn());
    	    SmartDashboard.putData("Shooter sequence off", new ShooterSequenceOff());
    	    SmartDashboard.putData("Agitator on", new AgitatorOn());
    	    SmartDashboard.putData("Agitator off", new AgitatorOff());
    	    SmartDashboard.putData("Feeder on", new FeederOn());
    	    SmartDashboard.putData("Feeder off", new FeederOff());
    	    SmartDashboard.putData("Shooter on", new ShooterOn());
    	    SmartDashboard.putData("Shooter off", new ShooterOff());
    	    SmartDashboard.putData("Expell gear", new ExpelGear());
    	    SmartDashboard.putData("Open door", new OpenDoors());
    	    SmartDashboard.putData("Close door", new CloseDoors());
    	    SmartDashboard.putData("Extend pusher", new ExtendPusher());
    	    SmartDashboard.putData("Retract pusher", new RetractPusher());
    	}
    //   SmartDashboard.putData("Climb", new Climb());
    

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
