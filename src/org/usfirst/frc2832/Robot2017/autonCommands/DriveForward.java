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
public class DriveForward extends Command {
	private double distance = 4000;
	//private double initRightEnc = 0;
	//private double initLeftEnc = 0;
	private double startTime;
    public static float maxAccel = 0;
    public static float minAccel = 0;
	private double currRightEnc;
	private double currLeftEnc;
	private double speed;
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
	
	

     public DriveForward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	}
    
     public DriveForward(double distance, double speed) {
        // Use requires() here to declare subsystem dependencies
    	this();
        this.speed = speed;
    	setDistance(distance);
    	}
     
    
	// Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("DriveForward: " + distance);
    	RobotMap.driveTrainRightFront.setPosition(0);
    	RobotMap.driveTrainLeftFront.setPosition(0);
    	startTime = Timer.getFPGATimestamp();
    	maxAccel = 0;
    	minAccel = 0;
    	//initRightEnc = DriveEncoders.getRawRightValue();
    	//initLeftEnc = DriveEncoders.getRawLeftValue();
    	//prevTime = System.currentTimeMillis();
    	//prevRightError = 0;
    	//prevLeftError = 0;
    	//SmartDashboard.putString("Auton Debugging", "DriveForwardInit");
    	//System.out.println("DriveFowardInit");
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
    	System.out.println("PID Value " + currRightControl);
    	System.out.println("Change in time " + (currTime - prevTime));
    	System.out.println("Error " + currRightError);
    	
    	prevTime = currTime;
    	prevRightError = currRightError;
    	prevLeftError = currLeftError;
    	*/
    	if (Math.abs(DriveEncoders.getRawEncDifference()) < 20 ){
    		Robot.driveTrain.setTankDriveCommand(.6 * speed, .6 * speed);
    	}
    	else if (DriveEncoders.getRawEncDifference() > 50){
    		//Robot.driveTrain.setTankDriveCommand(.7, .5);
    		Robot.driveTrain.setTankDriveCommand(.5 * speed, .6 * speed);  
    	}
    	else if (DriveEncoders.getRawEncDifference() < -20)
    	{
    		Robot.driveTrain.setTankDriveCommand(.6 * speed, .5 * speed);
    	}
    	//System.out.println(NavX.ahrs.getWorldLinearAccelY());
    	
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
    protected boolean isFinished() {
       	return (Timer.getFPGATimestamp() - startTime) > 10  || (NavX.ahrs.getWorldLinearAccelY() < -0.8 && Math.abs(RobotMap.driveTrainRightFront.getEncPosition()) > distance);
    }

    // Called once after isFinished returns true2000
    protected void end() {
    	System.out.println("DriveForward End");
    	Robot.driveTrain.setTankDriveCommand(0.0, 0.0);
    	//SmartDashboard.putString("Auton Debugging", "DriveForwardEnd");
    	//System.out.println("DriveFowardEnd" + DriveEncoders.getAbsoluteValue());

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
