package org.usfirst.frc2832.Robot2017.autonCommands;

import org.usfirst.frc2832.Robot2017.DriveEncoders;
import org.usfirst.frc2832.Robot2017.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonAimGear extends Command {
	private double initEncoderVal =0;
	private double distance;
	

    public AutonAimGear() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
     	requires (Robot.driveTrain);
   
    }

    public AutonAimGear(double distance) {
        // Use requires() here to declare subsystem dependencies
        
    	this();
    	setDistance(distance);
    	
    	}
    // Called just before this Command runs the first time
    protected void initialize() {
    	setInitEncoderVal(DriveEncoders.getAbsoluteValue());
    	
    }
    
	public void setDistance(double straightDistance) {
		this.distance = straightDistance;
	}

	 public double getDistance() {
			return distance;
		}
	
	
	public double getInitEncoderVal() {
		return initEncoderVal;
	}


	public void setInitEncoderVal(double initEncoderVal) {
		this.initEncoderVal = initEncoderVal;
	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//figure out how close is "close enough" because it's unlikely we'll ever get to 2.5 on the dot.  Figure this out through testing
    	
    	Robot.driveTrain.setTankDriveCommand(.5, .5);
    	if (Robot.pixyInput.getAverageVoltage() > 1.1 && Robot.pixyInput.getAverageVoltage() < 2.1){
    		Robot.driveTrain.setTankDriveCommand(.5, .25);
    		SmartDashboard.putString("PixyImage", "turning right");
    	}
    	else if (Robot.pixyInput.getAverageVoltage() < 0.9){
			Robot.driveTrain.setTankDriveCommand(.25, .5);
			SmartDashboard.putString("PixyImage", "turning left");
    	}
    	else if (Robot.pixyInput.getAverageVoltage() > 2.1)
    		SmartDashboard.putString("PixyImage", "no image");
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.pixyWidth.getAverageVoltage() > 2);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.setTankDriveCommand(0, 0);
    	
    }
    

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
