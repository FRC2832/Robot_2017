package org.usfirst.frc2832.Robot2017.commands.shooter;

import org.usfirst.frc2832.Robot2017.OI;
import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.RobotMap;
import org.usfirst.frc2832.Robot2017.commands.WaitUntilSpeed;
import org.usfirst.frc2832.Robot2017.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShooterSequenceOn extends CommandGroup {

    public ShooterSequenceOn() {
    	addSequential(new AgitatorOn());
    	addSequential(new ShooterOn());
    	addSequential(new WaitUntilSpeed(Shooter.shooterShootMotor, 50, Robot.shooter));
    	addSequential(new FeederOn());
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
