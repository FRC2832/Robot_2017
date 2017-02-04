package org.usfirst.frc2832.Robot2017.commands.gearunload;

import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.commands.WaitUntilTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *The command group that handles the process of unloading a year
 */
public class ExpelGear extends CommandGroup {

    public ExpelGear() {
    	addSequential(new OpenDoors());
    	addSequential(new WaitUntilTime(300, Robot.gearScore));
    	addSequential(new RetractPusher());
    	addSequential(new WaitUntilTime(400, Robot.gearScore));
    	addSequential(new ExtendPusher());
    	addSequential(new WaitUntilTime(500, Robot.gearScore));
    	addSequential(new CloseDoors());
    }
}
