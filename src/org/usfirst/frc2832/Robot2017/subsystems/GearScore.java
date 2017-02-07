package org.usfirst.frc2832.Robot2017.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * Handles gear vomiting
 */
public class GearScore extends Subsystem {

	public static DoubleSolenoid gearScorePusher;
    public static DoubleSolenoid gearScoreDoor;
    
    public GearScore() {
    	super();
    	
        gearScorePusher = new DoubleSolenoid(3, 4);
        LiveWindow.addActuator("GearScore", "Pusher", gearScorePusher);

        gearScoreDoor = new DoubleSolenoid(6, 5);
        LiveWindow.addActuator("GearScore", "Door", gearScoreDoor);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

