package org.usfirst.frc2832.Robot2017.commands.auton;

import java.util.ArrayList;

import org.usfirst.frc2832.Robot2017.commands.gearunload.CloseDoors;
import org.usfirst.frc2832.Robot2017.commands.gearunload.ExpelGear;

import edu.wpi.first.wpilibj.command.Command;

//helper class to read the SendableChooser inputs and eventually start autonomous
public class ParseInput {

	static ArrayList<Command> auto_Commands;
	
	public static ArrayList<Command> takeInput(String movement)
	{
		auto_Commands = new ArrayList<Command>(0);
		if(movement.charAt(0) == 'f')
			auto_Commands.add(new DriveForward(Double.valueOf(movement.substring(1)), 1));
		
		//use for expelling the gear from the middle position for practice bot. VALUES HAVE BEEN TESTED
		else if(movement.charAt(0) == 'e'){
			//auto_Commands.add(new DriveForwardPlain(4000, 1.15));
			auto_Commands.add(new DriveForward(4000, 1.15));
			auto_Commands.add(new ExpelGear());
			auto_Commands.add(new DriveBackward(2));
			auto_Commands.add(new CloseDoors());
		}
		
		//use for driving forward from right or left position from practice bot. VALUES HAVE BEEN TESTED
		else if (movement.charAt(0) == 'c'){
			auto_Commands.add(new DriveForwardDist(152.4, 4000, 10));

		}
		//Expel Gear From Right NavX(practice bot) VALUES HAVE BEEN TESTED
		else if (movement.charAt(0) == 'i'){
			auto_Commands.add(new DriveForwardDist(152.4, 1870, 5));
			auto_Commands.add(new RotateNavX(-59));
			auto_Commands.add(new DriveForward(1000, 1.2));
			auto_Commands.add(new ExpelGear());
			auto_Commands.add(new DriveBackward(2));
			auto_Commands.add(new RotateNavX(40));
			auto_Commands.add(new CloseDoors());
			auto_Commands.add(new DriveForwardDist(152.4, 7000, 10));
		}
		
		//Expel Gear From Left NavX(practice bot) VALUES HAVE BEEN TESTED
		else if (movement.charAt(0) == 'h'){
			auto_Commands.add(new DriveForwardDistGyro(152.4, 1906, 5));
			auto_Commands.add(new RotateNavX(58));
			auto_Commands.add(new DriveForwardGyro(1000, 1.2, 5));
			auto_Commands.add(new ExpelGear());
			auto_Commands.add(new DriveBackward(2));
			auto_Commands.add(new RotateNavX(-40));
			auto_Commands.add(new CloseDoors());
			auto_Commands.add(new DriveForwardDistGyro(152.4, 7000, 10));
		}
				
		//use for driving forward from right or left position from actual bot.  THESE VALUE HAVE BEEN TESTED
		else if (movement.charAt(0) == 'b'){
			//auto_Commands.add(new DriveForward(3.8, 1));
			auto_Commands.add(new DriveForwardDist(158.75, 4000, 12));

		}
		//use for driving forward and deploy gear from right position on actual bot.
		else if (movement.charAt(0) == 'a'){
			auto_Commands.add(new DriveForwardDist(158.75, 1756, 5));
			auto_Commands.add(new RotateNavX(-59));
			auto_Commands.add(new DriveForward(1000, 1.2));
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
			auto_Commands.add(new DriveForward(4000, 1.15));
			auto_Commands.add(new ExpelGear());
			auto_Commands.add(new DriveBackward(2));
			auto_Commands.add(new CloseDoors());
		}
				else if (movement.charAt(0) == 'j'){
					auto_Commands.add(new DriveForwardDistGyro(152.4, 1850, 5)); //1906
					auto_Commands.add(new RotateNavX(60f));
					//auto_Commands.add(new DriveForward(1000, 1.1));
					auto_Commands.add(new VisionAimGear());
					auto_Commands.add(new ExpelGear());
					auto_Commands.add(new DriveBackward(2));
					auto_Commands.add(new CloseDoors());
					//auto_Commands.add(new RotateNavX(-40));
					//auto_Commands.add(new CloseDoors());
					//auto_Commands.add(new DriveForwardDistGyro(152.4, 7000, 10));

		}
		//use for Practice Bot = Mr Zobel testing
				else if (movement.charAt(0) == 'z'){
					auto_Commands.add(new AutonAimGear());
					auto_Commands.add(new ExpelGear());
					auto_Commands.add(new DriveBackward(2));
					auto_Commands.add(new CloseDoors());
					//auto_Commands.add(new RotateNavX(60f));
					/*auto_Commands.add(new DriveForwardDist(152.4, 1906, 5));
					auto_Commands.add(new RotateWithPIDTankDrive(-59.0));	
					//auto_Commands.add(new DriveForward(1000, 1.2));
					auto_Commands.add(new AutonAimGear());
					auto_Commands.add(new ExpelGear());
					auto_Commands.add(new DriveBackward(2));
					auto_Commands.add(new CloseDoors());
					auto_Commands.add(new RotateWithPIDTankDrive(0.0));			
					auto_Commands.add(new DriveForwardDist(152.4, 6000, 7));
					*/
				}
			
		return auto_Commands; //an arraylist of the commands to be executed in autonomous
	}
	
}