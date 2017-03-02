// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2832.Robot2017;

import org.usfirst.frc2832.Robot2017.autonCommands.DriveForward;
import org.usfirst.frc2832.Robot2017.commands.AllForward;
import org.usfirst.frc2832.Robot2017.commands.Climb;
import org.usfirst.frc2832.Robot2017.commands.InterfaceFlip;
import org.usfirst.frc2832.Robot2017.commands.TankDriveLeft;
import org.usfirst.frc2832.Robot2017.commands.TankDriveRight;
import org.usfirst.frc2832.Robot2017.commands.gearunload.CloseDoors;
import org.usfirst.frc2832.Robot2017.commands.gearunload.ExpelGear;
import org.usfirst.frc2832.Robot2017.commands.gearunload.ExtendPusher;
import org.usfirst.frc2832.Robot2017.commands.gearunload.OpenDoors;
import org.usfirst.frc2832.Robot2017.commands.gearunload.RetractPusher;
import org.usfirst.frc2832.Robot2017.commands.ingest.IngestToggle;
import org.usfirst.frc2832.Robot2017.commands.shooter.AgitatorOff;
import org.usfirst.frc2832.Robot2017.commands.shooter.AgitatorOn;
import org.usfirst.frc2832.Robot2017.commands.shooter.FeederOff;
import org.usfirst.frc2832.Robot2017.commands.shooter.FeederOn;
import org.usfirst.frc2832.Robot2017.commands.shooter.ShooterOff;
import org.usfirst.frc2832.Robot2017.commands.shooter.ShooterOn;
import org.usfirst.frc2832.Robot2017.commands.shooter.ShooterSequenceOff;
import org.usfirst.frc2832.Robot2017.commands.shooter.ShooterSequenceOn;
import org.usfirst.frc2832.Robot2017.commands.MotorPositionCheck;
import org.usfirst.frc2832.Robot2017.commands.RelayOn;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	//// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	public Joystick xBoxController;
	public JoystickButton aButton, xButton, bButton, yButton, rightBumper, leftBumper, startButton, selectButton, leftJoystick, rightJoystick;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	public static boolean doorsOpen = false;

    @SuppressWarnings("deprecation")
	public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        xBoxController = new Joystick(0);
        
        aButton = new JoystickButton(xBoxController, 1);
        bButton = new JoystickButton(xBoxController, 2);
        xButton = new JoystickButton(xBoxController, 3);
        yButton = new JoystickButton(xBoxController, 4);
        leftBumper = new JoystickButton(xBoxController, 5);
        rightBumper = new JoystickButton(xBoxController, 6);
        selectButton = new JoystickButton(xBoxController, 7);
        startButton = new JoystickButton(xBoxController, 8);
        leftJoystick = new JoystickButton(xBoxController, 9);
        rightJoystick = new JoystickButton(xBoxController, 10);
        
        //aButton.whenPressed(new ShooterSequenceOn());
        //aButton.whenReleased(new ShooterSequenceOff());
        startButton.whenPressed(new InterfaceFlip());
        yButton.whenPressed(new ExpelGear());
        bButton.whenPressed(new IngestToggle());
        xButton.whileHeld(new CloseDoors());
        aButton.whenPressed(new FeederOn());
        //selectButton.whileHeld(new Rotate());
        //startButton.whenPressed(new Climb());
        leftBumper.whileHeld(new TankDriveLeft());
        rightBumper.whileHeld(new TankDriveRight());
        //Have Motor Position Check on smart dashboard only, not controller

        //SmartDashboard.putData("Autonomous Command", new DriveForward(4.0));
  	    //SmartDashboard.putData("RelayOn", new RelayOn());
  	    //SmartDashboard.putData("AllForward", new AllForward());
  	    //SmartDashboard.putData("MotorPositionCheck", new MotorPositionCheck());
  	    SmartDashboard.putData("Shooter sequence on", new ShooterSequenceOn());
  	    SmartDashboard.putData("Shooter sequence off", new ShooterSequenceOff());
  	    SmartDashboard.putData("Agitator on", new AgitatorOn());
  	    SmartDashboard.putData("Agitator off", new AgitatorOff());
  	    SmartDashboard.putData("Shooter on", new ShooterOn());
  	    SmartDashboard.putData("Shooter off", new ShooterOff());
  	    SmartDashboard.putData("Expell gear", new ExpelGear());
  	    //SmartDashboard.putData("Open door", new OpenDoors());
  	    //SmartDashboard.putData("Close door", new CloseDoors());
  	    //SmartDashboard.putData("Extend pusher", new ExtendPusher());
  	    //SmartDashboard.putData("Retract pusher", new RetractPusher());
  	    SmartDashboard.putData("Flip motors", new InterfaceFlip());
  	    
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        //SmartDashboard Data
        new Thread(() -> {
	    	
	        while(true) {
	        	
	        	//Put SmartDashboard values here!!
	        	
	            SmartDashboard.putNumber("Left Encoder Value: ", RobotMap.driveTrainLeftFront.getEncPosition());
	            SmartDashboard.putNumber("Right Encoder Value: ", RobotMap.driveTrainRightFront.getEncPosition());
	            //SmartDashboard.putNumber("Compressor", RobotMap.compressor.getCompressorCurrent());	
	            SmartDashboard.putNumber("Left Trigger: ", Robot.lTrigger);
	            SmartDashboard.putNumber("Right Trigger: ", Robot.rTrigger);
	            SmartDashboard.putBoolean("Door Status", doorsOpen);
	            //SmartDashboard.putNumber("Shooting speed", shootSpeeed);
	        }
	        
        }).start();
        
    }
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getXBoxController() {
        return xBoxController;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

