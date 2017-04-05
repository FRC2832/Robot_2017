package org.usfirst.frc2832.Robot2017.autonCommands;

import org.usfirst.frc2832.Robot2017.DriveEncoders;
import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.RobotMap;
import org.usfirst.frc2832.Robot2017.subsystems.DriveTrain;
import org.usfirst.frc2832.Robot2017.subsystems.NavX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionRotate extends Command {
	private double initEncoderVal =0;
	private double distance;
	private byte[] buffer;
	//private int pixyImage;
	private double startTime;
	private double timeOut;
	private byte[] sendBuffer = "draco".getBytes();

	
    public VisionRotate() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
     	requires (Robot.driveTrain);
     	timeOut = 2;
     	
    }

    public VisionRotate(double distance, int timeout) {
        // Use requires() here to declare subsystem dependencies
        
    	this();
    	setDistance(distance);
    	timeOut = timeout;
    	
    	
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
    	double power = 0;
    	
    /*	if (Robot.pixyCamera.transaction(sendBuffer, sendBuffer.length, buffer, 1))
    	{
    		pixyValue = buffer[0]  & 0xFF;
    	} */
    	
    	SmartDashboard.putNumber("pixyXAutonAimGear", Robot.pixyValue);
    	if (Robot.pixyValue == 255) {
    		SmartDashboard.putString("PixyImage", "no image");
        	Robot.driveTrain.setTankDriveCommand(power, power);
    	} else {
    		final double turnP = 0.6;
    		double turn = (127.0 - ((double)Robot.pixyValue)) / 127;
    		
    		System.out.println("VR-Pixy: " + Robot.pixyValue + " turn: " + turn);
    		Robot.driveTrain.setTankDriveCommand(power + (turn * turnP), power - (turn * turnP));
    	}	
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		System.out.println("VR-Pixy-end: " + Robot.pixyValue + "diff: " + Math.abs(127-Robot.pixyValue) );
    	return Math.abs(127-Robot.pixyValue) < 2 || (Timer.getFPGATimestamp() - startTime > timeOut);
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
