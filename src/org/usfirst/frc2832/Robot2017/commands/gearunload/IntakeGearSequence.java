package org.usfirst.frc2832.Robot2017.commands.gearunload;

import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeGearSequence extends CommandGroup {

    public IntakeGearSequence() {
    	addSequential(new OpenDoors());
    	addSequential(new Wait(100, Robot.gearIntake));
    	addSequential(new ExtendPusher());
    	addSequential(new Wait(100, Robot.gearIntake));
    	addSequential(new RetractPusher());
    	addSequential(new Wait(100, Robot.gearIntake));
    	addSequential(new CloseDoors());
    }
}
