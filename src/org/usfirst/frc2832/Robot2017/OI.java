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


import org.usfirst.frc2832.Robot2017.commands.auton.RotateWithPIDTankDrive;
import org.usfirst.frc2832.Robot2017.commands.InterfaceFlip;
import org.usfirst.frc2832.Robot2017.commands.SwitchCamera;
import org.usfirst.frc2832.Robot2017.commands.TankDriveLeft;
import org.usfirst.frc2832.Robot2017.commands.TankDriveRight;
import org.usfirst.frc2832.Robot2017.commands.gearunload.CloseDoors;
import org.usfirst.frc2832.Robot2017.commands.gearunload.ExpelGear;
import org.usfirst.frc2832.Robot2017.commands.ingest.IngestToggle;
import org.usfirst.frc2832.Robot2017.commands.shooter.AgitatorOff;
import org.usfirst.frc2832.Robot2017.commands.shooter.AgitatorOn;
import org.usfirst.frc2832.Robot2017.commands.shooter.FeederOn;
import org.usfirst.frc2832.Robot2017.commands.shooter.ShooterOff;
import org.usfirst.frc2832.Robot2017.commands.shooter.ShooterOn;
import org.usfirst.frc2832.Robot2017.commands.shooter.ShooterSequenceOff;
import org.usfirst.frc2832.Robot2017.commands.shooter.ShooterSequenceOn;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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


	public Joystick xBoxController;
	public JoystickButton aButton, xButton, bButton, yButton, rightBumper, leftBumper, startButton, selectButton, leftJoystick, rightJoystick;
	public static boolean doorsOpen = false;

    @SuppressWarnings("deprecation")
	public OI() {

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
		startButton.whenPressed(new InterfaceFlip());
		yButton.whenPressed(new ExpelGear());
		bButton.whenPressed(new IngestToggle());
		xButton.whileHeld(new CloseDoors());
		aButton.whenPressed(new FeederOn());
		selectButton.whenPressed(new SwitchCamera());
		leftBumper.whileHeld(new TankDriveLeft());
		rightBumper.whileHeld(new TankDriveRight());

		//Buttons and auton selector
		SmartDashboard.putData("Autonomous Selection", Robot.auto);
		SmartDashboard.putData("Shooter sequence on", new ShooterSequenceOn());
		SmartDashboard.putData("Shooter sequence off", new ShooterSequenceOff());
		SmartDashboard.putData("Agitator on", new AgitatorOn());
		SmartDashboard.putData("Agitator off", new AgitatorOff());
		SmartDashboard.putData("Shooter on", new ShooterOn());
		SmartDashboard.putData("Shooter off", new ShooterOff());
		SmartDashboard.putData("Expell gear", new ExpelGear());
		SmartDashboard.putData("Flip motors", new InterfaceFlip());
		SmartDashboard.putData("RotatePID Home", new RotateWithPIDTankDrive(0.0));
		SmartDashboard.putData("RotatePID 60", new RotateWithPIDTankDrive(60.0));
		SmartDashboard.putData("RotatePID -60 (left)", new RotateWithPIDTankDrive(-60.0));
		SmartDashboard.putData("RotatePID 120", new RotateWithPIDTankDrive(120.0));
		SmartDashboard.putData("RotatePID -120 (60 right?)", new RotateWithPIDTankDrive(-120.0));

		//Initial values
		SmartDashboard.putBoolean("NavX_Start_Connected", NavX.ahrs.isConnected());
		SmartDashboard.putNumber("camerasleect", Robot.camera);
	}

    public void update() {
		SmartDashboard.putNumber("Left Encoder Value: ", RobotMap.driveTrainLeftFront.getEncPosition());
		SmartDashboard.putNumber("Right Encoder Value: ", RobotMap.driveTrainRightFront.getEncPosition());
		SmartDashboard.putNumber("Left Trigger: ", Robot.lTrigger);
		SmartDashboard.putNumber("Right Trigger: ", Robot.rTrigger);
		SmartDashboard.putBoolean("Door Status", doorsOpen);
		SmartDashboard.putNumber("camera", Robot.camera);
		SmartDashboard.putNumber("Pixy X value", Robot.pixyValue  );
		SmartDashboard.putBoolean("IsIngesting", Robot.isIngesting);

		SmartDashboard.putNumber("Right Encoder", DriveEncoders.getRawRightValue());
		SmartDashboard.putNumber("Left Encoder", DriveEncoders.getRawLeftValue());
		SmartDashboard.putNumber("Encoder Differences", DriveEncoders.getRawEncDifference());

		SmartDashboard.putNumber("Accelerometer", NavX.ahrs.getWorldLinearAccelY());
		SmartDashboard.putBoolean("IMU_Connected", NavX.ahrs.isConnected());
		SmartDashboard.putNumber("IMU_Yaw", NavX.ahrs.getYaw());

	}

    public Joystick getXBoxController() {
        return xBoxController;
    }
}

