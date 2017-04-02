package org.usfirst.frc2832.Robot2017;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveEncoders extends Subsystem {

	
	static double[] vals = new double[2];
	static final double TOLERANCE = 0.1;
	private static final double ENCODER_TO_METERS = 1.96;
	private static final double ENCODER_PULSE_PER_METER = 2800;
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public static double getAbsoluteValue()
	{
		vals = getBothValues();
		if (Math.abs(vals[0]) < TOLERANCE)
			return vals[1];
		else if (Math.abs(vals[1]) < TOLERANCE)
			return vals[0];
		else
			return ENCODER_TO_METERS*(vals[0] - vals[1])/2; //CURRENTLY ONE IS OPPOSITE OF OTHER. IF ENCODER VALUE IS NOT CHANGING, CONSIDER REPLACING MINUS WITH PLUS
			//return Math.sqrt(vals[0]*vals[1]);
	}
	//0: no difference.  Positive: left > right.  Negative: Right>left
	public static double getRawEncDifference()
	{
		return (Math.abs(DriveEncoders.getRawLeftValue()) - Math.abs(DriveEncoders.getRawRightValue()));
	}
	public static double getRawLeftValue()
	{
		return RobotMap.driveTrainLeftFront.getEncPosition();
	}
	public static double getRawRightValue()
	{
		return RobotMap.driveTrainRightFront.getEncPosition();
	}
	public static double getLeftValue()
	{
		return RobotMap.driveTrainLeftFront.getEncPosition() / ENCODER_PULSE_PER_METER;
	}
	
	public static double getRightValue()
	{
		return RobotMap.driveTrainRightFront.getEncPosition() / ENCODER_PULSE_PER_METER;
	}
	
	public static double[] getBothValues()
	{
		vals[0] = RobotMap.driveTrainLeftFront.getEncPosition() / ENCODER_PULSE_PER_METER;
		vals[1] = RobotMap.driveTrainRightFront.getEncPosition() / ENCODER_PULSE_PER_METER;
		return vals;
	}
	
	public static void intializeEncoders()
	{
		RobotMap.driveTrainLeftFront.setEncPosition(0);
		RobotMap.driveTrainRightFront.setEncPosition(0);
		RobotMap.driveTrainRightFront.setPosition(0);
    	RobotMap.driveTrainLeftFront.setPosition(0);
    	System.out.println("OLD - RF: " + RobotMap.driveTrainRightFront.getEncPosition() + " LF:" + RobotMap.driveTrainLeftFront.getEncPosition());
    	Timer.delay(0.04);
    	System.out.println("NEW: RF: " + RobotMap.driveTrainRightFront.getEncPosition() + " LF:" + RobotMap.driveTrainLeftFront.getEncPosition());
	}
}
