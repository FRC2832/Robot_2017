package org.usfirst.frc2832.Robot2017.autonCommands;

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
			auto_Commands.add(new DriveForward(Double.valueOf(movement.substring(1))));
		
		//use for expelling the gear from the middle position for practice bot
		else if(movement.charAt(0) == 'e'){
			auto_Commands.add(new DriveForward(3.65));//Double.valueOf(movement.substring(1))));
			auto_Commands.add(new ExpelGear());
			auto_Commands.add(new DriveBackward(2));
			auto_Commands.add(new CloseDoors());
		}
		//use for expelling the gear from the middle position of the actual bot
		else if (movement.charAt(0) == 'd'){
			auto_Commands.add(new DriveForward(3.4));
			auto_Commands.add(new ExpelGear());
			auto_Commands.add(new DriveBackward(1));
			auto_Commands.add(new CloseDoors());
			
			//auto_Commands.add(new AutonAimGear());
			//auto_Commands.add(new ExpelGear());
			//auto_Commands.add(new DriveBackward(Double.valueOf(movement.substring(2))));
		}
		//use for driving forward from right or left position from practice bot
		else if (movement.charAt(0) == 'c'){
			auto_Commands.add(new DriveForward(4.5));
		}
		//use for driving forward from right or left position from actual bot
		else if (movement.charAt(0) == 'b'){
			auto_Commands.add(new DriveForward(3.8));
		}
			
		return auto_Commands; //an arraylist of the commands to be executed in autonomous
	}
	
}
