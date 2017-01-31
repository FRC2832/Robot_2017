package org.usfirst.frc2832.Robot2017.subsystems;

import org.usfirst.frc2832.Robot2017.commands.GearIntakeOpen;

import edu.wpi.first.wpilibj.command.Subsystem;

public class GearIntake extends Subsystem {
	private boolean extended;
	public GearIntake() {
		this.extended = false;
	}
// Put methods for controlling this subsystem
    // here. Call these from Commands.
	public boolean getExtended() {
		return extended;
	}
	
	public void toggleExtended() {
		extended = !extended;
		
	}
    public void initDefaultCommand() {
    	
    }
}

