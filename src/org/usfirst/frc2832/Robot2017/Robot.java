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

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc2832.Robot2017.commands.*;
import org.usfirst.frc2832.Robot2017.commands.gearunload.CloseDoors;
import org.usfirst.frc2832.Robot2017.commands.shooter.AgitatorOn;
import org.usfirst.frc2832.Robot2017.commands.shooter.FeederOff;
import org.usfirst.frc2832.Robot2017.commands.shooter.FeederOn;
import org.usfirst.frc2832.Robot2017.commands.shooter.ShooterOff;
import org.usfirst.frc2832.Robot2017.commands.shooter.ShooterOn;
import org.usfirst.frc2832.Robot2017.commands.shooter.ShooterSequenceOff;
import org.usfirst.frc2832.Robot2017.commands.shooter.ShooterSequenceOn;
import org.usfirst.frc2832.Robot2017.pixy.PixyException;
import org.usfirst.frc2832.Robot2017.pixy.PixyI2C;
import org.usfirst.frc2832.Robot2017.pixy.PixyPacket;
import org.usfirst.frc2832.Robot2017.subsystems.*;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
//robot code
public class Robot extends IterativeRobot {

    Command autonomousCommand;

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static BallIntake ballIntake;
    public static Climbing climb;
    public static GearIntake gearIntake;
    public static GearScore gearScore;
    public static Shooter shooter;
    public static Lights lights;
    public static boolean isClimbing = false, isIngesting = false, povActivated = false;
    public static NavX navX;
    public static double lTrigger, rTrigger;
    public static PixyI2C testPixy;
    public static int shootSpeeed = 50, pov;
    public static SendableChooser<String> auto;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	RobotMap.init();
    	RobotMap.driveTrainLeftFront.enableBrakeMode(true);
    	RobotMap.driveTrainRightFront.enableBrakeMode(true);
    	RobotMap.driveTrainLeftRear.enableBrakeMode(true);
    	RobotMap.driveTrainRightRear.enableBrakeMode(true);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

  //  	lights = new Lights();
    	new Thread(() -> {
	    	
	        
	        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	        camera.setResolution(720, 480);
	        CvSink cvSink = CameraServer.getInstance().getVideo();
	        CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
	        
	        Mat source = new Mat();
	        Mat output = new Mat();
	        
	        while(true) {
	            cvSink.grabFrame(source);
	            Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
	            outputStream.putFrame(output);
	        }
        }).start();
    	
    	new Thread(() -> {
	    	
	        
	        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	        camera.setResolution(720, 480);
	        CvSink cvSink = CameraServer.getInstance().getVideo();
	        CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
	        
	        Mat source = new Mat();
	        Mat output = new Mat();
	        
	        while(true) {
	            cvSink.grabFrame(source);
	            Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
	            outputStream.putFrame(output);
	        }
        }).start();
        

        driveTrain = new DriveTrain();
        ballIntake = new BallIntake();
        climb = new Climbing();
        gearIntake = new GearIntake();
        gearScore = new GearScore();
        shooter = new Shooter();
        lights = new Lights();
        navX = new NavX();
        testPixy = new PixyI2C();
     
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        auto = new SendableChooser<String>();
        auto.addObject("Do nothing at all", "0");
        auto.addObject("Move Forward 3", "f3");
        auto.addDefault("Expel Gear Forward", "e3");
        auto.addDefault("Expel Gear Right", "e4");
        auto.addDefault("Expel Gear Left", "e5");
        SmartDashboard.putData("Autonomous Selection", auto);
        autonomousCommand = new AutonomousCommand();

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	RobotMap.driveTrainLeftFront.enableBrakeMode(false);
    	RobotMap.driveTrainRightFront.enableBrakeMode(false);
    	RobotMap.driveTrainLeftRear.enableBrakeMode(false);
    	RobotMap.driveTrainRightRear.enableBrakeMode(false);
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
    	RobotMap.driveTrainLeftFront.enableBrakeMode(true);
    	RobotMap.driveTrainRightFront.enableBrakeMode(true);
    	RobotMap.driveTrainLeftRear.enableBrakeMode(true);
    	RobotMap.driveTrainRightRear.enableBrakeMode(true);
        // schedule the autonomous command (example)
    	//next two lines of code work for now, but we'll probably want to replace them with a more 
    	//elegant way of selecting the auton mode we want from the smart dashboard 
    	
    	
    	
    	autonomousCommand = (CommandGroup)new AutonCommandGroup (ParseInput.takeInput((String)auto.getSelected())); 
       
    	if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();

    }

    public void teleopInit() {
    	RobotMap.driveTrainLeftFront.enableBrakeMode(true);
    	RobotMap.driveTrainRightFront.enableBrakeMode(true);
    	RobotMap.driveTrainLeftRear.enableBrakeMode(true);
    	RobotMap.driveTrainRightRear.enableBrakeMode(true);
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        lTrigger = oi.getXBoxController().getRawAxis(2);
        rTrigger = oi.getXBoxController().getRawAxis(3);
        pov = oi.getXBoxController().getPOV(0);
        if(lTrigger > 0.1)
        	new Climb().start();
        if(rTrigger > 0.1) {
        	new ShooterSequenceOn().start();
        } else {
        	new ShooterSequenceOff().start();
        }
        //if(pov == -1) {
       	//	povActivated = false;
        //} else {
        	if (pov != -1)
        		if(pov > 90 && pov < 270) {
        			shootSpeeed--;
        		} else {
        			shootSpeeed++;
        		}
        				
        //}
        SmartDashboard.putNumber("Climbing speeed", shootSpeeed);
        
     try {
        	
        	PixyPacket example1 = testPixy.readPacket(1);
        	PixyPacket example2 = testPixy.readPacket(1);
        	if (example1 != null){
        		
        		SmartDashboard.putNumber("height 1", example1.Height);
	        	SmartDashboard.putNumber("width 1", example1.Width);
	        	SmartDashboard.putNumber("x 1", example1.X);
	        	SmartDashboard.putNumber("y 1", example1.Y);
        	}
        	if (example2 != null){
        		
        		SmartDashboard.putNumber("height 2", example2.Height);
	        	SmartDashboard.putNumber("width 2", example2.Width);
	        	SmartDashboard.putNumber("x 2", example2.X);
	        	SmartDashboard.putNumber("y 2", example2.Y);
        	}
        } catch (PixyException e){
        	e.printStackTrace();
        }
        

    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
