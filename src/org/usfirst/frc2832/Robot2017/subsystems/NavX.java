package org.usfirst.frc2832.Robot2017.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SerialPort;
import com.kauailabs.navx.frc.AHRS;

public class NavX extends Subsystem implements PIDOutput {
    public static AHRS ahrs;
	public static PIDController turnController;
	public static final double kToleranceDegrees = 2;
	public static double rotateToAngleRate;
	static final double kP = 0.03;
	static final double kI = 0.00;
	static final double kD = 0.00;
	static final double kF = 0.00;

	public NavX() {
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

			ahrs = new AHRS(SerialPort.Port.kUSB);
		} catch (RuntimeException ex ) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
		}

	}
	
	@Override
	protected void initDefaultCommand() {
		turnController = new PIDController(kP, kI, kD, kF, ahrs, this);
		turnController.setInputRange(-180.0f,  180.0f);
		turnController.setOutputRange(-1.0, 1.0);
		turnController.setAbsoluteTolerance(kToleranceDegrees);
		turnController.setContinuous(true);
		
	}
	
	@Override
	public void pidWrite(double output) {
		rotateToAngleRate = output;		
	}
	
	public static double getHeading() {
        return ahrs.getAngle();
    }	
	
	public static double getYaw() {
        return ahrs.getYaw();
    }
	
}
