package org.usfirst.frc2832.Robot2017.commands;

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
		else if(movement.charAt(0) == 'e'){
			auto_Commands.add(new DriveForward(Double.valueOf(movement.substring(1))));
			auto_Commands.add(new ExpelGear());
		}
		
		
			
		return auto_Commands; //an arraylist of the commands to be executed in autonomous
	}
	
}
