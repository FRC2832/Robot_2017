package org.usfirst.frc2832.Robot2017.commands;

import org.usfirst.frc2832.Robot2017.OI;
import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.RobotMap;
import org.usfirst.frc2832.Robot2017.subsystems.GearIntake;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearIntakeOpen extends Command implements Runnable {
	public GearIntakeOpen() {
		requires(Robot.gearIntake);
		
	}
	
	protected void initialize() {
		requires(Robot.driveTrain);
	}
	
	protected void execute() {
		RobotMap.gearIntakeRamp.set(DoubleSolenoid.Value.kForward);
		new Thread(this).start();
	}
	
	protected void end() {
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	public void run() {
		long then = System.currentTimeMillis();
		boolean running = true;
		while(running) {
			if(System.currentTimeMillis() - then > 3000) {
				RobotMap.gearIntakeRamp.set(DoubleSolenoid.Value.kReverse);
				running = false;
				
			}
			
		}
		
	}
	
}
