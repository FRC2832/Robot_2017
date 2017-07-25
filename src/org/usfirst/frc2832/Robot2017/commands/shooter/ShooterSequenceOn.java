package org.usfirst.frc2832.Robot2017.commands.shooter;

import org.usfirst.frc2832.Robot2017.OI;
import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.RobotMap;
import org.usfirst.frc2832.Robot2017.commands.WaitUntilSpeed;
import org.usfirst.frc2832.Robot2017.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShooterSequenceOn extends CommandGroup {

    public ShooterSequenceOn() {
    	addSequential(new AgitatorOn());
    	addSequential(new ShooterOn());
    	addSequential(new WaitUntilSpeed(Shooter.shooterShootMotor, 50, Robot.shooter));
    	addSequential(new FeederOn());
    }
}
