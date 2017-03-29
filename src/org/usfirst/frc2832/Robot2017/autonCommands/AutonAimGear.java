package org.usfirst.frc2832.Robot2017.autonCommands;

import org.usfirst.frc2832.Robot2017.DriveEncoders;
import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.subsystems.NavX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonAimGear extends Command {
	private double initEncoderVal =0;
	private double distance;
	private byte[] buffer;
	private int pixyImage;
	private double startTime;
	private double endTime;
	private byte[] sendBuffer = "draco".getBytes();

	

    public AutonAimGear() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
     	requires (Robot.driveTrain);
     	endTime = 5;
     	
    }

    public AutonAimGear(double distance, int timeout) {
        // Use requires() here to declare subsystem dependencies
        
    	this();
    	setDistance(distance);
    	endTime = timeout;
    	
    	
    	}
    // Called just before this Command runs the first time
    protected void initialize() {
    	setInitEncoderVal(DriveEncoders.getAbsoluteValue());
    	buffer = new byte[1];
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
		startTime = Timer.getFPGATimestamp();
		pixyImage =  255;
	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//figure out how close is "close enough" because it's unlikely we'll ever get to 2.5 on the dot.  Figure this out through testing
    	
    	Robot.driveTrain.setTankDriveCommand(.5, .5);
    	if (Robot.pixyCamera.transaction(sendBuffer, sendBuffer.length, buffer, 1))
    	{
    		pixyImage = buffer[0]  & 0xFF;
    	}
    	
    	if ((int) pixyImage > 127 && (int) pixyImage != 255)
    			{
    		Robot.driveTrain.setTankDriveCommand(.5, .25);
    		SmartDashboard.putString("PixyImage", "turning right");
    	}
    	else if ((int) pixyImage < 127){
			Robot.driveTrain.setTankDriveCommand(.25, .5);
			SmartDashboard.putString("PixyImage", "turning left");
    	}
    	else if (pixyImage == 255)
    		SmartDashboard.putString("PixyImage", "no image");
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Timer.getFPGATimestamp() - startTime) > endTime  || (NavX.ahrs.getWorldLinearAccelY() < -0.8);
    
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
