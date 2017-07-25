package org.usfirst.frc2832.Robot2017;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SerialPort;
import com.kauailabs.navx.frc.AHRS;

public class NavX implements PIDOutput {

    public static AHRS ahrs;
	private static PIDController turnController;
	private static double rotateToAngleRate;

	private static final double K_TO_TOLERANCE_DEGREES = 2;
	private static final double KP = 0.03;
	private static final double KI = 0.00;
	private static final double KD = 0.00;
	private static final double KF = 0.00;

	public NavX() {
		ahrs = new AHRS(SerialPort.Port.kUSB);
		turnController = new PIDController(KP, KI, KD, KF, ahrs, this);
		turnController.setInputRange(-180.0f,  180.0f);
		turnController.setOutputRange(-1.0, 1.0);
		turnController.setAbsoluteTolerance(K_TO_TOLERANCE_DEGREES);
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
