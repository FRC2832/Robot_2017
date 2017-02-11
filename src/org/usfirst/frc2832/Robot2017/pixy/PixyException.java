package org.usfirst.frc2832.Robot2017.pixy;
/**
 * 
 * @author 2B || !2B from https://www.chiefdelphi.com/forums/showpost.php?p=1443536&postcount=7
 * This class handles exceptions thrown from the pixy(ie. null pointers)
 */
public class PixyException extends Exception {
	public PixyException(String message){
		super(message);
	}
}