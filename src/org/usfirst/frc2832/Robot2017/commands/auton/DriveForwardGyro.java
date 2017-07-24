package org.usfirst.frc2832.Robot2017.commands.auton;

import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.RobotMap;
import org.usfirst.frc2832.Robot2017.subsystems.NavX;
import org.usfirst.frc2832.Robot2017.DriveEncoders;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
//	// For getRawEncDifference: 0: no difference.  Positive: left > right.  Negative: Right>left
public class DriveForwardGyro extends Command {
	private double distance = 4000;
	//private double initRightEnc = 0;
	//private double initLeftEnc = 0;
	private double startTime;
    public static float maxAccel = 0;
    public static float minAccel = 0;
	private double speed;
	private double initDeg;
	//private double diffCounts;
	private double stopLevel = -1.0; // Acceleration backwards to stop
	private double timeout;

     public DriveForwardGyro() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	}
    
     public DriveForwardGyro(double distance, double speed) {
        // Use requires() here to declare subsystem dependencies
    	this();
        this.speed = speed;
    	setDistance(distance);
    	timeout = 7;
    	}
     public DriveForwardGyro(double distance, double speed, double time) {
         // Use requires() here to declare subsystem dependencies
     	this();
         this.speed = speed;
     	setDistance(distance);
     	timeout = time;
     	}
     
    
	// Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.driveTrainRightFront.setPosition(0);
		RobotMap.driveTrainRightFront.setEncPosition(0);
    	startTime = Timer.getFPGATimestamp();
    	maxAccel = 0;
    	minAccel = 0;
    	initDeg = NavX.getYaw();
    	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
    	//compensate based on gyro measurement, 0.04 is 4% correction per degree error 
    	double turn = (NavX.getYaw() - initDeg) * 0.02;
    	
    	/*
    	diffCounts = DriveEncoders.getRawEncDifference();
    	if (Math.abs(diffCounts) < 20 ){
    		Robot.driveTrain.setTankDriveCommand(.6 * speed, .6 * speed);
    	}
    	else if (diffCounts > 50){
    		//Robot.driveTrain.setTankDriveCommand(.7, .5);
    		Robot.driveTrain.setTankDriveCommand(.5 * speed, .6 * speed);  
    	}
    	else if (diffCounts < -20)
    	{
    		Robot.driveTrain.setTankDriveCommand(.6 * speed, .5 * speed);
    	}
*/
    	Robot.driveTrain.setArcadeDriveCommand(0.6, -turn);
    	
    	if (minAccel > NavX.ahrs.getWorldLinearAccelY())
        	minAccel = NavX.ahrs.getWorldLinearAccelY();
        if (maxAccel < NavX.ahrs.getWorldLinearAccelY())
        	maxAccel = NavX.ahrs.getWorldLinearAccelY();
        SmartDashboard.putNumber("Max Accelerometer", maxAccel);
        SmartDashboard.putNumber("Min Accelerometer", minAccel);
    }
    
    // Make this return true when this Command no longer needs to run execute()
    // Require minimum distance before trigger on accelerometer 
    protected boolean isFinished() {
    	if (Timer.getFPGATimestamp() - startTime > timeout) {
    		System.out.println("End for time");
			return true;
    	}
			else if (NavX.ahrs.getWorldLinearAccelY() < stopLevel && Math.abs(RobotMap.driveTrainRightFront.getEncPosition()) > distance) {
			System.out.println("end for Accel and Dist: Cur:" + RobotMap.driveTrainRightFront.getEncPosition() + " Dist: " + distance);
			return true;
		
		}
			else 
				return false;
    }    		
    	/*			
       	return (Timer.getFPGATimestamp() - startTime) > 5 || 
       			(NavX.ahrs.getWorldLinearAccelY() < -0.8 && 
       					Math.abs(RobotMap.driveTrainRightFront.getEncPosition()) > distance);
    	 */ 
    

    // Called once after isFinished returns true2000
    protected void end() {
    	Robot.driveTrain.setTankDriveCommand(0.0, 0.0);
    	//SmartDashboard.putString("Auton Debugging", "DriveForwardEnd");

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    

    
    
    public double getDistance() {
		return distance;
	}


	public void setDistance(double straightDistance) {
		this.distance = straightDistance;
	}


/*	public double getInitEncoderVal() {
		return initEncoderVal;
	}


	public void setInitEncoderVal(double initEncoderVal) {
		this.initEncoderVal = initEncoderVal;
	}
*/

}
