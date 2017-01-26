package org.usfirst.frc2832.Robot2017.commands;

import org.usfirst.frc2832.Robot2017.OI;
import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.RobotMap;
import org.usfirst.frc2832.Robot2017.subsystems.GearIntake;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearIntakeOpen extends Command {
	public GearIntakeOpen() {
		requires(Robot.gearIntake);
		
	}
	
	protected void initialize() {
		Robot.gearIntake.toggleExtended();
		SmartDashboard.putBoolean("Piston Extended", Robot.gearIntake.getExtended());
	}
	
	protected void execute() {
		if(Robot.gearIntake.getExtended()) {
			RobotMap.gearIntakeRamp.set(DoubleSolenoid.Value.kForward);
		} else {
			RobotMap.gearIntakeRamp.set(DoubleSolenoid.Value.kReverse);
		}
	}
	
	protected void end() {
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	
}
