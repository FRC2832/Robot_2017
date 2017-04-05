package org.usfirst.frc2832.Robot2017.autonCommands;

import org.usfirst.frc2832.Robot2017.DriveEncoders;
import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.RobotMap;
//import org.usfirst.frc2832.Robot2017.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * Created 4/4/2017 with Pat from Lightning.  Make direction adjustment smoother
 */
public class VisionAimGear extends Command {
	private double initEncoderVal =0;
	private double distance;
	private byte[] buffer;
	private double startTime;
	private double endTime;
	private byte[] sendBuffer = "draco".getBytes();

	    public VisionAimGear() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
     	requires (Robot.driveTrain);
     	endTime = 7;
     	
    }

    public VisionAimGear(double distance, int timeout) {
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
    	double power = 0.5;
    	int dispCount = 0;
    	
    /*	if (Robot.pixyCamera.transaction(sendBuffer, sendBuffer.length, buffer, 1))
    	{
    		pixyValue = buffer[0]  & 0xFF;
    	} */
    	
    	SmartDashboard.putNumber("pixyXVisionAimGear", Robot.pixyValue);
    	if (Robot.pixyValue == 255) {
    		SmartDashboard.putString("PixyImage", "no image");
        	Robot.driveTrain.setTankDriveCommand(power, power);
    	} else {
    		final double turnP = 0.15;
    		double turn = (127.0 - ((double)Robot.pixyValue)) / 127;
    		
    		Robot.driveTrain.setTankDriveCommand(power + (turn * turnP), power - (turn * turnP));

    		if (dispCount == 10) {	
    			System.out.println("Pixy: " + Robot.pixyValue + " turn: " + turn);
    			dispCount = 0;
    		}
    		else {
    			dispCount++;
    		
    		}
    	}

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        double wheelSpeedR;
        double wheelSpeedL;
        double elapsedTime;
        wheelSpeedL = RobotMap.driveTrainLeftFront.getSpeed();
        wheelSpeedR = RobotMap.driveTrainRightFront.getSpeed();
        elapsedTime = Timer.getFPGATimestamp() - startTime;
    	return (elapsedTime > 1 && wheelSpeedL == 0 && wheelSpeedR == 0) || (elapsedTime > endTime); //|| (NavX.ahrs.getWorldLinearAccelY() < -1); //-0.8);
        
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
