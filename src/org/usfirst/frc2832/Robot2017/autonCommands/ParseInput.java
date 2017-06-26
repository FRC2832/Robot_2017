package org.usfirst.frc2832.Robot2017.autonCommands;

import java.util.ArrayList;

import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.commands.Rotate;
import org.usfirst.frc2832.Robot2017.commands.WaitUntilTime;
import org.usfirst.frc2832.Robot2017.commands.gearunload.CloseDoors;
import org.usfirst.frc2832.Robot2017.commands.gearunload.ExpelGear;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

//helper class to read the SendableChooser inputs and eventually start autonomous
public class ParseInput {

	static ArrayList<Command> auto_Commands;
	
	public static ArrayList<Command> takeInput(String movement)
	{
		auto_Commands = new ArrayList<Command>(0);
		if(movement.charAt(0) == 'f')
			auto_Commands.add(new DriveForward(Double.valueOf(movement.substring(1)), 1));
		
		//ACTUAL CENTER AUTON FOR IRON KRAKEN
		else if(movement.charAt(0) == 'e'){
			//auto_Commands.add(new DriveForwardDist(158.75, 4050, 5));
			auto_Commands.add(new DriveForward(4000, 1.00));
			//auto_Commands.add(new WaitUntilTime(500, Robot.driveTrain));
			auto_Commands.add(new ExpelGear());
			//auto_Commands.add(new WaitUntilTime(500, Robot.driveTrain));
			auto_Commands.add(new DriveBackward(1));
			//auto_Commands.add(new VisionAimGear());
			//auto_Commands.add(new ExpelGear());
			//auto_Commands.add(new DriveBackward(2));
			//auto_Commands.add(new CloseDoors());
		}
		//ACTUAL Left Gear auton for IRON KRAKEN
		else if(movement.charAt(0) == 'z'){
			//967 is the magic encoder turn number
			//5077 is the magic straight forward number
			
			auto_Commands.add(new DriveForwardDist(158.75, 1650, 5)); // 1756 original, 1708 States, 1730
			//auto_Commands.add(new PixyRotate("left"));
			auto_Commands.add(new RotateWithPIDTankDrive(60));
			auto_Commands.add(new DriveForward(2000, 1.00, 2));
			//auto_Commands.add(new RotateNavX(60));
			//auto_Commands.add(new RotateNavXWithEnc(50));
			//auto_Commands.add(new VisionAimGear());
			auto_Commands.add(new ExpelGear());
			auto_Commands.add(new DriveBackward(2));
			auto_Commands.add(new RotateNavXWithEnc(-40));
			auto_Commands.add(new CloseDoors());	
			auto_Commands.add(new DriveForwardDist(158.75, 7000, 10));
			//auto_Commands.add(new VisionAimGear());
			//auto_Commands.add(new ExpelGear());
			//auto_Commands.add(new DriveBackward(2));
			//auto_Commands.add(new CloseDoors());
		}
		//ACTUAL RIGHT AUTON FOR IRON KRAKEN
		else if (movement.charAt(0) == 'y'){
			auto_Commands.add(new DriveForwardDist(158.75, 1650, 5)); // 1756 original, 1708 States //1730
			//auto_Commands.add(new PixyRotate("left"));
			auto_Commands.add(new RotateWithPIDTankDrive(-60));
			auto_Commands.add(new DriveForward(2000, 1.00, 2));
			auto_Commands.add(new ExpelGear());
			auto_Commands.add(new DriveBackward(2));
			auto_Commands.add(new RotateNavXWithEnc(40));
			auto_Commands.add(new CloseDoors());	
			auto_Commands.add(new DriveForwardDist(158.75, 7000, 10));
		
		}
		
		//ACTUAL DRIVE STRAIGHT FORWARD FROM SIDES FOR IRON KRAKEN
		else if (movement.charAt(0) == 'b'){
			//auto_Commands.add(new DriveForward(3.8, 1));
			auto_Commands.add(new DriveForwardDist(158.75, 4000, 12));
		}
		
		
		
		
		
		
		
		
		
//===================================================================================================
		//TESTING
		
		
		
		//TEST CENTER AUTON
		else if (movement.charAt(0) == 'w'){
			//auto_Commands.add(new DriveForwardPlain(4000));
			auto_Commands.add(new DriveForwardDist(158.75, 1758, 5));
			//auto_Commands.add(new DriveForward(4000, 1.00));
			//auto_Commands.add(new WaitUntilTime(500, Robot.driveTrain));
			//auto_Commands.add(new ExpelGear());
			//auto_Commands.add(new WaitUntilTime(500, Robot.driveTrain));
			//auto_Commands.add(new DriveBackward(1));
			
		}
		//TEST LEFT AUTON
		else if (movement.charAt(0) == 'u'){
			auto_Commands.add(new DriveForwardDist(158.75, 1730, 5)); // 1756 original, 1708 States
			//auto_Commands.add(new PixyRotate("left"));
			auto_Commands.add(new RotateWithPIDTankDrive(60));
			auto_Commands.add(new VisionAimGear());
			auto_Commands.add(new ExpelGear());
			auto_Commands.add(new DriveBackward(2));
			auto_Commands.add(new RotateNavXWithEnc(-40));
			auto_Commands.add(new CloseDoors());	
			auto_Commands.add(new DriveForwardDist(158.75, 7000, 10));
			
			
		}
		//TEST RIGHT AUTON
		else if (movement.charAt(0) == 't'){
			auto_Commands.add(new DriveForwardDist(158.75, 1730, 5)); // 1756 original, 1708 States
			//auto_Commands.add(new PixyRotate("left"));
			auto_Commands.add(new RotateWithPIDTankDrive(-60));
			auto_Commands.add(new DriveForward(2000, 1.00, 2));
			auto_Commands.add(new ExpelGear());
			auto_Commands.add(new DriveBackward(2));
			auto_Commands.add(new RotateNavXWithEnc(40));
			auto_Commands.add(new CloseDoors());	
			auto_Commands.add(new DriveForwardDist(158.75, 7000, 10));
			
			
		}
		//Left auton turn NO GEAR
		else if (movement.charAt(0) == 's')
		{
			auto_Commands.add(new DriveForwardDist(158.75, 1650, 5)); //1730
			//auto_Commands.add(new PixyRotate("left"));
			auto_Commands.add(new RotateWithPIDTankDrive(60));
			auto_Commands.add(new DriveForward(2000, 1.00, 2));
		}
		//Right auton turn NO GEAR
				else if (movement.charAt(0) == 'r')
				{
					auto_Commands.add(new DriveForwardDist(158.75, 1650, 5));
					//auto_Commands.add(new PixyRotate("left"));
					auto_Commands.add(new RotateWithPIDTankDrive(-60));
					auto_Commands.add(new DriveForward(2000, 1.00, 2));
				}
		
//=========================================================================================================
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//JUST DONT USE THESE
		
		
		
		//use for driving forward from right or left position from practice bot. VALUES HAVE BEEN TESTED
		else if (movement.charAt(0) == 'c'){
			auto_Commands.add(new DriveForwardDist(152.4, 4000, 10));

		}
		//Expel Gear From Right NavX(practice bot) VALUES HAVE BEEN TESTED
		else if (movement.charAt(0) == 'i'){
			auto_Commands.add(new DriveForwardDist(152.4, 1906, 5));
			//auto_Commands.add(new PixyRotate("left"));
			auto_Commands.add(new RotateWithPIDTankDrive(-60));
			auto_Commands.add(new VisionAimGear());
			auto_Commands.add(new ExpelGear());
			auto_Commands.add(new DriveBackward(2));
			auto_Commands.add(new RotateNavXWithEnc(40));
			auto_Commands.add(new CloseDoors());	
			auto_Commands.add(new DriveForwardDist(158.75, 7000, 10));
		}
		//Expel Gear From Left NavX(practice bot) VALUES HAVE BEEN TESTED
		else if (movement.charAt(0) == 'h'){
			auto_Commands.add(new DriveForwardDist(152.4, 1906, 5));
			auto_Commands.add(new RotateNavX(60f));
			auto_Commands.add(new DriveForward(1000, 1.2));
			auto_Commands.add(new ExpelGear());
			auto_Commands.add(new DriveBackward(2));
			auto_Commands.add(new RotateNavX(-40));
			auto_Commands.add(new DriveForwardDist(152.4, 7000, 10));
			auto_Commands.add(new CloseDoors());
		}
				
	
		//use for driving forward and deploy gear from right position on actual bot.
		else if (movement.charAt(0) == 'a'){
			auto_Commands.add(new DriveForwardDist(158.75, 1756, 5));
			//auto_Commands.add(new RotateWithPIDTankDrive(-59));
			auto_Commands.add(new RotateNavX(-59));
			auto_Commands.add(new DriveForward(1000, 1.2));
			//auto_Commands.add(new AutonAimGear());
			auto_Commands.add(new ExpelGear());
			auto_Commands.add(new DriveBackward(2));
			auto_Commands.add(new RotateNavX(40));
			auto_Commands.add(new CloseDoors());	
			auto_Commands.add(new DriveForwardDist(158.75, 7000, 10));
		}
		//use for driving forward and turning from left position on actual bot.
		else if (movement.charAt(0) == 'g'){
			auto_Commands.add(new DriveForwardDist(158.75, 1756, 5));
			auto_Commands.add(new RotateNavX(60f));
			auto_Commands.add(new DriveForward(1000, 1.2));
			auto_Commands.add(new ExpelGear());
			auto_Commands.add(new DriveBackward(2));
			auto_Commands.add(new RotateNavX(-40));
			auto_Commands.add(new CloseDoors());				
			auto_Commands.add(new DriveForwardDist(158.75, 7000, 10));
		}
				
		//use for expelling the gear from the middle position of the actual bot
		else if (movement.charAt(0) == 'd'){
			auto_Commands.add(new DriveForward(4000, 1.00));
			//auto_Commands.add(new WaitUntilTime(500, Robot.driveTrain));
			auto_Commands.add(new ExpelGear());
			auto_Commands.add(new WaitUntilTime(500, Robot.driveTrain));
			auto_Commands.add(new DriveBackward(1));
			//auto_Commands.add(new CloseDoors());
		}
		/*		else if (movement.charAt(0) == 'j'){
					
					//auto_Commands.add(new DriveForward(3.7));
					//auto_Commands.add(new Rotate(40));
					//auto_Commands.add(new DriveForwardDist(152.4, 1906, 5));
					//auto_Commands.add(new RotateNavX(60));
					auto_Commands.add(new AutonAimGear());
					auto_Commands.add(new ExpelGear());
					auto_Commands.add(new DriveBackward(2));
					auto_Commands.add(new CloseDoors());
				}
		//use for Practice Bot = Mr Zobel testing
				else if (movement.charAt(0) == 'x'){
					auto_Commands.add(new DriveForwardDist(152.4, 1906, 5));
					auto_Commands.add(new RotateWithPIDTankDrive(-59.0));	
					//auto_Commands.add(new DriveForward(1000, 1.2));
					auto_Commands.add(new AutonAimGear());
					auto_Commands.add(new ExpelGear());
					auto_Commands.add(new DriveBackward(2));
					auto_Commands.add(new CloseDoors());
					auto_Commands.add(new RotateWithPIDTankDrive(0.0));			
					auto_Commands.add(new DriveForwardDist(152.4, 6000, 7));
				}
		*/	
		return auto_Commands; //an arraylist of the commands to be executed in autonomous
	}
	
}
