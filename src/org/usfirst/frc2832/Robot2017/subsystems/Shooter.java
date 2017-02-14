package org.usfirst.frc2832.Robot2017.subsystems;

import org.usfirst.frc2832.Robot2017.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * Handles the shooting
 */
public class Shooter extends Subsystem {

	public static CANTalon shooterShootMotor;
    public static CANTalon shooterFeeder;
    public static CANTalon shooterAgitator;
	
	public Shooter() {
		super();

	shooterFeeder = new CANTalon(8);
    LiveWindow.addActuator("Shooter", "Feeder", shooterFeeder);
    shooterFeeder.enableBrakeMode(false);
    
    shooterShootMotor = new CANTalon(7);
    LiveWindow.addActuator("Shooter", "ShootMotor", shooterShootMotor);
    shooterShootMotor.enableBrakeMode(false);
    
    shooterAgitator = new CANTalon(10);
    LiveWindow.addActuator("Shooter", "Agitator", shooterAgitator);
    shooterAgitator.enableBrakeMode(false);
    
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

