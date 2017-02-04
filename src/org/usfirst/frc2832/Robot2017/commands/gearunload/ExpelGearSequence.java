package org.usfirst.frc2832.Robot2017.commands.gearunload;

import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *The command group that handles the process of unloading a year
 */
public class ExpelGearSequence extends CommandGroup {

    public ExpelGearSequence() {
    	addSequential(new OpenDoors());
    	addSequential(new Wait(300, Robot.gearIntake));
    	addSequential(new RetractPusher());
    	addSequential(new Wait(400, Robot.gearIntake));
    	addSequential(new ExtendPusher());
    	addSequential(new Wait(500, Robot.gearIntake));
    	addSequential(new CloseDoors());
    }
}
