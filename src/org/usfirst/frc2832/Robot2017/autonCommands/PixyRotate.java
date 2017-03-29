package org.usfirst.frc2832.Robot2017.autonCommands;

import org.usfirst.frc2832.Robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
//values between 0 and 255 inclusive.  127 is straight on.  TUrn right is > 127 and turn left < 127. 255 means it cant see
 */
//used for turning while not moving forward
public class PixyRotate extends Command {
	private String rOrL;
	private byte[] buffer;
	private int pixyImage;
	private byte[] sendBuffer = "draco".getBytes();

    public PixyRotate(String direction) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	rOrL = direction;
    	buffer = new byte[1];
    	pixyImage = 300;
    	
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (rOrL.equals("right"))
    	{
    		Robot.driveTrain.setTankDriveCommand(0, .5);
    	}
    	else if (rOrL.equalsIgnoreCase("left"))
    	{
    		Robot.driveTrain.setTankDriveCommand(.5, 0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.pixyCamera.read(0, 1, buffer))
    		pixyImage = buffer[0] & 0xFF;
    		
    	if ( 124 < pixyImage || 130 > pixyImage)
    	{
    		return true;
    	}
    
    	return false;
        /*if (Robot.pixyInput.getAverageVoltage()  > .95 && Robot.pixyInput.getAverageVoltage() < 1.05)
        	return true;
        return false;
        */
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.setTankDriveCommand(0, 0);
    } 

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
