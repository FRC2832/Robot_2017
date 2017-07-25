package org.usfirst.frc2832.Robot2017.subsystems;

import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Handles the shooting
 */
public class Shooter extends Subsystem {

	public static CANTalon shooterShootMotor;
    public static CANTalon shooterFeeder;
    public static CANTalon shooterAgitator;
	

	/** Steps to calibrate velocity mode */
	/* First, setup how many pulses per revolution with the selected encoder.  
	 * This number is not critical, but do not change once the rest is calibrated. */
	final int PulsesPerRevolution = 1024;
	
	/* Second, calibrate the max speed.  To do this, command full speed to the Talon.
	 * Then, connect to the webdashboard, and check the talon's self-test mode.
	 * Find where encoders are listed, check RPM that it is moving, and record
	 * the Velocity number below it (this is pulses per 100ms) and record it here.
	 */
	//final int PulsesPer100ms = 46720;
	final int PulsesPer100ms = 34642; // Bag Motor
	// final static int MaxRpm = 6843;  // first motor
	final static int MaxRpm = 5100; // second motor
	//final double pidF = (0.75 * 1023)/PulsesPer100ms;  			//(75% * 1023 native units)/PulsesPer100ms
	//final double pidF = 0; //initial value
	final double pidF = 0.02953; //F-gain recalculated by hand & calculator
	
	/* Third, calibrate the P-term.  This sets how much extra correction is needed 
	 * when the speed goes away from the target speed.  To run this test, do a full
	 * sweep of the motor speed and find the biggest error.  
	 */
	final float CorrPercent = 0.1f;	//+-10% error correction
	//final int ErrorFactor = 9576;
	final int ErrorFactor = 0;
	//final double pidP = (CorrPercent * 1023)/ErrorFactor;
	//final double pidP = 0.010683;
	final double pidP = 0.10; //initial value
	
	
	public Shooter() {
		super();
		shooterFeeder = new CANTalon(8);
	    LiveWindow.addActuator("Shooter", "Feeder", shooterFeeder);
	    shooterFeeder.enableBrakeMode(false);
	    
	    shooterShootMotor = new CANTalon(7);
	    LiveWindow.addActuator("Shooter", "ShootMotor", shooterShootMotor);
	    shooterShootMotor.enableBrakeMode(false);
        /* choose the sensor */
	    shooterShootMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
	    shooterShootMotor.reverseSensor(true);
	    shooterShootMotor.configEncoderCodesPerRev(PulsesPerRevolution); // if using FeedbackDevice.QuadEncoder

        /* set the peak and nominal outputs, 12V means full */
	    shooterShootMotor.configNominalOutputVoltage(+0.0f, -0.0f);
	    shooterShootMotor.configPeakOutputVoltage(+12.0f, -12.0f);
        
        /* set closed loop gains in slot0 */
	    shooterShootMotor.setProfile(0);
	    shooterShootMotor.setF(pidF);
        shooterShootMotor.setP(pidP);	
        
        //only change I and D to smooth out control
        shooterShootMotor.setI(0); 
        shooterShootMotor.setD(0);
	    
	    shooterAgitator = new CANTalon(10);
	    LiveWindow.addActuator("Shooter", "Agitator", shooterAgitator);
	    shooterAgitator.enableBrakeMode(false);
	}
	
	public void trigger() {
		shooterAgitator.set(-0.7);
		shooterShootMotor.changeControlMode(TalonControlMode.Speed);
		shooterShootMotor.set(Robot.shootSpeeed);
		//double speeed = Math.max(0, Math.min(100, Robot.shootSpeeed));
		//shooterShootMotor.changeControlMode(TalonControlMode.PercentVbus);
		//shooterShootMotor.set(speeed / 100);
		//SmartDashboard.putNumber("SPEEED", shooterShootMotor.getEncVelocity());
		//if(shooterShootMotor.getEncVelocity() / 100 > speeed / 2) {
		//	shooterFeeder.set(1);
		//}
		printVelocityString();
		
	}
	
	public static void commandShooterSpeed(double rpm) {
		shooterShootMotor.changeControlMode(TalonControlMode.Speed);
		shooterShootMotor.set(rpm);
		if(Robot.rTrigger <= 0.5){
			shooterShootMotor.set(Robot.shootSpeeed);
		}
		printVelocityString();
	}
	
	static int _loops = 0;
	
	private static void printVelocityString() {
		//only print every 10 loops (200ms to not flood the dashboard with messages)
        if(++_loops >= 10) {
        	StringBuilder _sb = new StringBuilder();
        	double motorOutput = shooterShootMotor.getOutputVoltage() / shooterShootMotor.getBusVoltage();
        	/* prepare line to print */
    		_sb.append("\tout:");
    		_sb.append(motorOutput);
            _sb.append("\tspd:");
            _sb.append(shooterShootMotor.getSpeed() );
        	/* append more signals to print when in speed mode. */
            _sb.append("\terr:");
            _sb.append(shooterShootMotor.getClosedLoopError());
            _sb.append("\ttrg:");
            _sb.append(Robot.shootSpeeed);
            _sb.append("\tSetPt:");
            _sb.append(shooterShootMotor.getSetpoint());
            _loops = 0;
        	System.out.println(_sb.toString());
        	//zero the string
            _sb.setLength(0);
        }
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

