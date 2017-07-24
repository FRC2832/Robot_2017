package org.usfirst.frc2832.Robot2017.autonCommands;

import java.util.ArrayList;

import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.commands.Rotate;
import org.usfirst.frc2832.Robot2017.commands.WaitUntilTime;
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
			//auto_Commands.add(new DriveForwardDist(152.4, 1860, 10));
			auto_Commands.add(new DriveForward(4000, 1));
			//auto_Commands.add(new DriveForward(3.65));//Double.valueOf(movement.substring(1))));
			//auto_Commands.add(new WaitUntilTime(500, Robot.driveTrain));
			auto_Commands.add(new ExpelGear());
			auto_Commands.add(new DriveBackward(2));
			auto_Commands.add(new CloseDoors());
		}
		
		//use for driving forward from right or left position from practice bot. VALUES HAVE BEEN TESTED
		else if (movement.charAt(0) == 'c'){
			auto_Commands.add(new DriveForward(4.5, 1));
		}
		//Expel Gear From Right NavX(practice bot)
		else if (movement.charAt(0) == 'i'){
			auto_Commands.add(new DriveForwardDist(152.4, 1870, 5));
			//auto_Commands.add(new RotateWithAngelTankDrive(60));
			auto_Commands.add(new RotateNavX(-59));
			auto_Commands.add(new DriveForward(1000, 1.2));
			//auto_Commands.add(new DriveForwardDist(152.4, 1800, 5));
			//auto_Commands.add(new WaitUntilTime(500, Robot.driveTrain));
			auto_Commands.add(new ExpelGear());
			auto_Commands.add(new DriveBackward(2));
			auto_Commands.add(new CloseDoors());
			//auto_Commands.add(new DriveForwardDist(0, 0));
		}
		//Expel Gear From Left NavX(practice bot)
				else if (movement.charAt(0) == 'h'){
					auto_Commands.add(new DriveForwardDist(152.4, 1906, 5));
					auto_Commands.add(new RotateNavX(60));
					//auto_Commands.add(new DriveForward(1000, 1.2));
					auto_Commands.add(new AutonAimGear());
					//auto_Commands.add(new DriveForwardDist(152.4, 1185, 5));
					//auto_Commands.add(new WaitUntilTime(500, Robot.driveTrain));
					auto_Commands.add(new ExpelGear());
					auto_Commands.add(new DriveBackward(2));
					auto_Commands.add(new CloseDoors());
					//auto_Commands.add(new DriveForwardDist(0, 0));
				}
				
		//use for driving forward from right or left position from actual bot.  THESE VALUE HAVE BEEN TESTED
		else if (movement.charAt(0) == 'b'){
			auto_Commands.add(new DriveForward(3.8, 1));
		}
		//use for driving forward and turning from right position on actual bot.  VALUES MUST BE CHECKED WITH IRON KRAKEN
		else if (movement.charAt(0) == 'a'){
			auto_Commands.add(new DriveForwardDist(158.75, 1756, 10));
			auto_Commands.add(new RotateNavX(60));
			//auto_Commands.add(new DriveForwardDist(0, 0));
		}
		//use for driving forward and turning from left position on actual bot.  VALUES MUST BE CHECKED WITH IRON KRAKEN
				else if (movement.charAt(0) == 'g'){
					auto_Commands.add(new DriveForwardDist(158.75, 1756, 10));
					auto_Commands.add(new RotateNavX(-60));
					//auto_Commands.add(new DriveForwardDist(0, 0));
				}
				
		//use for expelling the gear from the middle position of the actual bot THESE VALUES HAVE BEEN TESTED
				else if (movement.charAt(0) == 'd'){
					auto_Commands.add(new DriveForwardDist(158.75, 1860, 10));
					//auto_Commands.add(new DriveForward(3.4));
					//auto_Commands.add(new DriveCorrected(3.3));
					//auto_Commands.add(new WaitUntilTime(500, Robot.driveTrain));
					auto_Commands.add(new ExpelGear());
					auto_Commands.add(new DriveBackward(1));
					//auto_Commands.add(new CloseDoors());
					
					//auto_Commands.add(new AutonAimGear());
					//auto_Commands.add(new ExpelGear());
					//auto_Commands.add(new DriveBackward(Double.valueOf(movement.substring(2))));
				}
				else if (movement.charAt(0) == 'j'){
					
					//auto_Commands.add(new DriveForward(3.7));
					//auto_Commands.add(new Rotate(40));
					//auto_Commands.add(new DriveForwardDist(152.4, 1906, 5));
					//auto_Commands.add(new RotateNavX(60));
					auto_Commands.add(new AutonAimGear());
					auto_Commands.add(new ExpelGear());
					auto_Commands.add(new DriveBackward(2));
					auto_Commands.add(new CloseDoors());
				}
				
		return auto_Commands; //an arraylist of the commands to be executed in autonomous
	}
	
}