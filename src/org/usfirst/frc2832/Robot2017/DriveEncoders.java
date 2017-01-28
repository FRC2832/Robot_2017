package org.usfirst.frc2832.Robot2017;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveEncoders extends Subsystem {

	static double left;
	static double right;
	static double[] vals = new double[2];
	static final double TOLERANCE = 0.1;
	private static final double ENCODER_TO_METERS = 1.96;
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
	
	public static double getLeftValue()
	{
		return RobotMap.driveTrainLeftFront.getEncPosition(); //RobotMap.ENCODER_PULSE_PER_METER;
	}
	
	public static double getRightValue()
	{
		return RobotMap.driveTrainRightFront.getEncPosition(); // RobotMap.ENCODER_PULSE_PER_METER;
	}
	
	public static double[] getBothValues()
	{
		vals[0] = RobotMap.driveTrainLeftFront.getEncPosition(); // RobotMap.ENCODER_PULSE_PER_METER;
		vals[1] = RobotMap.driveTrainRightFront.getEncPosition(); // RobotMap.ENCODER_PULSE_PER_METER;
		return vals;
	}
	
	

}
