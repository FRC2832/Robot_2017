package org.usfirst.frc2832.Robot2017.autonCommands;

import java.util.ArrayList;

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
		
		//use for expelling the gear from the middle position
		else if(movement.charAt(0) == 'e'){
			auto_Commands.add(new DriveForward(3.65));//Double.valueOf(movement.substring(1))));
			auto_Commands.add(new ExpelGear());
			auto_Commands.add(new DriveBackward(2));
		}
		else if (movement.charAt(0) == 'd'){
			auto_Commands.add(new AutonAimGear());
			auto_Commands.add(new ExpelGear());
			//auto_Commands.add(new DriveBackward(Double.valueOf(movement.substring(2))));
		}
		else if (movement.charAt(0) == 'c'){
			auto_Commands.add(new SensorForward());
		}
		
		
			
		return auto_Commands; //an arraylist of the commands to be executed in autonomous
	}
	
}
