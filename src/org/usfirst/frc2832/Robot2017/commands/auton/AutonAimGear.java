package org.usfirst.frc2832.Robot2017.commands.auton;

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
	//private int pixyImage;
	private double startTime;
	private double endTime;
	private byte[] sendBuffer = "draco".getBytes();

	

    public AutonAimGear() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
     	requires (Robot.driveTrain);
     	endTime = 7;
     	
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
    	startTime = Timer.getFPGATimestamp();
		Robot.pixyValue = (byte) 300;
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
    	
    	Robot.driveTrain.setTankDriveCommand(.6, .6);
    /*	if (Robot.pixyCamera.transaction(sendBuffer, sendBuffer.length, buffer, 1))
    	{
    		pixyValue = buffer[0]  & 0xFF;
    	} */
    	SmartDashboard.putNumber("pixyXAutonAimGear", Robot.pixyValue);
    	if ((int) Robot.pixyValue > 128 && (int) Robot.pixyValue != 255) //132
    			{
    		Robot.driveTrain.setTankDriveCommand(.3, .6);
    		SmartDashboard.putString("PixyImage", "turning right");
    	}
    	else if ((int) Robot.pixyValue < 126){
			Robot.driveTrain.setTankDriveCommand(.6, .3);
			SmartDashboard.putString("PixyImage", "turning left");//123
    	}
    	else if (Robot.pixyValue == 255)
    		SmartDashboard.putString("PixyImage", "no image");
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Timer.getFPGATimestamp() - startTime) > endTime; //|| (NavX.ahrs.getWorldLinearAccelY() < -1); //-0.8);
    
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
