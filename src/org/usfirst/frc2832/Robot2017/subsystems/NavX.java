package org.usfirst.frc2832.Robot2017.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SPI;
import com.kauailabs.navx.frc.AHRS;

public class NavX extends Subsystem implements PIDOutput {
	public static PIDController turnController;
	public static final double kToleranceDegrees = 2;
	public static double rotateToAngleRate;
	static final double kP = 0.03;
	static final double kI = 0.00;
	static final double kD = 0.00;
	static final double kF = 0.00;
	public static AHRS ahrs;

	@Override
	protected void initDefaultCommand() {
		ahrs = new AHRS(SPI.Port.kMXP); 
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
	
}
