package org.usfirst.frc2832.Robot2017.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *Handles ball intake
 */
public class BallIntake extends Subsystem {
	
    public static CANTalon ballIntakeMotor;

    public BallIntake() {
    	super();
    	ballIntakeMotor = new CANTalon(9);
        LiveWindow.addActuator("BallIntake", "Intake", ballIntakeMotor);
        ballIntakeMotor.enableBrakeMode(false);
        
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

