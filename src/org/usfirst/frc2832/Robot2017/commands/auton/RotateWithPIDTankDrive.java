package org.usfirst.frc2832.Robot2017.commands.auton;

import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.NavX;

//import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
//import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Author: Jing Xu
 * Email: jeanxu2000@gmail.com
 * Date: March 4, 2017
 * Created for : NavX Rotate
 * 3/20? pzobel - when over-rotates it doesn't have way to correct
 * 			how can we get rate to start fast enough and slow down enough?
 * 3/28  pzobel/zack - retuned PID using method from FRC 1114. 
 */
public class RotateWithPIDTankDrive extends Command implements PIDOutput{
	
    private PIDController turnController2;
    private double targetAngleDegrees;
    private double rotateToAngleRate;    
    
    private static final double kP = 0.1;
    private static final double kI = 0.00;
    private static final double kD = 0.15;
    private static final double kF = 0.00;
    private int counter = 0;
    
    static final double kToleranceDegrees = 2.0f;       
    
    public RotateWithPIDTankDrive(double targetAngleDegrees) {
      requires(Robot.driveTrain);
      this.targetAngleDegrees=targetAngleDegrees;
      turnController2 = new PIDController(kP, kI, kD, kF, NavX.ahrs, this);
      turnController2.setInputRange(-180.0f,  180.0f);
      turnController2.setOutputRange(-0.7, 0.7);
      turnController2.setAbsoluteTolerance(kToleranceDegrees);
      turnController2.setContinuous(true);
      turnController2.disable();
    }

    // Called just before this Command runs the first time
    protected void initialize() {    

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (!turnController2.isEnabled()) {
    		SmartDashboard.putNumber("TargetAngle: ", targetAngleDegrees);
    		turnController2.setSetpoint(targetAngleDegrees);
			rotateToAngleRate = 0; // This value will be updated in the pidWrite() method.
			turnController2.enable();
		}
    	
		double turnRate = rotateToAngleRate;
		SmartDashboard.putNumber("Rotate Rate: ", rotateToAngleRate);
		SmartDashboard.putNumber("Turn value: ", turnRate);
		SmartDashboard.putNumber("Heading (Yaw): ", NavX.ahrs.getYaw());
		SmartDashboard.putNumber("Orientation (Angle): ", NavX.ahrs.getAngle());
		Robot.driveTrain.setTankDriveCommand(turnRate,  -turnRate);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return false;
    	final int PATIENCE = 50;
    	if((Math.abs(NavX.ahrs.getYaw() - targetAngleDegrees)) <= kToleranceDegrees)
    	{
    		counter++;
    	} else {
    		counter=0;
    	}
    	if(counter > PATIENCE){
    		return true;
    	}else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.driveTrain.setTankDriveCommand(0.0, 0.0);
		SmartDashboard.putNumber("End Yaw: ", NavX.ahrs.getYaw());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	@Override
	public void pidWrite(double output) {
		 rotateToAngleRate = output;
	}
}
