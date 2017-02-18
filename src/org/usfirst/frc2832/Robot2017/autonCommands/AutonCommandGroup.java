package org.usfirst.frc2832.Robot2017.autonCommands;

import java.util.ArrayList;

import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.commands.shooter.AgitatorOff;
import org.usfirst.frc2832.Robot2017.commands.shooter.AgitatorOn;
import org.usfirst.frc2832.Robot2017.commands.shooter.FeederOff;
import org.usfirst.frc2832.Robot2017.commands.shooter.FeederOn;
import org.usfirst.frc2832.Robot2017.commands.shooter.ShooterOff;
import org.usfirst.frc2832.Robot2017.commands.shooter.ShooterOn;
import org.usfirst.frc2832.Robot2017.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonCommandGroup extends CommandGroup {

	public AutonCommandGroup(ArrayList <Command> commands){
		for(Command e: commands)
			addSequential(e);


	}

	public AutonCommandGroup(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

}
