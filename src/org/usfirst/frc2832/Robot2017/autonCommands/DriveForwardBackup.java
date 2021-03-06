package org.usfirst.frc2832.Robot2017.autonCommands;

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
public class DriveForwardBackup extends Command {
	private double distance = 4000;
	//private double initRightEnc = 0;
	//private double initLeftEnc = 0;
	private double startTime;
    public static float maxAccel = 0;
    public static float minAccel = 0;
	private double currRightEnc;
	private double currLeftEnc;
	private double speed;
	private double diffCounts;
	private double stopLevel = -1.0; // Acceleration backwards to stop
	private double power = 0.5;
/*private double prevRightError;
	private double prevLeftError;
	private double currRightError;
	private double currLeftError;
	private double iTerm = 0;
	private double pTerm = 1;
	private double currTime;
	private double prevTime;
	private double currRightControl;
	private double prevLeftControl;
	private double currLeftControl;
	private double prevRightControl;
	private double rightDeltaT;
	private double leftDeltaT; */
	
	

     public DriveForwardBackup() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	}
    
     public DriveForwardBackup(double distance, double speed) {
        // Use requires() here to declare subsystem dependencies
    	this();
        this.speed = speed;
    	setDistance(distance);
    	}
     
    
	// Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.driveTrainRightFront.setPosition(0);
    	RobotMap.driveTrainLeftFront.setPosition(0);
		RobotMap.driveTrainLeftFront.setEncPosition(0);
		RobotMap.driveTrainRightFront.setEncPosition(0);
    	startTime = Timer.getFPGATimestamp();
    	maxAccel = 0;
    	minAccel = 0;
    	//initRightEnc = DriveEncoders.getRawRightValue();
    	//initLeftEnc = DriveEncoders.getRawLeftValue();
    	//prevTime = System.currentTimeMillis();
    	//prevRightError = 0;
    	//prevLeftError = 0;
    	//SmartDashboard.putString("Auton Debugging", "DriveForwardInit");
    	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
    	//currTime = System.currentTimeMillis();
    	currRightEnc = DriveEncoders.getRawRightValue();
    	currLeftEnc = DriveEncoders.getRawLeftValue();

    	/*currRightError = currRightEnc - initRightEnc;
    	currLeftError = currLeftEnc - initLeftEnc;
    	
    	rightDeltaT = iTerm * (currTime - prevTime) * currRightError + (pTerm * (currRightError - prevRightError))/(currTime - prevTime);
    	currRightControl = prevRightControl + rightDeltaT;
    	
    	leftDeltaT = iTerm * (currTime - prevTime) * currLeftError + (pTerm * (currLeftError - prevLeftError))/(currTime - prevTime);
    	currLeftControl = prevLeftControl + leftDeltaT;

    	
    	prevTime = currTime;
    	prevRightError = currRightError;
    	prevLeftError = currLeftError;
    	*/
    	
    	//diffCounts = DriveEncoders.getRawEncDifference();
    	
    	if (Math.abs(DriveEncoders.getRawEncDifference()) < 51 ){
    		Robot.driveTrain.setTankDriveCommand(power, power);
    		//System.out.println("Good");
    	}
    	else if (DriveEncoders.getRawEncDifference() > 50){
    		//Robot.driveTrain.setTankDriveCommand(.7, .5);
    		Robot.driveTrain.setTankDriveCommand(power/2.0, power); //* (currRightEnc / currLeftEnc));
    		//System.out.println("Left");
    	}
    	else if (DriveEncoders.getRawEncDifference() < -50) {
    		Robot.driveTrain.setTankDriveCommand(power, power/2.0);
    		//System.out.println("Right");
    	}

    	if (minAccel > NavX.ahrs.getWorldLinearAccelY())
        	minAccel = NavX.ahrs.getWorldLinearAccelY();
        if (maxAccel < NavX.ahrs.getWorldLinearAccelY())
        	maxAccel = NavX.ahrs.getWorldLinearAccelY();
        SmartDashboard.putNumber("Max Accelerometer", maxAccel);
        SmartDashboard.putNumber("Min Accelerometer", minAccel);

    	//SmartDashboard.putNumber("distanceAuton", DriveEncoders.getAbsoluteValue());
    	//SmartDashboard.putNumber("auton stop", (getDistance()) * (DriveEncoders.getAbsoluteValue() - getInitEncoderVal());
    }
    
    // Make this return true when this Command no longer needs to run execute()
    // Require minimum distance before trigger on accelerometer 
    protected boolean isFinished() {
    	if (Timer.getFPGATimestamp() - startTime > 5) {
    		System.out.println("End for time");
			return true;
    	}
		else if (NavX.ahrs.getWorldLinearAccelY() < stopLevel && Math.abs(RobotMap.driveTrainRightFront.getEncPosition()) > distance) {
			System.out.println("end for Accel and Dist: Cur: " + minAccel + " " + RobotMap.driveTrainRightFront.getEncPosition() + " Dist: " + distance);
			return true;
		
		}
			else 
				return false;
    }    		  

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
