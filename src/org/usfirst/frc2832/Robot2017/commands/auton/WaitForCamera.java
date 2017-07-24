package org.usfirst.frc2832.Robot2017.commands.auton;

import org.usfirst.frc2832.Robot2017.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class WaitForCamera extends Command {
    private double started;

    @Override
    protected void initialize() {
        // max wait time (seconds)
        setTimeout(2);
        started = Timer.getFPGATimestamp();
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut() || (Robot.lastPixyRead >= started);
    }
}
