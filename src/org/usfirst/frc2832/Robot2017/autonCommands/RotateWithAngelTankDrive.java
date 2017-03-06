package org.usfirst.frc2832.Robot2017.autonCommands;

import org.usfirst.frc2832.Robot2017.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Author: Jing Xu
 * Email: jeanxu2000@gmail.com
 * Date: March 4, 2017
 * Created for : NavX Rotate
 */
public class RotateWithAngelTankDrive extends Command implements PIDOutput{
	
	private AHRS ahrs;

    private PIDController turnController;
    private double targetAngleDegrees;
    private double rotateToAngleRate;
    
    
    private static final double kP = 0.03;
    private static final double kI = 0.00;
    private static final double kD = 0.00;
    private static final double kF = 0.00;
    
    static final double kToleranceDegrees = 2.0f;       
    
    public RotateWithAngelTankDrive(double targetAngleDegrees) {
      requires(Robot.driveTrain);
      
      try {
			/***********************************************************************
			 * navX-MXP:
			 * - Communication via RoboRIO MXP (SPI, I2C, TTL UART) and USB.            
			 * - See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface.
			 * 
			 * navX-Micro:
			 * - Communication via I2C (RoboRIO MXP or Onboard) and USB.
			 * - See http://navx-micro.kauailabs.com/guidance/selecting-an-interface.
			 * 
			 * Multiple navX-model devices on a single robot are supported.
			 ************************************************************************/
  		
          ahrs = new AHRS(SPI.Port.kMXP); 
      } catch (RuntimeException ex ) {
          DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
      }
      this.targetAngleDegrees=targetAngleDegrees;
      turnController = new PIDController(kP, kI, kD, kF, ahrs, this);
      turnController.setInputRange(-180.0f,  180.0f);
      turnController.setOutputRange(-1.0, 1.0);
      turnController.setAbsoluteTolerance(kToleranceDegrees);
      turnController.setContinuous(true);
      turnController.disable();
    }

    // Called just before this Command runs the first time
    protected void initialize() {    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (!turnController.isEnabled()) {
		 	turnController.setSetpoint(targetAngleDegrees);
			rotateToAngleRate = 0; // This value will be updated in the pidWrite() method.
			turnController.enable();
		}
		double leftStickValue = rotateToAngleRate;
		double rightStickValue = rotateToAngleRate;
		Robot.driveTrain.setTankDriveCommand(leftStickValue,  rightStickValue);
    }

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

	@Override
	public void pidWrite(double output) {
		 rotateToAngleRate = output;
		
	}
}
