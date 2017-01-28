package org.usfirst.frc2832.Robot2017.subsystems;

import org.usfirst.frc2832.Robot2017.RobotMap;
import org.usfirst.frc2832.Robot2017.commands.Climb;
import org.usfirst.frc2832.Robot2017.commands.DefaultClimb;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climbing extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Climb());
    	
    }
    public void setClimbMotorSpeed(double speed) {
    	RobotMap.climbMotor1.set(speed);
    	RobotMap.climbMotor1.set(speed);
    }
}

